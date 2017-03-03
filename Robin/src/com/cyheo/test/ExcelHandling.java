package com.cyheo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandling {

	/**
	 * @brief 
	 * @details
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		String rootPath = "D:/tmp2/";

		List<String> fileList = getFileNames(rootPath);

		if(fileList == null || fileList.size() == 0) {
			logW("Excel file is nothing!!!");
			return;
		}
		
		FileInputStream fis = null;
		FileWriter fw = null;
		File file = null;
		XSSFWorkbook workbook = null;
		String dstFilePath = "";
		String flatFileName = "";

		XSSFSheet curSheet;
		XSSFRow curRow;
		XSSFCell curCell;

		StringBuffer sb = new StringBuffer();
		String curVal = "";
		int fCnt = 1;
		int colNum = 0;

		for (String fileName : fileList) {
			dstFilePath = rootPath + fileName.substring(0, fileName.lastIndexOf(".")) + "/";
			logW(dstFilePath);

			try {

				logW("Begin Work!");

				file = new File(dstFilePath);
				if(!file.exists()) file.mkdir();

				fis = new FileInputStream(rootPath + fileName);

				workbook = new XSSFWorkbook(fis);

				flatFileName = "";
				sb = new StringBuffer();
				curVal = "";
				fCnt = 1;

				curSheet = workbook.getSheetAt(1);

				colNum = getValPos(curSheet);

				for(int rowIndex=0; rowIndex<curSheet.getPhysicalNumberOfRows(); rowIndex++) {

					curRow = curSheet.getRow(rowIndex);

					if(curRow != null && curRow.getCell(colNum) != null) {

						curCell = curRow.getCell(colNum);
						curVal = curCell.getStringCellValue();

						if(curVal.startsWith("MSG_ID")) {
							if(sb.length() > 0 && !"".equals(flatFileName)) {
								fw = new FileWriter(dstFilePath + flatFileName + ".txt");
								fw.write(sb.toString());
								fw.close();
								logW(""+fCnt + ":" + flatFileName);
								flatFileName = getFileName(curVal);
								fCnt++;
							} else {
								flatFileName = getFileName(curVal);
							}
							sb = new StringBuffer();
							sb.append(curVal);
						} else {
							sb.append(curVal);
						}

					}
				}

				if(sb.length() > 0) {
					fw = new FileWriter(dstFilePath + flatFileName + ".txt");
					fw.write(sb.toString());
					fw.close();
					logW(""+fCnt + ":" + flatFileName);
				}

				logW("End Work!");

			} catch(Exception ex) {
				logW(ex.getMessage());
			}
		}

	}

	public static String getFileName(String val) {
		String fileName = "default";
		String [] arrStr = val.split("\n");
		for(int i=0; i<arrStr.length; i++) {
			if(arrStr[i].split(":")[0].equals("MUID")) {
				fileName = arrStr[i].split(":")[1];
			}
		}
		return fileName;
	}

	public static List<String> getFileNames(String path) {
		
		File dir = new File(path);
		File [] files = dir.listFiles();
		List<String> fileList = new ArrayList<String>();

		if(files != null) {
			for(File file : files) {
				if(file.isFile() && file.getName().endsWith("xlsx")) {
//					logW(file.getName());
					fileList.add(file.getName());
				}
			}
		}

		return fileList;
	}

	public static int getValPos(XSSFSheet curSheet) {
		int colNum = 0;
		XSSFRow curRow;
		XSSFCell curCell;

		for(int rowIndex=0; rowIndex<curSheet.getPhysicalNumberOfRows(); rowIndex++) {

			curRow = curSheet.getRow(rowIndex);

			if(curRow != null) {

				for(int colIndex = 0; colIndex<100; colIndex++) {
					
					curCell = curRow.getCell(colIndex);

					if(curCell != null && curCell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {

						try {
							if(curCell.getStringCellValue() != null) {
								logW("cellType(" + colIndex + ", " + rowIndex + "):" + curCell.getStringCellValue());
								if(curCell.getStringCellValue().startsWith("MSG_ID:")) {
									colNum = colIndex;
									break;
								}
							}
						} catch (Exception ex) {
							colNum = 0;
						}
					}
				}

				if(colNum > 0) break;
			}
		}

		return colNum;
	}

	public static void logW(String msg) {
		java.lang.System.out.println(msg);
	}
	
}