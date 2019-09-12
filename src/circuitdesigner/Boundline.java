/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Line;

/**
 *
 * @author allva
 */
class BoundLine extends Line {
    
    BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startYProperty().set(startY.getValue());
      startXProperty().set(startX.getValue());
      endYProperty().set(endY.getValue());
      endXProperty().set(endX.getValue());
      //startYProperty().setValue(startY.getValue());
      //startXProperty().setValue(startX.getValue());
      //endYProperty().setValue(endY.getValue());
      //endXProperty().setValue(endX.getValue());
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);
      
    }
    
  }