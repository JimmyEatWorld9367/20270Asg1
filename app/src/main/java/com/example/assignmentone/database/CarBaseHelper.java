package com.example.assignmentone.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignmentone.database.CarDbSchema.CarTable;

import java.util.UUID;

public class CarBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "carBase.db";

    public CarBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CarTable.NAME + "(" +
                " _id integer primary key autoincrement,  uuid integer , color varchar(30), model varchar(30), year integer, rego varchar(30), price integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
