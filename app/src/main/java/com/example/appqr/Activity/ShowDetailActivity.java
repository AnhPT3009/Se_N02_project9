package com.example.appqr.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appqr.R;
import com.example.appqr.Utils.Utils;
import com.example.appqr.models.Cart;
import com.example.appqr.models.SkDetail;
import com.example.appqr.viewModels.ShowDetailViewModels;

import java.util.List;

import io.paperdb.Paper;

public class ShowDetailActivity extends AppCompatActivity {
    ShowDetailViewModels viewModel;
    int amount = 1;
    SkDetail skDetail;
    TextView txtAmount,txtTicket,txtPrice,txtDesc;
    ImageView imageAdd,image;
    ImageView imageSub;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Paper.init(this);
        int id = getIntent().getIntExtra("id", 0);
        getData(id);
        eventClick();
        showToData(id);


        txtTicket = findViewById(R.id.txtTicket);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);
        image = findViewById(R.id.image6);
    }

    private void showToData(int id) {
        if (Paper.book().read("cart") != null) {
            List<Cart> list = Paper.book().read("cart");
            Utils.cartList = list;
        }

        txtAmount = findViewById(R.id.txtamunt);

        if (Utils.cartList.size() > 0) {
            for (int i = 0; i < Utils.cartList.size(); i++) {
                if (Utils.cartList.get(i).getSkDetail().getId() == id) {
                    txtAmount.setText(String.valueOf(Utils.cartList.get(i).getAmount()));
                    break;
                }
            }
        } else {
            txtAmount.setText(String.valueOf(amount));
        }
    }

    private void eventClick() {
        imageAdd = findViewById(R.id.imageadd);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(txtAmount.getText().toString()) + 1;
                txtAmount.setText(String.valueOf(amount));
            }
        });

        imageSub = findViewById(R.id.imagesub);
        imageSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(txtAmount.getText().toString()) > 1) {
                    amount = Integer.parseInt(txtAmount.getText().toString()) - 1;
                    txtAmount.setText(String.valueOf(amount));
                }
            }
        });

        btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(amount);
            }
        });
    }

    private void addToCart(int amount) {
        boolean checkExit = false;
        int n = 0;
        if (Utils.cartList.size() > 0) {
            for (int i = 0; i < Utils.cartList.size(); i++) {
                if (Utils.cartList.get(i).getSkDetail().getId() == skDetail.getId()) {
                    checkExit = true;
                    n = i;
                    break;
                }
            }
        }
        if (checkExit) {
            Utils.cartList.get(n).setAmount(amount);
        } else {
            Cart cart = new Cart();
            cart.setSkDetail(skDetail);
            cart.setAmount(amount);
            Utils.cartList.add(cart);
        }
        Toast.makeText(getApplicationContext(), "Added to your cart", Toast.LENGTH_LONG).show();
        Paper.book().write("cart", Utils.cartList);
    }

    private void getData(int id) {
        txtTicket = findViewById(R.id.txtTicket);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);
        image = findViewById(R.id.image6);

        viewModel = new ViewModelProvider(this).get(ShowDetailViewModels.class);
        viewModel.skDetailModelMutableLiveData(id).observe(this, skDetailModel -> {
            if (skDetailModel.isSuccess()) {
                skDetail = skDetailModel.getResult().get(0);
                Log.d("logg", skDetailModel.getResult().get(0).getName());
                txtTicket.setText(skDetail.getName());
                txtPrice.setText("$ " + skDetail.getPrice());
                txtDesc.setText(skDetail.getInstructions());

            }
        });
    }

}