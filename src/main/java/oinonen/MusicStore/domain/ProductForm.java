package oinonen.MusicStore.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductForm {
 
 @NotNull
 @Size(min=3, max=10)
 private String name;
 
 @NotNull
 private int model_year;
 
 @NotNull
 private Double list_price;
 
 @NotNull
 private int quantity;
 
 @NotNull
 private String brand;
 
 @NotNull
 private String category;
  
 private String imgUrl;

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getModel_year() {
  return model_year;
 }

 public void setModel_year(int model_year) {
  this.model_year = model_year;
 }

 public Double getList_price() {
  return list_price;
 }

 public void setList_price(Double list_price) {
  this.list_price = list_price;
 }

 public int getQuantity() {
  return quantity;
 }

 public void setQuantity(int quantity) {
  this.quantity = quantity;
 }

 public String getBrand() {
  return brand;
 }

 public void setBrand(String brand) {
  this.brand = brand;
 }

 public String getCategory() {
  return category;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public String getImgUrl() {
  return imgUrl;
 }

 public void setImgUrl(String imgUrl) {
  this.imgUrl = imgUrl;
 }

}
