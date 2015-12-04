package me.shuter.paldb;

import java.io.File;

import me.shuter.paldb.entity.config.Config;

import com.linkedin.paldb.api.Configuration;
import com.linkedin.paldb.api.PalDB;
import com.linkedin.paldb.api.StoreWriter;

public class GenerateDB {
	private StoreWriter writer;
	
	public GenerateDB(String db) {
		Configuration config = Config.getConfigure();
		writer = PalDB.createWriter(new File(db), config);
	}
	
	public void put(Object key, Object value) {
		writer.put(key, value);
	}
	
	public void close() {
		writer.close();
	}
}
