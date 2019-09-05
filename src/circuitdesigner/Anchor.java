/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author allva
 */
class Anchor extends Circle { 
    Delta dragDelta = Imagen.getDelta();
    Double newX, newY;
    Anchor(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 3);

      x.bind(centerXProperty());
      y.bind(centerYProperty());
      
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
            setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            setCenterY(newY);        
        }
      });
    } 
  }
