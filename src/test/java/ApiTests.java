import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ApiTests {

    @Test
    public void addItem_ShouldReturnSuccess() throws IOException {
        // Arrange
        String url = "https://warehouse.newdev.matgry.net/api/Items/AddItem";

        // Request body
        String requestBody = "{\n" +
                "  \"Name\": \"product\",\n" +
                "  \"NameAR\": \"منتج\",\n" +
                "  \"Code\": \"ASD123\",\n" +
                "  \"ItemCategoryId\": \"6CAF97CC-0202-4C9E-A308-08DCFF36121D\",\n" +
                "  \"HasSerials\": false,\n" +
                "  \"IsServiceWithoutBalance\": false,\n" +
                "  \"IsServiceWithDatesFromTo\": false,\n" +
                "  \"IsAllowedToSellWithoutBalance\": false,\n" +
                "  \"IsNonTexable\": false,\n" +
                "  \"IsSellPriceNeglected\": false,\n" +
                "  \"UOMId\": \"3E64A5C2-7D1F-43B3-BD8A-0A4C6B7BB49F\",\n" +
                "  \"IsBalance\": true,\n" +
                "  \"BalanceType\": 1,\n" +
                "  \"ItemImageURL\": \"https://picsum.photos/id/237/200/300\"\n" +
                "}";

        // Act
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // Assert
            int statusCode = response.getStatusLine().getStatusCode();
            Assertions.assertEquals(200, statusCode, "Status code should be 200");

            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            Assertions.assertNotNull(responseBody, "Response body should not be null");
            Assertions.assertTrue(responseBody.toLowerCase().contains("success"), "Response should contain 'success'");
        }
    }
}
