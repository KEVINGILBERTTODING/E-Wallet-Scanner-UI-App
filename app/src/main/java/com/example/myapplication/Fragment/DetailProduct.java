package com.example.myapplication.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Fragment.ScannerFragment;
import com.example.myapplication.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class DetailProduct extends Fragment {

    TextView tv_product_name;
    ImageView iv_product, iv_qrcode;
    String imageProduct, productName, productId, packageName;
    ImageButton btnBack;
    Button  btnScan, btnPayment;

    QRGEncoder qrgEncoder;
    Bitmap bitmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        tv_product_name = view.findViewById(R.id.tv_product_name);
        iv_product = view.findViewById(R.id.iv_product);
        btnBack = view.findViewById(R.id.btn_back);
        iv_qrcode = view.findViewById(R.id.iv_qrcde);
        btnScan = view.findViewById(R.id.btn_scan);
        btnPayment = view.findViewById(R.id.btn_pay);

        // get data from adapter
        productName = getArguments().getString("product_name");
        imageProduct = getArguments().getString("product_image");
        productId = getArguments().getString("product_id");
        packageName = getArguments().getString("package_name");

        // Generate qrcode by product_id
        qrgEncoder = new QRGEncoder(productId, null, QRGContents.Type.TEXT, 500);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            iv_qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }



        btnBack.setOnClickListener(view1 ->  {
            FragmentManager fr = getFragmentManager();
            fr.popBackStack();
            });

        btnPayment.setOnClickListener(view1 -> {


            Fragment fragment = new TransactionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("product_name", productName);
            bundle.putString("image", imageProduct);
            bundle.putString("product_id", productId);
            bundle.putString("package_name", packageName);
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);
        });

        btnScan.setOnClickListener(view1 -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new ScannerFragment());
            fr.commit();
            fr.addToBackStack(null);
        });

        // set content
        tv_product_name.setText(packageName);
        Glide.with(getContext())
                .load(imageProduct)
                .override(300, 300)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv_product);


        return view;
    }
}