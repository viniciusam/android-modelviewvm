package com.viniciusam.modelviewvm.usecase.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viniciusam.modelviewvm.executor.UseCase;
import com.viniciusam.modelviewvm.sqlite.DBContract.TodoEntry;
import com.viniciusam.modelviewvm.sqlite.DBOpenHelper;

/**
 * Created by Vinicius on 08/02/2017.
 */
public class RemoveTodoUC extends UseCase<Void> {

    private Context mContext;
    private long mId;

    public RemoveTodoUC(Context context, long id) {
        mContext = context;
        mId = id;
    }

    @Override
    public Void run() throws Exception {
        SQLiteDatabase db = new DBOpenHelper(mContext).getWritableDatabase();
        try {
            db.delete(TodoEntry.TABLE_NAME, TodoEntry._ID + " = ?", new String[]{Long.toString(mId)});
            return null;
        } finally {
            db.close();
        }
    }
}
