package com.salon.visibleelegance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(min = 8,message = "Password must be at least 8 characters")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+"),
            @Pattern(regexp = "(?=.*[a-z]).+"),
            @Pattern(regexp = "(?=.*[A-Z]).+"),
            @Pattern(regexp = "(?=.*[!@#\\$%\\^&\\*]).+"),
            @Pattern(regexp = "(?=\\S+$).+")
    })
    @JsonIgnore
    private String password;

    @Email(message = "Email can't be empty")
    @NotBlank(message = "Email can't be empty")
    @Pattern(
            regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$",
            message = "Invalid Email"
    )
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank(message = "First name can't be empty")
    @Size(min = 2,message = "That name is too short")
    @Pattern(regexp = "^([^0-9]*)$", message = "Name must not contain numbers")
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "First name can't be empty")
    @Size(min = 2,message = "That name is too short")
    @Pattern(regexp = "^([^0-9]*)$", message = "Name must not contain numbers")
    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(length = 250)
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    @Column
    @JsonIgnore
    private Boolean isAdmin = false;

    @Column(columnDefinition = "integer default 0")
    @JsonIgnore
    private int suggestedCount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

    public User(){}

    // Update User
    public User(String email, String firstName) {
        this.email = email;
        this.firstName = firstName.trim();
    }
    // Setter
    public User(String email, String firstName, String password) {
        this.email = email;
        this.firstName = firstName.trim();
        this.password = password;
    }
    // Getter
    public User(long id, String email, String firstName, String password, String avatar, String aboutMe, Boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.avatar = avatar;
        this.aboutMe = aboutMe;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public int getSuggestedCount() {
        return suggestedCount;
    }

    public void setSuggestedCount(int suggestedCount) {
        this.suggestedCount = suggestedCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
