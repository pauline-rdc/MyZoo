package com.example.pauliner.myzoo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stéphane.e on 13/01/2016.
 */
public class ZooSQLite extends SQLiteOpenHelper {

    private static final String TABLE_THEME = "table_theme";
    private static final String COL_ID = "id_thm";
    private static final String COL_NAME = "name_thm";

    private static final String CREATE_BDD = "CREATE TABLE IF NOT EXISTS " + TABLE_THEME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL);";

    private static final String TABLE_ANIMAL = "table_animal";
    private static final String COL_ID_ANM = "id_anm";
    private static final String COL_NAME_ANM = "name_anm";
    private static final String COL_DESC_ANM = "desc_anm";
    private static final String COL_LOC_ANM = "loc_anm";
    private static final String COL_SND_ANM = "snd_anm";
    private static final String COL_IMG_ANM = "img_anm";
    private static final String COL_THM_ANM = "id_thm_anm";

    private static final String CREATE_BDD_ANIMAL = "CREATE TABLE IF NOT EXISTS " + TABLE_ANIMAL + " (" + COL_ID_ANM + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME_ANM + " TEXT NOT NULL, " + COL_DESC_ANM + " TEXT NOT NULL, " + COL_LOC_ANM + " TEXT NOT NULL, " + COL_SND_ANM + " TEXT, " + COL_IMG_ANM + " TEXT, " + COL_THM_ANM + " INTEGER NOT NULL);";

    private static final String TABLE_FAVORIS = "table_favoris";
    private static final String COL_ID_FAV = "id_fav";
    private static final String COL_ANM_FAV = "id_anm_fav";

    private static final String CREATE_BDD_FAVORIS = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORIS + " (" + COL_ID_FAV + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ANM_FAV + " INTEGER NOT NULL);";

    public ZooSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_BDD_ANIMAL);
        db.execSQL(CREATE_BDD_FAVORIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORIS + ";");
        onCreate(db);
    }

    public void initialize(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL + ";");
        onCreate(db);
    }
}
