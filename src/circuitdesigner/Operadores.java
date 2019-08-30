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
    protected ListLinked<Entradas> entradas;


    /**
     *@see Corresponde al constructor de la clase abstracta Operadores
     * es el encargado de crear las entradas de cada compuerta según las 
     * solicitadas por el usuario. Recibe la cantidad de entradas como
     * parámetro.
     * @param cantidadDeEntradas
     */
    public Operadores(int cantidadDeEntradas){
        this.cantidadDeEntradas = cantidadDeEntradas;
        this.entradas = new ListLinked<>();
        crearEntradas(cantidadDeEntradas);
 
        

    }
    
    /**
     * @see Recibe la cantidad de entradas del operador y
     * crea la cantidad correspondiente con valor nulo, añade las entradas
     * a una lista enlazada.
     * @param cantidadDeEntradas 
     */
    public void crearEntradas(int cantidadDeEntradas){
        int contador = 0;
        
        while(contador < cantidadDeEntradas){
            Entradas entrada = new Entradas();
            entradas.añadirFinal(entrada);
            contador++;
        }
        
    }
    
    /**
     * @see Es un getter de la variable salida de cada compuerta. Creada desde el 
     * padre de los operadores lógicos. Retorna el valor booleano de la salida
     * @return salida
     */
    public Boolean getSalida(){
        return salida;
    }
    
    /**
     *
     * @param entrada
     * @param valor
     */
    public void setEntrada(int entrada,Boolean valor){

        Entradas entradaACambiar = (Entradas) entradas.buscarElemento(entrada);
        entradaACambiar.setValue(valor);
    }
    
    public Entradas getEntrada(int entrada){
        Entradas entradaBuscada = (Entradas) entradas.buscarElemento(entrada);
        return entradaBuscada;
    }
    
    public void agregasEntradas(int entrada){
        crearEntradas(entrada);
    }

    /**
     * @see Método mediante el cual se operan las entradas para obtener una salida
     * según corresponda al tipo de compuerta. Método abstracto que se define en cada
     * operador concreto.
     * @param entradas
     * @return valor booleano de la operación.
     */
    public abstract Boolean operación(ListLinked entradas);
    
    
}
