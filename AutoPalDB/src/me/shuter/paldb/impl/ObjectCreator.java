package me.shuter.paldb.impl;

import me.shuter.paldb.api.Creator;

public abstract class ObjectCreator implements Creator {
	
	@Override
	public String getFormatClassName(String className) {
		return className;
	}
	
	@Override
	public String getValueType(String field) {
		return null;
	}
	
	@Override
	public String getFormatGetter(String field) {
		return "get" + firstChUpperCase(field);
	}
	
	@Override
	public String getFormatSetter(String field) {
		return "set" + firstChUpperCase(field);
	}
	
	/**
	 * Camel-Case default
	 */
	@Override
	public String getFormatField(String field) {
		if (field == null || field.length() < 1) {
			return field;
		}
		return firstChLowerCase(field);
		
	}
	
	@Override
	public String firstChUpperCase(String str) {
		if(str.length() == 1) {
			return str.toUpperCase();
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	@Override
	public String firstChLowerCase(String str) {
		if(str.length() == 1) {
			return str.toUpperCase();
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	@Override
	public String getType(String type) {
		String t = null;
		
		if (type.startsWith("String")) {
			t = "UTF";
		} else if(type.startsWith("Long")) {
			t = "Long";
		} else {
			t = "Int";
		}
		
		return t;
	}
}
