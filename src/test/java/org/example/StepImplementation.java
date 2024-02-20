package org.example;

import com.thoughtworks.gauge.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.SacueDemoLocators;
import main.BrowserDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static BasePageObjects.PageObjects.click;
import static BasePageObjects.PageObjects.typeText;
import static locators.SacueDemoLocators.*;

public class StepImplementation {

    public static WebDriver driver;

    //loctors



    @Step("Open the website <url>")
    public void openSauceDemoWebsite(String url) {
        driver = BrowserDriverFactory.getDriver();
        driver.get(url);
    }

    @Step("Log into the page with user <username> and password <password>")
    public void loginToSauceDemo(String username, String password) {
        click(userNameField);
        typeText(userNameField,"standard_user");
        click(passwordField);
        typeText(passwordField,"secret_sauce");
        click(loginButton);
    }

    @Step("Add the bike light to the cart")
    public void addProductsToCart() {
        // Implementation to add specified products to the shopping cart
    }

    @Step("Go to the shopping cart")
    public void goToShoppingCart() {
        // Implementation to navigate to the shopping cart
    }

    @Step("Click the Checkout button")
    public void clickCheckoutButton() {
        // Implementation to click the Checkout button
    }

    @Step("Fill in details with first name <name>, last name <address>, and zip code <zip>")
    public void fillInDetails(String name, String address, String zip) {
        // Implementation to fill in user details
    }

    @Step("Click the Continue button")
    public void clickContinueButton() {
        // Implementation to click the Continue button
    }

    @Step("Validate the total sum with 8% tax is correct")
    public void validateTotalSumWithTax() {
        // Implementation to calculate and validate the total sum with tax
    }

    @Step("Click the Finish button")
    public void clickFinishButton() {
        // Implementation to click the Finish button
    }

    @Step("Validate the Success message is displayed")
    public void validateSuccessMessage() {
        // Implementation to validate the success message
    }



    @Step("Close the browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
