package com.example.kirandeep.phoneutilization.networkConnection;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by abc on 13-09-2017.
 */

public class Service {

    public final String BASE_URL = "http://172.31.73.251:8000/";

    public void sendPost(final String username, final String password, final ServiceCallback serviceCallback) {
        Retrofit retrofit = RetrofitBuilder
                .with()
                .baseUrl(BASE_URL)
                .build();
        Post post = new Post(username, password);
        Apis.SendPost sendPost = (retrofit.create(Apis.SendPost.class));
        Call<String> call = sendPost.sendPostData(post);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    serviceCallback.onSuccess(response.body().toString());
                } else {
                    Log.d("Response received: ", "Authorization failure");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }


        });
    }

}
