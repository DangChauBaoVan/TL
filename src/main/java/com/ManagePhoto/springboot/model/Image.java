package com.ManagePhoto.springboot.model;




import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;



@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String category;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datetime")
	private Date datetime;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="keyword")
	private String keyword;
	
	@Column(name="like_status",nullable = false,columnDefinition = "int default 0")
	private int like_status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	


	public int getLike_status() {
		return like_status;
	}

	public void setLike_status(int like_status) {
		this.like_status = like_status;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", Title=" + title + ", Category=" + category + ",Date=" + datetime + ", image="+image+ "]";
	}

}
