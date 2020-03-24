package com.example.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.databinding.UserListItemBinding;
import com.example.mvvm.model.User;

import java.util.ArrayList;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {

    private ArrayList<User> users;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserListItemBinding userListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.user_list_item, parent, false);
        return new UserViewHolder(userListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = users.get(position);
        holder.userListItemBinding.setUser(currentUser);
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    public void setUserList(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private UserListItemBinding userListItemBinding;

        public UserViewHolder(@NonNull UserListItemBinding userListItemBinding) {
            super(userListItemBinding.getRoot());

            this.userListItemBinding = userListItemBinding;
        }
    }
}
