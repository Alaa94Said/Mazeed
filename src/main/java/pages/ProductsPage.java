package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

	
	    private WebDriver driver;
	    
	    private By firstItemAddButton = By.id("add-to-cart-sauce-labs-backpack");
	    private By secondItemAddButton = By.id("add-to-cart-sauce-labs-bike-light");
	    private By cartIcon = By.className("shopping_cart_link");

	    public ProductsPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void addFirstTwoItemsToCart() {
	        driver.findElement(firstItemAddButton).click();
	        driver.findElement(secondItemAddButton).click();
	    }

	    public void goToCart() {
	        driver.findElement(cartIcon).click();
	    }
	}


