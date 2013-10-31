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
@Table(name = "LOGGING")
public class Logging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ACTIVITY", nullable = false)
    private String activity;

    @Column(name = "DATE", nullable = false)
    private String date;

    /*Getters and Setters*/
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof Logging) &&
                ((Logging) o).getId() != null &&
                ((Logging) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
