package com.example.favouriteshow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "SHOWS";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String SHOW = "show";

    static final String DB_NAME = "FAVOURITE_SHOWS.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL," + EMAIL + " TEXT," +
            SHOW + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
