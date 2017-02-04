package com.viniciusam.modelviewvm.usecase.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viniciusam.modelviewvm.executor.UseCase;
import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.sqlite.DBContract.TodoEntry;
import com.viniciusam.modelviewvm.sqlite.DBOpenHelper;

import java.util.Date;

/**
 * Created by Vinicius on 04/02/2017.
 */
public class InsertTodoUC extends UseCase<Todo> {

    private Context mContext;
    private String mTitle;

    public InsertTodoUC(Context context, String title) {
        mContext = context;
        mTitle = title;
    }

    @Override
    public Todo run() throws Exception {
        SQLiteDatabase db = new DBOpenHelper(mContext).getWritableDatabase();
        try {
            long newId = TodoEntry.getLastId(db) + 1;

            Todo newTodo = new Todo(newId, mTitle, new Date(), false);

            ContentValues values = new ContentValues();
            values.put(TodoEntry._ID, newTodo.getId());
            values.put(TodoEntry.COLUMN_TITLE, newTodo.getTitle());
            values.put(TodoEntry.COLUMN_CREATED_AT, newTodo.getCreatedAt().getTime());
            values.put(TodoEntry.COLUMN_COMPLETED, newTodo.isCompleted());

            db.insertOrThrow(TodoEntry.TABLE_NAME, null, values);
            return newTodo;
        } finally {
            db.close();
        }
    }

}
