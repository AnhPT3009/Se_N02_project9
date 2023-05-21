package com.example.appqr.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appqr.R;
import com.example.appqr.Utils.Utils;
import com.example.appqr.adapters.CartAdapter;
import com.example.appqr.listener.ChangeNumListener;
import com.example.appqr.models.Cart;

import java.util.List;

import io.paperdb.Paper;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recycleCart;
    private TextView txtItem, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();
        initData();
        totalPrice();
    }

    private void initView() {
        recycleCart = findViewById(R.id.recyclecart);
        txtItem = findViewById(R.id.txtitem);
        txtPrice = findViewById(R.id.txtt);

        recycleCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleCart.setLayoutManager(layoutManager);
    }

    private void initData() {
        List<Cart> carts = Paper.book().read("cart");
        Utils.cartList = carts;
        CartAdapter adapter = new CartAdapter(this, Utils.cartList, new ChangeNumListener() {
            @Override
            public void change() {
                totalPrice();
            }
        });

        recycleCart.setAdapter(adapter);
    }

    private void totalPrice() {
        int item = 0;
        for (int i = 0; i < Utils.cartList.size(); i++) {
            item = item + Utils.cartList.get(i).getAmount();
        }
        txtItem.setText(String.valueOf(item));

        double price = 0.0;
        for (int i = 0; i < Utils.cartList.size(); i++) {
            price = price + (Utils.cartList.get(i).getSkDetail().getPrice() * Utils.cartList.get(i).getAmount());
        }
        txtPrice.setText("$" + String.valueOf(price));
    }
}
