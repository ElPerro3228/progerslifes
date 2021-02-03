package com.progerslifes.diplom.facades.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostDTO {

    @NotNull
    @Size(min = 1)
    private String text;

    private int userId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
