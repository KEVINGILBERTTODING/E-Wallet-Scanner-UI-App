package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationFragment extends Fragment {
    EditText edtPassword;
    Button btnConfirm;
    Integer total, user_id = 1;
    String date, username, productName, productId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_confirmation, container, false);

        edtPassword = view.findViewById(R.id.edt_password);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        // Get value from bundel
        date = getArguments().getString("date");
        total = getArguments().getInt("total");
        username = getArguments().getString("username");
        productName = getArguments().getString("product_name");
        productId = getArguments().getString("product_id");


        btnConfirm.setOnClickListener(view1 -> {
            getConfirmation();
        });


        return view;
    }

    // METHOD FOR CONFRIMATION
    private void getConfirmation(){
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.checkPassword(user_id, edtPassword.getText().toString()).enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {

                if (response.isSuccessful()){
                    MyModel myModel = response.body();
                    if (myModel.getStatus().equals("1")) {
                        Fragment fragment = new Fragment_receipt();
                        Bundle bundle = new Bundle();
                        bundle.putString("date", date);
                        bundle.putInt("total", total);
                        bundle.putString("username", username);
                        bundle.putString("product_name", productName);
                        bundle.putString("product_id", productId);
                        fragment.setArguments(bundle);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_container, fragment);
                        ft.commit();

                    } else {
                        edtPassword.setError("Password invalid");
                        Toast.makeText(getContext(), "Password invalid", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Snackbar.make(getView(), "No connection", Snackbar.LENGTH_SHORT).show();
                Log.e("MyApp", t.getMessage());

            }
        });

    }
}