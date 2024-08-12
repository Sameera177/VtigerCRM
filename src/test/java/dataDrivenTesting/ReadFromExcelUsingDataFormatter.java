package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcelUsingDataFormatter {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
//		DataFormatter df = new DataFormatter();
//		
//		String data = df.formatCellValue(wb.getSheet("Sheet1").getRow(2).getCell(1));
		System.out.println(wb.getSheet("Sheet1").getRow(2).getCell(1).getNumericCellValue());
		
		wb.close();
	}

}