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
      
      private Anchor endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      private Delta dragDelta = Imagen.getDelta();
      private Double newX,newY;
      private Imagen compuertaConectada;
      public Entrada(ImageView imagenVista, DoubleProperty startx, DoubleProperty starty,int i){
          this.imagenVista = imagenVista;
          valor = null;
          DoubleProperty endy = new SimpleDoubleProperty(0);
          DoubleProperty endx = new SimpleDoubleProperty(0);
          endE      = new Anchor(Color.TOMATO,    endx,   endy,"i<"+ i +">",valor);
          lineE     = new BoundLine(startx, starty, endx, endy);
          lineE.setStroke(colorLinea());
          System.out.println(lineE.getFill());
          CircuitDesigner.getControlador().getRoot().getChildren().addAll(endE,lineE);  
          endE.setOnDragDetected(MouseDetected);
          endE.setOnMousePressed(MousePressed);
          endE.setOnMouseDragged(MouseDragged);
          endE.setOnMouseDragReleased(DragRelease);
          endE.setOnMouseClicked(cambiarValor);
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
              Anchor fin = imagen.getEnd();
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
      EventHandler<MouseEvent> MousePressed = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            dragDelta.x = endE.getCenterX() - t.getX();
            dragDelta.y = endE.getCenterY() - t.getY();

        }
      };
      EventHandler<MouseEvent> MouseDragged = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            newX = t.getX() + dragDelta.x;
            endE.getEtiqueta().setLayoutX(newX);
            endE.setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            endE.getEtiqueta().setLayoutY(newY);
            endE.setCenterY(newY);
            colisiónE();
            }
        };
      
      EventHandler<MouseDragEvent> DragRelease = new EventHandler<MouseDragEvent>() {

          @Override
          public void handle(MouseDragEvent event) {
              System.out.println(event.getGestureSource());
              System.out.println(event.getSource());
              System.out.println(event.getTarget());
            if(event.getGestureSource() == event.getSource()){
                System.out.println("algo");
            };
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
      
      public Anchor getEndE(){
          return endE;
      }
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        return Color.color(red, green, blue);
    }
}