package me.shuter.paldb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import me.shuter.paldb.api.Creator;

public class ExcelUtil {
	private static String EXCEL_SUFFIX = ".xls";
	
	/**
	 * 根据excel生成类和序列化的类
	 * @param inputPath excel文件路径
	 * @param outputPath 类输出路径
	 * @param packName 类包名
	 * @param creator 类创建器
	 */
	public static void excel2JavaObject(String inputPath, String outputPath, String packageName, Creator creator) {
		File sourceDoc = new File(inputPath);
		File[] files = sourceDoc.listFiles();
		
		List<String> serializers = new ArrayList<>();
		for(File file : files) {
			if (file.getName().endsWith(EXCEL_SUFFIX) == false) {
				continue;
			}
			
			InputStream is = null;
			HSSFWorkbook hssfWorkbook = null;
			try {
				is = new FileInputStream(file);
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (Exception e) {
				continue;
			}

			//read first sheet
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			if (hssfSheet == null) {
				continue;
			}

			//read first row for field name
			HSSFRow hssfRowTitle = hssfSheet.getRow(0);
			if (hssfRowTitle == null) {
				continue;
			}

			ArrayList<String> fields = new ArrayList<String>();
			HashMap<String, String> fieldsMap = new HashMap<String, String>();
			for (int i = 0; i <= hssfRowTitle.getLastCellNum(); i++) {
				HSSFCell cell = hssfRowTitle.getCell(i);
				if (cell == null || cell.toString() == null || cell.toString().trim().length() < 1) {
					//read cell until not null
					break;
				}

				String field = cell.toString();
				
				if (fieldsMap.containsKey(field)) {
					//field can't be repeat
					continue;
				}

				fields.add(field);
				fieldsMap.put(field, field);
			}
			
			creator.createJavaObject(outputPath, file.getName().substring(0, file.getName().indexOf(".")), fields, packageName);
			String serializer = creator.createSerializer(outputPath, file.getName().substring(0, file.getName().indexOf(".")), fields, packageName);
			if(serializer != null) {
				serializers.add(serializer);
			}
		}
		
		creator.createConfiguration(outputPath, "Config", serializers, packageName);
	}
}
