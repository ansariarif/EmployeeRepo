package com.neosoft.spring.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "car")
public class AddCar {
	
	@Id
	@Column(name = "carno")
	private int carno;
	
	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "issued")
	private int issued;
	
	@Column(name = "returnstatus")
	private String returnstatus;
	
	

}
