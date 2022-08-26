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
import com.example.myapplication.Fragment.Fragment_receipt;
import com.example.myapplication.Interface.MyInterface;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;
import com.example.myapplication.Util.DataApi;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context context;
    List<MyModel> myModelList;
    Integer userId;
    String username;

    public TransactionAdapter(Context context, List<MyModel> myModelList) {
        this.context = context;
        this.myModelList = myModelList;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        holder.tv_product_name.setText(myModelList.get(position).getProduct_name());
        Glide.with(context)
                .load(myModelList.get(position).getImage())
                .into(holder.iv_product);
        holder.tv_date.setText(myModelList.get(position).getDate());
        holder.tv_total.setText("- Rp.  " + decimalFormat.format(myModelList.get(position).getTotal()));

    }

    @Override
    public int getItemCount() {
        return myModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_product;
        TextView tv_product_name, tv_date, tv_total;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_product = itemView.findViewById(R.id.iv_photo_product);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_total = itemView.findViewById(R.id.tv_total);

            itemView.setOnClickListener(this);

            getUserInfo();
        }

        @Override
        public void onClick(View view) {
            Fragment fragment = new Fragment_receipt();
            Bundle bundle = new Bundle();
            bundle.putString("date", myModelList.get(getAdapterPosition()).getDate());
            bundle.putInt("total", myModelList.get(getAdapterPosition()).getTotal());
            bundle.putString("username", username);
            bundle.putString("product_name", myModelList.get(getAdapterPosition()).getProduct_name());
            bundle.putString("product_id", myModelList.get(getAdapterPosition()).getProduct_id());
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);

        }
    }


    // get userInfo

    private void getUserInfo() {
        MyInterface myInterface = DataApi.getClient().create(MyInterface.class);
        myInterface.getCardInfo(userId).enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()) {

                    List<MyModel> myModelList = response.body();
                    if (myModelList.get(0).getStatus().equals("1")) {
                        username = myModelList.get(0).getUsername();

                    }
                }

            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {


            }
        });
    }

}
