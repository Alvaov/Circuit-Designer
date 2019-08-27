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
   
    private Object valor;
    private Nodo siguiente;
    private Nodo anterior;

    
    public Nodo(Object valor, Nodo siguiente, Nodo anterior){
        this.valor = valor;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
    public Nodo(Object valor){
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public Object getValor(){ 
        return valor;
    }
    
    public Nodo getSiguiente(){
        return siguiente;
    }
    public void setSiguiente(Nodo newSiguiente){
        this.siguiente = newSiguiente;
    }
    public Nodo getAnterior(){
        return anterior;
    }
    public void setAnterior(Nodo newAnterior){
        this.anterior = newAnterior;
    }
}