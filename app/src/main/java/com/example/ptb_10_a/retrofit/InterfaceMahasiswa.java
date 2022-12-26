package com.example.ptb_10_a.retrofit;

import com.example.ptb_10_a.models.LoginResponse;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.models.ProfileResponse;

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

}
