package com.example.mvvm.network;

import com.example.mvvm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserDataService {
    @GET("users")
    Call<List<User>> getUsers();
}
