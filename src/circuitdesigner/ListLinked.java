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
public class ListLinked {
   
    public Nodo cabeza;
    public int size;
    
    public ListLinked(){
        cabeza = null;
        size = 0;
    }
    
    public void añadirInicio(Operadores valor){
        if(cabeza != null){
            Nodo nodo = new Nodo(valor);
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
            size++;
        }
        else{
            cabeza = new Nodo(valor);
            size++;
        }
    }
    public void añadirFinal(Operadores valor){
        Nodo nodo = new Nodo(valor);
        Nodo puntero = cabeza;
        while(puntero.getSiguiente() != null){
            puntero = puntero.getSiguiente();
        }
        puntero.setSiguiente(nodo);
    }
    
    public void añadirEnPosición(Operadores valor, int i){
        Nodo nodo = new Nodo(valor);
        if (cabeza == null){
            nodo = cabeza;
            size++;
        }
        else if(i == 0){
            añadirInicio(valor);
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
    }
}
