package locators;
import org.openqa.selenium.By;

public class SauceDemoSiteLocators {

    //login page
    public static By userNameField = By.id("user-name");
    public static By passwordField = By.id("password");
    public static By loginButton = By.id("login-button");

    //menu section
    public static By addToCartBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    public static By addToCartOnesieButton = By.id("add-to-cart-sauce-labs-onesie");
    public static By cartButton = By.id("shopping_cart_container");

    //cart page
    public static By checkoutButton = By.id("checkout");

    //before checkout page
    public static By firstNameField = By.id("first-name");
    public static By lastNameField = By.id("last-name");
    public static By postalCodeField = By.id("postal-code");
    public static By continueButton = By.id("continue");

    //checkout page
    public static By itemPriceFromCheckoutPage = By.className("inventory_item_price");
    public static By finishButton = By.id("finish");
    public static By completeOrderHeader = By.className("complete-header");
    public static By taxRatePriceFromCheckout = By.className("summary_tax_label");
}