/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class ControllerValorEntradas implements Initializable{
@FXML
RadioButton setTrue;

@FXML
RadioButton setFalse;

@FXML
Button AceptarButton;

CirculoEntrada circuloEntrada;
Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        System.out.println("se creó la ventana");
        ToggleGroup grupo = new ToggleGroup();
        setFalse.setToggleGroup(grupo);
        setTrue.setToggleGroup(grupo);
        AceptarButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(grupo.selectedToggleProperty().getValue().toString());
                if (grupo.selectedToggleProperty().getValue().equals(setTrue)){
                    System.out.println("true");
                    circuloEntrada.setValor(Valores.True);
                    stage.close();
                    
                }else{
                    circuloEntrada.setValor(Valores.False);
                    stage.close();
                }
            }
            
        });
    }
    public void ACambiar(CirculoEntrada circulo){
        this.circuloEntrada = circulo;
    }
    public void enviarStage(Stage stage){
        this.stage = stage;
    }
}
