package me.shuter.paldb;

import me.shuter.paldb.entity.Hello;

public class TestSimplePalDBClient {

	public static void main(String[] args) {
		SimplePalDBClient client = new SimplePalDBClient("store.paldb");
		
		Hello hello = client.getValue(1, Hello.class);
		
		System.out.println(hello.getText());
		
		client.close();
	}

}
