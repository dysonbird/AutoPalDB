package me.shuter.paldb;

import java.io.File;

import com.linkedin.paldb.api.Configuration;
import com.linkedin.paldb.api.PalDB;
import com.linkedin.paldb.api.StoreReader;

import me.shuter.paldb.entity.config.Config;

public class SimplePalDBClient {
	private StoreReader reader;
	
	public SimplePalDBClient(String db) {
		Configuration config = Config.getConfigure();
		reader = PalDB.createReader(new File(db), config);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(Object key, Class<T> t) {
		return (T)reader.get(key);
	}
	
	public void close() {
		reader.close();
	}
}
