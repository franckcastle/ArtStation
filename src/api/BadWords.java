package api;
/*
package api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BadWords {


    private static final String PURGOMALUM_API_ENDPOINT = "https://www.purgomalum.com/service/containsprofanity?text=This%20sentence%20contains%20no%20profanity";

    public static String checkWords(String paragraph) {
        try {
            String sanitizedParagraph = paragraph.replaceAll("\\s", "");
            URL url = new URL(PURGOMALUM_API_ENDPOINT + sanitizedParagraph);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                StringBuilder content;
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }
                return content.toString();
            } else {
                throw new IOException("HTTP error code: " + status);
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + PURGOMALUM_API_ENDPOINT, e);
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to PurgoMalum API", e);
        }
    }

        private boolean checkBadWords (String x){
        String[] badWords = {"kelmakhayba", "aslema", "beslema"}; // liste de gros mots à interdire
        for (String word : badWords) {
            if (x.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
//    private static void saveWord(String word) {
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            String query = "INSERT INTO badwords (word) VALUES (?)";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            String sanitizedWord = word.substring(0, 1) + "*".repeat(word.length() - 1);
//            stmt.setString(1, word);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error saving word to database", e);
//        }
//    }
}
*/
/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BadWords {

    private static final String BAD_WORDS_FILE = "C:\\Users\\user\\OneDrive\\Bureau\\badwords.txt";

    public static String checkWords(String paragraph) {
        List<String> badWords = loadBadWords();
        String[] words = paragraph.split("\\s+");
        for (String word : words) {
            if (badWords.contains(word.toLowerCase())) {
                return "true";
            }
        }
        return "false";
    }

    private static List<String> loadBadWords() {
        List<String> badWords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(BAD_WORDS_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                badWords.add(line.toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            // handle the exception
        }
        return badWords;
    }
}*/
import utils.MyDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BadWords {

    private static final String BAD_WORDS_FILE = "C:\\Users\\user\\OneDrive\\Bureau\\badwords.txt";

    public static String checkWords(String paragraph) {
        List<String> badWords = loadBadWords();
        String[] words = paragraph.split("\\s+");
        for (String word : words) {
            if (badWords.contains(word.toLowerCase())) {
                saveBadWord(word);
                return "true";
            }
        }
        return "false";
    }

    private static List<String> loadBadWords() {
        List<String> badWords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(BAD_WORDS_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                badWords.add(line.toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            // handle the exception
        }
        return badWords;
    }

    private static void saveBadWord(String word) {
        Connection cnx = MyDB.getInstance().getCnx();
        try {
            String sql = "INSERT INTO badwords (word) VALUES (?)";
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, maskWord(word));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
        }
    }

    private static String maskWord(String word) {
        String firstLetter = word.substring(0, 1);
        String restOfWord = word.substring(1);
        return firstLetter + restOfWord.replaceAll(".", "*");
    }
}
