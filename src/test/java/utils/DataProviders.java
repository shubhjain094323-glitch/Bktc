package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")

	public String[][] getData() {

		// String path = System.getProperty("user.dir") + "\\test-Data\\LoginData.xlsx";

		String path = "./test-Data/LoginData.xlsx";

		ExcelUtility xlutility = new ExcelUtility(path, "Sheet1");

		int rowscount = xlutility.getRowCount();
		int columncount = xlutility.getColumnCount();

		String logindata[][] = new String[rowscount - 1][columncount];

		System.out.println("Row Count = " + xlutility.getRowCount());
		System.out.println("Column Count = " + xlutility.getColumnCount());

		for (int r = 1; r < rowscount; r++) {
			for (int c = 0; c < columncount; c++) {

				logindata[r - 1][c] = xlutility.getCellData(r, c);
			}

			System.out.println(logindata[r - 1][0] + " | " + logindata[r - 1][1] + " | " + logindata[r - 1][2] + " | "
					+ logindata[r - 1][3]);

		}

		xlutility.closeWorkbook();
		return logindata;

	}

}
