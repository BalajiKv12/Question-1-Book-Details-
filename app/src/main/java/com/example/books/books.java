package com.example.books;

//creating a model class for storing book details from database
public class books {
    String id,name,author,year;
    int image;

    //creating constructor with book name, id, author, year and image
    public books(String id, String name, String author, String year, int image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.image=image;
    }

    //getters and setters for all details in books
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
