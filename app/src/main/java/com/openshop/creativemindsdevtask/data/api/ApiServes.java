package com.openshop.creativemindsdevtask.data.api;

import android.util.Log;


import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServes {
    @GET("users/square/repos")
    Call<List<GithubSquare>> getSquare();
    @GET("user/repos")
    Call<List<GithubSquare>> getMine(@Query("page") int page ,
                         @Query("per_page") int per_page ,
                         @Query("access_token") String access_token);







}
