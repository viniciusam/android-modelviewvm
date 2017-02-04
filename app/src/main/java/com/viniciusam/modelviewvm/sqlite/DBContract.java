package com.viniciusam.modelviewvm.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Vinicius on 03/02/2017.
 */
public class DBContract {

    private DBContract() {}

    public static class TodoEntry implements BaseColumns {

        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CREATED_AT = "created_at";
        public static final String COLUMN_COMPLETED = "completed";

        public static long getLastId(SQLiteDatabase db) {
            Cursor cursor = db.rawQuery("SELECT MAX(" + _ID + ") FROM " + TABLE_NAME, null);
            try {
                return (cursor.moveToFirst()) ? cursor.getLong(0) : 0;
            } finally {
                cursor.close();
            }
        }
    }

}
