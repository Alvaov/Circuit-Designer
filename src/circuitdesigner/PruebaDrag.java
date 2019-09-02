/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;


import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class PruebaDrag {
    
    double orgSceneX, orgSceneY, newX, newY;
    int cantidadDeEntradas;
    String ruta;
    Image image;
    ImageView imageView;
    Anchor start;
    Anchor end;
    Line line;
    
    Delta dragDelta = new Delta();
    
    DoubleProperty inicioY = new SimpleDoubleProperty (orgSceneY+55);
    DoubleProperty inicioX = new SimpleDoubleProperty (orgSceneX+145);
    DoubleProperty endX   = new SimpleDoubleProperty(300);
    DoubleProperty endY   = new SimpleDoubleProperty(200);
    
    public PruebaDrag(String ruta,int cantidadDeEntradas){
        
    this.cantidadDeEntradas = cantidadDeEntradas;
    this.ruta = ruta;
    image = new Image(ruta);
    imageView = new ImageView(image);

    end      = new Anchor(Color.TOMATO,    endX,   endY);
    start    = new Anchor(Color.PALEGREEN, inicioX, inicioY);
    line     = new BoundLine(inicioX,inicioY, endX, endY);
    
   // getRoot().getChildren().addAll(imageView,end,start,line);
    
    }

   public void algo() {
      
    imageView.setY(orgSceneY);
    imageView.setX(orgSceneX);
    imageView.setOnMousePressed(MousePressed);
    imageView.setOnMouseDragged(MouseDragged);  

    
  }

  class BoundLine extends Line {
    BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);

    }
  }

  class Anchor extends Circle { 
    Anchor(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 2);

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
    // records relative x and y co-ordinates.
    public class Delta { double x, y; }
   
 
  EventHandler<MouseEvent> MousePressed = 
        new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            dragDelta.x = imageView.getX() - t.getX();
            dragDelta.y = imageView.getY() - t.getY();
        }
        };
     
    EventHandler<MouseEvent> MouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {

            newX = t.getX() + dragDelta.x;
            newY = t.getY() + dragDelta.y;
            
            imageView.setX(newX);
            start.setCenterX(newX+145);

            imageView.setY(newY);
            start.setCenterY(newY+55);
        }
    };
  
}
    
