import java.util.ArrayList;

public class driverSequential2 {
	/* Purpose : 
	1. Read UTC and RS
2. Identify the Run to execute
	3. Go to each browser as chosen. 
	4. Execute all UTC for each browser 
	
	
	*/
	
	// Unique Test Cases and RunSettings
	static String[][] xUTC, xRunSettings;
	
	// Run Setting Parameters
	static int setTurboSpeed;
	static String[] setBrowser;
	
	// Array of threads to run in parallel ** TURBO **
	static ArrayList<Thread> threadListTC = new ArrayList<Thread>(50);
	
	// Inbuilt libraries
	static XLLibrary xlObject = new XLLibrary();
	static openSourceKeywords obj = new openSourceKeywords();
	
	static long startTime, endTime, totalTime;
	static int xUTC_Rows, xRunSettings_Rows;
	static long countUTC, countTotalUTC;
	
	// Selected Run - ENTERED BY THE USER.
	static String selectedRunID = "R17";

	
	public static void main(String[] args) throws Exception{
		startTime = System.currentTimeMillis();
		System.out.println("Hello and Welcome to AnyAUT!");
		// obj.searchGoogle("Chrome", "IteLearn");

		// Read UTC and RunSettings
		xUTC = XLLibrary.readXL("C:\\Users\\employee5\\eclipse-workspace\\AAP_Turbo\\src\\Files\\UTCFile2.xls", "UTCases");
		xRunSettings = XLLibrary.readXL("C:\\Users\\employee5\\eclipse-workspace\\AAP_Turbo\\src\\Files\\UTCFile2.xls", "RunSettings");

		xUTC_Rows = xUTC.length; //26
		//System.out.println("xUTC_Rows: "+xUTC_Rows);
		xRunSettings_Rows = xRunSettings.length;// 18
		//System.out.println("xRunSettings_Rows: "+xRunSettings_Rows);
		countUTC = 0;
		
		//1 - 18
		for (int i=1; i<xRunSettings_Rows ; i++){
			//System.out.println("~Run ID: " + xRunSettings[i][0]);
			if (xRunSettings[i][0].equalsIgnoreCase(selectedRunID)){ // r17 == r17
				System.out.println("Running: "+selectedRunID);
				// 1. Read the test settings
				//setTurboSpeed = (Integer.valueOf(xRunSettings[i][1])).intValue();
				setBrowser = xRunSettings[i][2].split(",");
				//System.out.println("setBrowser: "+setBrowser[0]);
				countTotalUTC = setBrowser.length*xUTC_Rows;
				System.out.println("~Total UTC's are : " + countTotalUTC);
				System.out.println("~~Now Running: " + selectedRunID);

				int j = 0;
				while(j < setBrowser.length){
					System.out.println("~~Executing for : " + setBrowser[j]);
					//for (int k=1;k<xUTC_Rows;k++){
					for (int k=1;k<4;k++){				// 1+1 = 2
						System.out.println("Executing : " + (countUTC+1) + " of " + countTotalUTC);
						
						// Replace with UTC call
						// executeUTCs();
						obj.searchGoogle(setBrowser[j], xUTC[k][6]);
															// 0/26 = 0
						System.out.println("Completed: " +(countUTC/countTotalUTC) + " %.");
						countUTC++;  
					}
					j++;
				}

			}
			

		}
		
		System.out.println("Execution Done!");
		
		endTime = System.currentTimeMillis();
		System.out.println("Time taken: "+((endTime- startTime)/1000 )+" seconds" );

		
		}
}

