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
public class CircuitDesigner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Operadores hola = new AndOperator(1,0);
        Nodo nodo = new Nodo(hola);
        System.out.println(0|0);
    }
    
}
