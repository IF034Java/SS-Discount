package com.springapp.mvc.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Email;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class ProposedEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "NAME", nullable = false)
    @Size(min = 2, max = 30)
    private String name;

    @Column(name = "DISCOUNT_MIN", nullable = false)
    @NotNull
    private Integer discountMin;

    @Column(name = "DISCOUNT_MAX", nullable = false)
    @NotNull
    private Integer discountMax;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false, length = 800)
    @Size(min = 5)
    private String Description;

    @Column(name = "MAIL", nullable = true)
    @Email
    private String mail;

    @Column(name = "WEBSITE", nullable = true)
    @Size(min = 4)
    private String webSite;

    @Size(min = 5)
    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Size(min = 5)
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Column(name = "LOGO_PATH", nullable = true)
    private String logoPath;

    @Lob
    @Column(name = "LOGO", nullable = true)
    private byte[] logo;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    /*Getter and setters*/
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscountMin(Integer discountMin) {
        this.discountMin = discountMin;
    }

    public void setDiscountMax(Integer discountMax) {
        this.discountMax = discountMax;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getDiscountMin() {
        return discountMin;
    }

    public Integer getDiscountMax() {
        return discountMax;
    }

    public String getDescription() {
        return Description;
    }

    public String getMail() {
        return mail;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public byte[] getLogo() {
        return logo;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof ProposedEnterprise) &&
                ((ProposedEnterprise) o).getId() != null &&
                ((ProposedEnterprise) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
