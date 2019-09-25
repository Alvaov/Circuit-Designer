/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import listlinked.ListLinked;
import operadores.Valores;

/**
 * @see CirculoEntrada es la clase que crea aquellos círculos que corresponden
 * a entradas de la compuerta.
 * @author allva
 */
public class CirculoEntrada extends Circulo{
    
    private CirculoEntrada valorConectado;
    private ListLinked<CirculoEntrada> listaEntradas = new ListLinked<>();
    /**
     * @see Clase que hereda de la clase Círculo, implementada para identificar si un círculo es una salida
     * o una entrada.
     * @param etiqueta
     * @param valor
     */
    public CirculoEntrada(Valores valor) {
        super(valor);
    }
    
    /**
     * @see método que permite cambiar el valor actual del círculo
     * @param Valores valor
     */
    @Override
    public void setValor(Valores valor){
        System.out.println("cambiar entrada");
        this.valor = valor;
        if(valorConectado != null){
            valorConectado.setValor(valor);
        }if(listaEntradas.getSize() > 0){
            System.out.println("entradas conectadas cambiadas");
            for(int i = 0; i < listaEntradas.getSize();i++){
                listaEntradas.getValor(i).setValor(valor);
            }
        }
    }
    
    public void setValorConectado(CirculoEntrada conectado){
        valorConectado = conectado;
    }
    
    public void añadirEntradas(CirculoEntrada entrada){
        listaEntradas.añadirFinal(entrada);
    }

    public ListLinked<CirculoEntrada> getEntradasConectadas(){
        return listaEntradas;
    }
}
