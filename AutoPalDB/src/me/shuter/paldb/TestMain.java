package me.shuter.paldb;

import me.shuter.paldb.impl.JavaObjectCreator;
import me.shuter.paldb.utils.ExcelUtil;

public class TestMain {
	public static void main(String[] args) {
		String packageName= "me.shuter.paldb.entity";
		
		String inputPath = ClassLoader.getSystemResource("excel").getPath();
		String outputPath = "E:\\workspace\\AutoPalDB\\src\\me\\shuter\\paldb\\entity\\";
		
		JavaObjectCreator creator = new JavaObjectCreator();
		ExcelUtil.excel2JavaObject(inputPath, outputPath, packageName, creator);
	}
}
