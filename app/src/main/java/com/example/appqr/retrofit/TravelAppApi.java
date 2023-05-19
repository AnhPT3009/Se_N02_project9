package com.example.appqr.retrofit;

import com.example.appqr.models.EventModels;
import com.example.appqr.models.SkDetailModel;
import com.example.appqr.models.skModels;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TravelAppApi {
    @GET("event.php")
    Call<EventModels> getEnvent();

    @POST("sk.php")
    @FormUrlEncoded
    Call<skModels> getsk(
         @Field("idcate") int idcate
    );

    @POST("sukidetail.php")
    @FormUrlEncoded
    Call<SkDetailModel> getSkDetail(
            @Field("id") int id
    );
}
