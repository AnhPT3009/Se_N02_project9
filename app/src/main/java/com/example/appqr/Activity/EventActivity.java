package com.example.appqr.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appqr.R;
import com.example.appqr.adapters.SkAdapter;
import com.example.appqr.viewModels.EventViewModel;

public class EventActivity extends AppCompatActivity {

    EventViewModel viewModel;
    RecyclerView rcCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        rcCategory = findViewById(R.id.rc_category);
        initView();
        initData();
    }

    private void initView() {
        rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcCategory.setLayoutManager(layoutManager);
    }

    private void initData() {
        int idcate = getIntent().getIntExtra("idcate", 1);
        viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.skModelsMutableLiveData(idcate).observe(this, skModels -> {
            if (skModels.isSuccess()) {
                SkAdapter adapter = new SkAdapter(skModels.getResult());
                rcCategory.setAdapter(adapter);
            }
        });
    }
}
