package com.example.myapplication.Model;


import com.example.myapplication.Util.DataApi;
import com.example.myapplication.Util.ServerApi;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyModel implements Serializable {
    @SerializedName("user_id")
    String user_id;
    @SerializedName("username")
    String username;
    @SerializedName("photo_profile")
    String photo_profile;
    @SerializedName("ransaction_id")
    String transaction_id;
    @SerializedName("date")
    String date;
    @SerializedName("total")
    Integer total;
    @SerializedName("product_id")
    String product_id;
    @SerializedName("name")
    String product_name;
    @SerializedName("package_name")
    String package_name;
    @SerializedName("image")
    String image;
    @SerializedName("balance")
    Integer balance;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public MyModel (String user_id, String username, String photo_profile,
                    String transaction_id, String date, Integer total, String product_id,
                    String product_name, String package_name, String image, Integer balance,
                    String status, String message) {


        this.user_id = user_id;
        this.username = username;
        this.photo_profile = photo_profile;
        this.transaction_id = transaction_id;
        this.date = date;
        this.total = total;
        this.product_id = product_id;
        this.product_name = product_name;
        this.package_name = package_name;
        this.image = image;
        this.balance = balance;
        this.status = status;
        this.message = message;



    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_profile() {
        return ServerApi.BASE_URL + "img_profile/" + photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getImage() {
        return DataApi.Base_Url + "img_product/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
