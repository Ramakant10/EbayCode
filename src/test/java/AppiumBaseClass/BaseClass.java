package AppiumBaseClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import PageObject.EbayLogin;
import commonUtils.ReadEnvPropFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {

	public static AppiumDriver<WebElement> driver;
	public EbayLogin ebayLogin;

	/**
	 * Created by  : Ramakant Sharma
	 * Created Date: 01/09/2020
	 * Description: create driver for android 
	 */
	@BeforeTest
	public void initiateDriver() throws IOException{
		try{
			ReadEnvPropFile envValue = new ReadEnvPropFile();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, envValue.platformName());
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, envValue.platformVersion());
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			cap.setCapability(MobileCapabilityType.UDID, envValue.getUDID());
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, envValue.getDeviceName());
			cap.setCapability("appPackage", envValue.getAppPackageName());
			cap.setCapability("appActivity", envValue.getAppActivityName());
			cap.setCapability(MobileCapabilityType.NO_RESET, true);
			driver = new AppiumDriver<>(new URL(envValue.getAppiumURL()),cap);
		}catch(MalformedURLException e){
			e.getCause();
			e.printStackTrace();
		}

		ebayLogin = PageFactory.initElements(driver, EbayLogin.class);
	}

	/**
	 * Created by  : Ramakant Sharma
	 * Created Date: 01/09/2020
	 * Description: perform scroll down 
	 */
	public void scrollList(){
		Dimension size = driver.manage().window().getSize();
		int scrollstart = (int) (size.getHeight() * 0.5);
		int scrollend = (int) (size.getHeight() * 0.2);
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0,scrollstart))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(0,scrollend))
		.release().perform();
	}

	/**
	 * Created by  : Ramakant Sharma
	 * Created Date: 01/09/2020
	 * Description: perform wait for element
	 */
	public void waitElement(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	/**
	 * Created by  : Ramakant Sharma
	 * Created Date: 01/09/2020
	 * Description: perform scroll for add cart button -- work for landscape/portrait mode
	 */
	public WebElement addCart(){
		MobileElement elementCart = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.ebay.mobile:id/fragmentContainer\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.Button\"), \"Add to cart\")"));
		return elementCart;
	}

	/**
	 * Created by  : Ramakant Sharma
	 * Created Date: 01/09/2020
	 * Description: perform scroll for go check out button -- work for landscape/portrait mode
	 */
	public WebElement checkOut(){
		MobileElement elementCheckOut = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.ebay.mobile:id/shopping_cart_contents\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.Button\"), \"Go to checkout\")"));
		return elementCheckOut;
	}

}
