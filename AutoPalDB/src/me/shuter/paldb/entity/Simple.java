package me.shuter.paldb.entity;

public class Simple{
	 private Integer id;
	 private String name;
	 private Long bigNumber;

	 public Integer getId() {
		 return this.id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getName() {
		 return this.name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public Long getBigNumber() {
		 return this.bigNumber;
	 }

	 public void setBigNumber(Long bigNumber) {
		 this.bigNumber = bigNumber;
	 }

	 public String toString() {
		 return "[" + 
		 "id=" + id + 
		 "name=" + name + 
		 "bigNumber=" + bigNumber + 
		 "]";
	 }
}
