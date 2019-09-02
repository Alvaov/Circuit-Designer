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
    Imagen imagen;
    String ruta;
    Anchor start, end;
    Line line;
    
    Delta dragDelta = new Delta();
    
    DoubleProperty inicioY = new SimpleDoubleProperty (orgSceneY+23);
    DoubleProperty inicioX = new SimpleDoubleProperty (orgSceneX+45);
    DoubleProperty endX   = new SimpleDoubleProperty(orgSceneX+75);
    DoubleProperty endY   = new SimpleDoubleProperty(orgSceneY+23);
    
    public PruebaDrag(String ruta,int cantidadDeEntradas){

        this.cantidadDeEntradas = cantidadDeEntradas;
        imagen = new Imagen(ruta, new AndOperator(cantidadDeEntradas));
       // imagen.setOnMousePressed(MousePressed);
      //  imagen.setOnMouseDragged(MouseDragged);
        
        for (int i = 0; i < cantidadDeEntradas; i++){
            Entradas nuevaEntrada = new Entradas();
            DoubleProperty endy = new SimpleDoubleProperty(0);
            DoubleProperty endx = new SimpleDoubleProperty(0);
            DoubleProperty startx = new SimpleDoubleProperty(orgSceneX);
            DoubleProperty starty = new SimpleDoubleProperty(orgSceneY);
            Anchor endE      = new Anchor(Color.TOMATO,    endx,   endy);
            Anchor startE    = new Anchor(Color.PALEGREEN, startx, starty);
            Line lineE     = new BoundLine(inicioX,inicioY, endx, endy);
            CircuitDesigner.getController().getRoot().getChildren().addAll(endE,startE,lineE);
        }
           
       
        end      = new Anchor(Color.TOMATO,    endX,   endY);
        start    = new Anchor(Color.PALEGREEN, inicioX, inicioY);
        line     = new BoundLine(inicioX,inicioY, endX, endY);
    
         CircuitDesigner.getController().getRoot().getChildren().addAll(imagen,end,start,line);

    }
    
  class Imagen extends ImageView{
      Operadores compuerta;
      ImageView imagenVista;
      public Imagen(String ruta, Operadores compuerta){
          
          this.compuerta = compuerta;
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);
          imagenVista.setOnMousePressed(MousePressed);
          imagenVista.setOnMouseDragged(MouseDragged);

          CircuitDesigner.getController().getRoot().getChildren().add(imagenVista);
          
      }
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
    // records relative x and y co-ordinates.
    public class Delta { double x, y; }
   
 
  EventHandler<MouseEvent> MousePressed = 
        new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            dragDelta.x = imagen.imagenVista.getX() - t.getX();
            dragDelta.y = imagen.imagenVista.getY() - t.getY();
        }
        };
     
    EventHandler<MouseEvent> MouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {

            newX = t.getX() + dragDelta.x;
            newY = t.getY() + dragDelta.y;
            
            imagen.imagenVista.setX(newX);
            start.setCenterX(newX+45);
                
            imagen.imagenVista.setY(newY);
            start.setCenterY(newY+23);
        }
    };
  
}
    
