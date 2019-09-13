/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import operadores.Valores;
import listlinked.ListLinked;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;

/**
 *
 * @author allva
 */
public class Entrada extends Observable{
      
      private CirculoEntrada endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      private Double newX,newY;
      private Imagen compuertaConectada;
      private Circulo circulo;
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
              System.out.println("release entrada");
              if (event.getGestureSource() instanceof CirculoSalida){
                  System.out.println("Conectar");
                  endE.setValor(((CirculoSalida) event.getGestureSource()).getValor());
              }
              ((CirculoEntrada)event.getGestureSource()).setMouseTransparent(false);
          });
          endE.setOnMouseClicked(cambiarValor);
      }

      public Valores getValor(){
          return endE.getValor();
      }
      
      public void setValor(Valores valorNuevo){
          //revisarEntradas();
          endE.setValor(valorNuevo);
      }
      
      public void colisiónE(){
          ListLinked<Imagen> circuito = Facade.getCircuito();
          
          for (int c = 0; c < circuito.getSize(); c++){
              Imagen imagen = circuito.getValor(c);
              CirculoSalida fin = imagen.getEnd();
              for(int i = 0; i < imagen.getEntradas().getSize(); i++){
                  Entrada entrada = imagen.getEntrada(i);
                  if (entrada.endE == this.endE){                     
                      continue;
                        }
                  if (fin.getCenterX()+2 >= this.endE.getCenterX()&& fin.getCenterX()-2 <= this.endE.getCenterX()){
                      if(fin.getCenterY()+2 >= this.endE.getCenterY() && fin.getCenterY()-2 <= this.endE.getCenterY()){
                          
                          this.endE.setCenterX(fin.getCenterX());
                          this.endE.setCenterY(fin.getCenterY());
                          }
                  }
                    else{
                      if(entrada.endE.getCenterX()+4 >= this.endE.getCenterX() && entrada.endE.getCenterX()-4 <= this.endE.getCenterX()){
                          if(entrada.endE.getCenterY()+4 >= this.endE.getCenterY() && entrada.endE.getCenterY()-4 <= this.endE.getCenterY()){
                              this.endE.setCenterX(entrada.endE.getCenterX());
                              this.endE.setCenterY(entrada.endE.getCenterY());
                          }
                      }
                   }
                }
            }
      }

      EventHandler<MouseEvent> MouseDetected = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            endE.startFullDrag();
            endE.setMouseTransparent(true);
            System.out.println("hey");
        }
      };

      EventHandler<MouseEvent> cambiarValor = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          if (t.getClickCount() == 2){
              if (endE.getValor() == null){
                  System.out.println("valor a añadir");
                  //tirar ventana con opciones
                  //setear valor
              }else{
                  System.out.println("no se puede añadir un valor");
                  //Ya tiene un valor asignado por una salida;
              }
          }
          if(t.isSecondaryButtonDown()){
              //if(endE.getValor() != null){
              System.out.println("eliminar");
                  endE.setCenterY(endE.getCenterY()+15);
                  endE.setValor(null);
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
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        return Color.color(red, green, blue);
    }
}