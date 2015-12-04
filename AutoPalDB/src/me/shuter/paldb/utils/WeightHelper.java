package me.shuter.paldb.utils;

public abstract class WeightHelper {
	
	/**
	 * copy from https://github.com/linkedin/PalDB/blob/master/paldb/src/main/java/com/linkedin/paldb/impl/StorageCache.java
	 * and do some change
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int getValueWeight(Object value) {
		if (value == null) {
			return 0;
		}
		if (value.getClass().isArray()) {
			Class cc = value.getClass().getComponentType();
			if (cc.isPrimitive()) {
				if (cc.equals(int.class)) {
					return ((int[]) value).length * 4;
				} else if (cc.equals(long.class)) {
					return ((long[]) value).length * 8;
				} else if (cc.equals(double.class)) {
					return ((double[]) value).length * 8;
				} else if (cc.equals(float.class)) {
					return ((float[]) value).length * 4;
				} else if (cc.equals(boolean.class)) {
					return ((boolean[]) value).length * 1;
				} else if (cc.equals(byte.class)) {
					return ((byte[]) value).length * 1;
				} else if (cc.equals(short.class)) {
					return ((short[]) value).length * 2;
				} else if (cc.equals(char.class)) {
					return ((char[]) value).length * 2;
				}
			} else if (cc.equals(String.class)) {
				String[] v = (String[]) value;
				int res = 0;
				for (int i = 0; i < v.length; i++) {
					res += v[i].length() * 2 + 40;
				}
				return res;
			} else if (cc.equals(int[].class)) {
				int[][] v = (int[][]) value;
				int res = 0;
				for (int i = 0; i < v.length; i++) {
					res += v[i].length * 4;
				}
				return res;
			} else if (cc.equals(long[].class)) {
				long[][] v = (long[][]) value;
				int res = 0;
				for (int i = 0; i < v.length; i++) {
					res += v[i].length * 8;
				}
				return res;
			} else {
				Object[] v = (Object[]) value;
				int res = 0;
				for (int i = 0; i < v.length; i++) {
					res += getValueWeight(v[i]);
				}
				return res;
			}
		} else if (value instanceof String) {
			return ((String) value).length() * 2 + 40;
		} else if (value instanceof Integer) {
			return 4;
		} else if (value instanceof Long) {
			return 8;
		} else if (value instanceof Double) {
			return 8;
		} else if (value instanceof Float) {
			return 4;
		} else if (value instanceof Boolean) {
			return 1;
		} else if (value instanceof Byte) {
			return 1;
		} else if (value instanceof Short) {
			return 2;
		} else if (value.getClass().getComponentType().equals(char.class)) {
			return 2;
		}
		
		return 16;
	}
}
