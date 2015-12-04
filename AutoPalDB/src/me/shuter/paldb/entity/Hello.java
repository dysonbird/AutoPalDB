package me.shuter.paldb.entity;

public class Hello{
	 private Integer id;
	 private Integer score;
	 private String text;

	 public Integer getId() {
		 return this.id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public Integer getScore() {
		 return this.score;
	 }

	 public void setScore(Integer score) {
		 this.score = score;
	 }

	 public String getText() {
		 return this.text;
	 }

	 public void setText(String text) {
		 this.text = text;
	 }

	 public String toString() {
		 return "[" + 
		 "id=" + id + 
		 "score=" + score + 
		 "text=" + text + 
		 "]";
	 }
}
