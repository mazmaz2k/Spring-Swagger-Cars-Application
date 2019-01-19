package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//    @ApiModelProperty(notes = "The ID of a car", hidden=true)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	@Size(min=2, message="Type should have atleast 2 characters")
	private String type;
	
	@Column(nullable = false)
	private String color;
	
	@Column(nullable = false)
	private int mileage;
	
	@Column(nullable = false)
	private int price;

	protected Car() {
	     // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }
	
	
	public Car(String type, String color, int mileage, int price) {
		this.type = type;
		this.color = color;
		this.mileage = mileage;
		this.price = price;
	}

    @ApiModelProperty(notes = "The ID of a car")
	public Long getId() {
		return id;
	}
	
	
    @ApiModelProperty(notes = "The type of a car", required = true)
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


    @ApiModelProperty(notes = "The color of a car", required = true)
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


    @ApiModelProperty(notes = "The mileage of a car", required = true)
	public int getMileage() {
		return mileage;
	}


	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
    @ApiModelProperty(notes = "The price of a car", required = true)
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
}