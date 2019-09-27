/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import circuitdesigner.Entrada;
import listlinked.ListLinked;

/**
 * Clase encargada de crear las instancias correspondientes a las negaciones de las compuertas and, or, y xor, así como de la compuerta de tipo not
 * @author allva
 */
public class NegaciónOperador extends Operadores{
    
    private Operadores operador;
    private int tipo;

    /**
     * Constructor que recibe un entero que especifica el tipo de negación de compuerta que se quiere, crea una instancia del tipo 
     * correspondiente y lo asigna al atributo operador propio de la clase, con las entradas que se le envíen.
     * Código base para switch-case tomado de https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
     * @param entradas, entradas a evaluar
     * @param tipo, define qué tipo de negación de compuerta es
     */
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
                
        }
        
    }
    /**
     * Método que realiza la operación de la compuerta de negación, en caso de ser tipo 4 solo toma su entrada e invierte su valor, para cualquier otro tipo
     * calcula la salida de la compuerta original y lo invierte para así dar el resultado esperado.
     * @param entradas, entradas a evaluar
     * @return salida, la salida evaluada
     */
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
}
