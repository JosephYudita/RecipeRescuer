package com.example.reciperescuer;

public class Recipe {
    private int imageResID;
    private String title;

    public Recipe(int imageResID, String title) {
        this.imageResID = imageResID;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResID;
    }
}
