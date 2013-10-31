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
@Table(name = "ENTERPRISE_RATIO")
public class EnterpriseRatio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ENTERPRISE_ID")
    private Enterprise enterprise;

    @Column(name = "VALUE")
    private Integer value;

    /*Getters and Setters*/

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
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

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof EnterpriseRatio) &&
                ((EnterpriseRatio) o).getId() != null &&
                ((EnterpriseRatio) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

