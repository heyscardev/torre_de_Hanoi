/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import zunayedhassan.AnimateFX.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Helitzaith
 */
public class VistaController implements Initializable {
   
    @FXML
    private AnchorPane fondo;

    @FXML
    private VBox torreA;

    @FXML
    private VBox torreB;

    @FXML
    private VBox torreC;

    @FXML
    private Label titulo;

    @FXML
    private HBox mientras;

    @FXML
    private Label alertas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           Generar();
           eventos();
    }    
    public void Generar(){
       try {
           String [] colores = {"e0bbe4","957dad","d291bc","fec8d8","ffdfd3","e2f0cb"};
                FXMLLoader [] d = new FXMLLoader[6];
                 DiscoController [] dControl  = new DiscoController[6];
                 int color  = 200;
                for(int i = 0; i<6 ;i++){
                     d[i] = new FXMLLoader();
                d[i].setLocation(getClass().getResource("Disco.fxml"));
                d[i].load();
               
                dControl[0] = d[i].getController();
                Button b = d[i].getRoot();
                torreA.getChildren().add(b);
               b.setOnAction(ee->{
                VBox  p = (VBox) b.getParent();
                 if (mientras.getChildren().isEmpty()){
         if(p.getChildren().isEmpty()){
                
            }else{
        Button al = (Button) p.getChildren().get(0);
        p.getChildren().remove(0);
        
        mientras.getChildren().add(al);
        }
        }else{
            if(p.getChildren().isEmpty()){
            p.getChildren().add(0,mientras.getChildren().get(0));
             
             }else{
                 Button baseb = (Button)p.getChildren().get(0);
                 Button nuevob = (Button)mientras.getChildren().get(0);
                  int base = Integer.parseInt(baseb.getText());
                 int nuevo = Integer.parseInt(nuevob.getText());
                 if(base> nuevo){
                
                    p.getChildren().add(0,mientras.getChildren().get(0));
                }else{
                     Toolkit.getDefaultToolkit().beep();
                     alerta("no puedes poner un piso mas grande que el de la torrre");
                   new  zunayedhassan.AnimateFX.PulseAnimation(mientras).Play();
                 }
        }
            
        }    
                 ganar();
                });
                
                dControl[0].generar(colores[i],i+1);
                }
               
            } catch (IOException ex) {
                Logger.getLogger(VistaController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void eventos(){
        VBox [] torres =  {torreA,torreB,torreC};
        //for para asignar eventos de mover 
        for(VBox ss : torres){
           ss.setOnMousePressed(EVE->{
        if (mientras.getChildren().isEmpty()){
         if(ss.getChildren().isEmpty()){
                
            }else{
        Button al = (Button) ss.getChildren().get(0);
        ss.getChildren().remove(0);
        
        mientras.getChildren().add(al);
        }
        }else{
            if(ss.getChildren().isEmpty()){
            ss.getChildren().add(0,mientras.getChildren().get(0));
             
             }else{
                 Button baseb = (Button)ss.getChildren().get(0);
                 Button nuevob = (Button)mientras.getChildren().get(0);
                  int base = Integer.parseInt(baseb.getText());
                 int nuevo = Integer.parseInt(nuevob.getText());
                 if(base> nuevo){
                
                    ss.getChildren().add(0,mientras.getChildren().get(0));
                }else{
                     Toolkit.getDefaultToolkit().beep();
                     alerta("no puedes poner un piso mas grande que el de la torrre");
                   new  zunayedhassan.AnimateFX.PulseAnimation(mientras).Play();
                 }
        }
            
        }  
               ganar();
        });
        }
        
        
        
    }
   public void alerta(String nombre){
       alertas.setText(nombre);
       new zunayedhassan.AnimateFX.BounceInUpAnimation(alertas).Play();
 BounceOutDownAnimation salida = new zunayedhassan.AnimateFX.BounceOutDownAnimation(alertas);
 salida.GetTimeline().setDelay(Duration.seconds(5));
 salida.Play();
 
   
   }
   public void ganar(){
       if(torreC.getChildren().size()>=6){
           Alert ganaste  = new Alert(Alert.AlertType.CONFIRMATION);
           ganaste.setTitle("Ganador");
           ganaste.setHeaderText("!GANASTE");
           ganaste.setContentText("deseas jugar otra vez?");
           ganaste.showAndWait();
           if(ganaste.getResult() == ButtonType.CANCEL){
                 Stage sta = (Stage) fondo.getScene().getWindow();
               sta.close();
           }
           else{
                torreC.getChildren().clear();
              Generar();
            
           }
       }
   }
}
