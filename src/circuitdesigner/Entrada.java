/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import operadores.Valores;
import listlinked.ListLinked;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * Mase Entrada, donde se crean y configuran todos los aspectos que involucra una entrada, tales como métodos
 * de modificacion de valor, arrastre e interconexión.
 * @author allva
 */
public class Entrada{
      
      private CirculoEntrada endE;
      private CirculoEntrada compuertaConectada = new CirculoEntrada(Valores.Default);
      private Line lineE;
      private Valores valor;
      private CirculoEntrada entradaConectada = new CirculoEntrada(Valores.Default);
      private Circulo circulo;
      private Compuerta compuertaPadre;
      
      
    /**
     * Mtor constructor de la clase Entrada, recibe la imagen de la compuerta, un círculo 
     * que toma como e inicial para el dibujo de las líneas respectivas. Sintaxis lambda obtenida de 
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html.
     * ChangeListener https://docs.oracle.com/javafx/2/api/javafx/beans/value/ChangeListener.html
     * Eventos de mouse https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/MouseDragEvent.html
     * @param circulo, el circulo de inicio de la linea que conecta el circulo entrada con la compuerta
     */
    public Entrada(Circulo circulo){
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
                  
                  ChangeListener<Number> listener = (observedado, valorAnterior, valorNuevo) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoSalida)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX()+2);
                      ((CirculoSalida)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY()+2);
                  };
                  ChangeListener<Number> listenerCompuerta = (observado, valorAnterior, valorNuevo) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoSalida)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX()+2);
                      ((CirculoSalida)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY()+2);
                  };
                  endE.setUserData(listener);
                  endE.getCompuertaPadre().getCompuerta().setUserData(listenerCompuerta);
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
                 
                  ((CirculoSalida)event.getGestureSource()).getParent().layoutXProperty().addListener(listenerCompuerta);
                  ((CirculoSalida)event.getGestureSource()).getParent().layoutYProperty().addListener(listenerCompuerta);
                  
              }else if (event.getGestureSource() instanceof CirculoEntrada){
                  
                  endE.añadirEntradas(((CirculoEntrada)event.getGestureSource()));
                  endE.getCompuertaPadre().getCompuerta().toFront();
                  endE.toFront();
                  ChangeListener<Number> listener = (observed, oldValue, newValue) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX()+((CirculoEntrada)event.getGestureSource()).getRadius());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY()+((CirculoEntrada)event.getGestureSource()).getRadius());
                  };
                  ChangeListener<Number> listenerCompuerta = (observed, oldValue, newValue) -> {
                      Bounds coordenadas = endE.getParent().localToParent(endE.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX()+((CirculoEntrada)event.getGestureSource()).getRadius());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY()+((CirculoEntrada)event.getGestureSource()).getRadius());
                  };
                  compuertaConectada.setUserData(listenerCompuerta);
                  entradaConectada.setUserData(listener);
                  endE.layoutXProperty().addListener(listener);
                  endE.layoutYProperty().addListener(listener);
                  endE.getCompuertaPadre().getCompuerta().layoutXProperty().addListener(listener);
                  endE.getCompuertaPadre().getCompuerta().layoutYProperty().addListener(listener);
                  
                  ((CirculoEntrada)event.getGestureSource()).getParent().layoutXProperty().addListener(listenerCompuerta);
                  ((CirculoEntrada)event.getGestureSource()).getParent().layoutYProperty().addListener(listenerCompuerta);
                  
                  ((CirculoEntrada)event.getGestureSource()).setIsConected(true);
                  endE.setIsConected(true);
                  endE.setValorEntradaConectado(true);
                  endE.setValor(((CirculoEntrada)event.getGestureSource()).getValor());
                  Main.getControlador().actualizarEtiquetas();
                  ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
              }
          });
          endE.setOnMouseClicked(cambiarValor);
      }

    /**
     * M valor actual de la entrada.
     * @return Valores, el valor actual de la entrada
     */
    public Valores getValor(){
          return endE.getValor();
      } 
    /**
     * Mpermite modificar el valor de la entrada según el parámetro que se reciba.
     * @param valorNuevo, el nuevo valor de la entrada
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
              if (endE.getValor() == null || (endE.getIsConected() == false && endE.getValorEntradaConectado()== true || endE.getValorEntradaConectado()== false)){
                  try {
                      crearVentana();
                  } catch (IOException ex) {
                     
                  }
              }else{
              }
          }
          if(t.getButton()== MouseButton.SECONDARY){
              if(endE.getIsConected() == true){
                  endE.layoutXProperty().removeListener((ChangeListener)entradaConectada.getUserData());
                  endE.layoutYProperty().removeListener((ChangeListener)entradaConectada.getUserData());
                  endE.getCompuertaPadre().getCompuerta().layoutXProperty().removeListener((ChangeListener)entradaConectada.getUserData());
                  endE.getCompuertaPadre().getCompuerta().layoutYProperty().removeListener((ChangeListener)entradaConectada.getUserData());
                  while(endE.getEntradasConectadas().getSize() >0){
                      
                      endE.getEntradasConectadas().getValor(0).getParent().layoutXProperty().removeListener((ChangeListener)compuertaConectada.getUserData());
                      endE.getEntradasConectadas().getValor(0).getParent().layoutYProperty().removeListener((ChangeListener)compuertaConectada.getUserData());
                      endE.getEntradasConectadas().getValor(0).setIsConected(false);
                      endE.getEntradasConectadas().getValor(0).setValorEntradaConectado(false);
                      endE.getEntradasConectadas().eliminarInicio();
                      Main.getControlador().actualizarEtiquetas();
                  }
                  endE.setLayoutY(endE.getCenterY()+10);
                  endE.setValor(null);
                  endE.setIsConected(false);
                  endE.setValorEntradaConectado(false);
                  Main.getControlador().actualizarEtiquetas();
              }
          }

        }
      };
      
    /**
     * Método que retorna el circulEntrada correspondiente
     * @return endE, el circuloEntrada
     */
    public CirculoEntrada getEndE(){
          return endE;
      }

    /**
     * Método que permite accesar a la línea a la que está conectada cada entrada de la compuerta.
     * @return Line, retorna la linea
     */
    public Line getLinea(){
          return lineE;
      }
      
    /**
     * Método que retorna como valor la compuerta a la cual pertenece la entrada, conocida como compuerta padre.
     * @return Compuerta, la compuerta a la que pertenece
     */
    public Compuerta getCompuertaPadre(){
          return compuertaPadre;
      }
      
    /**
     * Método que permite la modificación del valor de la variable compuertaPadre perteneciente a cada entrada,
     * a través de un parámetro de tipo Compuerta
     * @param compuertaPadre, la compuerta a la que pertenece
     */
    public void setCompuertaPadre(Compuerta compuertaPadre){
          this.compuertaPadre = compuertaPadre;
      }
      
    /**
     * Método que calcula posibles colores diferentes para cada línea de la interfaz,
     * además verifica que estos colores no se repitan en ninguna línea por más similares que sean estos colores.
     * Código base tomado de https://www.quora.com/How-can-I-randomize-colors-in-Java
     * @return color, el color a asignar
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
     * Método desde el cual se crea la venta donde se puede asignar un valor a cada entrada según corresponda.
     * Código base tomado de https://youtu.be/3G8nTLujI5U.
     * @throws IOException error
     */
    public void crearVentana() throws IOException{
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ValorEntradas.fxml"));
        Parent ventana = (Parent) loader.load();
        ControllerValorEntradas controlador= (ControllerValorEntradas)loader.getController();
        controlador.ACambiar(endE);
        Stage stage = new Stage();
        controlador.enviarStage(stage);
        stage.setScene(new Scene(ventana));
        
        stage.show();
    }
}