package com.example.mvvm;

public class UserInfo {
    private long id;
    private String login;
    private String avatar_url;
    private boolean site_admin;

    public long getId() {
        return id;
    }

    String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public boolean getSiteAdmin() {
        return site_admin;
    }

}
