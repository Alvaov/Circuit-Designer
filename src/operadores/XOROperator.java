/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/**
 * Clase de la compuerta xor
 * @author allva
 */
public class XOROperator extends Operadores{

    /**
     * Constructor de la compuerta lógica de tipo xor, recibe las entradas que se deben operar como argumento
     * @param entradas, las entradas a evaluar
     */
    public XOROperator(ListLinked<Entrada> entradas) {
        super(entradas);

    }
    /**
     * Método que calcula la operación realizada por una compuerta de tipo xor según la lista de entradas que se le envíen.
     * Operación obtenida de https://www.mecatronicalatam.com/es/compuertas-logicas/compuerta-xor.
     * @param entradas, las entrada a evaluar
     * @return salida, la salica evaluada
     */
    @Override
    public Valores operación(ListLinked<Entrada> entradas) {
        int contUnos = 0;
        for(int i = 0; i < entradas.getSize(); i++){
            System.out.println("while");
            if (entradas.getValor(i).getValor() == Valores.True){
                contUnos++;
            }
        }
        if(contUnos > 1){
            return Valores.False;
        }else{
            System.out.println("True");
            return Valores.True;
        }
    }

}
