package com.viniciusam.modelviewvm.model;

import java.util.Date;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class Todo {

    private String title;
    private Date date;
    private boolean completed;

    public Todo(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
