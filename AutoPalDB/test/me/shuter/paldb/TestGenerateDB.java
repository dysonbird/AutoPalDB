package me.shuter.paldb;

import me.shuter.paldb.entity.Hello;

public class TestGenerateDB {
	public static void main(String[] args) {
		GenerateDB db = new GenerateDB("store.paldb");
		
		Hello hello = new Hello();
		hello.setId(1);
		hello.setScore(2);
		hello.setText("Hello World");
		
		db.put(hello.getId(), hello);
		
		db.close();
	}
}
