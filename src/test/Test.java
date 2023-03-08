/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.CustomerListParams;
import entities.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import services.*;

/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) throws SQLException {

   /* Stripe.apiKey="sk_test_51Mhso8JUWt71NA3D0Xfp4L2IrdqJuTSymxdex0kR8NYbwiUCeTJL1fybK9Hj7p9msIFb3aGVjWsHGYhg4trH4Jm800im117qdR";

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        int i=2000;
        chargeParams.put("amount", i);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", "tok_visa"); // This is a test card number provided by Stripe
        chargeParams.put("description", "Test Charge");

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }*/
/* ShoppingCart sc =new ShoppingCart("df","sdf","sdf","fzdf",3,25.0f);
        CartItem cs =new CartItem(20,3,2,1.0f);
       ProduitService ss = new ProduitService();
       System.out.println(ss.getByorderId(20).toString());*/
        Remise r = new Remise(12346,"amine",0);
        RemiseService rs = new RemiseService();
       rs.modifier(r);
    }
    
}
