package com.example.dolly.Model.FireBaseModels;

public class FilterModel {
    private String id;
    private String gender;
    private String ageMin;
    private String ageMax;
    private String distance;
    private String selected_disability;
    private String disability;
    private String profession;
    private String relationship_status;
    private String favourite_sports;
    private String education;
    private String smoker;
    private String religion;
    private String body_type;
    private String language;
    private String drinking;
    private String exercise;
    private String tattoos;
    private String pets;
    private String music;
    private String diet;
    private String children;

    public FilterModel() {
    }

    public FilterModel(String id, String gender, String ageMin, String ageMax, String distance, String selected_disability, String disability, String profession, String relationship_status, String favourite_sports, String education, String smoker, String religion, String body_type, String language, String drinking, String exercise, String tattoos, String pets, String music, String diet, String children) {
        this.id = id;
        this.gender = gender;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.distance = distance;
        this.selected_disability = selected_disability;
        this.disability = disability;
        this.profession = profession;
        this.relationship_status = relationship_status;
        this.favourite_sports = favourite_sports;
        this.education = education;
        this.smoker = smoker;
        this.religion = religion;
        this.body_type = body_type;
        this.language = language;
        this.drinking = drinking;
        this.exercise = exercise;
        this.tattoos = tattoos;
        this.pets = pets;
        this.music = music;
        this.diet = diet;
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAgeMin(String ageMin) {
        this.ageMin = ageMin;
    }

    public void setAgeMax(String ageMax) {
        this.ageMax = ageMax;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setSelected_disability(String selected_disability) {
        this.selected_disability = selected_disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setRelationship_status(String relationship_status) {
        this.relationship_status = relationship_status;
    }

    public void setFavourite_sports(String favourite_sports) {
        this.favourite_sports = favourite_sports;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setTattoos(String tattoos) {
        this.tattoos = tattoos;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getAgeMin() {
        return ageMin;
    }

    public String getAgeMax() {
        return ageMax;
    }

    public String getDistance() {
        return distance;
    }

    public String getSelected_disability() {
        return selected_disability;
    }

    public String getDisability() {
        return disability;
    }

    public String getProfession() {
        return profession;
    }

    public String getRelationship_status() {
        return relationship_status;
    }

    public String getFavourite_sports() {
        return favourite_sports;
    }

    public String getEducation() {
        return education;
    }

    public String getSmoker() {
        return smoker;
    }

    public String getReligion() {
        return religion;
    }

    public String getBody_type() {
        return body_type;
    }

    public String getLanguage() {
        return language;
    }

    public String getDrinking() {
        return drinking;
    }

    public String getExercise() {
        return exercise;
    }

    public String getTattoos() {
        return tattoos;
    }

    public String getPets() {
        return pets;
    }

    public String getMusic() {
        return music;
    }

    public String getDiet() {
        return diet;
    }

    public String getChildren() {
        return children;
    }
}
