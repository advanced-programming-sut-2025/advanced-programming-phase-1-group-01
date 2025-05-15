package models.AI;

import models.dateTime.Season;
import models.relations.FriendshipLevel;
import models.weather.Weather;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIChat {
    public static String messageGenerator(String message, Season season, Weather weather, int hour, int FriendshipLevelInt) {
        try {
            URL url = new URL("http://localhost:11434/api/generate");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String requestBody = String.format("""
                    {
                      "model": "phi3",
                      "prompt": "Question: (%s) — Reply naturally with a short, clear message (max 30 words, this is critical). Use exactly 2 emojis at the end, followed by a #. Match the tone to: Season: [%s], Weather: [%s], Hour of the day: [%d], Friendship: [%s]. Avoid extra sentences or errors — answer only the question.",
                      "stream": false
                    }
                    """, message, season.name(), weather.name(), hour, FriendshipLevel.getLevelDescription(FriendshipLevelInt));

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                String raw = response.toString();
                int start = raw.indexOf("\"response\":\"") + 11;
                int end = raw.indexOf("\",\"done\":");
                String cleaned = raw.substring(start + 1, end).replaceAll("\\\\.\\d*", "")
                        .replaceAll("#[A-Za-z]*", "").replace("(", "")
                        .replace(")", "");
                cleaned = truncateAtThirtyWords(cleaned);
                return cleaned;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String truncateAtThirtyWords(String text) {
        String[] words = text.split("\\s+");
        if (words.length <= 30) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        String[] sentences = text.split("(?<=[.!?])\\s+");
        int count = 0;
        while (result.length() < 30) {
            result.append(sentences[count]);
            result.append(" ");
            if (result.length() >= 30) {
                break;
            }
            count++;
        }
        return result.toString();
    }
}
