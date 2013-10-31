package com.springapp.mvc.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "RATIO", nullable = false)
    private Integer ratio;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ENTERPRISE_ID")
    private Enterprise enterprise;

    @Lob
    @Column(name = "CONTENT", nullable = false, length = 800)
    private String content;

    @Column(name = "DATE", nullable = false, length = 30)
    private String date;

    @OneToMany(targetEntity = CommentRatio.class, mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<CommentRatio> commentRatios;

    @OneToOne(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment parentComment;

    @Column(name = "EDIT_DATE", nullable = true, length = 30)
    private String editDate;


    public Comment() {
        setRatio(0);
    }

    /*Getters and Setters*/
    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setCommentRatios(Set<CommentRatio> commentRatios) {
        this.commentRatios = commentRatios;
    }

    public Set<CommentRatio> getCommentRatios() {
        return commentRatios;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof Comment) &&
                ((Comment) o).getId() != null &&
                ((Comment) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
