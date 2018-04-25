package com.daracul.android.booklistingapp;

/**
 * Created by amalakhov on 25.04.2018.
 */

public class Book {
   private String author, title, description, imageUrl;

    public Book(String author, String title, String description, String imageUrl) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
