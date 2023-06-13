package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {

	// Create multiple DataProviders for multiple data sets

	@DataProvider(name = "data")
	public Object[][] dataProviderUtilityUsingHashMap() throws Exception {

		// Reading the Excel File String excelPath, String sheetName
		String excelPath = "";
		String sheetName = "";

		File file = new File(excelPath);

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		workbook.close();

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		// Data Array
		Object[][] dataSet = new Object[rowCount][1];
		// Reading Data from each Cell
		for (int row = 1; row <= rowCount; row++) {
			// Define map
			Map<Object, Object> dataMap = new HashMap<Object, Object>();
			for (int col = 0; col < colCount; col++) {
				dataMap.put(sheet.getRow(0).getCell(col).toString(), sheet.getRow(row).getCell(col).toString());
			}
			dataSet[row - 1][0] = dataMap;
		}
		return dataSet;
	}

	@DataProvider(name = "UserData")
	public Object[][] userDetails() {

		// Data Array
		Object[][] dataSet = new Object[1][1];

		// Define map
		HashMap<Object, Object> dataMap = new HashMap<Object, Object>();
		dataMap.put("id", 0);
		dataMap.put("username", "DummyName");
		dataMap.put("firstName", "Dummy");
		dataMap.put("lastName", "Name");
		dataMap.put("email", "dummy@gamil.com");
		dataMap.put("phone", "123456789");
		dataMap.put("password", "2ASD@1234");

		dataSet[0][0] = dataMap;
		return dataSet;
	}

	@DataProvider(name = "UserUpdateData")
	public Object[][] updateDetails() {
		
		// Data Array
		Object[][] dataSet = new Object[1][1];

		// Define map
		HashMap<Object, Object> dataMap = new HashMap<Object, Object>();
		dataMap.put("id", 0);
		dataMap.put("username", "DummyName");
		dataMap.put("firstName", "UpdatedFirstName");
		dataMap.put("lastName", "UpdatedLastName");
		dataMap.put("email", "updated_Email@gamil.com");
		dataMap.put("phone", "123456789");
		dataMap.put("password", "2ASD@1234");

		dataSet[0][0] = dataMap;
		return dataSet;
	}

}
