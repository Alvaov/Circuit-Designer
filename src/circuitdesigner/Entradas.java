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
    
    private Boolean valor;

    public Entradas(){
       
    }
    
    public void setValue(Boolean valorB){
        this.valor = valorB;
    }
    
    public Boolean getValue(){
        return valor;
    }
}
