package com.example.mvvm.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private long id;
    @SerializedName("node_id")
    private String node_id;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("gravatar_id")
    private String gravatar_id;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String html_url;
    @SerializedName("followers_url")
    private String followers_url;
    @SerializedName("following_url")
    private String following_url;
    @SerializedName("gists_url")
    private String gists_url;
    @SerializedName("starred_url")
    private String starred_url;
    @SerializedName("subscriptions_url")
    private String subscriptions_url;
    @SerializedName("organizations_url")
    private String organizations_url;
    @SerializedName("repos_url")
    private String repos_url;
    @SerializedName("events_url")
    private String events_url;
    @SerializedName("received_events_url")
    private String received_events_url;
    @SerializedName("type")
    private String type;
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

    public String getHtmlUrl() {
        return html_url;
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
