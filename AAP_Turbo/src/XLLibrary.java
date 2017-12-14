import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.*;

public class XLLibrary {
		
	//openSourceKeywords obj = new openSourceKeywords();
	// Method to read XL
	// Purpose: This method reads an excel based on the file path and sheet name provided
	public static String[][] readXL(String fPath, String fSheet) throws Exception {
		// Inputs : XL Path and XL Sheet name
		// Output : 
		String[][] xData;  
		int xRows, xCols;
		String  cellValue;
		
		File myxl = new File(fPath);                                
		FileInputStream myStream = new FileInputStream(myxl);                                
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);                                
		HSSFSheet mySheet = myWB.getSheet(fSheet);                                 
		xRows = mySheet.getLastRowNum()+1;                                
		xCols = mySheet.getRow(0).getLastCellNum();   
		xData = new String[xRows][xCols];   

		for (int i = 0; i < xRows; i++) {  
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < xCols; j++) { 
				//cellValue = "-";
				cellValue = cellToString(row.getCell(j));
				if (cellValue!=null) {
					xData[i][j] = cellValue; 
					//System.out.print(cellValue);
					//System.out.print("~~~~~~~~");
					//obj.searchGoogle();
				}  
				//System.out.println("");
			}        
		} 
		//obj.searchGoogle();
		myxl = null; // Memory gets released
		myWB.close();
		return xData;
	}

	//Change cell type
			public static String cellToString(HSSFCell cell) { 
				// This function will convert an object of type excel cell to a string value
				int type = cell.getCellType();                        
				Object result;                        
				switch (type) {                            
				case HSSFCell.CELL_TYPE_NUMERIC: //0                                
					result = cell.getNumericCellValue();                                
					break;                            
				case HSSFCell.CELL_TYPE_STRING: //1                                
					result = cell.getStringCellValue();                                
					break;                            
				case HSSFCell.CELL_TYPE_FORMULA: //2                                
					throw new RuntimeException("We can't evaluate formulas in Java");  
				case HSSFCell.CELL_TYPE_BLANK: //3                                
					result = "%";                                
					break;                            
				case HSSFCell.CELL_TYPE_BOOLEAN: //4     
					result = cell.getBooleanCellValue();       
					break;                            
				case HSSFCell.CELL_TYPE_ERROR: //5       
					throw new RuntimeException ("This cell has an error");    
				default:                  
					throw new RuntimeException("We don't support this cell type: " + type); 
				}                        
				return result.toString();      
			}


	public static void writeXLSheets(String sPath, String iSheet,int sheetIndex, String[][] xData)
			throws Exception
	{
		HSSFWorkbook  wb,newWB;
		HSSFSheet osheet;
		File outFile = new File(sPath);
		if ((outFile.isFile() == true)&&(outFile.exists()==true))
		{
			final InputStream is = new FileInputStream(outFile);
			try {
					wb = new HSSFWorkbook(is);
					if((wb.getNumberOfSheets())== sheetIndex)
					wb.createSheet();
		
					if((wb.getSheetName(sheetIndex)).equalsIgnoreCase(iSheet))
					{
						wb.removeSheetAt(sheetIndex);
						osheet=wb.createSheet(iSheet);
						wb.setSheetOrder(iSheet, sheetIndex);
			
						int xR_TS = xData.length;
					       int xC_TS = xData[0].length;
					   	for (int myrow = 0; myrow < xR_TS; myrow++)
					   	{
						       HSSFRow row = osheet.createRow(myrow);
						       for (int mycol = 0; mycol < xC_TS; mycol++)
						       {
						       	HSSFCell cell = row.createCell(mycol);
						       	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						       	cell.setCellValue(xData[myrow][mycol]);
						      
						       }
						       FileOutputStream fOut = new FileOutputStream(outFile);
						       wb.write(fOut);
						       fOut.flush();
						       fOut.close();
					   	}//outerfor 
			
					}//end if sheet exists
					else 
					{
						osheet=wb.createSheet(iSheet);
						wb.setSheetOrder(iSheet, sheetIndex);
					int xR_TS = xData.length;
					    int xC_TS = xData[0].length;
					   	for (int myrow = 0; myrow < xR_TS; myrow++)
					   	{
					   		HSSFRow row = osheet.createRow(myrow);
					   		for (int mycol = 0; mycol < xC_TS; mycol++)
					   		{
						       	HSSFCell cell = row.createCell(mycol);
						       	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						       	cell.setCellValue(xData[myrow][mycol]);
				   		}
					   		if (wb.getSheetName(wb.getNumberOfSheets()-1).equalsIgnoreCase("Sheet1")) 
					   			wb.removeSheetAt(wb.getNumberOfSheets()-1);
							FileOutputStream fOut = new FileOutputStream(outFile);
						    wb.write(fOut);
						    fOut.flush();
						    fOut.close();
					   	} 
					}
				} 
			finally 
			{ 
				is.close();
			}
		}//end of first if file exists
		else if (outFile.isFile() == false)
		{
			newWB = new HSSFWorkbook();
			HSSFSheet newsheet = newWB.createSheet(iSheet);

			int xR_TS = xData.length;
			int xC_TS = xData[0].length;
		   	for (int myrow = 0; myrow < xR_TS; myrow++) 
		   	{
			       HSSFRow row = newsheet.createRow(myrow);
			       for (int mycol = 0; mycol < xC_TS; mycol++) 
			       {
				       	HSSFCell cell = row.createCell(mycol);
				       	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				       	cell.setCellValue(xData[myrow][mycol]);
				       /*	if(myrow==0)
					       	cell.setCellStyle(style);
					       	else
					       	cell.setCellStyle(style1);*/
			       }
			       if (newWB.getSheetName(newWB.getNumberOfSheets()-1).equalsIgnoreCase("Sheet1")) 
			    	   newWB.removeSheetAt(newWB.getNumberOfSheets()-1);
			       FileOutputStream fOut = new FileOutputStream(outFile);
			       newWB.write(fOut);
			       fOut.flush();
			       fOut.close();
		   	}
		}
	
	}
	
	
}
