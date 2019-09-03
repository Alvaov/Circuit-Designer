/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
/**
 *
 * @author allva
 */
public class CircuitDesigner extends Application{

    /**
     * @see Es el método que inicializa todo el código del programa.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch (args);
    }
    
    /**
     * @see se encarga de iniciar la interfaz gráfica de la aplicación. Es un método
     * heredado de java Application donde se selecciona el archivo FXML a leer y la clase
     * controladora.
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Circuit Designer");
        controlador = new ControllerCircuito();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCircuitDesigner.fxml"));
        loader.setController(controlador);
        
       Scene myScene = (new Scene(loader.load()));
       primaryStage.setScene(myScene);
       
       primaryStage.show();
    }
    
    static ControllerCircuito controlador;
    
    public static ControllerCircuito getController(){
        return controlador;
    }
}
