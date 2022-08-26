package com.example.myapplication.Fragment;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import java.util.Date;
import java.util.List;

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
    Integer userId = 1;
    BigDecimal value;
    TextView tv_username, tv_productName, tv_productid, tv_total;
    String currentDateTimeString;

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


            value = MoneyTextWatcher.parseCurrencyValue(edt_value.getText().toString());

            checkBalance(user_id, value);

        });

        // get current date and time

        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());






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

                        tv_productName = dialog.findViewById(R.id.tv_product_name);
                        tv_productid = dialog.findViewById(R.id.tv_product_id);
                        tv_username = dialog.findViewById(R.id.tv_username);
                        tv_total = dialog.findViewById(R.id.tv_total);

                        Button btnSend = dialog.findViewById(R.id.btn_send);

                        btnSend.setOnClickListener(view -> {
                            postTransaction(userId, productId, value) ;


                            dialog.dismiss();
                        });

                        // set content
                        tv_productName.setText(productName);
                        tv_productid.setText(productId);
                        tv_total.setText(edt_value.getText().toString());
                        getUserInfo();




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
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    // get user info
    private void getUserInfo(){
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.getCardInfo(userId).enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {

                if (response.isSuccessful()){
                    List<MyModel> myModelList = response.body();
                    tv_username.setText(myModelList.get(0).getUsername());

                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    // method for post transaction
    private void postTransaction(Integer userId, String productId, BigDecimal total){
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.postTransaction(userId,productId, total, currentDateTimeString ).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if (response.isSuccessful()){
                    MyModel myModel = response.body();

                    if (myModel.getStatus().equals("1")) {

                        // call method transaction
                        transcation();

                    }
                    else {
                        Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_SHORT).show();
                Log.e("MyApp", "onFailure: " + t.getMessage());

            }
        });
    }

    // Menurangi balance dengan jumlah total pembayaran
    private void transcation() {
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.transaction(user_id, value).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if (response.isSuccessful()){
                    MyModel myModel = response.body();
                    if (myModel.getStatus().equals("1")){

                        Fragment fragment = new Fragment_receipt();
                        Bundle bundle = new Bundle();
                        bundle.putString("date", currentDateTimeString);
                        bundle.putInt("total", value.intValue());
                        bundle.putString("username", tv_username.getText().toString());
                        bundle.putString("product_name", productName);
                        bundle.putString("product_id", productId);
                        fragment.setArguments(bundle);


                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, fragment);
                        fragmentTransaction.commit();

                    }
                    else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }


}