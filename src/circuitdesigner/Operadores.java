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
public abstract class Operadores {
    

    private int cantidadDeEntradas;
    protected Boolean salida;
    protected ListLinked entradas;

    public Operadores(int cantidadDeEntradas){
        this.cantidadDeEntradas = cantidadDeEntradas;
        crearEntradas(cantidadDeEntradas);
    }
    /**
     * @see Recibe la cantidad de entradas del operador y
     * crea la cantidad correspondiente con valor nulo, añade las entradas
     * a una lista enlazada.
     * @param cantidadDeEntradas 
     */
    private void crearEntradas(int cantidadDeEntradas){
        int contador = 0;
        while(contador < cantidadDeEntradas){
            Entradas entrada = new Entradas();
            entradas.añadirFinal(entrada);
            contador++;
        }
    }
    
    public void setEntrada(int i,Boolean valor){
        Entradas entrada = (Entradas) entradas.getValor(i);
        entrada.setValue(valor);
    }

    
    public abstract Boolean operación(ListLinked entradas);
    
    
}
