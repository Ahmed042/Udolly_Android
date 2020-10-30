package com.example.datingpro.Model;

public class NearByModel {
  public String id,name,distance;
  public String image;

    public NearByModel() {
    }

    public NearByModel(String id, String name, String distance, String image) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getImage() {
        return image;
    }
}
