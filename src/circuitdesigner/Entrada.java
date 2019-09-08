/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

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
public class Entrada{
      
      private Anchor endE;
      private ImageView imagenVista;
      private Line lineE;
      private Valores valor;
      //private DoubleProperty starty, startx;
      private Delta dragDelta = Imagen.getDelta();
      private Double newX,newY;
      
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

      public Valores getValor(){
          return valor;
      }
      
      public void setValor(Valores valorNuevo){
          valor = valorNuevo;
      }
      
      public Anchor colisiónE(){
          ListLinked<Imagen> circuito = Facade.getCircuito();
          
          for (int c = 0; c < circuito.getSize(); c++){
              Imagen imagen = circuito.getValor(c);
              Anchor fin = imagen.getEnd();
              for(int i = 0; i < imagen.getEntradas().getSize(); i++){
                  Entrada entrada = imagen.getEntrada(i);
                  if (entrada.endE == this.endE){                     
                      continue;
                        }
                  if (fin.getCenterX() == this.endE.getCenterX() && fin.getCenterY() == this.endE.getCenterY()){
                      fin.setCenterY(this.endE.getCenterY());
                      valor = imagen.getSalida();
                      return fin;
                      }
                    else{
                      if(entrada.endE.getCenterX() == this.endE.getCenterX() && entrada.endE.getCenterY() == this.endE.getCenterY()){
                         System.out.println("choqué");
                         return entrada.endE;
                         }
                       }
                  }
            }
          return null;
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
            endE.getEtiqueta().setLayoutX(newX);
            endE.setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            endE.getEtiqueta().setLayoutY(newY);
            endE.setCenterY(newY);
            colisiónE();

            if (colisiónE() != null){
                colisiónE().setCenterX(newX);
                colisiónE().setCenterY(newY);
            }
            }
        };

      
      public Anchor getEndE(){
          return endE;
      }
    
}