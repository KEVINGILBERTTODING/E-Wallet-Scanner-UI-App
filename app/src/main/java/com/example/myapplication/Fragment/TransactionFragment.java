package com.example.myapplication.Fragment;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.MoneyTextWatcher;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionFragment extends Fragment {

    TextView tv_product_name, tv_package_name, tv_product_id;
    EditText edt_value, edt_notes;
    ImageView iv_product;
    ImageButton btnBack;
    DecimalFormat decimalFormat;
    Button btnConfirm;
    String user_id = "1";

    String productName, packageName, productId, imageProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_transaction, container, false);

        tv_package_name = view.findViewById(R.id.tv_package_name);
        tv_product_name = view.findViewById(R.id.tv_product_name);
        tv_product_id = view.findViewById(R.id.tv_product_id);
        edt_value = view.findViewById(R.id.edt_value);
        edt_notes = view.findViewById(R.id.edt_notes);
        iv_product = view.findViewById(R.id.iv_product);
        btnBack = view.findViewById(R.id.btn_back);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        productName = getArguments().getString("product_name");
        packageName = getArguments().getString("package_name");
        productId = getArguments().getString("product_id");
        imageProduct = getArguments().getString("image_product");

        tv_package_name.setText(packageName);
        tv_product_name.setText(productName);
        tv_product_id.setText(productId);



        Glide.with(getContext())
                .load(imageProduct)
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iv_product);


        btnBack.setOnClickListener(view1 -> {
            FragmentManager fm = getFragmentManager();
            fm.popBackStack();
        });

        decimalFormat = new DecimalFormat("#,###");


       edt_value.addTextChangedListener(new MoneyTextWatcher(edt_value));
       edt_value.setText("0");


        btnConfirm.setOnClickListener(view1 -> {


            BigDecimal value = MoneyTextWatcher.parseCurrencyValue(edt_value.getText().toString());

            checkBalance(user_id, value);

        });







        return view;
    }

    // Check blance user

    private void checkBalance(String user_id, BigDecimal total) {
        MyInterface mi = DataApi.getClient().create(MyInterface.class);
        mi.checkBalance(user_id, total).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                MyModel myModel = response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(getContext(), myModel.getMessage(), Toast.LENGTH_SHORT).show();

                    if (myModel.getStatus().equals("1")) {
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.layout_confirmation);
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialog.show();
                    }
                    else {
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.layout_sad);
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        Button btnOk = dialog.findViewById(R.id.btn_ok);
                        btnOk.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                        dialog.show();
                    }

                }

            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Snackbar.make(getView(), "Cek koneksi", Snackbar.LENGTH_SHORT).show();

            }
        });
    }


}