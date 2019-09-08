package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allva
 */
public class AndOperator extends Operadores{
    
    

    public AndOperator(ListLinked<Entrada> entradas) {
        super(entradas);
        
    }
    
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
