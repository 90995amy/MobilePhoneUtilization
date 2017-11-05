package com.example.kirandeep.phoneutilization.networkConnection;

import android.util.Log;

import com.example.kirandeep.phoneutilization.localStorage.StatsModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by abc on 13-09-2017.
 */

public class Service {

    public final String BASE_URL = "http://ec2-52-15-178-137.us-east-2.compute.amazonaws.com/";/*ec2-52-15-178-137.us-east-2.compute.amazonaws.com/*/

    public void sendPost(StatsRequest statsRequest, final ServiceCallback serviceCallback) {
        Retrofit retrofit = RetrofitBuilder
                .with()
                .baseUrl(BASE_URL)
                .build();
        //Post post = new Post(username, password);
        Apis.SendPost sendPost = (retrofit.create(Apis.SendPost.class));
        Call<JsonObject> call = sendPost.sendPostData(statsRequest);
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    serviceCallback.onSuccess(response.body().toString());
                } else {
                    Log.d("Response received: ", "Authorization failure");


                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

}
