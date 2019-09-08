/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import listlinked.ListLinked;
import circuitdesigner.Entrada;

/**
 *
 * @author allva
 */
public abstract class Operadores {
    

    private int cantidadDeEntradas;
    protected ListLinked<Entrada> entradas;


    /**
     *@see Corresponde al constructor de la clase abstracta Operadores
     * es el encargado de crear las entradas de cada compuerta según las 
     * solicitadas por el usuario. Recibe la cantidad de entradas como
     * parámetro.
     * @param cantidadDeEntradas
     */
    public Operadores(ListLinked<Entrada> entradas){
        this.entradas = entradas;
       // crearEntradas(cantidadDeEntradas);
 
        

    }

    /**
     * @see Método mediante el cual se operan las entradas para obtener una salida
     * según corresponda al tipo de compuerta. Método abstracto que se define en cada
     * operador concreto.
     * @param entradas
     * @return valor de la operación.
     */
    public abstract Valores operación(ListLinked<Entrada> entradas);
    
    
}
