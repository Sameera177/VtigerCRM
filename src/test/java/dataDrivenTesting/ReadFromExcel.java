package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// 1. Convert physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		// 2. Open workbook
		Workbook wb = WorkbookFactory.create(fis);
		
//		// 3. Get control to the sheet
//		Sheet sheet = wb.getSheet("Sheet1");
//		
//		// 4. Get the control to the row
//		Row row = sheet.getRow(1);
//		
//		// 5. Get the control to the cell
//		Cell cell = row.getCell(1);
//		
//		// 6. Fetch the value
//		System.out.println(cell.getStringCellValue());
		
		String data = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		System.out.println(data);
		
		// 7. Close the workbook
		wb.close();
	}

}