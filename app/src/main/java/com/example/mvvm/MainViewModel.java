package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.User;
import com.example.mvvm.model.UserRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private UserRepository userRepository = new UserRepository();

    public MainViewModel() {
        super();
    }

    LiveData<List<User>> getAllUser() {
        return userRepository.getMutableLiveData();
    }
}
