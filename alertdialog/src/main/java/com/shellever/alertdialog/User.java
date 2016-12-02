package com.shellever.alertdialog;

/**
 * Author: Shellever
 * Date:   11/9/2016
 * Email:  shellever@163.com
 */

public class User {

    private int imageId;
    private String name;

    public User() {
    }

    public User(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                '}';
    }
}
