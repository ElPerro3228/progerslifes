package com.progerslifes.diplom.solr.service;

import java.io.Serializable;

public class SuggestedTerm implements Serializable {

    private String username;
    private String picturePath;

    public SuggestedTerm() {
    }

    public SuggestedTerm(String username, String picturePath) {
        this.username = username;
        this.picturePath = picturePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
