package com.tjnuman.retrofitpostget;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("posts") //https://jsonplaceholder.typicode.com/posts. here post is the relative url after the base url
    Call<List<PostModel>> getPost();

    @GET("posts/1") //https://jsonplaceholder.typicode.com/posts. here post is the relative url after the base url
    Call<PostModel> getSinglePost();

    @POST("posts")
    Call<PostModel>creatPost(@Body POST post);


}
