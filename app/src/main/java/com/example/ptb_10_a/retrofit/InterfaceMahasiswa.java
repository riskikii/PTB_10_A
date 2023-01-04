package com.example.ptb_10_a.retrofit;

import com.example.ptb_10_a.models.LogbooksResponse;
import com.example.ptb_10_a.models.LoginResponse;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.models.ProfileResponse;
import com.example.ptb_10_a.models.TmbhLBResponse;
import com.example.ptb_10_a.models.UbahPassword;
import com.example.ptb_10_a.models.UpdateProfilResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceMahasiswa {

    @FormUrlEncoded
    @POST("api/login/")
    Call<LoginResponse> login (@Field("username") String username, @Field("password") String password);

    @GET("api/me/")
    Call<ProfileResponse> profile (@Header("token")String token);

    @POST("api/logout/")
    Call<LogoutResponse> logout(@Header("token")String token);
    @GET ("api/theses/309/logbooks")
    Call<LogbooksResponse> getLB(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST ("api/theses/309/logbooks")
    Call<TmbhLBResponse>postLB(@Field("supervisor_id") Integer supervisor_id,@Field("date") String date, @Field("progress")String progress);

