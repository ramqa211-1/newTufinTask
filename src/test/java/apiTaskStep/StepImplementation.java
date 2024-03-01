package apiTaskStep;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class StepImplementation {

    @Step("Check if <firstName> <lastName> appears in any pages list")
    public void CheckIfSpecificNameAppearsInPagesList(String firstName, String lastName) {
        String apiUrl = "https://reqres.in/api/users";
        int totalPages = getTotalPages(apiUrl);

        boolean isTobiasFunkeNameAppears = false;
        for (int page = 1; page <= totalPages; page++) {
            Response response = RestAssured.get(apiUrl + "?page=" + page);
            if (response.getStatusCode() == 200) {
                String responseBody = response.getBody().asString();
                isTobiasFunkeNameAppears = responseBody.contains(firstName) && responseBody.contains(lastName);
                System.out.println("response body is " + responseBody + "");
            }
            if (isTobiasFunkeNameAppears) {
                System.out.println("Tobias Funke found in one of the pages");
                break;
            }
        }
        Assert.assertTrue(isTobiasFunkeNameAppears, "Tobias Funke name appears in one of the pages list.");
    }

    private int getTotalPages(String apiUrl) {
        Response response = RestAssured.get(apiUrl);
        return response.jsonPath().getInt("total_pages");
    }
}