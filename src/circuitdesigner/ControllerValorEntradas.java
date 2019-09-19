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
 * @see clase que permite asignar un valor a cada entrada que se encuentre en la mesa de trabajo.
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
    /**
     * @see método que inicializa los RadioButton y métodos necesarios para poder realizar de manera satisfactoria la 
     * modificación del valor que se deseaba. Si no se da aceptar el valor no se modifica aunque se haya marcado una opción. Solo 
     * se puede marcar una opción.
     */
    public void initialize(URL location, ResourceBundle resources) {        
        System.out.println("se creó la ventana");
        ToggleGroup grupo = new ToggleGroup();
        setFalse.setToggleGroup(grupo);
        setTrue.setToggleGroup(grupo);
        AceptarButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(grupo.selectedToggleProperty().getValue().toString());
                if(grupo.selectedToggleProperty().getValue() !=null){
                    if (grupo.selectedToggleProperty().getValue().equals(setTrue)){
                        circuloEntrada.setValor(Valores.True);
                        System.out.println("Seteó true");
                        stage.close();

                    }else{
                        circuloEntrada.setValor(Valores.False);
                        stage.close();
                    }
                }
                stage.close();
            }
        });
    }

    /**
     * @see cambia el valor del circulo sobre el cual se ejecutó el método.
     * @param circulo
     */
    public void ACambiar(CirculoEntrada circulo){
        this.circuloEntrada = circulo;
    }
    
    /**
     * @see recibe el stage sobre el cual se creó la ventana correspondiente para cerrarla luego.
     * @param stage
     */
    public void enviarStage(Stage stage){
        this.stage = stage;
    }
}
