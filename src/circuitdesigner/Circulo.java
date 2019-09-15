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
class Circulo extends Circle {
    protected Label etiqueta;
    protected Valores valor;
    protected boolean isConected;
    protected Imagen compuertaPadre;
    // ListLinked<Entrada> entradas = Facade.entradas;
    
    Circulo(String etiqueta, Valores valor) {
      super(3);
      this.etiqueta = new Label(etiqueta);
      this.valor = valor;
      this.isConected = false;
      //x.bind(layoutXProperty());
      //y.bind(layoutYProperty());
      //this.etiqueta.setLayoutX(x.get());
      //this.etiqueta.setLayoutY(y.get());
      //CircuitDesigner.getControlador().getRoot().getChildren().addAll(this.etiqueta);   
    }
    
    public Label getEtiqueta(){
        return etiqueta;
    }
    public Valores getValor(){
        return valor;
    }
    public void setValor(Valores valor){
        this.valor = valor;
        System.out.println("Setea valor circulo");
        compuertaPadre.revisarEntradas();
    }
    public void setIsConected(boolean valor){
        isConected = valor;
    }
    public boolean getIsConected(){
        return isConected;
    }
    public void setCompuertaPadre(Imagen compuertaPadre){
        this.compuertaPadre = compuertaPadre;
    }
    public Imagen getCompuertaPadre(){
        return compuertaPadre;
    }
}
