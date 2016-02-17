package com.agrimasthana.fuzz.Rest;

import com.agrimasthana.fuzz.Model.Data;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by agrimasthana on 2/12/16.
 */
public interface RestInterface {
    @GET("/quizzes/mobile/1/data.json")
    Call <List<Data>> getData();
}
