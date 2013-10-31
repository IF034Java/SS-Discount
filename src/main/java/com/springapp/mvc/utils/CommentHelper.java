package com.springapp.mvc.utils;// Created with IntelliJ IDEA by Yaroslav Kovbas (Xardas)

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class CommentHelper {

    private Comment comment;
    private User user;
    private static String serviceSymbols;

    public static String getUserProfilePath() {
        return userProfilePath;
    }

    public static void setUserProfilePath(String userProfilePath) {
        CommentHelper.userProfilePath = userProfilePath;
    }

    private static String userProfilePath;

    public static String getAuthorLinkStyle() {
        return authorLinkStyle;
    }

    private static String authorLinkStyle;

    static {
        serviceSymbols = "~@~%~";
        userProfilePath = "user/profile";
        authorLinkStyle = "comment_reply_author";
    }

    public static String getServiceSymbols() {
        return serviceSymbols;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthor() {
        return String.format("%s %s", comment.getUser().getName(), comment.getUser().getSurname());
    }


    public String getMessageToEdit() {
        if (!comment.getContent().contains(getServiceSymbols())) {
            return comment.getContent();
        } else {
            return comment.getContent().replace(getServiceSymbols(), "");
        }
    }

    public String getMessageToDisplay() {
        String[] messageParts = comment.getContent().split(getServiceSymbols());
        if (messageParts.length < 2) {
            return comment.getContent();
        } else {
            return String.format("<a class=\"%s\" href=\"/%s/%s\">%s</a>%s",
                    getAuthorLinkStyle(),
                    getUserProfilePath(),
                    messageParts[0],
                    messageParts[0],
                    messageParts[1]);
        }
    }
}
