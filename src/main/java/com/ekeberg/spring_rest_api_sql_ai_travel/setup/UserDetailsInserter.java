package com.ekeberg.spring_rest_api_sql_ai_travel.setup;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class UserDetailsInserter {

    public static void main(String[] args) {
        String userApiUrl = "http://localhost:8080/users"; // User API URL

        // User data
        String[] usersJson = {
                "{ \"name\": \"Liam Beachman\", \"birthDate\": \"1989-03-25\", \"password\": \"liamPass123\" }",
                "{ \"name\": \"Emma Safari\", \"birthDate\": \"1991-11-15\", \"password\": \"emmaSecure456\" }",
                "{ \"name\": \"Noah Roadtripper\", \"birthDate\": \"1993-06-30\", \"password\": \"noahPW789\" }",
                "{ \"name\": \"Olivia Explorer\", \"birthDate\": \"1987-04-22\", \"password\": \"oliviaSecure987\" }"
        };

        for (String userJson : usersJson) {
            sendPostRequest(userApiUrl, userJson);
        }

        // Interests data for each user
        String[][] interestsJson = {
                {
                        "{ \"description\": \"Beach relaxation\" }",
                        "{ \"description\": \"Swimming in the ocean\" }",
                        "{ \"description\": \"Snorkeling adventures\" }",
                        "{ \"description\": \"Cruise vacations\" }"
                },
                {
                        "{ \"description\": \"Wildlife safari\" }",
                        "{ \"description\": \"Bird watching\" }",
                        "{ \"description\": \"Camping in nature\" }",
                        "{ \"description\": \"Historical sites\" }"
                },
                {
                        "{ \"description\": \"Road trips\" }",
                        "{ \"description\": \"Island hopping\" }",
                        "{ \"description\": \"Camping under the stars\" }",
                        "{ \"description\": \"Food and wine tours\" }"
                },
                {
                        "{ \"description\": \"Cultural festivals\" }",
                        "{ \"description\": \"Scuba diving\" }",
                        "{ \"description\": \"Exploring national parks\" }"
                }
        };

        for (int userId = 1; userId <= interestsJson.length; userId++) {
            for (String interestJson : interestsJson[userId - 1]) {
                sendPostRequest("http://localhost:8080/users/" + userId + "/interests", interestJson);
            }
        }
    }

    private static void sendPostRequest(String apiUrl, String jsonInputString) {
        try {
            // Create URL object
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Add Basic Authentication
            String username = "user"; // replace with your username
            String password = "030de596-3bf7-448a-916a-24cee507d330"; // replace with your password
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

            conn.setDoOutput(true);

            // Write JSON input to the output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code for request " + jsonInputString + ": " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
