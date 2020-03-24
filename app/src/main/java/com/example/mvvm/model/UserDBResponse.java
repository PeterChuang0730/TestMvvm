
package com.example.mvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserDBResponse {
  @SerializedName("login")
  @Expose
  private String login;
  @SerializedName("avatar_url")
  @Expose
  private String avatar_url;
  @SerializedName("site_admin")
  @Expose
  private boolean site_admin;
  @SerializedName("data")
  @Expose
  private List<User> user = null;

  public String getLogin() {
    return login;
  }

  public String getAvatar() {
    return avatar_url;
  }

  public boolean getSiteAdmin() {
    return site_admin;
  }

  public List<User> getUser() {
    return user;
  }

  public void setEmployee(List<User> user) {
    this.user = user;
  }
}
