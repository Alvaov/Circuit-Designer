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
 * @see CirculoEntrada es la clase que crea aquellos círculos que corresponden
 * a entradas de la compuerta.
 * @author allva
 */
public class CirculoEntrada extends Circulo{
    
    /**
     * @see Clase que hereda de la clase Círculo, implementada para identificar si un círculo es una salida
     * o una entrada.
     * @param etiqueta
     * @param valor
     */
    public CirculoEntrada(String etiqueta, Valores valor) {
        super(etiqueta, valor);
    }
    

    
}
