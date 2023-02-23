package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {
    @FXML
    private PasswordField passwordTf;

    @FXML
    private TextField usernameTf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    void Connexion(ActionEvent event) {
        UserService us = new UserService();
        User u = new User();
        String username = usernameTf.getText();
            String password = passwordTf.getText();
            try {
                u = us.GetByUsername(username);
                if (password != u.getPassword()){
                    System.out.println(password.getClass());

                    System.out.println("000");
                }

            }catch (SQLException e){
                System.out.println(e);
            }

    }

}
