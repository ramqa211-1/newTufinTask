package sauceDemoSiteStep;
import com.thoughtworks.gauge.Step;
import base.BrowserDriverFactory;
import org.testng.Assert;
import pages.ConstantPage;
import static pages.BasePageObjects.*;
import static locators.SauceDemoSiteLocators.*;
import static pages.CheckoutPage.calculateItemPricesFromCheckoutPage;
import static pages.CheckoutPage.verifyTaxRate;

public class StepImplementation {
    ConstantPage constantPage = new ConstantPage();

    @Step("Open the website <url>")
    public void openSauceDemoWebsite(String url) {
        openUrl(url);
    }

    @Step("Log into the page with user and password")
    public void loginToSauceDemo() {
        click(userNameField);
        typeText(userNameField, constantPage.userNameField);
        click(passwordField);
        typeText(passwordField, constantPage.passwordField);
        click(loginButton);
    }

    @Step("Add the bike light And onesie items to the cart")
    public void addProductsToCart() {
        click(addToCartBikeLightButton);
        click(addToCartOnesieButton);
    }

    @Step("Go to the shopping cart")
    public void goToShoppingCart() {
        click(cartButton);
    }

    @Step("Click the Checkout button")
    public void clickOnCheckoutButton() {
        click(checkoutButton);
    }

    @Step("Fill in details with first name <name>, last name <address>, and zip code <zip>")
    public void fillInDetailsOnCheckOutPage(String name, String address, String zip) {
        click(firstNameField);
        typeText(firstNameField, name);
        click(lastNameField);
        typeText(lastNameField, address);
        click(postalCodeField);
        typeText(postalCodeField, zip);
    }

    @Step("Click on the Continue button")
    public void clickOnContinueButton() {
        click(continueButton);
    }

    @Step("Validate the total items sum with 8% tax is correct")
    public void validateTotalItemsSumWithCorrectTaxRate() {
        calculateItemPricesFromCheckoutPage();
        verifyTaxRate();
    }

    @Step("Click on the Finish button")
    public void clickOnFinishButton() {
        click(finishButton);
    }

    @Step("Check the Success message is displayed")
    public void CheckSuccessOrderCompleteMessageAppears() {
        String completeOrderHeaderText = getText(completeOrderHeader);
        Assert.assertTrue(completeOrderHeaderText.contains("Thank you for your order!"));
    }

    @Step("Close the browser")
    public static void closeBrowser() {
        BrowserDriverFactory.closeBrowser();
    }
}