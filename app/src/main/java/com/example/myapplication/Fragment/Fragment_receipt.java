package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.Date;

public class Fragment_receipt extends Fragment {

    TextView tv_value, tv_username, tv_package_name, tv_date, tv_product_id;
    String  username, packageName, date, productId;
    Integer value;

    ImageButton btnBack;
    DecimalFormat decimalFormat;
    Button btn_home;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

        tv_value = view.findViewById(R.id.tv_value);
        tv_username = view.findViewById(R.id.tv_username);
        tv_package_name = view.findViewById(R.id.tv_product_name);
        tv_date = view.findViewById(R.id.tv_date);
        tv_product_id = view.findViewById(R.id.tv_product_id);
        btnBack = view.findViewById(R.id.btn_back);
        btn_home = view.findViewById(R.id.btn_home);

        decimalFormat = new DecimalFormat("#,###");

        // GET VALUE
        value = getArguments().getInt("total");
        username = getArguments().getString("username");
        packageName = getArguments().getString("product_name");
        productId = getArguments().getString("product_id");
        date = getArguments().getString("date");


        // set value
        tv_value.setText("Rp. " + decimalFormat.format(value));
        tv_username.setText(username);
        tv_package_name.setText(packageName);
        tv_date.setText(date);
        tv_product_id.setText(productId);

        btn_home.setOnClickListener(View -> {
            Fragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        });










        return view;
    }
}