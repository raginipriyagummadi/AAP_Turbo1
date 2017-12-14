import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BuiltInKeywords {
	
	 public WebDriver driver;

	/// **********************Reusable function library*****************\\\
    public void openBrowser(String browserType)  throws InterruptedException{
            // Purpose: Open a Browser, Timeout
            // I/P : which Browser
            // o/p : N/A
            /*
             * Updated By:Aparna Date: 11/16/2016 Purpose:updated the .exe filepaths
             */
            
          /*  int browserTimeout = 0;
            String strTimeout = new UICode().returnValues("BrowserTimeOut");
            
           
            if (strTimeout.length() > 0) {
                    browserTimeout = Integer.valueOf(strTimeout);
            }else {
                    browserTimeout = 10;
            }*/
            
            try {
                    switch (browserType) {
                    case "Firefox":
                            if (System.getProperty("os.name").contains("Windows")) {
                            	 System.out.println("Entered Firefox browser");
                                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\employee5\\Desktop\\Selenium\\Browsers\\geckodriver.exe");
                                    //Thread.sleep(10000);
                            }
                            /*else {
                                    System.setProperty("webdriver.gecko.driver", Utilities.fileAbsolutePath() + "Browsers/Mac/geckodriver");
                            }*/
                            driver = new FirefoxDriver();
                            break;
                            
                    case "Chrome":                                   
                            if (System.getProperty("os.name").contains("Windows")) {
                            	 System.out.println("Entered Chrome browser");
                            	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\employee5\\Desktop\\Selenium\\Browsers\\chromedriver.exe");
                            }
                            /*else {
                                System.setProperty("webdriver.chrome.driver", Utilities.fileAbsolutePath() + "Browsers/Mac/chromedriver");
                        }*/
                            driver = new ChromeDriver();
                            System.out.println(driver);
                            break;
                  /*  case "IE":
                            System.setProperty("webdriver.ie.driver", Utilities.fileAbsolutePath() + "Browsers/Win/IEDriverServer.exe");
                            driver = new InternetExplorerDriver();
                            break;
                    case "Safari":
                            driver = new SafariDriver();
                            break;
                    case "PhantomJS":
                               System.setProperty("phantomjs.binary.path", Utilities.fileAbsolutePath() + "Browsers/Win/phantomjs.exe");  
                               driver = new PhantomJSDriver();
                            break;
                            */
                    default:
                            driver = new ChromeDriver();
    
                    }
            }catch(Exception ex) {
                    System.out.println("Error occured while initializing the browser drivers " + ex);
            }
            driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(browserTimeout, TimeUnit.SECONDS);
            Thread.sleep(1000);

    }
    
    public void navigateBrowser(String URL) {
        // Purpose: Navigates a browser
        // IP: URL
        // OP: N/A
       // driver.navigate().to(URL);
    	driver.get(URL);
	}
	public void typeText(String EleType,String EleLocator, String text) {
        // Purpose: Type a text into edit field
        // IP: Xpath of the element and the text to enter
        // OP: N/A
        driver.findElement(getByType(EleType,EleLocator)).clear();
        driver.findElement(getByType(EleType,EleLocator)).sendKeys(text);
	}
	
	public String verifyText(String EleType,String EleLocator, String text) {
        // Purpose: Verifies if a text is present
        // IP: xpath of the element and the text to verify
        // OP: true or false
        if (text.equalsIgnoreCase(driver.findElement(getByType(EleType,EleLocator)).getText())) {
                return "Pass";
        } else {
                return "Fail";
        }
}

	
	public void closeBrowser() {
        // Purpose: Closes All Browser related to teh Application .
        // IP: If there are Mutiple browser opened related to the application run by selenium 
        // OP: closes all the Browser related to the application run by selenium 

        driver.quit();
}
	
	public By getByType(String ElementBy,String ElementLocator)
	{   /*Purpose: Get the elementType (id, className,cssSelector, name, Xpath, linkText and partial linkText )
		 * I/P : ElementType and ElementLocator
		 * o/p : N/A
		 * Created By:Aparna 
		 * Date: 03/14/2016 
		 */
		By byType;
			switch (ElementBy) {
			case "id":
				byType = By.id(ElementLocator);
				break;
			case "className":
				byType = By.className(ElementLocator);
				break;
			case "cssSelector":
				byType = By.cssSelector(ElementLocator);
				break;
			case "name":
				byType = By.name(ElementLocator);
				break;
			case "linkText":
				byType = By.linkText(ElementLocator);
				break;
			case "xpath":
				byType = By.xpath(ElementLocator);
				break;
			case "PartialLinkText":
				byType = By.partialLinkText(ElementLocator);
				break;
			case "tagname":
				byType = By.tagName(ElementLocator);
				break;
				default:
					byType = By.xpath(ElementLocator);
			}
		return byType;
	}

}
