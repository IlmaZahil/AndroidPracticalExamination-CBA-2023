package endPoint;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RestApiClient {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        String endpoint = "http://123.231.9.43:3999";
        String username = "test";
        String password = "Test123";

        try {
            // Creating URL object with the endpoint
            URL url = new URL(endpoint);

            // Creating HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setting basic authentication credentials
            String credentials = username + ":" + password;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            // Setting request method and headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Sending the request
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Processing the response JSON
                String jsonResponse = response.toString();
                System.out.println(jsonResponse);
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            // Disconnecting the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
