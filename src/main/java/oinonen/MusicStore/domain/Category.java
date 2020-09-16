package oinonen.MusicStore.domain;

public class Category {
 
 private long id;
 private String category;
 
 public Category () {}
 
 public Category(String category) {
	super();
	this.id = 0;
	this.category = category;
 }
 
 public Long getId() {
  return id;
 }
 public void setId(Long id) {
  this.id = id;
 }
 public String getCategory() {
  return category;
 }
 public void setCategory(String category) {
  this.category = category;
 }

 @Override
 public String toString() {
	return this.getCategory();
 }
}
