package com.healthhub.authentication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String userName;
  private String email;
  private String password;
  private String role;
  private boolean isEnable;


  public User()
  {

  }


  public User(String userName, String email, String password,String role) {
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.role=role;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean isEnable) {
    this.isEnable = isEnable;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role=role;
  }
}
