package com.example.dolly.Model;

public class SwipeCardsModel {
    String id,name,description,distance;

    public SwipeCardsModel() {
    }

    public SwipeCardsModel(String id, String name, String description, String distance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.distance = distance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDistance() {
        return distance;
    }
}
