package oinonen.MusicStore.domain;

public class Customer {
 
 private Long id;
 private String first_name;
 private String last_name;
 private String street;
 private String city;
 private int post_code;
 
 public Customer() {}
 
 public Customer(String first_name, String last_name, String street, String city, int post_code) {
	super();
	this.id = (long) 0;
	this.first_name = first_name;
	this.last_name = last_name;
	this.street = street;
	this.city = city;
	this.post_code = post_code;
 }
 
 public Long getId() {
  return id;
 }
 public void setId(Long id) {
  this.id = id;
 }
 public String getFirst_name() {
  return first_name;
 }
 public void setFirst_name(String first_name) {
  this.first_name = first_name;
 }
 public String getLast_name() {
  return last_name;
 }
 public void setLast_name(String last_name) {
  this.last_name = last_name;
 }
 public String getStreet() {
  return street;
 }
 public void setStreet(String street) {
  this.street = street;
 }
 public String getCity() {
  return city;
 }
 public void setCity(String city) {
  this.city = city;
 }
 public int getPost_code() {
  return post_code;
 }
 public void setPost_code(int post_code) {
  this.post_code = post_code;
 }

 
}
