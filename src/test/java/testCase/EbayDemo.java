package testCase;

import java.io.IOException;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import AppiumBaseClass.BaseClass;
import commonUtils.ReadEnvPropFile;


/**
 * Created by  : Ramakant Sharma
 * Created Date: 01/09/2020
 * Description: verify the price and description of tv from cart
 */
public class EbayDemo extends BaseClass {
	String priceWithDecimal;
	String priceCartScreenValDecimal;
	String tvDescriptionSearchScr;
	String priceSearchScreen;
	String tvDescriptionCart;
	String priceCartScreen;
	@Test
	public void login() throws InterruptedException, IOException{
		try{
			ReadEnvPropFile envValue = new ReadEnvPropFile();
			waitElement(ebayLogin.searchBox);
			ebayLogin.searchBox.click();
			waitElement(ebayLogin.searchText);
			ebayLogin.searchText.sendKeys(envValue.getSearchData());
			waitElement(ebayLogin.searchList);
			ebayLogin.searchList.click();
			waitElement(ebayLogin.brandName);
			ebayLogin.brandName.click();
			waitElement(ebayLogin.clickTV);

			// Select random TV from list and perform scroll event 
			Random rnd = new Random();
			int rndInt = rnd.nextInt(ebayLogin.randomTV.size());
			if(rndInt==4){
				scrollList();
			}
			ebayLogin.randomTV.get(rndInt).click();

			// Get the TV description and amount on search screen
			waitElement(ebayLogin.TVDescription);
			tvDescriptionSearchScr =ebayLogin.TVDescription.getText();
			priceSearchScreen=ebayLogin.TVPrice.getText();
			String[] priceSearchScreenVal = priceSearchScreen.split("\\$");
			if(priceSearchScreenVal[1].contains(".")){
				priceWithDecimal = priceSearchScreenVal[1];
			}
			priceSearchScreenVal[1] = priceSearchScreenVal[1].substring(1);

			//Perform the aacroll ction on add to cart 
			addCart().click();
			ebayLogin.goToCart.click();
			waitElement(ebayLogin.cartTVDescription);

			//Perform the scroll action on the go to checkout
			checkOut().click();

			//ebayLogin.goToCheckOut.click();
			Thread.sleep(5000);
			if(ebayLogin.errorPopup.size()!=0){
				ebayLogin.errorPopup.get(0).click();
				ebayLogin.remove.click();
				Thread.sleep(2000);
			}else{

				// Get the TV description and amount on cart screen
				waitElement(ebayLogin.cartTVDescription);
				tvDescriptionCart = ebayLogin.cartTVDescription.getText();
				priceCartScreen = ebayLogin.cartTVPrice.getText();
				String[] priceCartScreenVal = priceCartScreen.split("\\$");
				if(priceWithDecimal!=null){
					priceCartScreenValDecimal = priceCartScreenVal[1];
				}else{
					String[] priceWithoutDecimal = priceCartScreenVal[1].split("\\.");
					priceCartScreenValDecimal= priceWithoutDecimal[0];
				}

				// Verify the amount and TV description screen vs cart screen
				if(priceSearchScreenVal[1].equalsIgnoreCase(priceCartScreenValDecimal) && tvDescriptionSearchScr.equalsIgnoreCase(tvDescriptionCart)){
					Assert.assertEquals(priceSearchScreenVal[1], priceCartScreenValDecimal, "price & Description for TV is verified successfully");
					Assert.assertTrue(true, "data verified succesfully as expected");
				}else{
					Assert.assertEquals(priceSearchScreenVal[1], priceCartScreenValDecimal, "price & description for TV is not verified as expected");
				}


				//remove the data from cart
				driver.navigate().back();
				waitElement(ebayLogin.remove);
				ebayLogin.remove.click();
				Thread.sleep(2000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// quite the driver after test case execution
	@AfterMethod(alwaysRun = true)
	public void method_tearDown(){
		driver.quit();
	}

}

