package com.springapp.mvc.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="USER")
public class User implements UserDetails{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false, length = 30)
    @Pattern(regexp="^[а-яА-ЯІіЇїЄєa-zA-Z0-9]+$",
            message="Name must be alpha numeric with no spaces")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters long.")
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 30)
    @Pattern(regexp="^[а-яА-ЯІіЇїЄєa-zA-Z0-9]+$",
            message="Surname must be alpha numeric with no spaces")
    @Size(min = 3, max = 30, message = "Surname must be between 3 and 30 characters long.")
    private String surname;

	@Column(name = "NICKNAME", unique = true, nullable = false, length = 30)
    @Pattern(regexp="^[a-zA-Z0-9]+$",
            message="Nickname must be alpha numeric with no spaces")
    @Size(min = 3, max = 30, message = "Nickname must be between 3 and 30 characters long.")
    private String nickname;

    @Column(name = "OPEN_ID_IDENTITY", unique = true, nullable = true)
    private String openIdIdentity;

    @ManyToOne @JoinColumn(name="ROLE_ID")
    private Role role;

    @Size(min = 6, message = "Password must be between 6 and 30 characters long.")
	@Column(name = "PASSWORD", nullable = false)
    private String password;

    @Pattern(regexp= "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message="Invalid email address.")
	@Column(name = "MAIL", nullable = false, length = 32)
	private String mail;

    @OneToMany(targetEntity = EnterpriseRatio.class, mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<EnterpriseRatio> enterpriseRatios;

    @OneToMany(targetEntity = CommentRatio.class, mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<CommentRatio> commentRatios;

    @OneToMany(targetEntity = Comment.class, mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<Comment> comment;

    @OneToMany(targetEntity = Logging.class, mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Logging> logging;

    @OneToMany(targetEntity = ProposedEnterprise.class, mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<ProposedEnterprise> proposedEnterprises;


    /*Getters and Setters*/
    public void setProposedEnterprises(Set<ProposedEnterprise> proposedEnterprises) {
        this.proposedEnterprises = proposedEnterprises;
    }

    public Set<ProposedEnterprise> getProposedEnterprises() {
        return proposedEnterprises;
    }

    public void setLogging(Set<Logging> logging) {
        this.logging = logging;
    }

    public Set<Logging> getLogging() {
        return logging;
    }

    public void setEnterpriseRatios(Set<EnterpriseRatio> enterpriseRatios) {
        this.enterpriseRatios = enterpriseRatios;
    }
    public Set<EnterpriseRatio> getEnterpriseRatios() {
        return enterpriseRatios;
    }
    public User() {
    }
    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
    public Set<Comment> getComment() {
        return comment;
    }
    public void setCommentRatios(Set<CommentRatio> commentRatios) {
        this.commentRatios = commentRatios;
    }
    public Set<CommentRatio> getCommentRatios() {
        return commentRatios;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }
    public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(this.role);
        return authorities;
    }

    public String getPassword() {
		return password;
	}

    @Override
    public String getUsername() {
        return this.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabled() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPassword(String password) {
		this.password = password;
	}

    @Override
    public boolean equals(Object o) {
        return (o != null) &&
                (o instanceof User) &&
                ((User) o).getId() != null &&
                ((User) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getOpenIdIdentity() {
        return openIdIdentity;
    }

    public void setOpenIdIdentity(String openIdIdentity) {
        this.openIdIdentity = openIdIdentity;
    }
}
