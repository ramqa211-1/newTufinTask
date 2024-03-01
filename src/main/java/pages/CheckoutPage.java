package pages;
import base.BrowserDriverFactory;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import locators.SauceDemoSiteLocators;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

import static pages.BasePageObjects.getText;

public class CheckoutPage {
    static SauceDemoSiteLocators sauceDemoSiteLocators = new SauceDemoSiteLocators();

    public static void calculateItemPricesFromCheckoutPage() {
        List<WebElement> itemPrices = BrowserDriverFactory.getDriver().findElements(SauceDemoSiteLocators.itemPriceFromCheckoutPage);
        double totalSum = 0;
        double taxRate = 0.08;
        for (WebElement itemPrice : itemPrices) {
            String priceText = itemPrice.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            totalSum += price;
        }
        double tax = totalSum * taxRate;
        tax = Double.parseDouble(String.format("%.2f", tax));
        System.out.println("the Tax rate is " + tax + "");
        ScenarioDataStore.put("totalSum", totalSum);
        ScenarioDataStore.put("tax", tax);
    }

    public static void verifyTaxRate() {
        double totalSum = (double) ScenarioDataStore.get("totalSum");
        double expectedTaxApplyOnTotalSumItems = totalSum * 0.08;
        String taxRateNumberFromCheckout = getText(sauceDemoSiteLocators.taxRatePriceFromCheckout);
        // Replace the dollar sign and any other non-numeric characters
        String taxRateNumberOnlyFromCheckout = taxRateNumberFromCheckout.replaceAll("[^\\d.]", "");
        double actualTaxRateFromCheckout = Double.parseDouble(taxRateNumberOnlyFromCheckout);
        expectedTaxApplyOnTotalSumItems = Double.parseDouble(String.format("%.2f", expectedTaxApplyOnTotalSumItems));  // Format expectedTaxApplyOnTotalSumItems to two decimal places
        Assert.assertEquals(actualTaxRateFromCheckout, expectedTaxApplyOnTotalSumItems, "Tax calculation is correct!");
    }
}