public class openSourceKeywords {
	
	public void searchGoogle(String browserName, String searchText) throws InterruptedException
	{
		
		BuiltInKeywords obj = new BuiltInKeywords();
		obj.openBrowser(browserName);
		obj.navigateBrowser("https://www.google.com/");
		obj.typeText("name", "q",searchText);
		obj.verifyText("name","q",searchText);
		obj.closeBrowser();
		System.out.println("Done");
	}
		
}
