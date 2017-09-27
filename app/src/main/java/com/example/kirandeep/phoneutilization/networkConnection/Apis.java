package com.example.kirandeep.phoneutilization.networkConnection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by abc on 13-09-2017.
 */

public interface Apis {
    interface SendPost {
        @POST("visualization/")
        Call<String> sendPostData(@Body Post number);
    }
}
