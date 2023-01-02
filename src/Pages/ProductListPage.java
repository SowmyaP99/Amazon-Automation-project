package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ProductListPage {
	 public String[] PrintFirst4Products_Name(WebDriver driver) {
	    	List<WebElement> list_of_dish = driver.findElements(By.xpath("//*[@class=\"a-size-medium a-color-base a-text-normal\"]"));
			System.out.println(list_of_dish);
			String arrPro_name[] = new String[list_of_dish.size()];
			int cnt = 0;
			for(WebElement rb : list_of_dish) {

				
				
				String strname = rb.getText();
				String strname1 = strname.substring(0,10);
				arrPro_name[cnt] = strname1;
				cnt++;
		}
			for (int indx = 0; indx < 4; indx++) {
				System.out.println(arrPro_name[indx]);
			}
	    
	    return arrPro_name;
	    
	    }
	    
	    
	    
	    public String[] PrintFirst4Products_Price(WebDriver driver) {
	    	
	    	List<WebElement> list_of_price = driver.findElements(By.xpath("//*[@class=\"a-price-whole\"]"));
			System.out.println(list_of_price.size());
			String arrPro_price[] = new String[list_of_price.size()];
			int cont=0;
			for(WebElement rb : list_of_price) {
				String strname = rb.getText();
				strname = strname.replace(",", "");
				arrPro_price[cont] = strname;
				cont++;
		}	System.out.println("Printing price ");
			for (int indx = 0; indx < 4; indx++) {
			System.out.println(arrPro_price[indx]);
		}
			
			return arrPro_price;	
	    	
	    }
	    
	    
	    public void ValidateListofProducts(WebDriver driver, String [] arrPro_name , String [] arrPro_price ) {
	        
//	    	String[] arrPro_name = PrintFirst4Products_Name(driver);
//	    	String[] arrPro_price =
	 
			
//			database connection
			String connstring = "jdbc:mysql://localhost:3306/Dishwash_Amazon";
			String strquery = "select * from dishwash_prods";
			
			System.out.println("....................");
			String arrPro_namefromDB[] = new String[4];
			String arrPro_pricefromDB[] = new String[4];
		
			try {
				Connection conn = DriverManager.getConnection(connstring,"root","root");
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(strquery);
//				System.out.println(rs.next());
				int x=0;
				while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				arrPro_namefromDB[x] = rs.getString(2);
				arrPro_pricefromDB[x] = rs.getString(3);
				x++;
				
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				System.out.println("printing arr_price list");
//				for (int indx = 0; indx < 4; indx++) {
//					if (arrPro_name[indx].equals(arrPro_namefromDB[indx])  &&  (arrPro_price[indx].equals(arrPro_pricefromDB[indx]))) {
//						System.out.println(arrPro_name[indx]+ " "+"product matches");
//						
//						
//					}else {
//						System.out.println(arrPro_name[indx]+" "+"product doesnt matches");
//					}
//				
//			}
				
				for (int indx = 0; indx < 4; indx++) {
					Boolean flag=false;
					for(int j=0; j<4; j++) {
						
						if (arrPro_name[indx].equals(arrPro_namefromDB[j])  &&  (arrPro_price[indx].equals(arrPro_pricefromDB[j])))
//						if(arrPro_name[indx].equals(arrPro_namefromDB[j]))
						{
//							System.out.println(arrPro_name[indx]+ " "+"product matches");
							flag=true;
							break;
						} 
//					System.out.println(arrPro_name[indx]+" "+"product doesnt matches");
						
						
					}
//					boolean flag = false;
					if (flag==true) {
						System.out.println(arrPro_name[indx]+ " "+"product matches");
//						ClickOnProduct(driver);
					}else {
						System.out.println(arrPro_name[indx]+" "+"product doesnt matches");
					}
				}
					
						
						
				
				
			
				
			System.out.println("everything  is working and fine");
			
			
	        
	        
	        
	    }
	    
	    
	    public void ClickOnProduct(WebDriver driver) {
//	    	Actions action = new Actions(driver);
//	    	ArrayList<String>Win_List   = new ArrayList<String>(driver.getWindowHandles());
//	    	action.keyDown(Keys.CONTROL).build().perform();
	    	driver.findElement(By.xpath("//*[text()=\"Voltas Beko 8 Place Settings Table Top Dishwasher (DT8S, Silver, Inbuilt Heater)\"]")).click();
//	    	action.keyUp(Keys.CONTROL).build().perform();
	    	ArrayList<String>Win_List1   = new ArrayList<String>(driver.getWindowHandles());
	    	driver.switchTo().window(Win_List1.get(0));
	    	driver.close();
	    	driver.switchTo().window(Win_List1.get(1));
	    	
	    	
	        
	    }

}
