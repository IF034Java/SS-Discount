package com.springapp.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT_RATIO")
public class CommentRatio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @Column(name = "VALUE", columnDefinition = "BIT", nullable = true)
    private Boolean value;

    /*Getters and Setters*/
    public void setValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public Comment getComment() {
        return comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof CommentRatio) &&
                ((CommentRatio) o).getId() != null &&
                ((CommentRatio) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
