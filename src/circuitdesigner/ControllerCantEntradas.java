/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.scene.control.Spinner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


/**
 * @see clase controladora de la ventana desde la cual se crean la cantidad de entradas por compuerta.
 * @author allva
 */
public class ControllerCantEntradas implements Initializable {
    @FXML
    private Spinner crearEntradas;
    @FXML
    private Button aceptarEntradas;
    @FXML
    private Button cancelarEntradas;
    
    private String ruta;
    private Stage stage;
    private double x,y;
    @Override
    /**
     * @see Método principal de la clase controladora, inicinializa el spinner y permite la interacción con este,
     * podteriormente es el encargado de crear las compuertas según haya sido requerido.
     */
    public void initialize(URL location, ResourceBundle resources) {
        
        initSpinner();
        
        
        aceptarEntradas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                int valor = (Integer) crearEntradas.getValue();
                Compuerta compuerta = new Compuerta(ruta,valor,x,y);
                
                for (int i = 0; i < compuerta.getEntradas().getSize(); i++){
                    Entrada entrada = (Entrada) compuerta.getEntradas().getValor(i);
                    entrada.getEndE().setCompuertaPadre(compuerta);
                }
                Main.getControlador().getCircuito().añadirFinal(compuerta);
                stage.close();
                Main.getControlador().actualizarEtiquetas();
            }
            
        });
        cancelarEntradas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                    stage.close();
            }
        });
    }

    /**
     * @see método encargado de inicializar el spinner que se utiliza en la ventana para la creación de diferentes cantidades
     * de entradas.
     */
    public void initSpinner(){
        crearEntradas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2,20));
    }
    
    /**
     * @see Método desde el cual se reciben los datos necesarios para la creación de una nueva
     * compuerta desde el controlador principal
     * @param ruta
     * @param x
     * @param y
     */
    public void valoresImagen(String ruta,double x, double y){
        this.ruta = ruta;
        this.x = x;
        this.y = y;
    }

    /**
     * @see método que recibe el stage de la ventana para cerrarla posteriormente que se termine su función
     * creando las entradas que el usuario requiera.
     * @param stage
     */
    public void enviarStage(Stage stage){
        this.stage = stage;
    }
}