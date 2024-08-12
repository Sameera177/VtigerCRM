package genericUtillities;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;

	public class ExcelUtility {
		private Workbook wb;
		DataFormatter df;
		
		
		public void excelInit(String excelpath) {
			FileInputStream fis =null;
			try {
				fis = new FileInputStream(excelpath);
				
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			try 
			{
				wb = WorkbookFactory.create(fis);	
			}
			catch(EncryptedDocumentException e)
			{
				e.printStackTrace();	
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			df = new DataFormatter();
		}
	     public String readFromExcel(String sheetName, int rowNum, int cellNum) {
	    	 return df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		
	     }
	     public Map<String, String> readFromExcel(String SheetName, String expectionTestName)
	     {
	    	 Sheet sheet = wb.getSheet(SheetName);
	    	 int requiredRowNum=0;
	    	 Map<String, String>map = new HashMap<String, String>();
	    	 for(int i = 0; i<= sheet.getLastRowNum(); i++)
	    	 {
	    		 requiredRowNum = i;
	    		 if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectionTestName))
	    			 break;
	    	 }
	    	 for(int i = requiredRowNum; i< sheet.getLastRowNum();i++)
	    	 {
	    		 requiredRowNum =i;
	    		 if(df.formatCellValue(sheet.getRow(i).getCell(1)).equals("####"))
	    			 break;
	    		 map.put(df.formatCellValue(sheet.getRow(i).getCell(2)), df.formatCellValue(sheet.getRow(i).getCell(3)));
	    	 }
	    	 return map;
	    	 
	     }
	     public void WriteToExcel(String SheetName, String expectedTestName, String status) {
	    	 Sheet sheet = wb.getSheet(SheetName);
	    	 for(int i = 0; i<= sheet.getLastRowNum(); i++)
	    	 {
	    		 if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestName))
	    		 {
	    			 sheet.getRow(i).createCell(4).setCellValue(status); 
	    		 }
	    	 } 
	     }
	     
	     public void saveExcel(String excelpath)
	     {
	    	 FileOutputStream fos = null;
	    	 try
	    	 {
	    		 fos = new FileOutputStream(excelpath);
	    	 }
	    	 catch(FileNotFoundException e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 try
	    	 {
	    		 wb.write(fos);
	    	 } 
	    	 catch (IOException e) 
	    	 {
				e.printStackTrace();
			 }
	     }
	     
	     public void closeExcel()
	     {
	    	 try 
	    	 {
	    		 wb.close();
	    	 }
	    	 catch(IOException e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	     }
	}

