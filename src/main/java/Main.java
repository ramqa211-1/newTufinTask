import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "{\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":1,\"email\":\"george.bluth@reqres.in\",\"first_name\":" +
                "\"George\",\"last_name\":\"Bluth\",\"avatar\":\"https://reqres.in/img/faces/1-image.jpg\"},{\"id\":2,\"email\":" +
                "\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"}," +
                "{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://reqres.in/img/faces/3-image.jpg\"}]}";

        List<String> result = extractDataWithJsonLibrary(text);

        // Print the result
        for (String info : result) {
            System.out.println(info);
        }
    }

    private static List<String> extractDataWithJsonLibrary(String text) {
        List<String> result = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(text);

            JsonNode dataArray = rootNode.get("data");
            if (dataArray != null && dataArray.isArray()) {
                for (JsonNode node : dataArray) {
                    String id = node.get("id").asText();
                    String firstName = node.get("first_name").asText();
                    String lastName = node.get("last_name").asText();

                    result.add("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}