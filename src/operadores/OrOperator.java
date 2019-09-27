/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/**
 * Clase encargada de la creación de compuertas lógicas de tipo or
 * @author allva
 */
public class OrOperator extends Operadores{

    /**
     * Constructor de la compuerta lógica de tipo or, recibe las entradas que se deben operar como argumento
     * @param entradas, entrada a evaluar
     */
    public OrOperator(ListLinked<Entrada> entradas) {
        super(entradas);

    }
    /**
     * Método que realiza la operación respectiva para una entrada de tipo or, donde si encuentra un valor que corresponda a Valores.True
     * devuelve Valores.True
     * @param entradas, entradas a evaluar
     * @return salida, la salica evaluada
     */
    @Override
    public Valores operación(ListLinked<Entrada> entradas) {
        int contador = 0;
        while (contador < entradas.getSize()){
            if (entradas.getValor(contador).getValor() == Valores.False){
                contador++;
            } 
            else{
                return Valores.True;
            }
        }
        return Valores.False;
    }
    
}
