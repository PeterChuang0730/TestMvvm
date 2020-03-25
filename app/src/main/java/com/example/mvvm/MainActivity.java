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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private UserDataAdapter userDataAdapter;

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.viewUsers;
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

                if (users != null) {
                    activityMainBinding.tvMain.setText(String.format(getString(R.string.show_number),
                            users.size()));
                }
            }
        });
    }
}
