/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import listlinked.ListLinked;
import operadores.Operadores;

/**
 *
 * @author allva
 */
class Imagen extends ImageView{
    private Operadores compuerta;
    private ImageView imagenVista;
    private Entrada entrada;
    ListLinked<Entrada> entradas = Facade.getListaEntradas();
    //int e = Facade.getCantidadDeEntradas();
    //int s = Facade.getCantidadDeSalidas();
      
    private double orgSceneX, orgSceneY, newX, newY;
    private int cantidadDeEntradas;
    private Anchor start,end, startE;
    private Line line;
    private static Delta dragDelta = new Delta();

    private DoubleProperty inicioY = new SimpleDoubleProperty (orgSceneY+23);
    private DoubleProperty inicioX = new SimpleDoubleProperty (orgSceneX+45);
    private DoubleProperty endX   = new SimpleDoubleProperty(orgSceneX+75);
    private DoubleProperty endY   = new SimpleDoubleProperty(orgSceneY+23);
    private DoubleProperty startx = new SimpleDoubleProperty(orgSceneX+23);
    private DoubleProperty starty = new SimpleDoubleProperty(orgSceneY+22);
      
      public Imagen(String ruta, Operadores compuerta, int cantidadDeEntradas){
          this.compuerta = compuerta;
          this.cantidadDeEntradas = cantidadDeEntradas;
          
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);
          imagenVista.setOnMousePressed(MousePressed);
          imagenVista.setOnMouseDragged(MouseDragged);
     
          end      = new Anchor(Color.TOMATO,    endX,   endY,"o<"+Facade.s+">");
          Facade.s++;
          start    = new Anchor(Color.PALEGREEN, inicioX, inicioY,"");
          line     = new BoundLine(inicioX,inicioY, endX, endY);
          
          startE   = new Anchor(Color.PALEGREEN, startx, starty,"");
          
          
          int y = 0;
          for (int i = 0; i < cantidadDeEntradas; i++){
              entrada = new Entrada(imagenVista,startx,starty,Facade.e);
              entrada.endE.setCenterY(y);
              entradas.añadirFinal(entrada);      
              Facade.e++;
              y += 7;
          }
          
          CircuitDesigner.getControlador().getRoot().getChildren().addAll(imagenVista,end,start,line);
          
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

            imagenVista.setY(newY);
            start.setCenterY(newY+23);
            
            for (int i = 0; i <= cantidadDeEntradas; i++){
                startE.setCenterY(newY+22);
                startE.setCenterX(newX+23);
            }
        }
    };
    
    public static Delta getDelta(){
        return dragDelta;
    }
    
  }