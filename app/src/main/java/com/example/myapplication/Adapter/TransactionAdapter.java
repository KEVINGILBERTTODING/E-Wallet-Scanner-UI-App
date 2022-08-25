package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.MyModel;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context context;
    List<MyModel> myModelList;

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_product;
        TextView tv_product_name, tv_date, tv_total;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_product = itemView.findViewById(R.id.iv_photo_product);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_total = itemView.findViewById(R.id.tv_total);
        }
    }
}
