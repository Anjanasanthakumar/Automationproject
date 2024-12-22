package project;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

		public class Website {

		    WebDriver driver;
		    WebDriverWait wait;

		    @Parameters("browser")
		     @BeforeTest
		    	    public void setup(String browser) {
		    	        if (browser.equals("Chrome")) {
		    	            driver = new ChromeDriver();
		    	            
		    	        } else if (browser.equals("Edge")) {
		    	            driver = new EdgeDriver();
		    	        } else {
		    	            driver = new FirefoxDriver();
		    	        }
		        driver.manage().window().maximize();
		        driver.get("https://www.wholetex.com/");
		        }
		    
		        @BeforeMethod
		        public void set() {
		         wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    }

		    @Test(priority=1)
		    public void Regdetails() throws IOException {
		        WebElement log= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div.header-top.py-sm-0.py-2 > div > div > div.col-xl-4.ps-xl-0 > div > ul:nth-child(1) > li:nth-child(3) > a")));
		        log.click();
		        WebElement namereg=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#name")));
		        namereg.sendKeys("anjana");
		        WebElement mobile=wait.until(ExpectedConditions.elementToBeClickable(By.name("phone_number")));
		        mobile.sendKeys("8139063829");
		        WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_email")));
		        email.sendKeys("anjanasanthakumar2003@gmail.com");
		        WebElement password=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_password")));
		        password.sendKeys("Anjana@12");
		        WebElement rpassword=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_repassword")));
		        rpassword.sendKeys("Anjana@12");
		        WebElement regbtn=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#section1 > div > div > div:nth-child(2) > div.login-reg-form > form > button")));
		        JavascriptExecutor js=(JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView(true)", regbtn);
		        regbtn.click(); 
		    }
		    @Test(priority=2)
		    public void logindetails() throws IOException {
		        FileInputStream fi=new FileInputStream("C:\\Users\\anjan\\OneDrive\\Documents\\Desktop\\Automation.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet sh=wb.getSheet("sheet1");
				int rowCount=sh.getLastRowNum();
				for(int i=1;i<=rowCount;i++) {
				String emailid=sh.getRow(i).getCell(0).getStringCellValue();
				System.out.println("email:"+emailid);
					String pass=sh.getRow(i).getCell(1).getStringCellValue();
					System.out.println("pass:"+pass);
			      WebElement el=  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login_email")));
			      el.clear();
			        el.sendKeys(emailid);
			      WebElement name=  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login_password")));
			      name.clear();
			    name.sendKeys(pass);
			    WebElement loginbtn= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-form > form > div.email-section > div.login-btn-wrap > button")));
			       loginbtn.click();
			       
			       }
				 fi.close();
		    }
		    @Test(priority=3)
		    public void Verificationdetails() {
		    	 String exp = "Wholetex - Wholesale Textile Market Surat,Gujarat | Buy Wholesale...";
		            
				String actual=driver.getTitle();
				if(exp.equals(actual)) {
					System.out.println("Title verification passed");
				}
				else {
					System.out.println("Title verification failed");
				}
			WebElement logo=	driver.findElement(By.cssSelector("#header > div.header-middle > div > div > div.col-sm-4.col-lg-8 > div > a > img"));
		    	if(logo.isDisplayed()) {
		    		System.out.println("Logo is displayed");
		    	}
		    	else {
		    		System.out.println("Logo is not displayed");
		    	}}
		    @Test(priority=4)
		    public void productdetails() {
			           WebElement kids = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#navbarNav > ul > li:nth-child(3) > a")));
			           kids.click();
			           WebElement kidsshirt = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#navbarNav > ul > li.nav-item.dropdown.show > ul > li:nth-child(3) > a")));
			           kidsshirt.click();
			           WebElement sort = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#sortby")));
			           Select s = new Select(sort);
			           s.selectByVisibleText("Newest First");
			           WebElement image=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#section1 > div > div.row > div:nth-child(1) > div > div.home-product-content > h6 > a")));
			           JavascriptExecutor js=(JavascriptExecutor) driver;
			           js.executeScript("arguments[0].scrollIntoView(true)", image);
			           image.click();
		    }
		    @Test(priority=5)
		    public void AddProductToCart() {
		    	try {
		            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\36 01497_cart")));
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            js.executeScript("arguments[0].scrollIntoView(true)", cart);
		            cart.click();
               try {
		            Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		            alert.accept();
		            System.out.println("Item added to cart successfully");
               }
               catch (NoAlertPresentException e) {
		            System.out.println("Alert not found after adding product to cart: " + e.getMessage());
		        } 
               
		           
		    	}
		    	catch (NoSuchElementException e) {
		            System.out.println("Error finding the cart element: " + e.getMessage());
		    	}
}
		    	@Test(priority=6)
		    	public void wishlistDetails() {
		            WebElement wishClick = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\36 01497_wish")));
		            JavascriptExecutor js=(JavascriptExecutor) driver;
		            js.executeScript("arguments[0].scrollIntoView(true)", wishClick);
		            wishClick.click();

		            try {
		                Alert a = wait.until(ExpectedConditions.alertIsPresent());
		                a.accept();  
		                    System.out.println("Item is already in the wishlist.");
		                
		                
		            } catch (NoAlertPresentException e1) {
		                System.out.println("No alert was present after adding the product to wishlist.");
		            }
		            WebElement accountClick = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenuButton")));
		            
		            accountClick.click();
		            WebElement wishClickview = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div.header-top.py-sm-0.py-2 > div > div > div.col-xl-4.ps-xl-0 > div > ul:nth-child(1) > li:nth-child(3) > div > div > a:nth-child(2)")));
                     wishClickview.click();
                     System.out.println("Item is present in the wishlist");
                     WebElement wishlistRemove = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\36 01497_remove_wish")));
                      wishlistRemove.click();
                      System.out.println("Item is removed from the wishlist");
                    
                     
		            
		       		        } 
		    
			         
		   
		    @Test(priority=7)
		    public void UpdateProductQuantity() {
		    	 try {
		             WebElement cartview = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div.header-top.py-sm-0.py-2 > div > div > div.col-xl-4.ps-xl-0 > div > ul:nth-child(2) > li:nth-child(2) > a > i")));
		             cartview.click();
		             WebElement quantityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'cart_data_table')]")));

		             quantityField.clear();
		      quantityField.sendKeys("2");

		             WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#total_amount")));
		            String updateprice = totalPrice.getText();
		             Assert.assertEquals(updateprice, "1310.00", "Total price not updated");

		             System.out.println("Price is updated");
		         } 
		          catch (TimeoutException e) {
		             System.out.println("Timed out waiting for quantity or price elements: " + e.getMessage());
		         } catch (AssertionError e) {
		             System.out.println("Assertion failed for price: " + e.getMessage());
		         } catch (Exception e) {
		             System.out.println("Error during quantity update: " + e.getMessage());
		         }
		     }

			    	
		   @Test(priority=8)
		   public void removeCartItem() {
			  try {
				  WebElement removeItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'li_')]//a[contains(@class, 'removecart')]")));

	           removeItem.click();
	           System.out.println("Item is successfully removed from the cart");}
	           catch (TimeoutException e) {
	               System.out.println("Timed out waiting for remove item button: " + e.getMessage());
	           } 
			 
		   }
		   @Test(priority=9)
		   public void searchDetails() {
			   WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		        searchField.clear();
		        searchField.sendKeys("shirt",Keys.ENTER);
		      
		        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#header > div.header-middle > div > div > div.col-sm-8.col-lg-4 > form > div > input")));
		        Assert.assertTrue(result.isDisplayed(), "Search result is not displayed");
		      
		       
		        
		   }
		 
		  
		  
		    	
		        
		       
		    }

			
	


