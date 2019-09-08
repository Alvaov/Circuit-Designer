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
import operadores.Valores;

/**
 *
 * @author allva
 */
class Anchor extends Circle { 
    private Delta dragDelta = Imagen.getDelta();
    private Double newX, newY;
    private Label etiqueta;
    private Valores valor;
    // ListLinked<Entrada> entradas = Facade.entradas;
    
    Anchor(Color color, DoubleProperty x, DoubleProperty y, String etiqueta, Valores valor) {
      super(x.get(), y.get(), 3);
      this.etiqueta = new Label(etiqueta);
      this.valor = valor;
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.etiqueta.setLayoutX(x.get());
      this.etiqueta.setLayoutY(y.get());
      CircuitDesigner.getControlador().getRoot().getChildren().addAll(this.etiqueta);   
    }
    
    public Label getEtiqueta(){
        return etiqueta;
    }
    public Valores getValor(){
        return valor;
    }
    public void setValor(Valores valor){
        this.valor = valor;
    }

}
