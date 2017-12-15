
public class Turbo implements Runnable {

	private String browserName;
	private String searchText;
	
	static openSourceKeywords obj = new openSourceKeywords();
	
	//searchGoogle(String browserName, String searchText)
	
	public Turbo()
	{
		this.browserName = "Chrome";
		this.searchText = "Sample";
	}
	
	public Turbo(String browserName, String searchText)
	{
		this.browserName = browserName;
		this.searchText = searchText;	
	}
	
	public void run()
	{
		try {
			obj.searchGoogle(browserName, searchText);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	public static void main(String args[])
	{
		Turbo t;
		for (int i = 0; i< 10; i++)
		{
			t= new Turbo("Chrome", "Selenium");
			t.run();
		}
		
	}
	*/
	
}
