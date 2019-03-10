package com.data.java.crawler.dto;

public class EastMoneyBlogDTO {
    private String title;
    private String href;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", author='" + author + '\'';
    }
}
