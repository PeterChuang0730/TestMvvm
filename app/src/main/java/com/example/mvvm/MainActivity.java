package com.example.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.adapter.UserDataAdapter;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private UserDataAdapter userDataAdapter;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        userDataAdapter = new UserDataAdapter();
        recyclerView.setAdapter(userDataAdapter);
        getAllUser();
    }

    private void getAllUser() {
        viewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userDataAdapter.setUserList((ArrayList<User>) users);
            }
        });
    }

    class LoadUserData extends Thread {
        String path_json = "https://api.github.com/users";
        String result_json = null;

        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                User[] userIfs = gson.fromJson(result_json, User[].class);

                StringBuilder sb = new StringBuilder();
                for (User userInfo : userIfs) {
                    sb.append("Login:").append(userInfo.getLogin()).append(" ")
                            .append("AvatarUrl:").append(userInfo.getAvatarUrl()).append(" ");
                }
            }
        };

        @Override
        public void run() {
            try {
                result_json = run(path_json);
                runOnUiThread(task);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
