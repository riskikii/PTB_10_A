package com.example.ptb_10_a.retrofit;

import com.example.ptb_10_a.models.LogbooksResponse;
import com.example.ptb_10_a.models.LoginResponse;
import com.example.ptb_10_a.models.LogoutResponse;
import com.example.ptb_10_a.models.ProfileResponse;
import com.example.ptb_10_a.models.TmbhLBResponse;
import com.example.ptb_10_a.models.UbahPassword;
import com.example.ptb_10_a.models.UpdateProfilResponse;
import com.example.ptb_10_a.retrofit.Listpesertasemhas.ListpesertasemhasResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceMahasiswa {

    @FormUrlEncoded
    @POST("api/login/")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("api/me/")
    Call<ProfileResponse> profile(@Header("token") String token);

    @POST("api/logout/")
    Call<LogoutResponse> logout(@Header("token") String token);

    @GET("api/theses/309/logbooks")
    Call<LogbooksResponse> getLB(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/theses/309/logbooks")
    Call<TmbhLBResponse> postLB(@Field("supervisor_id") Integer supervisor_id, @Field("date") String date, @Field("progress") String progress);

    Call<UbahPassword> UbahPassword(String s, String old_password, String new_password, String confirm_password);

    @FormUrlEncoded
    @POST("/api/thesis/seminars/322/audiences")
    Call<ListpesertasemhasResponse> Tambahpeserta(
            @Header("Authorization") String token,
            @Field("nim") String nim,
            @Field("name") String name);

    Call<UpdateProfilResponse> updateProfile(String s, String email, String nama);

    @GET("api/thesis/seminars/312/audiences")
    Call<ListpesertasemhasResponse> getPesertasemhas(@Header("Authorization") String token);

}