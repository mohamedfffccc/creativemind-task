package com.openshop.creativemindsdevtask.data.api;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit=null;
    private static String BASE_URL="https://api.github.com/";

    public static ApiServes getClient(){
//

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
//                .connectionSpecs(Collections.singletonList(spec))
                .build();

        if (retrofit == null){
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiServes.class);
    }
}
