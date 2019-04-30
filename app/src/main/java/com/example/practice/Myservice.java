package com.example.practice;

import com.example.practice.bean.PoemBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Myservice {
    @GET("getTangPoetry")
    Call<PoemBean> getBean();
}
