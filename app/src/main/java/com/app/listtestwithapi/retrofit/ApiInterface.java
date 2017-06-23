package com.app.listtestwithapi.retrofit;

import com.app.listtestwithapi.model.Quotes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by nct119 on 24/5/17.
 */

public interface ApiInterface {

    @GET("status.php")
    Call<Quotes> getQuotes(@QueryMap Map<String, Integer> options);
}