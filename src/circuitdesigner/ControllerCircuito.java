/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

/**
 *
 * @author allva
 */

import listlinked.ListLinked;
import operadores.Operadores;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControllerCircuito implements Initializable{
    

    @FXML
    private Button EncapsularButton;
    @FXML
    private Button SimulateButton;
    @FXML
    private ImageView ANDimage;
    @FXML
    private ImageView NANDimage;
    @FXML
    private ImageView ORimage;
    @FXML
    private ImageView NORimage;
    @FXML
    private ImageView XORimage;
    @FXML
    private ImageView XNORimage;
    @FXML
    private ImageView NOTimage;
    @FXML
    private AnchorPane AnchorCircuito;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        ANDimage.setOnMouseClicked(crearAnd);
        ORimage.setOnMouseClicked(crearOr);
        XORimage.setOnMouseClicked(crearXor);
        NANDimage.setOnMouseClicked(crearNegaciones);
        AnchorCircuito.setOnMouseDragOver(event ->{
            if (event.getGestureSource() instanceof CirculoEntrada){
                ((CirculoEntrada) event.getGestureSource()).setCenterX(event.getX());
                ((CirculoEntrada) event.getGestureSource()).setCenterY(event.getY());
            } 
            if (event.getGestureSource() instanceof CirculoSalida){
                ((CirculoSalida) event.getGestureSource()).setCenterX(event.getX());
                ((CirculoSalida) event.getGestureSource()).setCenterY(event.getY());
            }else{
                //System.out.println("varas");
            }
        });
        
    }
    
    EventHandler<MouseEvent> crearAnd = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                //crearVentana();
                
                CrearAnd("AND.png",2);
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };

    
    EventHandler<MouseEvent> MouseRelease = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {

            System.out.println("Se soltó");

        }
    };
    
    EventHandler<MouseEvent> crearOr = 
            new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
                Scene scene = new Scene(loader.load());
                Stage segundaVentana = new Stage();
                segundaVentana.setTitle("Crear entradas");
                segundaVentana.setScene(scene);
                
                //segundaVentana.show();
                CrearOr();
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };
    
    EventHandler<MouseEvent> crearXor = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
                Scene scene = new Scene(loader.load());
                Stage segundaVentana = new Stage();
                segundaVentana.setTitle("Crear entradas");
                segundaVentana.setScene(scene);
                
                //segundaVentana.show();
                CrearXor();
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };
    
    EventHandler<MouseEvent> crearNegaciones = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
                Scene scene = new Scene(loader.load());
                Stage segundaVentana = new Stage();
                segundaVentana.setTitle("Crear entradas");
                segundaVentana.setScene(scene);
                
                //segundaVentana.show();
                CrearOr();
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };
        
        
        
    public void CrearAnd(String ruta, int cantidadDeEntradas) throws FileNotFoundException{
        Facade algo = new Facade("AND.png",cantidadDeEntradas);
    }
    
    public void CrearOr() throws FileNotFoundException{
        
    }
    
    public void CrearXor() throws FileNotFoundException{

    }

    public AnchorPane getAnchor(){
        return AnchorCircuito;
    }
    public void crearVentana() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
        Scene scene = new Scene(loader.load());
        Stage segundaVentana = new Stage();
        segundaVentana.setTitle("Crear entradas");
        segundaVentana.setScene(scene);
                
        segundaVentana.show();
                
    }
    
}
