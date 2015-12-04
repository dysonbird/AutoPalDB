package me.shuter.paldb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileUtils {

	/**
	 * save code to file
	 * @param code
	 * @param outputPath
	 * @return
	 */
	public static boolean saveChatTxt(List<String> code, String outputPath) {
		if (code == null || code.size() < 1) {
			return true;
		}
		boolean ok = false;
		File file = null;
		FileOutputStream output = null;
		PrintWriter writer = null;
		try {
			file = new File(outputPath);
			if (file.exists()) {
				file.delete();
			}

			file.createNewFile();

			output = new FileOutputStream(file, false);
			writer = new PrintWriter(output);

			for (String msg : code) {
				writer.println(msg);
			}
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				writer.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}
}
