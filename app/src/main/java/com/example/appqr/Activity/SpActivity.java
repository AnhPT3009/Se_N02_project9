package com.example.appqr.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appqr.R;

public class SpActivity extends AppCompatActivity {
    private TextView tvGetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        tvGetting = findViewById(R.id.tvgetting);
        tvGetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
