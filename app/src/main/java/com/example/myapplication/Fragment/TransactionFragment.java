package com.example.myapplication.Fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;

public class TransactionFragment extends Fragment {

    TextView tv_product_name, tv_package_name, tv_product_id;
    EditText edt_value, edt_notes;
    ImageView iv_product;
    ImageButton btnBack;

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



        return view;
    }
}