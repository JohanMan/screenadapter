package com.johan.screenadapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 因为我的手机小米4的dpi是480，分辨率是1080*1920，最小宽度swdp=1080*160/480=360dp
 * 所以本人用这个作为base的dimens
 * @author Johan
 */
public class AdapterTool {
	
	public static void autoCreate() {
		
		int swBaseData = 360;
		File swBaseFile = new File("res/values-sw360dp/dimens.xml");
		
		BufferedReader reader = null;
		
		StringBuilder sw240Builder = new StringBuilder();
		StringBuilder sw320Builder = new StringBuilder();
//		StringBuilder sw360Builder = new StringBuilder();
		StringBuilder sw384Builder = new StringBuilder();
		StringBuilder sw400Builder = new StringBuilder();
		StringBuilder sw480Builder = new StringBuilder();
		StringBuilder sw540Builder = new StringBuilder();
		StringBuilder sw600Builder = new StringBuilder();
		
		try {
			
			System.out.println("原始dimen : " + swBaseFile);
			System.out.println("自动生成开始 ----------");
			
			InputStreamReader read = new InputStreamReader(new FileInputStream(swBaseFile),"UTF-8");
			reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("</dimen>")) {
					int startIndex = line.indexOf(">") + 1;
					int endIndex = line.lastIndexOf("</dimen>") - 2;
					String start = line.substring(0,  startIndex);
					String end = line.substring(endIndex);
					float data = Float.parseFloat(line.substring(startIndex, endIndex));
					float sw240Data = data * 240 / swBaseData;
					float sw320Data = data * 320 / swBaseData;
//					float sw360Data = data * 360 / swBaseData;
					float sw384Data = data * 384 / swBaseData;
					float sw400Data = data * 400 / swBaseData;
					float sw480Data = data * 480 / swBaseData;
					float sw540Data = data * 540 / swBaseData;
					float sw600Data = data * 600 / swBaseData;
					sw240Builder.append(start).append(formatData(sw240Data)).append(end).append("\n");
					sw320Builder.append(start).append(formatData(sw320Data)).append(end).append("\n");
//					sw360Builder.append(start).append(formatData(sw360Data)).append(end).append("\n");
					sw384Builder.append(start).append(formatData(sw384Data)).append(end).append("\n");
					sw400Builder.append(start).append(formatData(sw400Data)).append(end).append("\n");
					sw480Builder.append(start).append(formatData(sw480Data)).append(end).append("\n");
					sw540Builder.append(start).append(formatData(sw540Data)).append(end).append("\n");
					sw600Builder.append(start).append(formatData(sw600Data)).append(end).append("\n");
				} else {
					sw240Builder.append(line).append("\n");
					sw320Builder.append(line).append("\n");
//					sw360Builder.append(line).append("\n");
					sw384Builder.append(line).append("\n");
					sw400Builder.append(line).append("\n");
					sw480Builder.append(line).append("\n");
					sw540Builder.append(line).append("\n");
					sw600Builder.append(line).append("\n");
				}
			}
			reader.close();
			
			String sw240Path = "res/values-sw240dp";
			String sw320Path = "res/values-sw320dp";
//			String sw360Path = "res/values-sw360dp";
			String sw384Path = "res/values-sw384dp";
			String sw400Path = "res/values-sw400dp";
			String sw480Path = "res/values-sw480dp";
			String sw540Path = "res/values-sw540dp";
			String sw600Path = "res/values-sw600dp";
			
			createDir(sw240Path);
			createDir(sw320Path);
//			createDir(sw360Path);
			createDir(sw384Path);
			createDir(sw400Path);
			createDir(sw480Path);
			createDir(sw540Path);
			createDir(sw600Path);
			
			writeFile(sw240Path, sw240Builder.toString());
			writeFile(sw320Path, sw320Builder.toString());
//			writeFile(sw360Path, sw360Builder.toString());
			writeFile(sw384Path, sw384Builder.toString());
			writeFile(sw400Path, sw400Builder.toString());
			writeFile(sw480Path, sw480Builder.toString());
			writeFile(sw540Path, sw540Builder.toString());
			writeFile(sw600Path, sw600Builder.toString());
			
			System.out.println("自动生成结束 ----------");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String formatData(float data) {
		if (data * 10 % 10 == 0) {
			return String.valueOf((int) data);
		} else {
			return String.valueOf((float) (Math.round(data*10))/10);
		}
	}
	
	private static void createDir(String fileDir) {
		File dir = new File(fileDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	private static void writeFile(String filePath, String content) {
		BufferedWriter writer = null;
        try {
        	OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath + "/dimens.xml"), "UTF-8");
        	writer = new BufferedWriter(write); 
        	writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

}
