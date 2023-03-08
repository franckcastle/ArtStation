package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import entities.Remise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.RemiseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    @FXML
    private TextField code;
    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvcField;

    @FXML
    private Text price;
    @FXML
    private Button remise;

    @FXML
    private Button payButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    float amount;

    RemiseService rs = new RemiseService();

    public void setPrice(Float t) {
        price.setText(String.valueOf(t));
    }


    @FXML
    void handlePayButtonAction(ActionEvent event) {

        String cardNumber = cardNumberField.getText();
System.out.println(rem);

        Stripe.apiKey = "sk_test_51Mhso8JUWt71NA3D0Xfp4L2IrdqJuTSymxdex0kR8NYbwiUCeTJL1fybK9Hj7p9msIFb3aGVjWsHGYhg4trH4Jm800im117qdR";


        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) amount * 100);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", cardNumber);
        chargeParams.put("description", "Test Charge");

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
            statusLabel.setText("Payment successful!");
            rs.increment(remise_inc);
         /*   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Payment successful");
            alert.showAndWait();*/
        } catch (StripeException e) {
            statusLabel.setText("Payment failed. Please check your card details.");
            //    Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("");
//            alert.setHeaderText("Payment failed. Please check your card details.");
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleResetButtonAction(ActionEvent event) {

        cardNumberField.clear();

        cvcField.clear();
        statusLabel.setText("");
    }
int rem;
Remise remise_inc=new Remise();
    public void remisebtn(ActionEvent actionEvent) throws SQLException {
        String input = code.getText().trim(); // trim leading and trailing whitespace

        if (input.matches("\\d+")) {
             rem = Integer.parseInt(input);
            // rest of your code


        System.out.println( rs.getById(rem) );
            if (rs.getById(rem)!= null) {
                amount= (float) (amount-(amount*0.2));
                price.setText(String.valueOf(amount));
                remise_inc=rs.getById(rem);

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("code coupant introuvable");
                alert.showAndWait();
            }  } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Code coupant invalide");
            alert.showAndWait();
        }
    }

}