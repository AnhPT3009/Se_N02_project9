package com.example.appqr.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appqr.R;
import com.example.appqr.models.SkDetail;
import com.example.appqr.models.SkDetailModel;
import com.example.appqr.viewModels.ShowDetailViewModels;

public class ShowDetailActivity extends AppCompatActivity {
    ShowDetailViewModels viewModel;
    TextView txtTicket, txtPrice, txtDesc;
    ImageView image;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        txtTicket = findViewById(R.id.txtTicket);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);
        image = findViewById(R.id.image6);

        getData();
    }

    private void getData() {
        int id = getIntent().getIntExtra("id", 0);
        viewModel = new ViewModelProvider(this).get(ShowDetailViewModels.class);
        viewModel.skDetailModelMutableLiveData(id).observe(this, skDetailModel -> {
            if (skDetailModel.isSuccess()) {
                SkDetail skDetail = skDetailModel.getResult().get(0);
                Log.d("logg", skDetailModel.getResult().get(0).getName());
                txtTicket.setText(skDetail.getName());
                txtPrice.setText("$ " + skDetail.getPrice());
                txtDesc.setText(skDetail.getInstructions());

            }
        });
    }

}