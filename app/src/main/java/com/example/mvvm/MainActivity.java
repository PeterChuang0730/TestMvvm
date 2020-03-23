package com.example.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        binding.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.refresh();
            }
        });

        viewModel.mData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                binding.txtHelloWord.setText(data);
            }
        });

        viewModel.toastText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        new LoadUserData().start();
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
                UserInfo[] userInfos = gson.fromJson(result_json, UserInfo[].class);

                StringBuilder sb = new StringBuilder();
                for (UserInfo userInfo : userInfos) {
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
