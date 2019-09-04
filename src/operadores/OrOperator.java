/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import listlinked.ListLinked;

/**
 *
 * @author allva
 */
public class OrOperator extends Operadores{

    public OrOperator(int cantidadDeEntradas) {
        super(cantidadDeEntradas);

    }

    @Override
    public Boolean operación(ListLinked entradas) {
        int contador = 0;
        while (contador < entradas.getSize()){
            if ( (Boolean) entradas.getValor(contador) == false){
                contador++;
            } 
            else{
                return true;
            }
        }
        return false;
    }
    
}
