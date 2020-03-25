package com.example.mvvm.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.network.RetrofitClient;
import com.example.mvvm.network.UserDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public UserRepository() {
    }

    public MutableLiveData<List<User>> getMutableLiveData() {

        final UserDataService userDataService = RetrofitClient.getService();

        Call<List<User>> call = userDataService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>>
                    response) {
                List<User> users = response.body();
                if (users != null) {
                    mutableLiveData.setValue(users);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
            }
        });

        return mutableLiveData;
    }
}
