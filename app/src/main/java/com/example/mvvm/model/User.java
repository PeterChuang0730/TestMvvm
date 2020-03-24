package com.example.mvvm.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private long id;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("site_admin")
    private boolean site_admin;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public boolean getSiteAdmin() {
        return site_admin;
    }

    @BindingAdapter({"avatar_url"})
    public static void loadImage(ImageView imageView, String imageURL) {

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .into(imageView);
    }

}
