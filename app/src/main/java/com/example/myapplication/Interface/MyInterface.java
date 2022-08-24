package com.example.myapplication.Interface;

import com.example.myapplication.Model.MyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyInterface {

    // for get data user
    @GET("get_saldo_name.php")
    Call<List<MyModel>> getCardInfo(
            @Query("user_id") Integer user_id
    );
}
