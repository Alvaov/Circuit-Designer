/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadores;

import listlinked.ListLinked;
import circuitdesigner.Entrada;

/**
 * Clase padre de la cual heredan sus atributos y método abstracto los diferentes tipos de compuertas
 * @author allva
 */
public abstract class Operadores {
    
    protected ListLinked<Entrada> entradas;


    /**
     * Corresponde al constructor de la clase abstracta Operadores
     * es el encargado de crear las entradas de cada compuerta según las 
     * solicitadas por el usuario. Recibe la cantidad de entradas como
     * parámetro.
     * @param entradas, entradas a evaluar
     */
    public Operadores(ListLinked<Entrada> entradas){
        this.entradas = entradas;
 
        

    }

    /**
     * Método Método mediante el cual se operan las entradas para obtener una salida
     * según corresponda al tipo de compuerta. Método abstracto que se define en cada
     * operador concreto.
     * @param entradas, entradas a evaluar
     * @return salida, la salida evaluada
     */
    public abstract Valores operación(ListLinked<Entrada> entradas);
    
    
}
