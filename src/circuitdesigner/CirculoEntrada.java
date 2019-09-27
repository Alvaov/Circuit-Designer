/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import listlinked.ListLinked;
import operadores.Valores;

/**
 * Clase que corresponde aquellos círculos que corresponden
 * a entradas de la compuerta.
 * @author allva
 */
public class CirculoEntrada extends Circulo{
    
    private CirculoEntrada valorConectado;
    private ListLinked<CirculoEntrada> listaEntradas = new ListLinked<>();
    private Boolean conexiónEntreEntradas = false;
    private CirculoSalida salidaConectada;
    /**
     * Clase que hereda de la clase Círculo, implementada para identificar si un círculo es una salida
     * o una entrada.
     * @param valor, valor con el que iniciará el círculo 
     */
    public CirculoEntrada(Valores valor) {
        super(valor);
    }
    
    /**
     * Método que permite cambiar el valor actual del círculo, así como de aquellas que estén conectados
     * @param valor, valor que se asignará a la variable
     */
    @Override
    public void setValor(Valores valor){
        this.valor = valor;
        if(valorConectado != null){
            valorConectado.setValor(valor);
        }if(listaEntradas.getSize() > 0){
            for(int i = 0; i < listaEntradas.getSize();i++){
                listaEntradas.getValor(i).setValor(valor);
            }
        }
    }
    
    /**
     * Método que asigna un CirculoEntrada al atributo interno del CirculoEntrada en cuestión, utilizado para poder realizar los cambios
     * entre las entradas internas del circuito del usuario y aquellas que sí son directamente modificables.
     * @param conectado, circulo de entrada que se debe cambiar en el circuito encapsulado interno
     */
    public void setValorConectado(CirculoEntrada conectado){
        valorConectado = conectado;
    }
    
    /**
     * Método booleano que cambia de valor si una entrada está conectada a otra entrada de alguna compuerta. 
     * @param valor, indicador de si hay o no entradas conectadas
     */
    public void setValorEntradaConectado(Boolean valor){
        conexiónEntreEntradas = valor;
    }
    
    /**
     * Método booleano que permite identificar si la entrada está conectada a otra entrada de alguna compuerta. 
     * @return conexiónEntreEntrada, valor booleano que indica si hay o no conexión entre entradas
     */
    public Boolean getValorEntradaConectado(){
        return conexiónEntreEntradas;
    }
    
    /**
     * Método que permite añadir un elemento, siempre al final, a la lista de entradas conectadas.
     * @param entrada, la entrada que se conectó
     */
    public void añadirEntradas(CirculoEntrada entrada){
        listaEntradas.añadirFinal(entrada);
    }

    /**
     * Método que permite obtener la lista de entradas conectadas.
     * @return listaEntradas, la lista de entradas conectadas que posee
     */
    public ListLinked<CirculoEntrada> getEntradasConectadas(){
        return listaEntradas;
    }
    
    /**
     * Método Método que retorna la salida a la cual está conectada la entrada
     * @return salidaConectada, la salida a la cual está conectado
     */
    public CirculoSalida getSalidaConectada(){
        return salidaConectada;
    }
    
    /**
     * Método que permite cambiar el valor al atributo que corresponde a la salida a la cual está conectada la entrada.
     * @param salida, salida a la cual está conectado
     */
    public void setSalidaConectada(CirculoSalida salida){
        salidaConectada = salida;
    }
}
