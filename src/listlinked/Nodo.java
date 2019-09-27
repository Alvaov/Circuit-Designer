package listlinked;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * lase Nodo de la cual se componen los eslabones de la lista enlazada utilizada en este proyecto, el código base fue tomado de
 * https://www.cs.wcupa.edu/rkline/cs3/generic-lists.html
 * https://youtu.be/8oCjWIJJI9c
 * @author allva
 */
public class Nodo <T> {
   
    private T valor;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    /**
     * Constructor donde se crea el nodo y se le asignan los valores referentes a su nodo anterior y su nodo siguiente, así como el valor que va a contener
     * @param valor, valor del nodo
     * @param siguiente, siguiente del nodo
     * @param anterior, anterior del nodo
     */
    public Nodo(T valor, Nodo siguiente, Nodo anterior){
        this.valor = valor;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    /**
     * Constructor de un nodo donde solo se recibe un valor y se asignan las referencias a este nodo como nulas.
     * @param valor, valor con que se crea el nodo
     */
    public Nodo(T valor){
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
    
    /**
     * Retorna el valor tipo T que contiene el nodo
     * @return valor, obtiene el valor actual
     */
    public T getValor(){ 
        return valor;
    }

    /**
     * Asigna un valor tipo T al nodo 
     * @param valor, el nuevo valor
     */
    public void setValor(T valor){
        this.valor = valor;
    }
    
    /**
     * Permite obtener el nodo siguiente
     * @return siguiente, da accesos al nodo siguiente
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }

    /**
     * Método que permite asignar un nodo siguiente a través del argumento que se recibe.
     * @param newSiguiente, da un nuevo valor nodo siguiente
     */
    public void setSiguiente(Nodo newSiguiente){
        this.siguiente = newSiguiente;
    }

    /**
     * Método que permite obtener el nodo guardado como anterior al seleccionado.
     * @return anterior, da acceso al nodo anterior
     */
    public Nodo<T> getAnterior(){
        return anterior;
    }

    /**
     * Permite asignar un nodo anterior al cual se le aplica este método.
     * @param newAnterior, el nuevo nodo que va a ser el anterior
     */
    public void setAnterior(Nodo newAnterior){
        this.anterior = newAnterior;
    }
}