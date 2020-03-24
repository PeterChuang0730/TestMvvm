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
    private static final String TAG = "EmployeeRepository";
    private ArrayList<User> employees = new ArrayList<>();
    private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public UserRepository() {
    }

    public MutableLiveData<List<User>> getMutableLiveData() {

        final UserDataService userDataService = RetrofitClient.getService();

        Call<UserDBResponse> call = userDataService.getEmployees();
        call.enqueue(new Callback<UserDBResponse>() {
            @Override
            public void onResponse(Call<UserDBResponse> call, Response<UserDBResponse> response) {
                UserDBResponse employeeDBResponse = response.body();
                if (employeeDBResponse != null && employeeDBResponse.getEmployee() != null) {
                    employees = (ArrayList<User>) employeeDBResponse.getEmployee();
                    mutableLiveData.setValue(employees);
                }
            }

            @Override
            public void onFailure(Call<UserDBResponse> call, Throwable t) {
            }
        });

        return mutableLiveData;
    }
}
