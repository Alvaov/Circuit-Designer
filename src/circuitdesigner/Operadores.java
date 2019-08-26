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
public abstract class Operadores {
    
    int entradaA;
    int entradaB;
    
    public Operadores(int entradaA,int entradaB){
        this.entradaA = entradaA;
        this.entradaB = entradaB;
        
    }
    
    public abstract int Operación();
    
    
}
