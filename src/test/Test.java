/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.CartItem;
import entities.Categorie;
import entities.Produit;
import java.sql.SQLException;

import entities.ShoppingCart;
import services.CItemService;
import services.CategorieService;
import services.ProduitService;
import services.ShoppingCartService;

/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) throws SQLException {

        ShoppingCart sc =new ShoppingCart("df","sdf","sdf","fzdf",3,25.0f);
        CartItem cs =new CartItem(20,3,2,1.0f);
       ProduitService ss = new ProduitService();
       System.out.println(ss.getByorderId(20).toString());
    }
    
}
