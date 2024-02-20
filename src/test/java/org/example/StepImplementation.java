package org.example;

import com.thoughtworks.gauge.Step;
import main.BrowserDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static BasePageObjects.PageObjects.*;
import static locators.SauceDemoLocators.*;

public class StepImplementation {

    public static WebDriver driver;




    @Step("Open the website <url>")
    public void openSauceDemoWebsite(String url) {
        driver = BrowserDriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Log into the page with user <username> and password <password>")
    public void loginToSauceDemo(String username, String password)  {
        click(userNameField);
        typeText(userNameField, "standard_user");
        click(passwordField);
        typeText(passwordField, "secret_sauce");
        click(loginButton);
    }

    @Step("Add the bike light And onesie items to the cart")
    public void addProductsToCart()  {
        click(addToCartBikeLightButton);
        click(addToCartOnesieButton);
    }

    @Step("Go to the shopping cart")
    public void goToShoppingCart()  {
        click(cartButton);
    }

    @Step("Click the Checkout button")
    public void clickCheckoutButton()  {
        click(checkoutButton);
    }

    @Step("Fill in details with first name <name>, last name <address>, and zip code <zip>")
    public void fillInDetailsOnCheckOutPage(String name, String address, String zip)  {
        click(firstNameField);
        typeText(firstNameField, "a");
        click(lastNameField);
        typeText(lastNameField, "a");
        click(postalCodeField);
        typeText(postalCodeField,"111");
    }

    @Step("Click the Continue button")
    public void clickContinueButton()  {
       click(continueButton);
    }

    @Step("Validate the total sum with 8% tax is correct")
    public void validateTotalSumWithTax() {
        calculateItemPricesFromCheckoutPage();
        verifyTaxRate();
    }

    @Step("Click the Finish button")
    public void clickFinishButton()  {
        click(finishButton);
    }

    @Step("Validate the Success message is displayed")
    public void validateSuccessMessage()  {
        String completeOrderHeaderText = getText(completeOrderHeader);
        Assert.assertTrue(completeOrderHeaderText.contains("Thank you for your order!"));

    }

    @Step("Close the browser")
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
