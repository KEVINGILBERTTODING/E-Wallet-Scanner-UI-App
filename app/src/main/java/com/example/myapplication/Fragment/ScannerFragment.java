package com.example.myapplication.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;
import com.google.zxing.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerFragment extends Fragment {
    private CodeScanner mCodeScanner;
    CodeScannerView scannerView;
    ImageButton btnBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_scanner, container, false);
        final Activity activity = getActivity();

        scannerView = view.findViewById(R.id.scanner_view);
        btnBack = view.findViewById(R.id.btn_back);

        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getScanner(result.getText());
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        cameraPermission();

        btnBack.setOnClickListener(View-> {
            FragmentManager fr = getFragmentManager();
            fr.popBackStack();
        });


        return view;
    }

    // get permission camera
    private void cameraPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)

            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 1);
    }

    // get find product by qrcode
    private void getScanner(String product_id){
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.findProductId(product_id).enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()){
                    List<MyModel> myModelList = response.body();
                    Fragment fragment = new TransactionFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("product_name", myModelList.get(0).getProduct_name());
                    bundle.putString("product_id", myModelList.get(0).getProduct_id());
                    bundle.putString("package_name", myModelList.get(0).getPackage_name());
                    bundle.putString("image_product", myModelList.get(0).getImage());


                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {

            }
        });
    }




    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.startPreview();
        super.onPause();
    }


}