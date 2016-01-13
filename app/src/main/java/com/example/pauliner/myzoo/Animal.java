package com.example.pauliner.myzoo;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class Animal {
    private int id;
    private String name;
    private String desc;
    private String loc;
    private String snd;
    private String img;
    private Theme thm;

    public Animal() {

    }

    public Animal(String name, String desc, String loc, String snd, String img, Theme thm) {
        this.name = name;
        this.desc = desc;
        this.loc = loc;
        this.snd = snd;
        this.img = img;
        this.thm = thm;
    }

    public int getId() {
        return this.id;
    }

    public Animal setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return this.desc;
    }

    public Animal setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getLoc() {
        return this.loc;
    }

    public Animal setLoc(String loc) {
        this.loc = loc;
        return this;
    }

    public String getSnd() {
        return this.snd;
    }

    public Animal setSnd(String snd) {
        this.snd = snd;
        return this;
    }

    public String getImg() {
        return this.img;
    }

    public Animal setImg(String img) {
        this.img = img;
        return this;
    }

    public Theme getThm() {
        return this.thm;
    }

    public Animal setThm(Theme thm) {
        this.thm = thm;
        return this;
    }

    public String toString() {
        String nameT = null;
        if (this.thm != null) {
            nameT = this.thm.getName();
        }
        else {
            nameT = "Aucun thème";
        }
        return "id : " + this.id + "\nanimal : " + this.name + "\ndescription : " + this.desc + "\nlocalisation : " + this.loc + "\nson : " + this.snd + "\nimage : " + this.img + "\nthème : " + nameT;
    }
}
