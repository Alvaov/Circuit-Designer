/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

/**
 *
 * @author allva
 */
public class ControllerCantEntradas implements Initializable {
    
    @FXML Spinner crearEntradas;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crearEntradas.decrement(1);
        crearEntradas.increment(1);
    }
    
}
