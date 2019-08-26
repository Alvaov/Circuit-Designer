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

    public AndOperator(int entradaA, int entradaB) {
        super(entradaA, entradaB);
    }
    
    @Override
    public int Operación(){
        return entradaA & entradaB;
    }
}
