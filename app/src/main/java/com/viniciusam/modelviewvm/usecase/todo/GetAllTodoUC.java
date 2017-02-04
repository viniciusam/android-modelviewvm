package com.viniciusam.modelviewvm.usecase.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viniciusam.modelviewvm.executor.UseCase;
import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.sqlite.DBContract;
import com.viniciusam.modelviewvm.sqlite.DBOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinicius on 04/02/2017.
 */
public class GetAllTodoUC extends UseCase<List<Todo>> {

    private Context mContext;

    public GetAllTodoUC(Context context) {
        mContext = context;
    }

    @Override
    public List<Todo> run() throws Exception {
        List<Todo> list = new ArrayList<>();

        SQLiteDatabase db = new DBOpenHelper(mContext).getReadableDatabase();
        try {
            Cursor cursor = db.query(
                    DBContract.TodoEntry.TABLE_NAME,
                    new String[]{
                            DBContract.TodoEntry._ID,
                            DBContract.TodoEntry.COLUMN_TITLE,
                            DBContract.TodoEntry.COLUMN_CREATED_AT,
                            DBContract.TodoEntry.COLUMN_COMPLETED },
                    null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(0);
                    String title = cursor.getString(1);
                    Date createdAt = new Date(cursor.getLong(2));
                    boolean completed = cursor.getInt(3) == 1;

                    list.add(new Todo(id, title, createdAt, completed));
                } while (cursor.moveToNext());
            }
        } finally {
            db.close();
        }

        return list;
    }
}
