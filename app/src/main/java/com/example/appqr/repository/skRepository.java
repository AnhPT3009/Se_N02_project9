package com.example.appqr.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appqr.models.skModels;
import com.example.appqr.retrofit.RetrofitInstance;
import com.example.appqr.retrofit.TravelAppApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class skRepository {
    private TravelAppApi api;

    public skRepository(){
        api = RetrofitInstance.getRetrofit().create(TravelAppApi.class);
    }
    public MutableLiveData<skModels>getSk(int idcate){
        MutableLiveData<skModels> data = new MutableLiveData<>();
        api.getsk(idcate).enqueue(new Callback<skModels>() {
            @Override
            public void onResponse(Call<skModels> call, Response<skModels> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<skModels> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
