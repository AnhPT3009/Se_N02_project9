package com.example.appqr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appqr.R;
import com.example.appqr.Utils.Utils;
import com.example.appqr.listener.ChangeNumListener;
import com.example.appqr.models.Cart;

import java.util.List;

import io.paperdb.Paper;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private List<Cart> cartList;
    private ChangeNumListener changeNumListener;

    public CartAdapter(Context context, List<Cart> cartList, ChangeNumListener changeNumListener) {
        this.context = context;
        this.cartList = cartList;
        this.changeNumListener = changeNumListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.txtName.setText(cart.getSkDetail().getName());
        Glide.with(context).clear(holder.imageCart);

        holder.imageCart.setImageResource(R.drawable.img);
        holder.txtPrice.setText(String.valueOf(cart.getSkDetail().getPrice()));
        holder.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(holder.getAdapterPosition());
                notifyDataSetChanged();
                changeNumListener.change();
            }
        });
        holder.imageSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subToCart(holder.getAdapterPosition());
                notifyDataSetChanged();
                changeNumListener.change();
            }
        });
        holder.txtAmount.setText(String.valueOf(cart.getAmount()));
        holder.txtPrice2.setText("$" + String.valueOf(cart.getAmount() * cart.getSkDetail().getPrice()));
    }

    private void subToCart(int adapterPosition) {
        if (Utils.cartList.get(adapterPosition).getAmount() == 1) {
            Utils.cartList.remove(adapterPosition);
        } else {
            Utils.cartList.get(adapterPosition).setAmount(Utils.cartList.get(adapterPosition).getAmount() - 1);
        }
        Paper.book().write("cart", Utils.cartList);
    }

    private void addToCart(int adapterPosition) {
        Utils.cartList.get(adapterPosition).setAmount(Utils.cartList.get(adapterPosition).getAmount() + 1);
        Paper.book().write("cart", Utils.cartList);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCart, imageAdd, imageSub;
        private TextView txtName, txtPrice, txtAmount, txtPrice2;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.image_cart);
            imageAdd = itemView.findViewById(R.id.imag_add);
            imageSub = itemView.findViewById(R.id.imag_sub);
            txtName = itemView.findViewById(R.id.txtname);
            txtPrice = itemView.findViewById(R.id.txtprice1);
            txtAmount = itemView.findViewById(R.id.txtamount);
            txtPrice2 = itemView.findViewById(R.id.txtprice2);
        }
    }
}
