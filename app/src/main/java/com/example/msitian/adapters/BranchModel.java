package com.example.msitian.adapters;

public class BranchModel {
    String title, description;

    public BranchModel(String title, String description) {

        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
