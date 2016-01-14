package com.example.pauliner.myzoo;

/**
 * Created by Stéphane.e on 14/01/2016.
 */
public class Favoris {

    private int id;
    private Animal anm;

    public Favoris() {

    }

    public Favoris(Animal anm) {
        this.anm = anm;
    }

    public int getId() {
        return this.id;
    }

    public Favoris setId(int id) {
        this.id = id;
        return this;
    }

    public Animal getAnm() {
        return this.anm;
    }

    public Favoris setAnm(Animal anm) {
        this.anm = anm;
        return this;
    }

    public String toString() {
        if (this.anm != null) {
            return "Favoris : " + anm.getName();
        }
        return "Aucun favoris";
    }
}
