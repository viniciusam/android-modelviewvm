package com.viniciusam.modelviewvm.usecase.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.sqlite.DBContract.TodoEntry;
import com.viniciusam.modelviewvm.sqlite.DBOpenHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TodoUCTest {

    private static Context mAppContext;
    private static SQLiteOpenHelper mOpenHelper;

    @BeforeClass
    public static void createOpenHelper() {
        mAppContext = InstrumentationRegistry.getTargetContext();
        mOpenHelper = new DBOpenHelper(mAppContext);
    }

    @AfterClass
    public static void closeOpenHelper() {
        mOpenHelper.close();
    }

    @Before
    @After
    public void resetDatabase() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(TodoEntry.TABLE_NAME, null, null);
        db.close();
    }

    @Test
    public void insertTodoUCTest() throws Exception {
        Todo newTodo = new InsertTodoUC(mAppContext, "New Todo")
                .run();
        assertNotNull(newTodo);
    }

    @Test
    public void removeTodoUCTest() throws Exception {
        Todo newTodo = new InsertTodoUC(mAppContext, "New Todo")
                .run();
        new RemoveTodoUC(mAppContext, newTodo.getId())
                .run();
        List<Todo> todoList = new GetAllTodoUC(mAppContext)
                .run();
        assertEquals(todoList.size(), 0);
    }

    @Test
    public void getAllTodoUCTest() throws Exception {
        new InsertTodoUC(mAppContext, "New Todo")
                .run();

        List<Todo> todoList = new GetAllTodoUC(mAppContext)
                .run();
        assertEquals(todoList.size(), 1);
    }

}
