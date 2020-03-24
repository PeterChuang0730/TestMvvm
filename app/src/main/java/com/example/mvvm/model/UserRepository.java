package com.example.mvvm.model;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.network.RetrofitClient;
import com.example.mvvm.network.UserDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();
    private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public UserRepository() {
    }

    public MutableLiveData<List<User>> getMutableLiveData() {

        final UserDataService userDataService = RetrofitClient.getService();

        Call<UserDBResponse> call = userDataService.getUsers();
        call.enqueue(new Callback<UserDBResponse>() {
            @Override
            public void onResponse(Call<UserDBResponse> call, Response<UserDBResponse> response) {
                UserDBResponse userDBResponse = response.body();
                if (userDBResponse != null && userDBResponse.getUser() != null) {
                    users = (ArrayList<User>) userDBResponse.getUser();
                    mutableLiveData.setValue(users);
                }
            }

            @Override
            public void onFailure(Call<UserDBResponse> call, Throwable t) {
            }
        });

        return mutableLiveData;
    }
}
