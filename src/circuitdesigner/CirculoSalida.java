/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class CirculoSalida extends Circulo{
    
    public CirculoSalida(String etiqueta, Valores valor) {
        super(etiqueta, valor);
    }
    @Override
    public void setValor(Valores valor){
        this.valor = valor; 
         
    }

    
}
