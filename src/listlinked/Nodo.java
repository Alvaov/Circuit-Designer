package listlinked;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allva
 */
public class Nodo <T> {
   
    private T valor;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    
    public Nodo(T valor, Nodo siguiente, Nodo anterior){
        this.valor = valor;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
    public Nodo(T valor){
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public T getValor(){ 
        return valor;
    }
    
    public Nodo<T> getSiguiente(){
        return siguiente;
    }
    public void setSiguiente(Nodo newSiguiente){
        this.siguiente = newSiguiente;
    }
    public Nodo<T> getAnterior(){
        return anterior;
    }
    public void setAnterior(Nodo newAnterior){
        this.anterior = newAnterior;
    }
}