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
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;

/**
 *
 * @author allva
 */
public class Entrada extends Observable{
      
      private CirculoEntrada endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      private Imagen compuertaConectada;
      private Circulo circulo;
      private Imagen compuertaPadre;
      public Entrada(ImageView imagenVista, Circulo circulo,int i){
          this.imagenVista = imagenVista;
          this.circulo = circulo;
          valor = null;
          endE      = new CirculoEntrada("i<"+ i +">",valor);
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
                  endE.setUserData(listener);
                  System.out.println("Conectar");
                  endE.setValor(((CirculoSalida) event.getGestureSource()).getValor());
                  ((CirculoSalida) event.getGestureSource()).setEntradasConectadas(endE);
                  ((CirculoSalida)event.getGestureSource()).getParent().setMouseTransparent(false);
                  ((CirculoSalida)event.getGestureSource()).getParent().toFront();
                  ((CirculoSalida)event.getGestureSource()).setIsConected(true);
                  endE.setIsConected(true);
                  endE.layoutXProperty().addListener(listener);
                  endE.layoutYProperty().addListener(listener);
                  endE.getCompuertaPadre().getCompuerta().layoutXProperty().addListener(listener);
                  endE.getCompuertaPadre().getCompuerta().layoutYProperty().addListener(listener);
                  
              }else if (event.getGestureSource() instanceof CirculoEntrada){
                  
                  if (event.getGestureSource() instanceof Circulo){
                      System.out.println("Conectar multientradas");
                      endE.setValor(((CirculoEntrada)event.getGestureSource()).getValor());
                      ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
                  }
                  
                  System.out.println("entrada conectada");
              }
          });
          endE.setOnMouseClicked(cambiarValor);
      }

      public Valores getValor(){
          return endE.getValor();
      }
      
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
                  //Ya tiene un valor asignado por una salida;
              }
          }
          System.out.println(t.isSecondaryButtonDown());
          System.out.println(t.isPrimaryButtonDown());
          if(t.isSecondaryButtonDown()){
              //if(endE.getValor() != null){
              System.out.println("eliminar");
                  endE.setCenterY(endE.getCenterY()+15);
                  endE.setValor(null);
                  endE.setIsConected(false);
                  compuertaConectada = null;
              //}
          }

        }
      };
      
      public CirculoEntrada getEndE(){
          return endE;
      }
      public Line getLinea(){
          return lineE;
      }
      
      public Imagen getCompuertaPadre(){
          return compuertaPadre;
      }
      
      public void setCompuertaPadre(Imagen compuertaPadre){
          this.compuertaPadre = compuertaPadre;
      }
      
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        ListLinked<Color> coloresUsados = Factory.getColores();
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorLinea();
            }
        }
        return color;
    }
    
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