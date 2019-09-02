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
    
    DoubleProperty inicioY = new SimpleDoubleProperty (orgSceneY+23);
    DoubleProperty inicioX = new SimpleDoubleProperty (orgSceneX+45);
    DoubleProperty endX   = new SimpleDoubleProperty(orgSceneX+75);
    DoubleProperty endY   = new SimpleDoubleProperty(orgSceneY+23);
    
    public PruebaDrag(String ruta,int cantidadDeEntradas){

        this.cantidadDeEntradas = cantidadDeEntradas;
        this.ruta = ruta;
        image = new Image(ruta);
        imageView = new ImageView(image);
        imageView.setFitWidth(65.0);
        imageView.setFitHeight(40.0);
        imageView.setX(orgSceneX);
        imageView.setY(orgSceneY);
        imageView.setOnMousePressed(MousePressed);
        imageView.setOnMouseDragged(MouseDragged);
        
        int y = 0;
        for (int i = 0; i < cantidadDeEntradas; i++){
            
            Entradas nuevaEntrada = new Entradas();
            DoubleProperty endy = new SimpleDoubleProperty(y);
            DoubleProperty endx = new SimpleDoubleProperty(0);
            DoubleProperty startx = new SimpleDoubleProperty(orgSceneX);
            DoubleProperty starty = new SimpleDoubleProperty(orgSceneY);
            Anchor endE      = new Anchor(Color.TOMATO,    endx,   endy);
            Anchor startE    = new Anchor(Color.PALEGREEN, inicioX, inicioY);
            Line lineE     = new BoundLine(inicioX,inicioY, endx, endy);
            CircuitDesigner.getController().getRoot().getChildren().addAll(endE,startE,lineE);
            y += 7;
        }
       
        end      = new Anchor(Color.TOMATO,    endX,   endY);
        start    = new Anchor(Color.PALEGREEN, inicioX, inicioY);
        line     = new BoundLine(inicioX,inicioY, endX, endY);
    

   System.out.println("entróaquisi");
   CircuitDesigner.getController().getRoot().getChildren().addAll(imageView,end,start,line);
   System.out.println("entróaqui");
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
            start.setCenterX(newX+45);

            imageView.setY(newY);
            start.setCenterY(newY+23);
        }
    };
  
}
    
