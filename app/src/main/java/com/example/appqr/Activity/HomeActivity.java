package com.example.appqr.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.appqr.R;
import com.example.appqr.adapters.EventAdapter;
import com.example.appqr.adapters.PopularAdapter;
import com.example.appqr.listener.EventClickListener;
import com.example.appqr.listener.EventListener;
import com.example.appqr.models.Event;
import com.example.appqr.models.Sk;
import com.example.appqr.viewModels.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements EventListener, EventClickListener {
    HomeViewModel homeViewModel;
    RecyclerView rcCategory;
    RecyclerView rcPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData();
    }

    private void initView() {
        rcCategory = findViewById(R.id.rc_category);
        rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcCategory.setLayoutManager(layoutManager);

        rcPopular = findViewById(R.id.rc_popular);
        rcPopular.setHasFixedSize((true));
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        rcPopular.setLayoutManager(layoutManager1);
    }

    private void initData() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.eventModelsMutableLiveData().observe(this, eventModels -> {
            if (eventModels != null && eventModels.isSuccess()) {
                EventAdapter adapter = new EventAdapter(eventModels.getResult(),this );
                rcCategory.setAdapter(adapter);
            }
        });
        homeViewModel.skModelsMutableLiveData(1).observe(this,skModels -> {
            if (skModels != null && skModels.isSuccess()) {
                PopularAdapter adapter1 = new PopularAdapter(skModels.getResult(),this);
                rcPopular.setAdapter(adapter1);
            }
        });
    }

    @Override
    public void onEventClick(Event event) {
        Intent intent = new Intent(getApplicationContext(),EventActivity.class);
        intent.putExtra("idcate",event.getId());
        startActivity(intent);
    }

    @Override
    public void onPopularClick(Sk sk) {
        Intent intent = new Intent(getApplicationContext(),ShowDetailActivity.class);
        intent.putExtra("id",sk.getIdsk());
        startActivity(intent);
    }
}
