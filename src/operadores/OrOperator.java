/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/**
 *
 * @author allva
 */
public class OrOperator extends Operadores{

    public OrOperator(ListLinked<Entrada> entradas) {
        super(entradas);

    }

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
