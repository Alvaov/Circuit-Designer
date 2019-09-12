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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author allva
 */
public class ControllerCantEntradas implements Initializable {
    @FXML
    private Spinner crearEntradas;
    @FXML
    private Button aceptarEntradas;
    @FXML
    private Button cancelarEntradas;
    
    String ruta;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initSpinner();
        
        
        aceptarEntradas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                int valor = (Integer) crearEntradas.getValue();
                try {
                    CircuitDesigner.getControlador().CrearAnd(ruta, valor);
                    stage.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ControllerCantEntradas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        cancelarEntradas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                    stage.close();
            }
        });
    }
    public void initSpinner(){
        crearEntradas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000000));
    }
    
    public void rutaImagen(String ruta){
        this.ruta = ruta;
    }
    public void enviarStage(Stage stage){
        this.stage = stage;
    }
}
