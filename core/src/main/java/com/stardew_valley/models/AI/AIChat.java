package com.stardew_valley.models.AI;

import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.relations.FriendshipLevel;
import com.stardew_valley.models.weather.Weather;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AIChat {
    public static String messageGenerator(String message, Season season, Weather weather, int hour, int friendshipLevelInt) {
        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String prompt = String.format(
                "Question: (%s) — Reply naturally with a short, clear message (max 30 words, this is critical). " +
                    "Use exactly 2 emojis at the end, followed by a #. Match the tone to: Season: [%s], Weather: [%s], " +
                    "Hour of the day: [%d], Friendship: [%s]. Avoid extra sentences or errors — answer only the question.",
                message,
                season.name(),
                weather.name(),
                hour,
                FriendshipLevel.getLevelDescription(friendshipLevelInt)
            );

            String jsonPayload = "{"
                + "\"model\": \"phi3\","
                + "\"prompt\": \"" + prompt.replace("\"", "\\\\\"") + "\","
                + "\"stream\": false"
                + "}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status = connection.getResponseCode();
            if (status != 200) {
                return "❌ Server error: " + status;
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            String raw = response.toString();
            int start = raw.indexOf("\"response\":\"");
            int end = raw.indexOf("\",\"done\":");

            if (start == -1 || end == -1 || end <= start) {
                return "⚠️ Invalid response format.";
            }

            String extracted = raw.substring(start + 12, end)
                .replaceAll("\\\\n", " ")
                .replaceAll("\\\\\"", "\"")
                .trim();

            int hashIndex = extracted.indexOf('#');
            if (hashIndex != -1) {
                extracted = extracted.substring(0, hashIndex).trim();
            }

            return truncateAtThirtyWords(extracted);

        } catch (IOException e) {
            System.out.println("Error: " + e);
            return "💥 Connection error.";
        }
    }

    public static String truncateAtThirtyWords(String text) {
        String[] words = text.split("\\s+");
        if (words.length <= 30) return text;
        return String.join(" ", Arrays.copyOfRange(words, 0, 30));
    }
}
