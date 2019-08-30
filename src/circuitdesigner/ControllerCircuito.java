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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControllerCircuito implements Initializable{
    
    double orgSceneX, orgSceneY, orgTranslateY, orgTranslateX;
    ListLinked<Operadores> circuito;
    
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
    private Group root;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        ANDimage.setOnMouseClicked(crearAnd);
        ORimage.setOnMouseClicked(crearOr);
        XORimage.setOnMouseClicked(crearXor);
        NANDimage.setOnMouseClicked(crearNegaciones);
    }
    
    EventHandler<MouseEvent> crearAnd = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
                Scene scene = new Scene(loader.load());
                Stage segundaVentana = new Stage();
                segundaVentana.setTitle("Crear entradas");
                segundaVentana.setScene(scene);
                
                segundaVentana.show();
                CrearAnd();
            } catch (Exception e) {
                System.out.println("No se logró cargar la ventana");
            }
        }
    };
    
     EventHandler<MouseEvent> MousePressed = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
        }
    };
     
    EventHandler<MouseEvent> MouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
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
        
        
        
    public void CrearAnd() throws FileNotFoundException{
        //AndOperator CompuertaAnd = new AndOperator(5);
        //CompuertaAnd.setEntrada(1, false);
        //System.out.println(CompuertaAnd.getEntrada(1).getValue());
        Image imageAnd = new Image(new FileInputStream("C:\\Users\\allva\\Desktop\\CircuitDesigner\\src\\Imágenes Compuertas Lógicas\\AND.png"));
        ImageView and = new ImageView(imageAnd);
        and.setCursor(Cursor.MOVE);
        and.setX(-250);
        and.setY(-40);
        and.setFitWidth(65.0);
        and.setFitHeight(40.0);

        and.setOnMousePressed(MousePressed);
        and.setOnMouseDragged(MouseDragged);


        root.getChildren().add(and);
    }
    
    public void CrearOr() throws FileNotFoundException{
        //OrOperator CompuertaOr = new OrOperator(5);

        Image imageAnd = new Image(new FileInputStream("C:\\Users\\allva\\Desktop\\CircuitDesigner\\src\\Imágenes Compuertas Lógicas\\OR.png"));
        ImageView and = new ImageView(imageAnd);
        and.setCursor(Cursor.MOVE);
        and.setX(-250);
        and.setY(-40);
        and.setFitWidth(65.0);
        and.setFitHeight(40.0);

        and.setOnMousePressed(MousePressed);
        and.setOnMouseDragged(MouseDragged);

        root.getChildren().add(and);
    }
    
    public void CrearXor() throws FileNotFoundException{
        //XOROperator CompuertaXor = new XOROperator(5);

        Image imageAnd = new Image(new FileInputStream("C:\\Users\\allva\\Desktop\\CircuitDesigner\\src\\Imágenes Compuertas Lógicas\\XOR.png"));
        ImageView and = new ImageView(imageAnd);
        and.setCursor(Cursor.MOVE);
        and.setX(-250);
        and.setY(-40);
        and.setFitWidth(65.0);
        and.setFitHeight(40.0);

        and.setOnMousePressed(MousePressed);
        and.setOnMouseDragged(MouseDragged);


        root.getChildren().add(and);
    }
    
    
    
}
