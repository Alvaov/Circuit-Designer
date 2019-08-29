/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

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
                operacionNot((Entradas) entradas.getValor(0));
                
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
    public Boolean operacionNot(Entradas entrada){
        if (entrada.getValue()== false){
            return true;
        }else{
            return false;
        }
    }
}
