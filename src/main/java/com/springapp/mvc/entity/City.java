package com.springapp.mvc.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;

@Entity
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 30)
    @Size(min = 2, max = 15)
    private String name;

    @OneToMany(targetEntity = Enterprise.class, mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Enterprise> enterprises;

	/*Getters and Setters*/

    public void setEnterprises(Set<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

    public Set<Enterprise> getEnterprises() {
        return enterprises;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof City) &&
                ((City) o).getId() != null &&
                ((City) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

