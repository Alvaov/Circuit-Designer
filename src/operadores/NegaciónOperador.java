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
public class NegaciónOperador extends Operadores{
    
    Operadores operador;

    public NegaciónOperador(int cantidadDeEntradas,int tipo) {
        super(cantidadDeEntradas);
        
        switch (tipo){
            case 1:
                operador = new AndOperator(cantidadDeEntradas);
                break;
            case 2:
                operador = new OrOperator(cantidadDeEntradas);
                break;
            case 3:
                operador = new XOROperator(cantidadDeEntradas);
                break;
            case 4:
                Entradas entrada = new Entradas();
                entradas.añadirFinal(entrada);
                //operacionNot();
                
        }
        
    }

    @Override
    public Boolean operación(ListLinked entradas) {
        salida = operador.operación(entradas);
         if (salida == false){
                return true;
            }
            else{
                return false;
            }
    }
    public Boolean operacionNot(){
        
        if (entradas.getValor(0).getValue()== false){
            return true;
        }else{
            return false;
        }
    }
}
