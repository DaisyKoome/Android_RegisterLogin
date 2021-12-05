package com.example.joysplash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PasswordDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "password";
    public static final String TABlE_NAME = "user";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";

    public PasswordDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABlE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT UNIQUE, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABlE_NAME);
        onCreate(db);
    }

    //Registration handler
    public boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        long result = db.insert(TABlE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //Login handler
    public Cursor login_user(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABlE_NAME + " WHERE EMAIL='" + email + "';", null);
        return res;
    }

}
