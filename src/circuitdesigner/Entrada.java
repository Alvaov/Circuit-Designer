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
public class Entrada{
      
      private CirculoEntrada endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      private Double newX,newY;
      private Imagen compuertaConectada;
      public Entrada(ImageView imagenVista, DoubleProperty startx, DoubleProperty starty,int i){
          this.imagenVista = imagenVista;
          valor = null;
          DoubleProperty endy = new SimpleDoubleProperty(0);
          DoubleProperty endx = new SimpleDoubleProperty(0);
          endE      = new CirculoEntrada(endx,   endy,"i<"+ i +">",valor);
          lineE     = new BoundLine(startx, starty, endx, endy);
          lineE.setStroke(colorLinea());
          //CircuitDesigner.getControlador().getAnchor().getChildren().addAll(endE,lineE);  
          endE.setOnDragDetected(MouseDetected);
          endE.setOnMouseDragReleased(event->{
              if (event.getGestureSource() instanceof CirculoSalida){
                  System.out.println("Conectar");
              }
          });
          //endE.setOnMouseClicked(cambiarValor);
      }

      public Valores getValor(){
          return endE.getValor();
      }
      
      public void setValor(Valores valorNuevo){
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
            System.out.println("hey");
        }
      };

      EventHandler<MouseEvent> cambiarValor = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          if (t.getClickCount() == 2){
              if (endE.getValor() == null){
                  //tirar ventana con opciones
                  //setear valor
              }else{
                  //Ya tiene un valor asignado por una salida;
              }
          }
          if(t.isSecondaryButtonDown()){
              //if(endE.getValor() != null){
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