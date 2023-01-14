package com.lms.api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is a utility class and is used to Read Excel sheet
 */
public class ExcelReader {

	String path;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	Row row;

	public ExcelReader(String path) {
		super();
		this.path = path;
	}

	public void readSheet(String sheetName) throws Exception {

		File file = new File(path);
		fis = new FileInputStream(file);

		// Below API can ready both xls and xlsx formats
		workbook = WorkbookFactory.create(fis);

		// Below API only ready xlsx formats
		// workbook = new XSSFWorkbook(fis);

		// Below API only ready xls formats
		// workbook=new HSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

	}

	public String getDataFromExcel(String rowName, String colName) throws IOException {
		int dataRowNum = -1;
		int dataColNum = -1;
		String retVal = null;
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i <= totalRows; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equals(rowName)) {
				dataRowNum = i;
				break;
			}

		}
		for (int j = 0; j <= totalCols; j++) {
			if (sheet.getRow(0).getCell(j).getStringCellValue().equals(colName)) {
				dataColNum = j;
				break;
			}
		}
		// String body=sheet.getRow(dataRowNum).getCell(dataColNum).getStringCellValue();
		// fis.close();
		// return body;

		DataFormatter formatter = new DataFormatter(); // creating formatter using the default locale
		Cell cell = sheet.getRow(dataRowNum).getCell(dataColNum);
		String retVal1 = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
															// regardless of the cell type.
		fis.close();
		return retVal1;
	}
	
	public String getDataFromExcelPost(String rowName, String colName) throws IOException {
		int dataRowNum = -1;
		int dataColNum = -1;
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i <= totalRows; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equals(rowName)) {
				dataRowNum = i;
				break;
			}

		}
		for (int j = 0; j <= totalCols; j++) {
			if (sheet.getRow(0).getCell(j).getStringCellValue().equals(colName)) {
				dataColNum = j;
				break;
			}
		}
		String body=sheet.getRow(dataRowNum).getCell(dataColNum).getStringCellValue();
		fis.close();
		return body;

	}

}