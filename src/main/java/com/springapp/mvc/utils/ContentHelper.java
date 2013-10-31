package com.springapp.mvc.utils;// Created with IntelliJ IDEA by Yaroslav Kovbas (Xardas)

public class ContentHelper {

    public String getContent() {
        return content.length() > 140
                ? String.format("%s...",content.substring(0,140))
                : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
