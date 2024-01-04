package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clientapp.R;
import com.example.clientapp.activities.ShowDetailActivity;
import com.example.clientapp.model.Product;

import java.util.List;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {
    private List<Product> productsList;
    private Context context;

  public ProductAdaptor(List<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @Override
    public ProductAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_products, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(productsList.get(position).getTitle() + " ·");
        holder.price.setText(String.valueOf(productsList.get(position).getPrice()));
        holder.description.setText((String.valueOf(productsList.get(position).getDescription())));

        int drawableResourceId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(productsList.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.constraintLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", productsList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, price, description;
        private ImageView pic;
        private ConstraintLayout constraintLayoutBtn;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            pic = itemView.findViewById(R.id.pic);
            constraintLayoutBtn = itemView.findViewById(R.id.constraintLayoutBtn);
            description = itemView.findViewById(R.id.productList);
        }
    }
}
