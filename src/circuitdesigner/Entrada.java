/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import operadores.Valores;
import listlinked.ListLinked;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;

/**
 * @see Clase Entrada, donde se crean y configuran todos los aspectos que involucra una entrada, tales como métodos
 * de modificacion, arrastre, interconexión,etc.
 * @author allva
 */
public class Entrada extends Observable{
      
      private CirculoEntrada endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      private CirculoSalida salidaConectada;
      private Circulo circulo;
      private Compuerta compuertaPadre;
      
    /**
     * @see constructor de la clase Entrada, recibe la imagen de la compuerta, un círculo 
     * que toma como e inicial para el dibujo de las líneas respectivas.
     * @param imagenVista
     * @param circulo
     * @param i
     */
    public Entrada(Circulo circulo,int i){
          this.circulo = circulo;
          valor = null;
          endE      = new CirculoEntrada(valor);
          endE.getEtiqueta().layoutXProperty().bind(endE.layoutXProperty());
          endE.getEtiqueta().layoutYProperty().bind(endE.layoutYProperty());
          lineE     = new Line();
          lineE.setStroke(colorLinea());
          lineE.startXProperty().bind(this.circulo.layoutXProperty());
          lineE.startYProperty().bind(this.circulo.layoutYProperty());
          lineE.endXProperty().bind(endE.layoutXProperty());
          lineE.endYProperty().bind(endE.layoutYProperty());
          
          endE.setOnDragDetected(MouseDetected);
          
          endE.setOnMouseDragReleased(event->{
              if (event.getGestureSource() instanceof CirculoSalida){
                  
                  ChangeListener<Number> listener = (observed, oldValue, newValue) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoSalida)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoSalida)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  };
                  ChangeListener<Number> listenerCompuerta = (observed, oldValue, newValue) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoSalida)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoSalida)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  };
                  endE.setUserData(listener);
                  endE.getCompuertaPadre().getCompuerta().setUserData(listenerCompuerta);
                  System.out.println("Conectar");
                  endE.setValor(((CirculoSalida) event.getGestureSource()).getValor());
                  ((CirculoSalida) event.getGestureSource()).setEntradasConectadas(endE);
                  ((CirculoSalida)event.getGestureSource()).getParent().setMouseTransparent(false);
                  ((CirculoSalida)event.getGestureSource()).getParent().toFront();
                  ((CirculoSalida)event.getGestureSource()).setIsConected(true);
                  endE.setIsConected(true);
                  Main.getControlador().actualizarEtiquetas();
                  endE.layoutXProperty().addListener(listener);
                  endE.layoutYProperty().addListener(listener);
                  
                  endE.getCompuertaPadre().getCompuerta().layoutXProperty().addListener(listener);
                  endE.getCompuertaPadre().getCompuerta().layoutYProperty().addListener(listener);
                  salidaConectada = ((CirculoSalida)event.getGestureSource());
                 
                  ((CirculoSalida)event.getGestureSource()).getParent().layoutXProperty().addListener(listenerCompuerta);
                  ((CirculoSalida)event.getGestureSource()).getParent().layoutYProperty().addListener(listenerCompuerta);
                  
              }else if (event.getGestureSource() instanceof CirculoEntrada){
                  
                  if (event.getGestureSource() instanceof Circulo){
                      System.out.println("Conectar multientradas");
                      ((CirculoEntrada)event.getGestureSource()).setIsConected(true);
                      endE.setIsConected(true);
                      endE.setValor(((CirculoEntrada)event.getGestureSource()).getValor());
                      ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
                  }
                  
                  System.out.println("entrada conectada");
              }
          });
          endE.setOnMouseClicked(cambiarValor);
      }

    /**
     * @see da el valor actual de la entrada.
     * @return Valores
     */
    public Valores getValor(){
          return endE.getValor();
      }
      
    /**
     * @see permite modificar el valor de la entrada según el parámetro que se reciba.
     * @param valorNuevo
     */
    public void setValor(Valores valorNuevo){
          endE.setValor(valorNuevo);
      }

      EventHandler<MouseEvent> MouseDetected = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            endE.startFullDrag();
            ((CirculoEntrada) t.getSource()).getParent().setMouseTransparent(true);
        }
      };

      EventHandler<MouseEvent> cambiarValor = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          if (t.getClickCount() == 2){
              if (endE.getValor() == null || endE.getIsConected() == false){
                  try {
                      crearVentana();
                      System.out.println(endE.getValor());
                  } catch (IOException ex) {
                     
                  }
              }else{
                  System.out.println("no se puede añadir un valor");
              }
          }
          if(t.getButton()== MouseButton.SECONDARY){
              if(endE.getValor() != null){
                  System.out.println("eliminar");
                  endE.layoutXProperty().removeListener((ChangeListener)endE.getUserData());
                  endE.layoutYProperty().removeListener((ChangeListener)endE.getUserData());
                  endE.getCompuertaPadre().getCompuerta().layoutXProperty().removeListener((ChangeListener)endE.getUserData());
                  endE.getCompuertaPadre().getCompuerta().layoutYProperty().removeListener((ChangeListener)endE.getUserData());
                  
                  
                  endE.setLayoutY(endE.getCenterY()+10);
                  endE.setValor(null);
                  endE.setIsConected(false);
                  Main.getControlador().actualizarEtiquetas();
                  salidaConectada = null;
              }
          }

        }
      };
      
    /**
     * @see retorna el circulEntrada correspondiente
     * @return CirculoEntrada
     */
    public CirculoEntrada getEndE(){
          return endE;
      }

    /**
     * @see permite accesar a la línea a la que está conectada cada entrada de la compuerta.
     * @return Line
     */
    public Line getLinea(){
          return lineE;
      }
      
    /**
     * @see retorna como valor la compuerta a la cual pertenece la entrada, conocida como compuerta padre.
     * @return Compuerta
     */
    public Compuerta getCompuertaPadre(){
          return compuertaPadre;
      }
      
    /**
     * @see permite la modificación del valor de la variable compuertaPadre perteneciente a cada entrada,
     * a través de un parámetro de tipo Compuerta
     * @param compuertaPadre
     */
    public void setCompuertaPadre(Compuerta compuertaPadre){
          this.compuertaPadre = compuertaPadre;
      }
      
    /**
     * @see método que calcula posibles colores diferentes para cada línea de la interfaz,
     * además verifica que estos colores no se repitan en ninguna línea por más similares que sean estos colores.
     * @return color
     */
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        ListLinked<Color> coloresUsados = Main.getControlador().getColores();
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorLinea();
            }
        }
        coloresUsados.añadirFinal(color);
        return color;
    }
    
    /**
     * @see método desde el cual se crea la venta donde se puede asignar un valor a cada entrada según corresponda.
     * @throws IOException
     */
    public void crearVentana() throws IOException{
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ValorEntradas.fxml"));
        Parent root1 = (Parent) loader.load();
        ControllerValorEntradas controlador= (ControllerValorEntradas)loader.getController();
        controlador.ACambiar(endE);
        Stage stage = new Stage();
        controlador.enviarStage(stage);
        stage.setScene(new Scene(root1));
        
        stage.show();
    }
}