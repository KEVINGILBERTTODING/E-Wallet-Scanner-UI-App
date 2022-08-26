package com.example.myapplication.Interface;

import com.example.myapplication.Model.MyModel;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyInterface {

    // for get data user
    @GET("get_saldo_name.php")
    Call<List<MyModel>> getCardInfo(
            @Query("user_id") Integer user_id
    );

    // get all product

    @GET("get_product.php")
    Call<List<MyModel>> getProduct();

    // get all product

    @GET("get_transaction.php")
    Call<List<MyModel>> getTransaction(
            @Query("user_id") Integer user_id
    );

    // scanner
    @GET("get_scanner_product.php")
    Call<List<MyModel>> findProductId(
            @Query("product_id") String productId
    );

    // check balance
    @FormUrlEncoded
    @POST("check_balance.php")
    Call<MyModel> checkBalance (
            @Field("user_id") String user_id,
            @Field("total") BigDecimal total
    );

    // post transaction
    @FormUrlEncoded
    @POST("insert_transaction.php")
    Call<MyModel> postTransaction (
            @Field("user_id") Integer user_id,
            @Field("product_id") String product_id,
            @Field("total") BigDecimal total,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("transaction.php")
    Call<MyModel> transaction(
            @Field("user_id") String user_id,
            @Field("total") BigDecimal total

    );

}
