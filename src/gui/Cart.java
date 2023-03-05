package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class Cart {


    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvcField;

    @FXML
    private Text price;


    @FXML
    private Button payButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

  float amount;


    public void setPrice(Float t) {
        price.setText(String.valueOf(t));
    }


    @FXML
    void handlePayButtonAction(ActionEvent event) {

        String cardNumber = cardNumberField.getText();


        Stripe.apiKey = "sk_test_51Mhso8JUWt71NA3D0Xfp4L2IrdqJuTSymxdex0kR8NYbwiUCeTJL1fybK9Hj7p9msIFb3aGVjWsHGYhg4trH4Jm800im117qdR";

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount",(int)amount*100);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", cardNumber);
        chargeParams.put("description", "Test Charge");

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
            statusLabel.setText("Payment successful!");
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
        }
    }

    @FXML
    void handleResetButtonAction(ActionEvent event) {

        cardNumberField.clear();

        cvcField.clear();
        statusLabel.setText("");
    }
    }

