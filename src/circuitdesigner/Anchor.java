/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import listlinked.ListLinked;

/**
 *
 * @author allva
 */
class Anchor extends Circle { 
    Delta dragDelta = Imagen.getDelta();
    Double newX, newY;
    Label etiqueta;
    ListLinked<Entrada> entradas = Facade.getListaEntradas();
    Anchor(Color color, DoubleProperty x, DoubleProperty y, String etiqueta) {
      super(x.get(), y.get(), 3);
      this.etiqueta = new Label(etiqueta);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.etiqueta.setLayoutX(x.get());
      this.etiqueta.setLayoutY(y.get());
      CircuitDesigner.getControlador().getRoot().getChildren().addAll(this.etiqueta);   
      enableDrag();
    }

    public void enableDrag() {
      
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          dragDelta.x = getCenterX() - t.getX();
          dragDelta.y = getCenterY() - t.getY();

        }
      });
      setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            newX = t.getX() + dragDelta.x;
            etiqueta.setLayoutX(newX);
            setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            etiqueta.setLayoutY(newY);
            setCenterY(newY); 
            
            
            for (int i=0; i < entradas.getSize(); i++){
                if (newX == entradas.getValor(i).endE.getCenterX()){
                   // System.out.println("son iguales");
                }else{
                    if (entradas.getValor(i).endE.getCenterX() <= newX && newX <= entradas.getValor(i).endE.getCenterX()){
                        
                        if (entradas.getValor(i).endE.getCenterY() <= newY && newY <= entradas.getValor(i).endE.getCenterY()){
                          //  System.out.println("choqué");
                        }
                    }
                }
            }
        }
      });
    } 
  }
