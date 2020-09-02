package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by  : Ramakant Sharma
 * Created Date: 01/09/2020
 * Description: read data from properties file
 */
public class ReadEnvPropFile {
	Properties prop;
	FileInputStream input;
	public ReadEnvPropFile() throws IOException{
		input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\environment\\environmentData.properties");
		prop = new Properties();
		prop.load(input);
		
	}
	
	public String platformName(){
		String platformName = prop.getProperty("PLATFORM_NAME");
		return platformName;
	}
	
	public String platformVersion(){
		String platformVersion = prop.getProperty("PLATFORM_VERSION");
		return platformVersion;
	}
	
	public String getUDID(){
		String getUDID = prop.getProperty("UDID");
		return getUDID;
	}
	
	public String getDeviceName(){
		String getDeviceName = prop.getProperty("DEVICE_NAME");
		return getDeviceName;
	}
	
	public String getAppActivityName(){
		String getAppActivityName = prop.getProperty("APP_ACTIVITY");
		return getAppActivityName;
	}
	
	public String getAppPackageName(){
		String getAppPackageName = prop.getProperty("APP_PACKAGE");
		return getAppPackageName;
	}
	
	public String getAppiumURL(){
		String getAppiumURL = prop.getProperty("APPIUMURL");
		return getAppiumURL;
	}
	
	public String getSearchData(){
		String getSearchData = prop.getProperty("SEARCH_DATA");
		return getSearchData;
	}
	
}
