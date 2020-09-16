package oinonen.MusicStore.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
 
 private Long id;
 
 @NotEmpty
 @Size(min=3, max=20)
 private String name;
 
 @NotNull
 @Min(1)
 private Long model_year;
 
 @NotNull
 private Long quantity;
 
 @NotNull
 @Min(1)
 private Long list_price;
 
 @NotEmpty
 private String category;
 
 @NotEmpty
 private String brand;
 
 private int cart = 0;
 
 private String imgUrl;
 
 public Product () {
	this.cart = 0;
 }
  
 public Product(String name, Long model_year, Long list_price, Long quantity, String category, String brand, String imgUrl) {
	super();
	this.name = name;
	this.model_year = model_year;
	this.list_price = list_price;
	this.quantity = quantity;
	this.brand = brand;
	this.category = category;
	this.imgUrl = imgUrl;
 }

 public String getImgUrl() {
  return imgUrl;
 }
 
 public int getCart() {
  return this.cart;
 }
 
 public void setCart(int cart) {
		this.cart = cart;
 }

 public void setImgUrl(String imgUrl) {
  this.imgUrl = imgUrl;
 }

 public void setList_price(Long list_price) {
  this.list_price = list_price;
 }
 
 public Long getList_price() {
  return list_price;
 }

 public String getCategory() {
  return category;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public String getBrand() {
  return brand;
 }

 public void setBrand(String brand) {
  this.brand = brand;
 }

 public long getId() {
  return id;
 }
 public void setId(long id) {
  this.id = id;
 }
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 public Long getModel_year() {
  return model_year;
 }
 public void setModel_year(Long model_year) {
  this.model_year = model_year;
 }
 public Long getQuantity() {
  return quantity;
 }
 public void setQuantity(Long quantity) {
  this.quantity = quantity;
 }
 
 @Override
 public String toString() {
	return String.format("[%d - %s - %d - %d - %d - %s - %s]", id, name, model_year, quantity, list_price, category, brand);
 }

}
