package edu.poly.J6ShopNongsan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductCategoryReport implements Serializable{

	
	@Id
	String Name;
	Long quantity;
	
}
