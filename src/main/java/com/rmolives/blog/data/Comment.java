package com.rmolives.blog.data;

import java.util.LinkedList;
import java.util.List;

public class Comment {
    private List<Comment> comments;
    private String value;
    private User user;
    private long time;

    private Comment(String value, User user, long time) {
        this.comments = new LinkedList<>();
        this.value = value;
        this.time = time;
        this.user = user;
    }

    public static Comment newComment(String value, User user) {
        return new Comment(value, user, System.currentTimeMillis());
    }

    public long getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
