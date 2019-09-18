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
 * @see Clase main del programa, incluye los métodos del main, creación de la ventana principal
 * y obtener el controlador de todo el programa.
 * @author allva
 */
public class Main extends Application{
    

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
       
        getControlador();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCircuitDesigner.fxml"));
        ControllerCircuito controlador = ControllerCircuito.getControlador();
        loader.setController(controlador);
        this.controlador = controlador;
       Scene myScene = (new Scene(loader.load()));
       primaryStage.setScene(myScene);
       
       primaryStage.show();
    }
    
    private static ControllerCircuito controlador;
    
    /**
     * @see Función que crea el controlador a usarse a lo largo del programa. Utiliza un patrón
     * singleton que garantiza la creación de un único controlador. Si no existe uno lo crea, y 
     * si existe retorna este previamente creado.
     * @return controlador
     */
    public static ControllerCircuito getControlador(){
        return controlador;
    }
}
