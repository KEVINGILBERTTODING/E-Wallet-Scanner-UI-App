package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Fragment.DetailProduct;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<MyModel> myModelList;

    public ProductAdapter(Context context, List<MyModel> myModelList) {
        this.context = context;
        this.myModelList = myModelList;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.tv_product_name.setText(myModelList.get(position).getProduct_name());
        Glide.with(context)
                .load(myModelList.get(position).getImage())
                .into(holder.iv_product_image);





    }

    @Override
    public int getItemCount() {
        return myModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_product_name;
        ImageView iv_product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_product_image = itemView.findViewById(R.id.iv_photo_product);
            tv_product_name =itemView.findViewById(R.id.tv_product_name);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Fragment fragment = new DetailProduct();
            Bundle bundle = new Bundle();
            bundle.putString("product_name", myModelList.get(getAdapterPosition()).getProduct_name());
            bundle.putString("product_image", myModelList.get(getAdapterPosition()).getImage());
            bundle.putString("product_id", myModelList.get(getAdapterPosition()).getProduct_id());
            bundle.putString("package_name", myModelList.get(getAdapterPosition()).getPackage_name());
            fragment.setArguments(bundle);
            FragmentTransaction fr = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, fragment);
            fr.commit();
            fr.addToBackStack(null);

        }
    }
}
