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
public class Nodo {
   
    private Operadores valor;
    private Nodo siguiente;
    private Nodo anterior1;
    private Nodo anterior2;
   
    
    public Nodo(Operadores valor){
        this.valor = valor;
        this.siguiente = null;
    }
    
    public void Unir(Nodo i){
        siguiente = i;
    }
    
    public Operadores getValor(){
        return valor;
    }
    
    public Nodo getSiguiente(){
        return siguiente;
    }
    public void setSiguiente(Nodo newSiguiente){
        this.siguiente = newSiguiente;
    }
}