/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listlinked;

/**
 * Lista enlazada que se utlizó a lo largo del proyecto, código base encontrado en 
 * https://www.cs.wcupa.edu/rkline/cs3/generic-lists.html
 * https://youtu.be/8oCjWIJJI9c
 * @author allva
 */
public class ListLinked<T>{
   
    private Nodo<T> cabeza;
    private Nodo<T> ultimo;
    private int size;
    
    /**
     * Constructor de la lista enlazada, donde el tamaño inicia en 0, y la tanto el primer elemento como el último inician en nulo.
     */
    public ListLinked(){ 
        cabeza = null;
        ultimo = null;
        size = 0;
    }
    
    /**
     * Método de la lista que añade un elemento al inicio de la lista, si ya existe un elemento al inicio intercambia posiciones,
     * si no, lo crea con el valor asignado.
     * @param valor, valor a añadir
     */
    public void añadirInicio(T valor){
        if(cabeza != null){
            cabeza = new Nodo(valor,cabeza,null);
            cabeza.getSiguiente().setAnterior(cabeza);
        }
        else{
            cabeza = new Nodo(valor);
            ultimo = cabeza;

        }
        size++;
    }

    /**
     * Método que añade un elemento al final de la lista, salvo que esta se encuentre vacía, en tal caso lo añade al inicio de la lista.
     * @param valor, el valor a añadir
     */
    public void añadirFinal(T valor){
        if (cabeza != null){
            ultimo = new Nodo(valor,null,ultimo);
            ultimo.getAnterior().setSiguiente(ultimo);
            size++;
        }
        else{
            añadirInicio(valor);
        }
    }

    /**
     * Método con el cual se agrega un elemento en una posición específica de la listam, siendo i la variable que indica
     * dicha posición
     * @param valor, el valor del nuevo nodo
     * @param i, el índice donde se quiere añadir
     */
    public void añadirEnPosición(T valor, int i){ 
        Nodo nodo = new Nodo(valor);
        if (cabeza == null){
            nodo = cabeza;
        }
        else if(i == 0){
            añadirInicio(valor);
        }
        else if (i > size){
            añadirFinal(valor);
        }
        else{
            Nodo puntero = cabeza;
            int contador = 1;
            while(contador < i && puntero.getSiguiente() != null){

                puntero = puntero.getSiguiente();
                contador++;
            }
            nodo.setSiguiente(puntero.getSiguiente());
            puntero.setSiguiente(nodo);
        }
        size++;
    }
    
    /**
     * Método que devuelve el elemento en la posición que se indica en la variable i, sin embargo devuelve un nodo
     * no el valor de este
     * @param i, índice a buscar
     * @return  nodo, nodo en la posicón de índice i
     */
    public Nodo buscarElemento(int i){
        if(cabeza == null){
            return null;
        }
        else{
            Nodo<T> puntero = cabeza;
            int contador = 0;
            while(contador < i && puntero.getSiguiente() != null){
                puntero = puntero.getSiguiente();
                contador++;
            }
            return puntero;
        }
    }

    /**
     * Método que elimina aquel valor que se encuentre al inicio de la lista
     */
    public void eliminarInicio(){
        if (cabeza == ultimo){
            cabeza = ultimo = null;
        }
        else{
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        size--;
    }

    /**
     * Método que elimina el nodo con su valor que se encuentre en la última posición
     */
    public void eliminarFinal(){
        if (cabeza == ultimo){
            cabeza = ultimo = null;
        }
        else{
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(null);
        }
        size--;
    }
    
    /**
     * Método que eliminar el valor que se encuentre en la posición i que se envíe como parámetro, 
     * siendo que el índice comienza en 1 y no en 0
     * @param i, índice a eliminar
     */
    public void eliminarEnPosición(int i){
        if(i == 1){
            eliminarInicio();
        }
        else if (i >= size){
            eliminarFinal();
        }
        else{
            Nodo<T> puntero = cabeza;
            int contador = 1;
            while(contador < i && puntero.getSiguiente() != null){

                puntero = puntero.getSiguiente();
                contador++;
            }
            puntero = puntero.getAnterior();
            puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
            size--;
        }
    }

    /**
     * Método que retorna el valor en la posición que se envía como parámetro i, siendo que i comienza
     * en un índice 0, a diferencia de buscarElemento, este método sí devuelve el valor del nodo como tal.
     * @param i, índice a obtener
     * @return valorT, el valor que contiene el nodo en la posición de índice i
     */
    public T getValor(int i){
        Nodo<T> puntero = cabeza;
        int contador = 0;
        while(contador < i){
            puntero = puntero.getSiguiente();
            contador++;
        }
        return puntero.getValor();
    }
    
    /**
     * Método que devuelve el valor actual de la variable size de la lista, indicando el tamaño actual de esta.
     * @return size, el tamaño actual de la lista
     */
    public int getSize(){
        return size;
    }
    
    /**
     * Método que elimina toda la lista, eliminando la cabeza, y de esta forma, al perder el puntero el recolector de basura de java
     * se encarga de eliminar el resto de elementos.
     */
    public void eliminarLista(){
        cabeza = null;
    }

}
