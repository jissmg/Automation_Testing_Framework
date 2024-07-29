package com.saucedemo.Utils;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtils {

		//Represent the location of the excel sheet
		public static FileInputStream fi;
		
		public static FileOutputStream fo;

		//Represent the Whole excel sheet
		public static XSSFWorkbook wb;
		
		//Represent the Individual sheet
		public static XSSFSheet ws;
		
		//Represent the Rows of the sheet
		public static XSSFRow row;
		
		//Represent the cells of the sheet
		public static XSSFCell cell;
		
		public CellStyle style;

		String path=null;
		
		public ExcelUtils(String path) {
		
			this.path = path;
		}

		public int getRowCount( String sheetName) throws IOException {

			int rowCount = 0;
			// Fetch the file
			fi = new FileInputStream(path);
			// Initialize the Work Book
			wb = new XSSFWorkbook(fi);
			// Get the sheet from Work book
			ws = wb.getSheet(sheetName);
			// Get the last row.
			rowCount = ws.getLastRowNum();

			wb.close();
			fi.close();

			return rowCount;

		}

		public int getColumnCount( String sheetName, int rowNo) throws IOException {

			int colCount = 0;
			// Fetch the file
			fi = new FileInputStream(path);
			// Initialize the Work Book
			wb = new XSSFWorkbook(fi);
			// Get the last row.
			ws = wb.getSheet(sheetName);

			row = ws.getRow(rowNo);
			 colCount = row.getLastCellNum();
			wb.close();
			fi.close();
			return colCount;

		}

		public String getCellValue( String sheetName, int rowNo, int colCount) throws IOException {

			//String data = "";
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNo);
			cell = row.getCell(colCount);
			DataFormatter formatter=new DataFormatter();
			String data;
			try {
			data = formatter.formatCellValue(cell);
			}
			catch(Exception e) {
				data="";
			}
			wb.close();
			fi.close();
			return data;

		}
		public String setCellValue( String sheetName, int rowNo, int colCount,String data) throws IOException {

			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNo);
			cell = row.getCell(colCount);//setcell value
			fo=new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
			return data;

		}

	
}
