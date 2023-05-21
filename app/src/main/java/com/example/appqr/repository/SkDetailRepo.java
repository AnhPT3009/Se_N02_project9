package com.example.appqr.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appqr.models.SkDetailModel;
import com.example.appqr.retrofit.RetrofitInstance;
import com.example.appqr.retrofit.TravelAppApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkDetailRepo {
    private TravelAppApi appApi;
    public SkDetailRepo(){
        appApi = RetrofitInstance.getRetrofit().create(TravelAppApi.class);
    }
    public MutableLiveData<SkDetailModel> getSkDetail(int id){
        MutableLiveData<SkDetailModel> data = new MutableLiveData<>();
        appApi.getSkDetail(id).enqueue(new Callback<SkDetailModel>() {
            @Override
            public void onResponse(Call<SkDetailModel> call, Response<SkDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SkDetailModel> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
