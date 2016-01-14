package com.example.pauliner.myzoo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class ZooBDD {
    private static ArrayList<Theme> themes;
    private static ArrayList<Animal> animals;

    private static final int VERSION_BDD = 1;
    private static final String NAME_BDD = "zoo.db";

    private static final String TABLE_THEME = "table_theme";
    private static final String COL_ID = "id_thm";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "name_thm";
    private static final int NUM_COL_NAME = 1;

    private static final String TABLE_ANIMAL = "table_animal";
    private static final String COL_ID_ANM = "id_anm";
    private static final int NUM_COL_ID_ANM = 0;
    private static final String COL_NAME_ANM = "name_anm";
    private static final int NUM_COL_NAME_ANM = 1;
    private static final String COL_DESC_ANM = "desc_anm";
    private static final int NUM_COL_DESC_ANM = 2;
    private static final String COL_LOC_ANM = "loc_anm";
    private static final int NUM_COL_LOC_ANM = 3;
    private static final String COL_SND_ANM = "snd_anm";
    private static final int NUM_COL_SND_ANM = 4;
    private static final String COL_IMG_ANM = "img_anm";
    private static final int NUM_COL_IMG_ANM = 5;
    private static final String COL_THM_ANM = "id_thm_anm";
    private static final int NUM_COL_THM_ANM = 6;

    private static final String TABLE_FAVORIS = "table_favoris";
    private static final String COL_ID_FAV = "id_fav";
    private static final int NUM_COL_ID_FAV = 0;
    private static final String COL_ANM_FAV = "id_anm_fav";
    private static final int NUM_COL_ANM_FAV = 1;

    private static SQLiteDatabase bdd;

    private static ZooSQLite zooSQLite;

    private static Cursor cursorT;
    private static Cursor cursorA;
    private static Cursor cursorF;

    public ZooBDD(Context context) {
        zooSQLite = new ZooSQLite(context, NAME_BDD, null, VERSION_BDD);
    }

    public static void initialize() {
        zooSQLite.initialize(bdd);
    }

    public static void open() {
        bdd = zooSQLite.getWritableDatabase();
    }

    public static void close() {
        bdd.close();
    }

    public static long insertTheme(Theme theme) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, theme.getName());
        return bdd.insert(TABLE_THEME, null, values);
    }

    public static long insertAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME_ANM, animal.getName());
        values.put(COL_DESC_ANM, animal.getDesc());
        values.put(COL_LOC_ANM, animal.getLoc());
        values.put(COL_SND_ANM, animal.getSnd());
        values.put(COL_IMG_ANM, animal.getImg());
        values.put(COL_THM_ANM, animal.getThm().getId());
        return bdd.insert(TABLE_ANIMAL, null, values);
    }

    public static long insertFavoris(Favoris favoris) {
        ContentValues values = new ContentValues();
        values.put(COL_ANM_FAV, favoris.getAnm().getId());
        return bdd.insert(TABLE_FAVORIS, null, values);
    }

    public static int updateTheme(Theme theme) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, theme.getName());
        return bdd.update(TABLE_THEME, values, COL_ID + " = " + theme.getId(), null);
    }

    public static int updateAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME_ANM, animal.getName());
        values.put(COL_DESC_ANM, animal.getDesc());
        values.put(COL_LOC_ANM, animal.getLoc());
        values.put(COL_SND_ANM, animal.getSnd());
        values.put(COL_IMG_ANM, animal.getImg());
        values.put(COL_THM_ANM, animal.getThm().getId());
        return bdd.update(TABLE_ANIMAL, values, COL_ID_ANM + " = " + animal.getId(), null);
    }

    public static int removeTheme(Theme theme) {
        return bdd.delete(TABLE_THEME, COL_ID + " = " + theme.getId(), null);
    }

    public static int removeAnimal(Animal animal) {
        return bdd.delete(TABLE_ANIMAL, COL_ID_ANM + " = " + animal.getId(), null);
    }

    public static int removeFavoris(Favoris favoris) {
        return bdd.delete(TABLE_FAVORIS, COL_ID_FAV + " = " + favoris.getId(), null);
    }

    public static Theme getThemeById(int id) {
        cursorT = bdd.query(TABLE_THEME, new String[]{COL_ID, COL_NAME}, COL_ID + " = \"" + id + "\"", null, null, null, null);
        if (cursorT.getCount() == 0) {
            return null;
        }

        cursorT.moveToFirst();

        Theme theme = new Theme();
        theme.setId(cursorT.getInt(NUM_COL_ID));
        theme.setName(cursorT.getString(NUM_COL_NAME));

        return theme;
    }

    public static Animal getAnimalById(int id) {
        cursorA = bdd.query(TABLE_ANIMAL, new String[]{COL_ID_ANM, COL_NAME_ANM, COL_DESC_ANM, COL_LOC_ANM, COL_SND_ANM, COL_IMG_ANM, COL_THM_ANM}, COL_ID_ANM + " = \"" + id + "\"", null, null, null, null);
        if (cursorA.getCount() == 0) {
            return null;
        }

        cursorA.moveToFirst();

        Animal animal = new Animal();
        animal.setId(cursorA.getInt(NUM_COL_ID_ANM));
        animal.setName(cursorA.getString(NUM_COL_NAME_ANM));
        animal.setDesc(cursorA.getString(NUM_COL_DESC_ANM));
        animal.setLoc(cursorA.getString(NUM_COL_LOC_ANM));
        animal.setSnd(cursorA.getString(NUM_COL_SND_ANM));
        animal.setImg(cursorA.getString(NUM_COL_IMG_ANM));
        animal.setThm(getThemeById(cursorA.getInt(NUM_COL_THM_ANM)));

        return animal;
    }

    public static Favoris getFavorisByAnimal(int id) {
        cursorF = bdd.query(TABLE_FAVORIS, new String[]{COL_ID_FAV, COL_ANM_FAV}, COL_ANM_FAV + " = \"" + id + "\"", null, null, null, null);
        if (cursorF.getCount() == 0) {
            return null;
        }

        cursorF.moveToFirst();

        Favoris favoris = new Favoris();
        favoris.setId(cursorF.getInt(NUM_COL_ID_FAV));
        favoris.setAnm(getAnimalById(cursorF.getInt(NUM_COL_ANM_FAV)));

        return favoris;
    }

    public static boolean isFavoris(int id) {
        cursorF = bdd.query(TABLE_FAVORIS, new String[]{COL_ID_FAV, COL_ANM_FAV}, null, null, null, null, null);
        boolean favoris = false;
        if (cursorF.getCount() == 0) {
            return favoris;
        }

        cursorF.moveToFirst();
        do {
            Animal animal = getAnimalById(cursorF.getInt(NUM_COL_ANM_FAV));
            if (animal.getId() == id) {
                favoris = true;
            }
        }
        while (cursorF.moveToNext());

        return favoris;
    }

    public static String trtFavoris(int id) {
        if (isFavoris(id)) {
            removeFavoris(getFavorisByAnimal(id));
            return "delete";
        }
        else {
            Favoris favoris = new Favoris();
            favoris.setAnm(getAnimalById(id));
            insertFavoris(favoris);
            return "add";
        }
    }

    public static ArrayList<Animal> getAnimalFavoris() {
        animals = new ArrayList<Animal>();
        cursorF = bdd.query(TABLE_FAVORIS, new String[]{COL_ID_FAV, COL_ANM_FAV}, null, null, null, null, null);

        if (cursorF.getCount() == 0) {
            return animals;
        }

        cursorF.moveToFirst();
        do {
            Animal animal = getAnimalById(cursorF.getInt(NUM_COL_ANM_FAV));
            animals.add(animal);
        }
        while (cursorF.moveToNext());

        return animals;
    }

    public static ArrayList<Theme> getThemeByName(String name) {
        themes = new ArrayList<Theme>();
        cursorT = bdd.query(TABLE_THEME, new String[]{COL_ID, COL_NAME}, COL_NAME + " LIKE \"%" + name + "%\"", null, null, null, null);
        if (cursorT.getCount() == 0) {
            return themes;
        }

        cursorT.moveToFirst();
        do {
            Theme theme = new Theme();
            theme.setId(cursorT.getInt(NUM_COL_ID));
            theme.setName(cursorT.getString(NUM_COL_NAME));

            themes.add(theme);
        }
        while (cursorT.moveToNext());

        return themes;
    }

    public static ArrayList<Animal> getAnimalByName(String name) {
        animals = new ArrayList<Animal>();
        cursorA = bdd.query(TABLE_ANIMAL, new String[]{COL_ID_ANM, COL_NAME_ANM, COL_DESC_ANM, COL_LOC_ANM, COL_SND_ANM, COL_IMG_ANM, COL_THM_ANM}, COL_NAME_ANM + " LIKE \"%" + name + "%\"", null, null, null, null);

        if (cursorA.getCount() == 0) {
            return animals;
        }

        cursorA.moveToFirst();
        do {
            Animal animal = new Animal();
            animal.setId(cursorA.getInt(NUM_COL_ID_ANM));
            animal.setName(cursorA.getString(NUM_COL_NAME_ANM));
            animal.setDesc(cursorA.getString(NUM_COL_DESC_ANM));
            animal.setLoc(cursorA.getString(NUM_COL_LOC_ANM));
            animal.setSnd(cursorA.getString(NUM_COL_SND_ANM));
            animal.setImg(cursorA.getString(NUM_COL_IMG_ANM));
            animal.setThm(getThemeById(cursorA.getInt(NUM_COL_THM_ANM)));

            animals.add(animal);
        }
        while (cursorA.moveToNext());

        return animals;
    }

    public static ArrayList<Animal> getAnimalByTheme(int theme, String name) {
        animals = new ArrayList<Animal>();
        cursorA = bdd.query(TABLE_ANIMAL, new String[]{COL_ID_ANM, COL_NAME_ANM, COL_DESC_ANM, COL_LOC_ANM, COL_SND_ANM, COL_IMG_ANM, COL_THM_ANM}, COL_THM_ANM + " = \"" + theme + "\" AND " + COL_NAME_ANM + " LIKE \"%" + name + "%\"", null, null, null, null);

        if (cursorA.getCount() == 0) {
            return animals;
        }

        cursorA.moveToFirst();
        do {
            Animal animal = new Animal();
            animal.setId(cursorA.getInt(NUM_COL_ID_ANM));
            animal.setName(cursorA.getString(NUM_COL_NAME_ANM));
            animal.setDesc(cursorA.getString(NUM_COL_DESC_ANM));
            animal.setLoc(cursorA.getString(NUM_COL_LOC_ANM));
            animal.setSnd(cursorA.getString(NUM_COL_SND_ANM));
            animal.setImg(cursorA.getString(NUM_COL_IMG_ANM));
            animal.setThm(getThemeById(cursorA.getInt(NUM_COL_THM_ANM)));

            animals.add(animal);
        }
        while (cursorA.moveToNext());

        return animals;
    }
}
