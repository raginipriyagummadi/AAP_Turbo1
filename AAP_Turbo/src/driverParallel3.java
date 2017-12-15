import java.util.ArrayList;

public class driverParallel3 {
// priya code
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
	
	// Selected Run
	static String selectedRunID = "R16";

	
	public static void main(String[] args) throws Exception{
		
		//Turbo turboThread = new Turbo();
		Thread turboThread;
		startTime = System.currentTimeMillis();
		System.out.println("Hello and Welcome to AnyAUT!");
		// obj.searchGoogle("Chrome", "IteLearn");

		// Read UTC and RunSettings
		//C:\\Users\\employee5\\eclipse-workspace\\AAP_Turbo\\src\\Files\\UTCFile2.xls
		xUTC = XLLibrary.readXL("C:\\Users\\employee5\\eclipse-workspace\\AAP_Turbo\\src\\Files\\UTCFile2.xls", "UTCases");
		xRunSettings = XLLibrary.readXL("C:\\Users\\employee5\\eclipse-workspace\\AAP_Turbo\\src\\Files\\UTCFile2.xls", "RunSettings");

		xUTC_Rows = xUTC.length;
		xRunSettings_Rows = xRunSettings.length;
		countUTC = 0;
		
		for (int i=1; i<xRunSettings_Rows ; i++)
		{
			//System.out.println("~Run ID: " + xRunSettings[i][0]);
			
			if (xRunSettings[i][0].equalsIgnoreCase(selectedRunID))
			{
				// 1. Read the test settings
				setTurboSpeed = (int)(Double.parseDouble(xRunSettings[i][1]));
				
				setBrowser = xRunSettings[i][2].split(",");
				countTotalUTC = setBrowser.length*xUTC_Rows;
				
				System.out.println("~Total UTC's are : " + countTotalUTC);
				System.out.println("~~Now Running: " + selectedRunID);
				
				int nLoop = (int)(Math.round(countTotalUTC/setTurboSpeed));
				int nWhileLoopChecker = 0;
				
				int j = 0;
				
				
				
				while(j < setBrowser.length)
				{	
					try 
					{
						int setBrowserIndex = j;
						System.out.println("~~Executing for : " + setBrowser[j]);
						
						for(int k = 0; k<countTotalUTC; k++)
						{
							//int UTCIndex =( int)countUTC;
							System.out.println("Creating Thread : " + (countUTC +1) + " of " + countTotalUTC);
							
							turboThread = new Thread(new Runnable() {
								public void run()
								{
									try 
									{
										obj.searchGoogle(setBrowser[setBrowserIndex],xUTC[(int)countUTC][6]);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							});
						//	turboThread = new Turbo(setBrowser[j],xUTC[(int) k][6]);
							threadListTC.add(turboThread);
								countUTC++;
						}
							
						countUTC -=countTotalUTC;
						//while (countUTC<=countTotalUTC)
						while(nWhileLoopChecker < nLoop)
						{	
							
						
							for(long k = nWhileLoopChecker; k<setTurboSpeed; k++)
							{
								System.out.println("Running : " + (countUTC +1) + " of " + countTotalUTC);
								
								
								threadListTC.get((int)countUTC).start();
								
								countUTC++;
							}
							
							countUTC -=setTurboSpeed;
							
							for(long k = nWhileLoopChecker; k<setTurboSpeed; k++)
							{
								System.out.println("Exiting : " + (countUTC +1) + " of " + countTotalUTC);
								
								
							//	turboThread = new Turbo(setBrowser[j],xUTC[(int) k][6]);
								threadListTC.get((int)countUTC).join();
								
								countUTC++;
							}
								//System.out.println("Completed: " +(countUTC/countTotalUTC) + " %.");
								System.out.println(countUTC);
								//countUTC -=setTurboSpeed;
								//nWhileLoopChecker++;
							}
						}catch(Exception e)
						{
							System.out.println(e);
							threadListTC.get(threadListTC.size()-1).join();
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
	
	
	
	/*
	
	public void turboRunner() throws InterruptedException
	{
		int nWhileLoopChecker = 0;
		int nLoops = Math.round(countTotalUTC/setTurboSpeed);
		try {
		while (countUTC<=countTotalUTC)
		{	
			for(int j = nWhileLoopChecker; j<setTurboSpeed; j++)
				{
				threadListTC.get(nCounter).start();
					nCounter++;
				}
				
				nCounter-=turboSpeed; 
				
				
				for(int j = nWhileLoopChecker; j<turboSpeed; j++)
				{
					threadListTC.get(nCounter).join();
					nCounter++; 
				}
			}
		}catch(Exception e)
		{
			threadListTC.get(threadListTC.size()-1).join();
		}
		
	}
	
	
	for (int k=1;k<3;k++){
						System.out.println("Executing : " + (countUTC+1) + " of " + countTotalUTC);
						
						// Replace with UTC call
						// executeUTCs();
						obj.searchGoogle(setBrowser[j], xUTC[k][6]);
						
						System.out.println("Completed: " +(countUTC/countTotalUTC) + " %.");
						countUTC++;
					}
	
	*/


