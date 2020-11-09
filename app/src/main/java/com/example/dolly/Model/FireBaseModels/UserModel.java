package com.example.dolly.Model.FireBaseModels;

public class UserModel {
    private String user_id;
    private String full_name;
    private String username;
    private String user_mobile_no;
    private String email;
    private String gender;
    private String selected_disability;
    private String disability;
    private String description;
    private String age;
    private String image;

    public UserModel() {
    }

    public UserModel(String user_id, String full_name, String username, String user_mobile_no, String email, String gender, String selected_disability, String disability, String description, String age, String image) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.user_mobile_no = user_mobile_no;
        this.email = email;
        this.gender = gender;
        this.selected_disability = selected_disability;
        this.disability = disability;
        this.description = description;
        this.age = age;
        this.image = image;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSelected_disability(String selected_disability) {
        this.selected_disability = selected_disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getSelected_disability() {
        return selected_disability;
    }

    public String getDisability() {
        return disability;
    }

    public String getDescription() {
        return description;
    }

    public String getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }
}
