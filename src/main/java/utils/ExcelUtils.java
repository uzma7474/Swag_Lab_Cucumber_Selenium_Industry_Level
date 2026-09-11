package utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for Excel operations.
 *
 * Supports XLSX files using Apache POI.
 */
public class ExcelUtils implements AutoCloseable {

	private final Workbook workbook;

	private final String filePath;

	private final DataFormatter dataFormatter;

	/**
	 * Opens an Excel workbook.
	 *
	 * @param filePath Excel file path
	 */
	public ExcelUtils(String filePath) {

		this.filePath = filePath;

		this.dataFormatter = new DataFormatter();

		try (FileInputStream inputStream = new FileInputStream(filePath)) {

			this.workbook = new XSSFWorkbook(inputStream);

		} catch (IOException e) {

			throw new RuntimeException("Unable to open Excel file: " + filePath, e);
		}
	}

	// ========================================================
	// Sheet Operations
	// ========================================================

	/**
	 * Returns sheet by name.
	 *
	 * @param sheetName sheet name
	 * @return Sheet
	 */
	public Sheet getSheet(String sheetName) {

		Sheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {

			throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}

		return sheet;
	}

	/**
	 * Returns number of sheets.
	 *
	 * @return sheet count
	 */
	public int getSheetCount() {

		return workbook.getNumberOfSheets();
	}

	/**
	 * Returns sheet name by index.
	 *
	 * @param index sheet index
	 * @return sheet name
	 */
	public String getSheetName(int index) {

		return workbook.getSheetName(index);
	}

	/**
	 * Creates a new sheet if it does not exist.
	 *
	 * @param sheetName sheet name
	 */
	public void createSheet(String sheetName) {

		if (workbook.getSheet(sheetName) == null) {

			workbook.createSheet(sheetName);
		}
	}

	// ========================================================
	// Row / Column Operations
	// ========================================================

	/**
	 * Returns physical row count.
	 *
	 * @param sheetName sheet name
	 * @return row count
	 */
	public int getRowCount(String sheetName) {

		return getSheet(sheetName).getPhysicalNumberOfRows();
	}

	/**
	 * Returns last row number.
	 *
	 * @param sheetName sheet name
	 * @return last row index
	 */
	public int getLastRowNum(String sheetName) {

		return getSheet(sheetName).getLastRowNum();
	}

	/**
	 * Returns physical column count from a row.
	 *
	 * @param sheetName sheet name
	 * @param rowNumber row index
	 * @return column count
	 */
	public int getColumnCount(String sheetName, int rowNumber) {

		Row row = getSheet(sheetName).getRow(rowNumber);

		if (row == null) {
			return 0;
		}

		return row.getPhysicalNumberOfCells();
	}

	// ========================================================
	// Cell Operations
	// ========================================================

	/**
	 * Gets cell value as String.
	 *
	 * @param sheetName    sheet name
	 * @param rowNumber    zero-based row
	 * @param columnNumber zero-based column
	 * @return cell value
	 */
	public String getCellValue(String sheetName, int rowNumber, int columnNumber) {

		Sheet sheet = getSheet(sheetName);

		Row row = sheet.getRow(rowNumber);

		if (row == null) {
			return "";
		}

		Cell cell = row.getCell(columnNumber);

		return getCellValue(cell);
	}

	/**
	 * Converts cell to String.
	 *
	 * @param cell Excel cell
	 * @return formatted value
	 */
	private String getCellValue(Cell cell) {

		if (cell == null) {
			return "";
		}

		return dataFormatter.formatCellValue(cell);
	}

	/**
	 * Writes value to cell.
	 *
	 * @param sheetName    sheet name
	 * @param rowNumber    row
	 * @param columnNumber column
	 * @param value        value
	 */
	public void setCellValue(String sheetName, int rowNumber, int columnNumber, String value) {

		Sheet sheet = getSheet(sheetName);

		Row row = sheet.getRow(rowNumber);

		if (row == null) {

			row = sheet.createRow(rowNumber);
		}

		Cell cell = row.getCell(columnNumber);

		if (cell == null) {

			cell = row.createCell(columnNumber);
		}

		cell.setCellValue(value);
	}

	/**
	 * Saves workbook to original file.
	 */
	public void save() {

		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {

			workbook.write(outputStream);

		} catch (IOException e) {

			throw new RuntimeException("Unable to save Excel file: " + filePath, e);
		}
	}

	// ========================================================
	// Search Operations
	// ========================================================

	/**
	 * Finds column index by header name.
	 *
	 * @param sheetName  sheet name
	 * @param headerName header
	 * @return column index, -1 if not found
	 */
	public int findColumn(String sheetName, String headerName) {

		Sheet sheet = getSheet(sheetName);

		Row headerRow = sheet.getRow(0);

		if (headerRow == null) {
			return -1;
		}

		for (Cell cell : headerRow) {

			String value = getCellValue(cell);

			if (value.equalsIgnoreCase(headerName)) {

				return cell.getColumnIndex();
			}
		}

		return -1;
	}

	/**
	 * Finds first row where a column contains the expected value.
	 *
	 * @param sheetName     sheet
	 * @param columnNumber  column
	 * @param expectedValue value
	 * @return row index, -1 if not found
	 */
	public int findRow(String sheetName, int columnNumber, String expectedValue) {

		Sheet sheet = getSheet(sheetName);

		for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

			Row row = sheet.getRow(rowIndex);

			if (row == null) {
				continue;
			}

			Cell cell = row.getCell(columnNumber);

			String actualValue = getCellValue(cell);

			if (actualValue.equalsIgnoreCase(expectedValue)) {

				return rowIndex;
			}
		}

		return -1;
	}

	// ========================================================
	// Data Operations
	// ========================================================

	/**
	 * Reads complete sheet into List of Maps.
	 *
	 * First row is treated as column headers.
	 *
	 * Example:
	 *
	 * username | password user1 | pass1
	 *
	 * becomes:
	 *
	 * [{username=user1, password=pass1}]
	 *
	 * @param sheetName sheet name
	 * @return list of row data
	 */
	public List<Map<String, String>> getSheetData(String sheetName) {

		Sheet sheet = getSheet(sheetName);

		List<Map<String, String>> data = new ArrayList<>();

		Row headerRow = sheet.getRow(0);

		if (headerRow == null) {
			return data;
		}

		List<String> headers = new ArrayList<>();

		for (int column = 0; column < headerRow.getLastCellNum(); column++) {

			headers.add(getCellValue(headerRow.getCell(column)));
		}

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

			Row row = sheet.getRow(rowIndex);

			if (row == null) {
				continue;
			}

			Map<String, String> rowData = new LinkedHashMap<>();

			for (int column = 0; column < headers.size(); column++) {

				rowData.put(headers.get(column), getCellValue(row.getCell(column)));
			}

			data.add(rowData);
		}

		return data;
	}

	/**
	 * Closes workbook.
	 */
	@Override
	public void close() {

		try {

			workbook.close();

		} catch (IOException e) {

			throw new RuntimeException("Unable to close Excel workbook.", e);
		}
	}
}