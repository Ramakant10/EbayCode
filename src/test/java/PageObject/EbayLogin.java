package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EbayLogin {
	
	WebDriver driver;
	
	@FindBy(id="com.ebay.mobile:id/button_sign_in")
	public WebElement loginButton;

	@FindBy(id="com.ebay.mobile:id/search_box")
	public WebElement searchBox;
	
	@FindBy(id="com.ebay.mobile:id/search_src_text")
	public WebElement searchText;
	
	@FindBy(xpath="//android.widget.TextView[@text='Samsung']")
	public WebElement brandName;
	
	@FindBy(xpath="//android.widget.ListView//child::android.widget.RelativeLayout[1]//child::android.widget.TextView[1]")
	public WebElement searchList;
	
	@FindBy(xpath="//androidx.recyclerview.widget.RecyclerView//child::android.widget.RelativeLayout[1]//child::android.view.ViewGroup[1]")
	public WebElement clickTV;
	
	@FindBy(xpath="//android.widget.LinearLayout[2]//child::android.widget.LinearLayout[1]//child::android.widget.TextView[1]")
	public WebElement TVDescription;
	
	@FindBy(xpath="//android.widget.RelativeLayout//child::android.widget.LinearLayout[1]//child::android.widget.TextView[1]")
	public WebElement TVPrice;
	
	@FindBy(xpath="//android.widget.Button[@text='Add to cart']")
	public WebElement addToCart;
	
	@FindBy(xpath="//android.widget.Button[@text='Add to cart']")
	public List<WebElement> addToCartList;
	
	@FindBy(xpath="//android.widget.Button[@text='Go to Cart']")
	public WebElement goToCart;
	
	@FindBy(id="com.ebay.mobile:id/item_title")
	public WebElement cartTVDescription;
	
	@FindBy(id="com.ebay.mobile:id/item_price")
	public WebElement cartTVPrice;
	
	@FindBy(id="com.ebay.mobile:id/item_action_remove_from_cart")
	public WebElement remove;
	 
	@FindBy(id="com.ebay.mobile:id/cell_collection_item")
	public List<WebElement> randomTV;
	
	@FindBy(id="com.ebay.mobile:id/shopping_cart_checkout")
	public WebElement goToCheckOut;
	
	@FindBy(id="com.ebay.mobile:id/home")
	public WebElement goToHome;
	
	@FindBy(id="android:id/button1")
	public List<WebElement> errorPopup;
	
	
	
	
	
	

}
