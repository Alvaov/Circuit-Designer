/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listlinked;

import java.util.Observable;

/**
 *
 * @author allva
 */
public class ListLinked<T> extends Observable{
   
    public Nodo<T> cabeza;
    public Nodo<T> ultimo;
    private int size;
    
    public ListLinked(){ 
        cabeza = null;
        ultimo = null;
        size = 0;
    }
    
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
    public T buscarElemento(int i){
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
            return puntero.getValor();
        }
    }
    /*
    public void enseñarListaCabezaUltimo(){
        if (cabeza != null){
            String elementos = "";
            Nodo nodo = cabeza;
            while(cabeza != null){
                elementos = elementos + "["+cabeza.getValor()+"]"+",";
                cabeza = cabeza.getSiguiente();
            }
            System.out.println(elementos);
        }
        else{
            System.out.println("Lista vacía");
        }
    }
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
    public void eliminarEnPosición(int i){
        if(i == 0){
            eliminarInicio();
        }
        else if (i > size){
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
            puntero.setSiguiente(puntero.getSiguiente());
        }
        size--;
    }
    public T getValor(int i){
        Nodo<T> puntero = cabeza;
        int contador = 0;
        while(contador < i){
            puntero = puntero.getSiguiente();
            contador++;
        }
        return puntero.getValor();
    }
    
    public int getSize(){
        return size;
    }
}
