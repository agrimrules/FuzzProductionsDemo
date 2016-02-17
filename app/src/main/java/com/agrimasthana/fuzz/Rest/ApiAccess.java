package com.agrimasthana.fuzz.Rest;

import com.agrimasthana.fuzz.Model.Data;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by agrimasthana on 2/13/16.
 */
public class ApiAccess {
    public Call<List<Data>> loadAPI()
    {
        final String BASE_URL="http://quizzes.fuzzstaging.com";
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestInterface rest = rf.create(RestInterface.class);
        Call<List<Data>> ip = rest.getData();
        return ip;
    }


}
