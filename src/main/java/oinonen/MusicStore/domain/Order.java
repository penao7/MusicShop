package oinonen.MusicStore.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Order {
 
 private Long id;
 private Long total_price;
 private Timestamp date;
 
 public Order() {}
 
 public Order(Long id, Long total_price, Timestamp date) {
	super();
	this.id = id;
	this.total_price = total_price;
	this.date = date;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public Long getTotal_price() {
  return total_price;
 }

 public void setTotal_price(Long total_price) {
  this.total_price = total_price;
 }

 public String getDate() {
  SimpleDateFormat sdf = new SimpleDateFormat();
  sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
  return sdf.format(date);
 }
 
 public String getDateString() {
  return new SimpleDateFormat().format(date);
 }

 public Timestamp setDate(Timestamp date) {
  return this.date = date;
 }

}
