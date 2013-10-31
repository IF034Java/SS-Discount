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
import javax.persistence.Table;
import java.util.Set;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ENTERPRISE")
public class Enterprise {
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

    @Column(name = "MAIL", nullable = false)
    @Email
    private String mail;

    @Column(name = "WEBSITE", nullable = false)
    @Size(min = 4)
    private String webSite;

    @Column(name = "LATITUDE", nullable = false)
    @NotNull
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    @NotNull
    private Double longitude;

    @Size(min = 5)
    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Size(min = 5)
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToMany(targetEntity = Comment.class, mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Comment> comments;

    @OneToMany(targetEntity = EnterpriseRatio.class, mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<EnterpriseRatio> enterpriseRatios;

    @Column(name = "LOGO_PATH", nullable = true)
    private String logoPath;

    @Lob
    @Column(name = "LOGO", nullable = true)
    private byte[] logo;

    /*Getters and Setters*/
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setEnterpriseRatios(Set<EnterpriseRatio> enterpriseRatios) {
        this.enterpriseRatios = enterpriseRatios;
    }

    public Set<EnterpriseRatio> getEnterpriseRatios() {
        return enterpriseRatios;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
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

    public Integer getDiscountMin() {
        return discountMin;
    }

    public void setDiscountMin(Integer discountMin) {
        this.discountMin = discountMin;
    }

    public Integer getDiscountMax() {
        return discountMax;
    }

    public void setDiscountMax(Integer discountMax) {
        this.discountMax = discountMax;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof Enterprise) &&
                ((Enterprise) o).getId() != null &&
                ((Enterprise) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
