/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Helitzaith
 */
public class DiscoController implements Initializable {

    @FXML
    private Button btn_disco;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    }    
         
    public void generar(String  color, int posicion){
        float ancho   = 300 * ((float)posicion /9); 
        System.out.println(ancho);
        btn_disco.setPrefWidth(ancho);
        btn_disco.setPrefHeight(30);
        btn_disco.setStyle("-fx-background-color: "+color+" ;");
        btn_disco.setText(Integer.toString(posicion));
    }
}
