package api;

import utils.MyDb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BadWords {

   private static final String BAD_WORDS_FILE = "D:\\Dev\\ArtStation\\src\\api\\badwords.txt";


   //Utiliser ca quand je recupere l'user!
    //public static String checkWords(String paragraph,String userId) {
   //int count = 0;

        public static String checkWords(String paragraph) {
        List<String> badWords = loadBadWords();
        String[] words = paragraph.split("\\s+");
        for (String word : words) {
            if (badWords.contains(word.toLowerCase())) {
                saveBadWord(word);

//                count++; // incrémenter la variable de comptage
//                if (count > 3) { // vérifier si le nombre de gros mots dépasse trois
//                sendSMS(userId); // appeler la méthode sendSMS pour envoyer un message à l'utilisateur
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
        Connection cnx = MyDb.getInstance().getCnx();
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

//    private static void sendSMS(String userId) {
//        // code pour envoyer un SMS à l'utilisateur
//    }
}
