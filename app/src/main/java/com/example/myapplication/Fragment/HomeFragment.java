package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView tv_username, tv_balance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frgment, container, false);


        tv_username = view.findViewById(R.id.tv_username);
        tv_balance = view.findViewById(R.id.tv_balance);
        getInfoCard();


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
                    tv_balance.setText(myModelList.get(0).getBalance());
                }
                else {
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
}