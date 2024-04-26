package com.example.projectakhirandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SignLog.db";
    public static final String TABLE_NAME_USERS = "users";
    public static final String TABLE_NAME_PROFILE = "profil";
    public static final String COL_1 = "email"; // Assuming "email" is the primary key for users table
    public static final String COL_2 = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_USERS + " (" + COL_1 + " TEXT primary key, " + COL_2 + " TEXT)");
        db.execSQL("create table " + TABLE_NAME_PROFILE + " (nomor NUMERIC PRIMARY KEY, nama TEXT, tanggallahir TEXT, jeniskelamin TEXT, alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFILE);
        onCreate(db);
    }

    // Methods for user table (can be added later if needed)

    public boolean insertUserData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, email);
        contentValues.put(COL_2, password);
        long result = db.insert(TABLE_NAME_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + COL_1 + " = ?", new String[]{email});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + COL_1 + " = ? AND " + COL_2 + " = ?", new String[]{email, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Methods for profile table

    public boolean insertProfileData(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomor", nomor);
        contentValues.put("nama", nama);
        contentValues.put("tanggallahir", tanggalLahir);
        contentValues.put("jeniskelamin", jenisKelamin);
        contentValues.put("alamat", alamat);
        try {
            long result = db.insert(TABLE_NAME_PROFILE, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Indicate failure
        }
    }
    public Cursor getAllProfil() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from profil", null);
        return res;
    }


}

