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
public class OrOperator extends Operadores{

    public OrOperator(int entradaA, int entradaB) {
        super(entradaA, entradaB);
    }

    @Override
    public int Operación() {
       return entradaA | entradaB;
    }
    
}
