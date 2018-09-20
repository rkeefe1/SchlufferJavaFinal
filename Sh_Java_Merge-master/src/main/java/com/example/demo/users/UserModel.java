package com.example.demo.users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import com.example.demo.messages.Message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "SEQ_USER_TABLE", sequenceName = "SEQ_USER_TABLE")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_TABLE")
    private Integer userId;
    
    @Column
    @Length(min = 5, max = 15, message = "Username min 5, max 15 chars!")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;
    
    @Column
    @Email(message="Please provide a valid email address")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    private String email;
    
    @Column
    @Length(min = 8, max = 15, message = "Password min 8, max 15 chars!")
    private String Password;
    
    
    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Message> messagesToUser;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Message> messagesFromUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "user_list",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "to_user_id", referencedColumnName = "userId")})
    
    private Set<UserModel> users = new HashSet<>();
    
	public Integer getUserId() {
        return userId;
    }

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", login='" + username + '\'' +
                ", password='" + Password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserModel user = (UserModel) o;

        return new EqualsBuilder()
                .append(userId, user.userId)
                .append(username, user.username)
                .append(Password, user.Password)
                .append(email, user.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(username)
                .append(Password)
                .append(email)
                .toHashCode();
    }
    
    
}