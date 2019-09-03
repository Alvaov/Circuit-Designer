/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;


import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class PruebaDrag {
    
    double orgSceneX, orgSceneY, newX, newY;
    int cantidadDeEntradas;
    String ruta;
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

        ImageView imagen = new Imagen(ruta,new AndOperator(cantidadDeEntradas));

    }

    
  class Imagen extends ImageView{
      Operadores compuerta;
      ImageView imagenVista;
      Entrada entrada;
      ListLinked<Entrada> entradas;
      public Imagen(String ruta, Operadores compuerta){
          this.entradas = new ListLinked<>();
          this.compuerta = compuerta;
          
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);
          imagenVista.setOnMousePressed(MousePressed);
          imagenVista.setOnMouseDragged(MouseDragged);
          
          end      = new Anchor(Color.TOMATO,    endX,   endY);
          start    = new Anchor(Color.PALEGREEN, inicioX, inicioY);
          line     = new BoundLine(inicioX,inicioY, endX, endY);
          int y = 0;
          
          for (int i = 0; i < cantidadDeEntradas; i++){
              entrada = new Entrada(imagenVista);
              entrada.endE.setCenterY(y);
              entradas.añadirFinal(entrada);
              y += 7;
          }
          System.out.println("gola");
        

          CircuitDesigner.getController().getRoot().getChildren().addAll(imagenVista,end,start,line);
          
      }
      EventHandler<MouseEvent> MousePressed = 
        new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            dragDelta.x = imagenVista.getX() - t.getX();
            dragDelta.y = imagenVista.getY() - t.getY();
        }
        };
     
    EventHandler<MouseEvent> MouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {

            newX = t.getX() + dragDelta.x;
            newY = t.getY() + dragDelta.y;
            
            imagenVista.setX(newX);
            start.setCenterX(newX+45);
            
            //entrada.startE.setCenterX(newX+23);

            imagenVista.setY(newY);
            start.setCenterY(newY+23);
            
            for (int i = 0; i <= cantidadDeEntradas; i++){
                entradas.getValor(i).startE.setCenterY(newY+22);
                entradas.getValor(i).startE.setCenterX(newX+23);
            }
        }
    };
  }

  class Entrada{
      
      //Entradas entrada;
      Anchor endE,startE;
      ImageView imagenVista;
      Line lineE;
      Boolean valor;
      
      public Entrada(ImageView imagenVista){
          this.imagenVista = imagenVista;
          //entrada = new Entradas();
          DoubleProperty endy = new SimpleDoubleProperty(0);
          DoubleProperty endx = new SimpleDoubleProperty(0);
          DoubleProperty startx = new SimpleDoubleProperty(orgSceneX+23);
          DoubleProperty starty = new SimpleDoubleProperty(orgSceneY+22);
          endE      = new Anchor(Color.TOMATO,    endx,   endy);
          startE    = new Anchor(Color.PALEGREEN, startx, starty);
          lineE     = new BoundLine(startx, starty, endx, endy);
          endE.setOnMouseClicked(MouseClicked);
          CircuitDesigner.getController().getRoot().getChildren().addAll(startE,endE,lineE);   
      }
      EventHandler<MouseEvent> MouseClicked = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            valor = false;
            }
        };
    
      public Boolean getEntrada(){
          return valor;
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
    
  class Delta { double x, y; }
}
    
