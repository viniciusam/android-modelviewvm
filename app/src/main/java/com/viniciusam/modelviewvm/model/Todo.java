package com.viniciusam.modelviewvm.model;

import java.util.Date;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class Todo {

    private long id;
    private String title;
    private Date createdAt;
    private boolean completed;

    public Todo(long id, String title, Date createdAt, boolean completed) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
