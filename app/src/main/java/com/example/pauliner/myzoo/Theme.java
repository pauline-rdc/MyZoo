package com.example.pauliner.myzoo;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class Theme {
    private int id;
    private String name;

    public Theme() {
    }

    public Theme(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public Theme setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Theme setName(String name) {
        this.name = name;
        return this;
    }

    public String toString() {
        return "id : " + this.id + "\nthème : " + this.name;
    }
}
