/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import operadores.Valores;

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

      
      public Entrada(ImageView imagenVista, DoubleProperty startx, DoubleProperty starty,int i){
          this.imagenVista = imagenVista;
          valor = null;
          DoubleProperty endy = new SimpleDoubleProperty(0);
          DoubleProperty endx = new SimpleDoubleProperty(0);
          endE      = new Anchor(Color.TOMATO,    endx,   endy,"i<"+ i +">");
          lineE     = new BoundLine(startx, starty, endx, endy);
          CircuitDesigner.getControlador().getRoot().getChildren().addAll(endE,lineE);   
      }

      public Valores getEntrada(){
          return valor;
      }
  }
