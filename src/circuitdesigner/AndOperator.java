package circuitdesigner;

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
    
    

    public AndOperator(int cantidadDeEntradas) {
        super(cantidadDeEntradas);
        
    }
    
    @Override
    public Boolean operación(ListLinked entradas) {
        int contador = 0;
        while (contador < entradas.getSize()){
            if ( (Boolean) entradas.getValor(contador) == true){
                contador++;
            } 
            else{
                return false;
            }
        }
        return true;
    }

}
