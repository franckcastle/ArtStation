package api;

import javafx.application.Application;
        import javafx.geometry.Insets;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;
        import javafx.scene.layout.GridPane;
        import javafx.stage.Stage;
        import java.util.Arrays;
        import java.util.List;

public class ProfanityCheckerFX extends Application {

    private static final List<String> profanityDictionary = Arrays.asList("gros_mot_1", "gros_mot_2", "gros_mot_3");

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label commentLabel = new Label("Commentaire:");
        TextArea commentTextArea = new TextArea();
        commentTextArea.setWrapText(true);

        Button checkButton = new Button("Vérifier");
        Label resultLabel = new Label();

        checkButton.setOnAction(event -> {
            String comment = commentTextArea.getText();
            if (containsProfanity(comment, profanityDictionary)) {
                resultLabel.setText("Le commentaire contient des gros mots.");
            } else {
                resultLabel.setText("Le commentaire ne contient pas de gros mots.");
            }
        });

        root.add(commentLabel, 0, 0);
        root.add(commentTextArea, 1, 0);
        root.add(checkButton, 0, 1);
        root.add(resultLabel, 1, 1);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private boolean containsProfanity(String comment, List<String> profanityDictionary) {
        for (String word : comment.split(" ")) {
            if (profanityDictionary.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}