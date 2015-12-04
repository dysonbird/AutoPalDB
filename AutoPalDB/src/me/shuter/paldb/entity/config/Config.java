package me.shuter.paldb.entity.config;

import me.shuter.paldb.entity.serializer.HelloSerializer;
import me.shuter.paldb.entity.serializer.SimpleSerializer;
import me.shuter.paldb.entity.serializer.WorldSerializer;

import com.linkedin.paldb.api.Configuration;
import com.linkedin.paldb.api.PalDB;

public class Config {

	public static Configuration getConfigure() {
		Configuration config = PalDB.newConfiguration();
		config.registerSerializer(new HelloSerializer());
		config.registerSerializer(new SimpleSerializer());
		config.registerSerializer(new WorldSerializer());

		return config;
	}
}
