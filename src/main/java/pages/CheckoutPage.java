package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


	    public class CheckoutPage {

	        private WebDriver driver;

	        // Locators
	        private By firstNameInput = By.id("first-name");
	        private By lastNameInput = By.id("last-name");
	        private By zipCodeInput = By.id("postal-code");
	        private By continueButton = By.id("continue");
	        private By itemTotal = By.className("summary_subtotal_label");
	        private By tax = By.className("summary_tax_label");
	        private By total = By.className("summary_total_label");
	        private By itemPriceElements = By.className("inventory_item_price"); // Assumed locator

	        public CheckoutPage(WebDriver driver) {
	            this.driver = driver;
	        }

	        // Fill checkout form
	        public void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
	            driver.findElement(firstNameInput).sendKeys(firstName);
	            driver.findElement(lastNameInput).sendKeys(lastName);
	            driver.findElement(zipCodeInput).sendKeys(zipCode);
	            driver.findElement(continueButton).click();
	        }

	        // Returns Item Total as string 
	        public String getItemTotal() {
	            return driver.findElement(itemTotal).getText();
	        }

	        // Returns Tax as string 
	        public String getTax() {
	            return driver.findElement(tax).getText();
	        }

	        // Returns Total as string 
	        public String getTotal() {
	            return driver.findElement(total).getText();
	        }

	       

	        // Get list of item prices (as double values)
	        public List<Double> getItemPrices() {
	            List<WebElement> priceElements = driver.findElements(itemPriceElements);
	            List<Double> prices = new ArrayList<>();

	            for (WebElement element : priceElements) {
	                String priceText = element.getText().replace("$", "").trim();
	                prices.add(Double.parseDouble(priceText));
	            }
	            return prices;
	        }

	        // Get numeric value of Item Total
	        public double getItemTotalValue() {
	            String itemTotalText = driver.findElement(itemTotal).getText();
	            return extractPrice(itemTotalText);
	        }

	        // Get numeric value of Tax
	        public double getTaxValue() {
	            String taxText = driver.findElement(tax).getText();
	            return extractPrice(taxText);
	        }

	        // Get numeric value of Total
	        public double getTotalValue() {
	            String totalText = driver.findElement(total).getText();
	            return extractPrice(totalText);
	        }

	        // Utility method to extract price value
	        private double extractPrice(String text) {
	            String price = text.replaceAll("[^\\d.]", ""); // Remove non-numeric characters except dot
	            return Double.parseDouble(price);
	        }
	    }

	    



