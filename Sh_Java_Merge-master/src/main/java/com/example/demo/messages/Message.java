package com.example.demo.messages;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import com.example.demo.users.UserModel;


@Entity
@Table(name = "messages")
@SequenceGenerator(name = "SEQ_MESS_TABLE", sequenceName = "SEQ_MESS_TABLE")
public class Message {

    private static final List<String> SORT_FIELDS = Arrays.asList("createDate","subject","author","toUser");

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESS_TABLE")
    private Long id;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column
    @Length(min = 1, max = 50, message = "Subject max 50 chars!")
    private String subject;

    @Column
    @Length(min = 1, max = 250, message = "Message requires 1 to 250 chars")
    private String message;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "author_user_id")
    private UserModel author;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private UserModel toUser;

    
    public Message(Long id, Date timestamp, String subject, String message, Date createDate, UserModel author, UserModel toUser) {
		super();
		this.id = id;
		this.author = author;
		this.toUser = toUser;
		this.createDate = createDate;
	    this.subject = subject;
		this.message = message;
	}
    
    
    
    public Message() {}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public UserModel getToUser() {
        return toUser;
    }

    public void setToUser(UserModel toUser) {
        this.toUser = toUser;
    }

    public static Boolean isSortFieldValid(String field) {
        return SORT_FIELDS.contains(field);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", subject='" + subject + '\'' +
                ", body='" + message + '\'' +
                ", author=" + author +
                ", toUser=" + toUser +
                '}';
    }



	public void setUsername(UserModel userModel) {
		// TODO Auto-generated method stub
		
	}
}



