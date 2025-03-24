package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import Utilities.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class SwageLabTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SwageLabTest.class);

    @Test(priority = 1)
    public void loginFlowTest() {
        logger.info("Starting login flow test.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        logger.info("Logged in as standard_user.");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed!");
        logger.info("Login flow test passed.");
    }

    @Test(priority = 2)
    public void addToCartFlowTest() {
        logger.info("Starting add to cart flow test.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        logger.info("Logged in successfully.");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstTwoItemsToCart();
        logger.info("Added first two items to cart.");
        productsPage.goToCart();
        logger.info("Navigated to cart.");

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), "Cart page not opened!");
        logger.info("Add to cart flow test passed.");
    }

    @Test(priority = 3)
    public void receiptAmountAssertionTest() {
        logger.info("Starting receipt amount assertion test.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        logger.info("Logged in successfully.");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstTwoItemsToCart();
        logger.info("Added first two items to cart.");
        productsPage.goToCart();
        logger.info("Navigated to cart.");

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
        logger.info("Proceeded to checkout.");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInfo("Alaa", "Said", "12345");
        logger.info("Filled checkout information.");

        List<Double> itemPrices = checkoutPage.getItemPrices(); 
        double calculatedItemTotal = itemPrices.stream().mapToDouble(Double::doubleValue).sum();

        double displayedItemTotal = checkoutPage.getItemTotalValue(); // Returns item total as double
        double displayedTax = checkoutPage.getTaxValue(); // Returns tax as double
        double displayedTotal = checkoutPage.getTotalValue(); // Returns total as double

        logger.info("Calculated item prices sum: " + calculatedItemTotal);
        logger.info("Displayed item total: " + displayedItemTotal);
        logger.info("Displayed tax: " + displayedTax);
        logger.info("Displayed total: " + displayedTotal);

        // Assertions
        Assert.assertEquals(calculatedItemTotal, displayedItemTotal, 0.01, "Item total mismatch!");
        logger.info("Item total matches sum of individual items.");

        double expectedTotal = displayedItemTotal + displayedTax;
        Assert.assertEquals(displayedTotal, expectedTotal, 0.01, "Total amount mismatch!");
        logger.info("Total amount matches expected value (item total + tax).");

        logger.info("Receipt amount assertion test passed.");
    }
}
