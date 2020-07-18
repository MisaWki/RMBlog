package com.rmolives.blog.data;

public class Classification {
    public Article newArticle(String name) {
        return Article.newArticle(name, this);
    }
}
