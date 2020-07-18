package com.rmolives.blog.data;

import java.util.LinkedList;
import java.util.List;

// 这东西可能空指针，代修复
public class Article {
    private List<Comment> comments;
    private List<Tags> tags;
    private String name;
    private long time;
    private Classification classification;
    private String value;

    private Article(String name, long time, Classification classification) {
        comments = new LinkedList<>();
        tags = new LinkedList<>();
        this.name = name;
        this.time = time;
        this.classification = classification;
        this.value = "";
    }

    public static Article newArticle(String name, Classification classification) {
        return new Article(name, System.currentTimeMillis(), classification);
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public Classification getClassification() {
        return classification;
    }

    public boolean addTags(Tags tags) {
        if (this.tags.contains(tags))
            return false;
        this.tags.add(tags);
        return true;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
