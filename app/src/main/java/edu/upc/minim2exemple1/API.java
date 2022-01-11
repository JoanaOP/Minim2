package edu.upc.minim2exemple1;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    final String BASE_URL = "https://api.github.com/";

    @GET("/users/{username}/repos")
    Call<List<JsonObject>> getRepos(@Path("username") String username);

    @GET("/users/{username}")
    Call<JsonObject> getUser(@Path("username") String username);
}
