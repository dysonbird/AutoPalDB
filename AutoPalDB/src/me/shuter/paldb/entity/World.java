package me.shuter.paldb.entity;

public class World{
	 private Integer id;
	 private String placeName;
	 private String address;

	 public Integer getId() {
		 return this.id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getPlaceName() {
		 return this.placeName;
	 }

	 public void setPlaceName(String placeName) {
		 this.placeName = placeName;
	 }

	 public String getAddress() {
		 return this.address;
	 }

	 public void setAddress(String address) {
		 this.address = address;
	 }

	 public String toString() {
		 return "[" + 
		 "id=" + id + 
		 "placeName=" + placeName + 
		 "address=" + address + 
		 "]";
	 }
}
