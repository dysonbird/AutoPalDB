package me.shuter.paldb.api;

import java.util.List;

public interface Creator {
	public boolean createJavaObject(String outputPath, String className, List<String> fields, String packageName);
	
	public String createSerializer(String outputPath, String className, List<String> fields, String packageName);
	
	public boolean createConfiguration(String outputPath, String className, List<String> serializers, String packageName);
	
	public String getFormatClassName(String className);
	
	public String getFormatGetter(String field);
	
	public String getFormatSetter(String field);
	
	public String getFormatField(String field);
	
	public String getValueType(String field);
	
	public String getType(String type);
	
	public String firstChUpperCase(String str);
	
	public String firstChLowerCase(String str);
}
