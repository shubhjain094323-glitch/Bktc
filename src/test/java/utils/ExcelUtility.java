package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow rows;
	public XSSFCell cell;
	public CellStyle style;

	public ExcelUtility(String excelPath, String sheetName) {

		try {
			FileInputStream fis = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Row Count
	public int getRowCount() {
		// return sheet.getLastRowNum();
		return sheet.getPhysicalNumberOfRows();
	}

	// Get Column Count
	public int getColumnCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	// Read Cell Data
	public String getCellData(int rowNum, int colNum) {

		DataFormatter formatter = new DataFormatter();

		Cell cell = sheet.getRow(rowNum).getCell(colNum);

		return formatter.formatCellValue(cell);

	}

	// Write Data
	public void setCellData(String excelPath, int rowNum, int colNum, String value) {

		try {

			Row row = sheet.getRow(rowNum);

			if (row == null)
				row = sheet.createRow(rowNum);

			Cell cell = row.getCell(colNum);

			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(value);

			FileOutputStream fos = new FileOutputStream(excelPath);

			workbook.write(fos); 

			fos.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	// Close Workbook
	public void closeWorkbook() {

		try {

			workbook.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}