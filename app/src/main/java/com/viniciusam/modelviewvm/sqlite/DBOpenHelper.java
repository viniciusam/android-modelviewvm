package com.viniciusam.modelviewvm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.viniciusam.modelviewvm.sqlite.DBContract.TodoEntry;

/**
 * Created by Vinicius on 03/02/2017.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + TodoEntry.TABLE_NAME + " ( " +
                TodoEntry._ID + " INTEGER PRIMARY KEY, " +
                TodoEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_CREATED_AT + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_COMPLETED + " INTEGER NOT NULL )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
