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
public class Entradas {
    
    private Boolean valorB;
    private Operadores valorO;
    
    public Entradas(){
        valorB = null;
        valorO = null;
    }
    
    public void setValue(Boolean valorB){
        this.valorB = valorB;
    }
    
    public void setValue(Operadores valorO){
        this.valorO = valorO;
    }
    
}
