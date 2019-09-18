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
public class NegaciónOperador extends Operadores{
    
    private Operadores operador;
    private int tipo;

    public NegaciónOperador(ListLinked<Entrada> entradas,int tipo) {
        super(entradas);
        this.tipo = tipo;
        switch (tipo){
            case 1:
                operador = new AndOperator(entradas);
                break;
            case 2:
                operador = new OrOperator(entradas);
                break;
            case 3:
                operador = new XOROperator(entradas);
                break;
            case 4:
                operacionNot(entradas);
                
        }
        
    }

    @Override
    public Valores operación(ListLinked<Entrada> entradas) {
        if (tipo != 4){
        Valores salida = operador.operación(entradas);
         if (salida == Valores.False){
                return Valores.True;
            }
            else{
                return Valores.False;
            }
        }else{
            if (entradas.getValor(0).getValor() == Valores.False){
                return Valores.True;
            }else{
                return Valores.False;
            }
        }
    }
    public Valores operacionNot(ListLinked<Entrada> entradas){
        
        if (entradas.getValor(0).getValor() == Valores.False){
            return Valores.True;
        }else{
            return Valores.False;
        }
    }
}
