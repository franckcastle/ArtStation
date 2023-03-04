package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    @FXML
    private TextField amountField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvcField;




    @FXML
    private Button payButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    @FXML
    void handlePayButtonAction(ActionEvent event) {
        int amount =Integer.parseInt(amountField.getText()) ;
        String cardNumber = cardNumberField.getText();


        Stripe.apiKey = "sk_test_51Mhso8JUWt71NA3D0Xfp4L2IrdqJuTSymxdex0kR8NYbwiUCeTJL1fybK9Hj7p9msIFb3aGVjWsHGYhg4trH4Jm800im117qdR";

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", cardNumber);
        chargeParams.put("description", "Test Charge");

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
            statusLabel.setText("Payment successful!");
        } catch (StripeException e) {
            statusLabel.setText("Payment failed. Please check your card details.");
            e.printStackTrace();
        }
    }

    @FXML
    void handleResetButtonAction(ActionEvent event) {
        amountField.clear();
        cardNumberField.clear();

        cvcField.clear();
        statusLabel.setText("");
    }
    }

