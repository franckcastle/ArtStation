package gui;

import entities.CartItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.CItemService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;

public class AjouterItem  implements Initializable {

    @FXML
    private Button btnOnClick;

    @FXML
    private TextField id_field;

    @FXML
    private TextField order_id_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextField quantity_field;


    @FXML
    void ajouterItem(ActionEvent event) {
        CItemService cit =new CItemService();
        CartItem c = new CartItem();
        c.setOrderId(Integer.parseInt(order_id_field.getText()));
        c.setId(Integer.parseInt(id_field.getText()));
        c.setQuantity(Integer.parseInt(quantity_field.getText()));
        c.setPrice(parseFloat(price_field.getText()));
        try {
            cit.ajouter(c);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        private void reset() {
            order_id_field.setText("");
            id_field.setText("");
            quantity_field.setText("");
            price_field.setText("");
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
