package oinonen.MusicStore.domain;

public class Brand {
 
 private long id;
 private String brand;
 
 public Brand() {}
 
 public Brand(String brand) {
	super();
	this.id = 0;
	this.brand = brand;
 }
 public long getId() {
  return id;
 }
 public void setId(long id) {
  this.id = id;
 }
 public String getBrand() {
  return brand;
 }
 public void setBrand(String brand) {
  this.brand = brand;
 }

 @Override
 public String toString() {
	return this.getBrand();
 }
}
