package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Adapter.TransactionAdapter;
import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView tv_username, tv_username2, tv_username3, tv_balance;
    ImageView iv_profile;
    ProductAdapter productAdapter;
    RecyclerView rv_product, rv_transaction;
    LinearLayoutManager linearLayoutManager;
    TransactionAdapter transactionAdapter;
    DecimalFormat decimalFormat;
    Integer number = 1000000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frgment, container, false);
        tv_username = view.findViewById(R.id.tv_username1);
        tv_username2 = view.findViewById(R.id.tv_username2);
        tv_username3 = view.findViewById(R.id.tv_username3);
        iv_profile = view.findViewById(R.id.iv_photo_profile);
        tv_balance = view.findViewById(R.id.tv_balance);
        rv_product = view.findViewById(R.id.recycler_payment);
        rv_transaction = view.findViewById(R.id.rv_transaction);

        // create new b=object decimal format
        decimalFormat = new DecimalFormat("#,###");




        getInfoCard();
        getProduct();
        getTransaction();

        return view;
    }

    // method to get the info card of the user
    private void getInfoCard() {
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.getCardInfo(1).enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()) {
                    List<MyModel> myModelList = response.body();
                    tv_username.setText(myModelList.get(0).getUsername());
                    tv_username2.setText(myModelList.get(0).getUsername());
                    tv_username3.setText(myModelList.get(0).getUsername());

                    Glide.with(getContext())
                            .load(myModelList.get(0).getPhoto_profile())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(true)
                            .into(iv_profile);


                    // set balance wwith decimal format
                    tv_balance.setText("Rp. " + decimalFormat.format(myModelList.get(0).getBalance()));
//                    tv_balance.setText(number.toString());
//

                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

            }
        });


    }

    // method for get all product
    private  void getProduct(){
        MyInterface mi = DataApi.getClient().create(MyInterface.class);
        mi.getProduct().enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()){
                    List<MyModel> myModelList = response.body();
                    productAdapter = new ProductAdapter(getContext(), myModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    rv_product.setLayoutManager(linearLayoutManager);
                    rv_product.setAdapter(productAdapter);
                    rv_product.setHasFixedSize(true);

                }else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    // method to get all transaction by user_id
    private void getTransaction(){
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.getTransaction(1).enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()){
                    List<MyModel> myModelList = response.body();
                    transactionAdapter = new TransactionAdapter(getContext(), myModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rv_transaction.setLayoutManager(linearLayoutManager);
                    rv_transaction.setAdapter(transactionAdapter);
                    rv_transaction.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {

            }
        });

    }
}