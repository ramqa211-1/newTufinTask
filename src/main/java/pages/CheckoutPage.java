package pages;
import base.BrowserDriverFactory;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import locators.SauceDemoSiteLocators;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class CheckoutPage {

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
        double tax = (double) ScenarioDataStore.get("tax");
        double expectedTaxApplyOnTotalSumItems = totalSum * 0.08;
        expectedTaxApplyOnTotalSumItems = Double.parseDouble(String.format("%.2f", expectedTaxApplyOnTotalSumItems));  // Format expectedTaxApplyOnTotalSumItems to two decimal places
        System.out.println("the Tax amount in this purchase is " + expectedTaxApplyOnTotalSumItems + "");
        Assert.assertEquals(tax, expectedTaxApplyOnTotalSumItems, "Tax calculation is correct!");
    }
}