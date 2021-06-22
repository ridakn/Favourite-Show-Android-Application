package com.example.favouriteshow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String email, String show) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.NAME, name);
        values.put(dbHelper.EMAIL, email);
        values.put(dbHelper.SHOW, show);
        db.insert(dbHelper.TABLE_NAME, null, values);
    }


    public Cursor fetch() {
        String[] columns = new String[] {
                dbHelper._ID, dbHelper.NAME, dbHelper.EMAIL, dbHelper.SHOW
        };
        Cursor cursor = db.query(dbHelper.TABLE_NAME, columns,
                null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String email, String show) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.NAME, name);
        values.put(dbHelper.EMAIL, email);
        values.put(dbHelper.SHOW, show);
        int i = db.update(dbHelper.TABLE_NAME, values, dbHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        db.delete(dbHelper.TABLE_NAME, dbHelper._ID + " = " + _id, null);
    }


}

