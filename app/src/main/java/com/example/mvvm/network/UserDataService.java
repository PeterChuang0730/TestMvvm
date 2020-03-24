package com.example.mvvm.network;

import com.example.mvvm.model.UserDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserDataService {
    @GET("users/?per_page=12&amp;page=1")
    Call<UserDBResponse> getEmployees();
}
