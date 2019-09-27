/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import listlinked.ListLinked;
import operadores.Valores;

/**
 * Clase que corresponde aquellos círculos que son la salida de la compuerta.
 * @author allva
 */
public class CirculoSalida extends Circulo{
    
    private ListLinked<CirculoEntrada> entradasConectadas = new ListLinked<>();
    private CirculoSalida valorConectado;
    
    /**
     * Constructor de la clase que hereda directamente de su padre la clase Círculo.
     * @param valor, valor con que inicia la salida
     */
    public CirculoSalida(Valores valor) {
        super(valor);
    }
    /**
     * Método que permite modificar el valor que p el CirculoSalida 
     * @param valor, nuevo valor para la salida
     */
    @Override
    public void setValor(Valores valor){
        this.valor = valor;
        if(valorConectado != null){
            valorConectado.setValor(valor);
        }
         
    }
    
    /**
     * Permite obtener la lista de entradas conectadas
     * @return entradasConectadas, lista de entradas conectadas
     */
    public ListLinked<CirculoEntrada> getEntradasConectadas(){
        return entradasConectadas; 
         
    }
    
    /**
     * Método que añade un CirculoEntrada a la lista de entradas conectadas perteneciente al CirculoSalida, siempre lo añade al final de la lista.
     * @param nuevaEntrada, la nueva entrada que se conectó.
     */
    public void setEntradasConectadas(CirculoEntrada nuevaEntrada){
        entradasConectadas.añadirFinal(nuevaEntrada);
        isConected = true;
         
    }
    
    /**
     * Método que recibe un CirculoEntrada y lo busca en la lista de entradas conectadas perteneciente al CirculoSalida, lo busca
     * y al encontrarlo lo elimina de la lista respectiva.
     * @param entrada, la entrada a eliminar
     */
    public void eliminarEntrada(CirculoEntrada entrada){
        for (int i = 0; i < entradasConectadas.getSize(); i++){
            if(entradasConectadas.getValor(i).equals(entrada)){
                entradasConectadas.eliminarEnPosición(i);
            }
        }
    }
    
    /**
     * Método que retorna true o false según sea que la salida está conectada o no a una o más entradas.
     * @return isConected, valor que indica si está o no conectada
     */
    public boolean conectada(){
        if (entradasConectadas.getSize() < 1){
            isConected = false;
        }else{
            isConected = true;
        }
        return isConected;
    }

    /**
     * Método que permite guardar una salida relacionada a esta, utilizado en el circuito de usuario para poder relacionar las salidas de compuertas
     * internas con aquellas que ve el usuario. 
     * @param conectado, salida a la que está asociada en el circuito encapsulado
     */
    public void setValorConectado(CirculoSalida conectado){
        valorConectado = conectado;
    }
}
