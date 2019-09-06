/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import static circuitdesigner.ControllerCircuito.entradas;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import operadores.Valores;
import listlinked.ListLinked;

/**
 *
 * @author allva
 */
class Entrada{
      
      Anchor endE;
      ImageView imagenVista;
      Line lineE;
      Valores valor;
      DoubleProperty starty, startx;
      Delta dragDelta = Imagen.getDelta();
      Double newX,newY;
      //ListLinked<Entrada> entradas = Facade.entradas;
      
      public Entrada(ImageView imagenVista, DoubleProperty startx, DoubleProperty starty,int i){
          this.imagenVista = imagenVista;
          valor = null;
          DoubleProperty endy = new SimpleDoubleProperty(0);
          DoubleProperty endx = new SimpleDoubleProperty(0);
          endE      = new Anchor(Color.TOMATO,    endx,   endy,"i<"+ i +">");
          lineE     = new BoundLine(startx, starty, endx, endy);
          CircuitDesigner.getControlador().getRoot().getChildren().addAll(endE,lineE);   
          endE.setOnMousePressed(MousePressed);
          endE.setOnMouseDragged(MouseDragged);

      }

      public Valores getEntrada(){
          return valor;
      }
      public void colisión(){
                //System.out.println(entradas.getValor(0));
                for(int i = 0; i < entradas.getSize(); i++){
                    if (entradas.getValor(i).endE == this.endE){
                        continue;
                    }
                    else{
                        if(entradas.getValor(i).endE.getCenterX() == this.endE.getCenterX() && entradas.getValor(i).endE.getCenterY() == this.endE.getCenterY()){
                            System.out.println("choqué");
                        }
                    }
                }
      }

      
      EventHandler<MouseEvent> MousePressed = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          dragDelta.x = endE.getCenterX() - t.getX();
          dragDelta.y = endE.getCenterY() - t.getY();

        }
      };
        EventHandler<MouseEvent> MouseDragged = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            newX = t.getX() + dragDelta.x;
            endE.etiqueta.setLayoutX(newX);
            endE.setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            endE.etiqueta.setLayoutY(newY);
            endE.setCenterY(newY);
            colisión();
            }
        };
    
}