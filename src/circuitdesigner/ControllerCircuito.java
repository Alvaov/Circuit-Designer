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

import static circuitdesigner.CircuitDesigner.getControlador;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

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
                Point2D punto = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(event.getX(),event.getY());
                ((CirculoEntrada) event.getGestureSource()).setLayoutX(punto.getX());
                ((CirculoEntrada) event.getGestureSource()).setLayoutY(punto.getY());
            } 
            
            
            else if (event.getGestureSource() instanceof CirculoSalida){
                Point2D punto = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(event.getX(),event.getY());
                ((CirculoSalida) event.getGestureSource()).setLayoutX(punto.getX());
                ((CirculoSalida) event.getGestureSource()).setLayoutY(punto.getY());
                
                
            }else if(event.getGestureSource() instanceof Group){
                
                Point2D punto = ((Group) event.getGestureSource()).parentToLocal(event.getX(), event.getY());
                ((Group) event.getGestureSource()).setLayoutX(event.getX());
                ((Group) event.getGestureSource()).setLayoutY(event.getY());
                ((Group) event.getGestureSource()).setLayoutY(event.getY());
            }
        });
        
    }
    
    EventHandler<MouseEvent> crearAnd = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                crearVentana("AND.png");
                
                //CrearAnd("AND.png",2);
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };

    EventHandler<MouseEvent> crearOr = 
            new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {

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
    
    public void crearVentana(String ruta) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
        Parent root1 = (Parent) loader.load();
        ControllerCantEntradas controladorEntradas = (ControllerCantEntradas)loader.getController();
        controladorEntradas.rutaImagen(ruta);
        Stage stage = new Stage();
        controladorEntradas.enviarStage(stage);
        stage.setScene(new Scene(root1));
        
        stage.show();
    }
    
}
