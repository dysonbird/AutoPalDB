package me.shuter.paldb.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.shuter.paldb.utils.FileUtils;

public class JavaObjectCreator extends ObjectCreator {

	@Override
	public boolean createJavaObject(String outputPath, String className, List<String> fields, String packageName) {
		className = getFormatClassName(className);

		ArrayList<String> outputCode = new ArrayList<String>();//class code
		ArrayList<String> geterAndSetter = new ArrayList<String>();// getter and setter
		
		List<String> porperties = new ArrayList<>(); // property

		outputCode.add("package " + packageName + ";");// packName
		outputCode.add("");
		
		outputCode.add("public class " + className + "{");// class name

		// Fields
		for(int i = 0; i < fields.size(); i++) {
			String field = fields.get(i);
			
			String type = getValueType(field);
			
			field = getFormatField(field);
			
			porperties.add(field);
			
			outputCode.add("\t private " + type + " " + field + ";");
			
			// getter
			geterAndSetter.add("\t public " + type + " " + getFormatGetter(field) + "() {");
			geterAndSetter.add("\t\t return this." + field + ";");
			geterAndSetter.add("\t }");
			geterAndSetter.add("");
			
			//setter
			geterAndSetter.add("\t public void " + getFormatSetter(field) + "(" + type + " " + field + ") {");
			geterAndSetter.add("\t\t this." + field + " = " + field + ";");
			geterAndSetter.add("\t }");
			geterAndSetter.add("");
			
		}
		
		outputCode.add("");
		
		for (String str : geterAndSetter) {
			outputCode.add(str);
		}

		outputCode.add("\t public String toString() {"); // toString() 
		outputCode.add("\t\t return \"[\" + ");
		for (String property : porperties) {
			outputCode.add("\t\t " + "\"" + property + "=\" + " + property + " + ");
		}
		outputCode.add("\t\t \"]\";");
		outputCode.add("\t }");

		outputCode.add("}");// 结束:}

		String fileName = className + ".java";

		return FileUtils.saveChatTxt(outputCode, outputPath + fileName);
	}
	
	@Override
	public String createSerializer(String outputPath, String className, List<String> fields, String packageName) {
		className = getFormatClassName(className);
		
		String serializerName = className + "Serializer";

		ArrayList<String> outputCode = new ArrayList<String>();//class code
		ArrayList<String> writeMethod = new ArrayList<String>();
		ArrayList<String> readMethod = new ArrayList<String>();
		ArrayList<String> getWeightMethod = new ArrayList<String>();

		outputCode.add("package " + packageName + ".serializer;");// packName
		outputCode.add("");
		outputCode.add("import java.io.DataInput;");
		outputCode.add("import java.io.DataOutput;");
		outputCode.add("import java.io.IOException;");
		outputCode.add("");
		outputCode.add("import me.shuter.paldb.entity." + className + ";");
		outputCode.add("import me.shuter.paldb.utils.WeightHelper;");
		outputCode.add("");
		outputCode.add("import com.linkedin.paldb.api.Serializer;");
		outputCode.add("");
		outputCode.add("@SuppressWarnings(\"serial\")");
		outputCode.add("public class " + serializerName + " extends WeightHelper implements Serializer<" + className + "> {");// class name
		outputCode.add("");
		
		writeMethod.add("\t@Override");
		writeMethod.add("\tpublic void write(DataOutput dataOutput, " + className + " input) throws IOException {");
		
		
		readMethod.add("\t@Override");
		readMethod.add("\tpublic " + className +" read(DataInput dataInput) throws IOException {");
		readMethod.add("\t\t" + className + " output = new " + className + "();");

		getWeightMethod.add("\t@Override");
		getWeightMethod.add("\tpublic int getWeight(" + className +" instance) {");
		getWeightMethod.add("\t\tint weight = 0;");

		// Fields
		for(int i = 0; i < fields.size(); i++) {
			String field = fields.get(i);
			
			String type = getValueType(field);
			
			field = getFormatField(field);
			
			writeMethod.add("\t\tdataOutput.write" + getType(type) + "(input." + getFormatGetter(field) + "());");
			
			readMethod.add("\t\toutput." + getFormatSetter(field) + "(dataInput.read" + getType(type) + "());");
			
			getWeightMethod.add("\t\tweight += getValueWeight(instance." + getFormatGetter(field) + "());");
		}
		
		writeMethod.add("\t}");
		
		readMethod.add("\t\treturn output;");
		readMethod.add("\t}");
		
		getWeightMethod.add("\t\treturn weight;");
		getWeightMethod.add("\t}");
		
		for (String str : writeMethod) {
			outputCode.add(str);
		}
		
		outputCode.add("");
		
		for (String str : readMethod) {
			outputCode.add(str);
		}
		
		outputCode.add("");
		
		for (String str : getWeightMethod) {
			outputCode.add(str);
		}

		outputCode.add("}");// 结束:}

		String fileName = serializerName + ".java";

		boolean rs = FileUtils.saveChatTxt(outputCode, outputPath + File.separator + "serializer" + File.separator + fileName);
		
		if(rs) {
			return serializerName;
		}
		
		return null;
	}
	
	@Override
	public boolean createConfiguration(String outputPath, String className, List<String> serializers, String packageName) {
		ArrayList<String> outputCode = new ArrayList<String>();//class code
		
		outputCode.add("package " + packageName + ".config;");// packName
		outputCode.add("");
		for(String name : serializers) {
			outputCode.add("import me.shuter.paldb.entity.serializer." + name + ";");
		}
		outputCode.add("");
		outputCode.add("import com.linkedin.paldb.api.Configuration;");
		outputCode.add("import com.linkedin.paldb.api.PalDB;");
		outputCode.add("");
		
		outputCode.add("public class " + className + " {");// class name
		outputCode.add("");
		outputCode.add("\tpublic static Configuration getConfigure() {");
		outputCode.add("\t\tConfiguration config = PalDB.newConfiguration();");
		for(String name : serializers) {
			outputCode.add("\t\tconfig.registerSerializer(new " + name + "());");
		}
		outputCode.add("");
		outputCode.add("\t\treturn config;");
		outputCode.add("\t}");
		outputCode.add("}");

		String fileName = className + ".java";

		return FileUtils.saveChatTxt(outputCode, outputPath + File.separator + "config" + File.separator + fileName);
	}
	
	/**
	 * Camel-Case
	 */
	@Override
	public String getFormatClassName(String name) {
		if (name == null || name.length() < 1) {
			return name;
		}

		StringBuilder builder = new StringBuilder();
		name = firstChUpperCase(name);

		int index_ = name.indexOf("_");//有下划线的话,删除下划线,并把后面一个字母大写
		if (index_ >= 0) {
			String[] strArray = name.split("_");
			builder.append(strArray[0]);
			for (int i = 1; i < strArray.length; i++) {
				String str = strArray[i];
				if (str != null && str.length() > 0) {
					builder.append(firstChUpperCase(str));
				}
			}
		} else {
			builder.append(name);
		}

		return builder.toString();
	}
	
	@Override
	public String getValueType(String field) {
		String type = null;
		if (field.startsWith("str")) {
			type = "String";
		} else if(field.startsWith("long")) {
			type = "Long";
		} else {
			type = "Integer";
		}
		
		return type;
	}
	
	/**
	 * Camel-Case default
	 */
	@Override
	public String getFormatField(String field) {
		if (field == null || field.length() < 1) {
			return field;
		}
		
		//remove type prefix
		String[] strArray = field.split("_");
		
		if(strArray.length > 1) {
			return firstChLowerCase(strArray[1]);
		}
		
		return firstChLowerCase(strArray[0]);
	}
}
