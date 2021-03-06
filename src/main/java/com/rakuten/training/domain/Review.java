package com.rakuten.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String author;
	String text;
	float stars;
	@ManyToOne
	@JoinColumn(name="product_id")//owning should have join column
	Product product;
	public void setProduct(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}

	public Review(String author, String text, float stars) {
		super();
		this.author = author;
		this.text = text;
		this.stars = stars;
	}

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}
	

}
