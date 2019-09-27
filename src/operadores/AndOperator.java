package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase encargada de la creación de compuertas lógicas de tipo and
 * @author allva
 */
public class AndOperator extends Operadores{
    
    /**
     * Constructor de la compuerta lógica de tipo and, recibe las entradas que se deben operar como argumento
     * @param entradas, entradas a evaluar
     */
    public AndOperator(ListLinked<Entrada> entradas) {
        super(entradas);
        
    }
    /**
     * Realiza la operación correspondiente a una compuerta and, donde al encontrar un Valores.False, retornara un Valores.False
     * @param entradas, entradas a evaluar
     * @return Valores
     */
    @Override
    public Valores operación(ListLinked<Entrada> entradas) {
        int contador = 0;
        while (contador < entradas.getSize()){
            if (entradas.getValor(contador).getValor() == Valores.True){
                contador++;
            } 
            else{
                return Valores.False;
            }
        }
        return Valores.True;
    }

}
