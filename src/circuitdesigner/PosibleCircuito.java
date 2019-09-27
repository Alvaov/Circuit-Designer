/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import listlinked.ListLinked;
import operadores.Valores;
/**
 * Clase que se encarga de calcular todos los datos posibles según la cantidad de entradas,
 * las filas de la tabla de Verdad.
 * @author allva
 */
public class PosibleCircuito {
    
    private ListLinked<String> valores = new ListLinked<>();
    private static ListLinked<Integer> particiones = new ListLinked<>();
    private static ListLinked<Integer> contadores = new ListLinked<>();
    private int filas;
    private ListLinked<Entrada> entradasLista;
    private int entradasEvaluadas = 0;
    
    /**
     * Constructor que recibe la cantidad de entradas, de filas y la lista de entradas a cambiar, con estos datos calcula las particiones que debe realizar para asignar valores
     * de manera que nunca se repita una fila, y asigna los datos según la fila, además que agregar la cantidad de elementos a la lista valores correspondientes a salidas
     * según sea necesario.
     * @param entradas, la cantidad de entradas 
     * @param filas, la cantidad de filas
     * @param entradasLista, la lista de entrada de usuario del circuito
     */
    public PosibleCircuito(int entradas,int filas, ListLinked<Entrada> entradasLista){
        this.filas = filas;
        this.entradasLista = entradasLista;
        for(int i = 0; i < entradas;i++){
            String valor = new String();
            valores.añadirFinal(valor);
        }
        if(particiones.getSize() < entradas){
            while(this.filas > 1){
                int partición = this.filas/2;
                particiones.añadirFinal(partición);
                contadores.añadirFinal(1);
                this.filas = this.filas/2;
            }
        }
        asignarValores();
    }
    
    /**
     * Método que asigna los valores correspondientes a las entradas que puede tener un circuito de una cierta cantidad de entradas,
     * se asegura que todas las posibilidadas sean calculcadas. Y llama posteriormenta a la función que asigna estos valores a las salidas que corresponda
     *  para su simulación.
     */
    public void asignarValores(){
        for(int i = 0; i< valores.getSize(); i++){
            
            String valor = valores.getValor(i);
            int contador = contadores.getValor(i);
            int partición = particiones.getValor(i);
            if(contador <= partición){
                valores.buscarElemento(i).setValor("1");
                valor = "1";
                contadores.buscarElemento(i).setValor(contador+1);
            }
            else if(contador > partición && contador <= partición*2){
                valores.buscarElemento(i).setValor("0");
                valor = "0";
                if(contador == partición*2){
                    contadores.buscarElemento(i).setValor(1);
                }else{
                    contadores.buscarElemento(i).setValor(contador+1);
                }
            }
        }
        asignarEntradas();
    }

    /**
     * Método que asigna los datos de la lista Valores previamente creada y con base en estos datos toma el circuito y asigna dichas salidas correspondientes, luego de esto
     * llama a la función que se encarga de calcular las salidas según corresponda a las entradas asignadas por esta función.
     * @return null
     */
    public ListLinked<Compuerta> asignarEntradas(){
        ListLinked<Valores> salidas = new ListLinked<>();
        ListLinked<Compuerta> circuito = Main.getControlador().getCircuito();
        int indiceValor = 0;
        int indiceLista = 0;
        
        for(int i = 0; i < circuito.getSize(); i++){

            Compuerta compuerta = circuito.getValor(i);
                
            for(int j = 0; j < compuerta.getEntradas().getSize(); j++){
               Entrada entrada = compuerta.getEntrada(j);
               if (indiceLista < entradasLista.getSize() && entrada == entradasLista.getValor(indiceLista)){
                    if (valores.getValor(indiceValor).equalsIgnoreCase("1")){
                        compuerta.getEntrada(j).setValor(Valores.True);
                    }else if(valores.getValor(j).equalsIgnoreCase("0")){
                        compuerta.getEntrada(j).setValor(Valores.False);
                    }
                    indiceLista++;
                    indiceValor++;
                }
            }
        }
        return emularCircuito(circuito);
    }
    
    /**
     * Método que realiza las emulaciones respectivas del circuito con las diferentes combinaciones de entradas posibles,
     * añade además estas salidas a la lista valores con la cual luego se completa las filas de la tabla de verdad.
     * @param circuito, el circuito general 
     * @return null
     */
    public ListLinked<Compuerta> emularCircuito (ListLinked<Compuerta> circuito){
        for(int i = 0; i < circuito.getSize(); i++){
            
            Compuerta compuerta = circuito.getValor(i);
            if (compuerta.revisarEntradas()== true){
                
                compuerta.operarSalida();
                entradasEvaluadas +=1;
                if(compuerta.getEnd().conectada()== true){
                    ListLinked<CirculoEntrada> entradasConectadas = compuerta.getEnd().getEntradasConectadas();
                    
                    for(int e = 0; e < entradasConectadas.getSize(); e++){
                        entradasConectadas.getValor(e).setValor(compuerta.getEnd().getValor());
                    }
                    compuerta.getEnd().setValor(Valores.Default);
                    
                }else{
                    if(compuerta.getEnd().getValor() == Valores.False){
                        valores.añadirFinal("0");
                    }else{
                        valores.añadirFinal("1");
                    }
                    compuerta.getEnd().setValor(Valores.Default);
                }
            }
        }
        if(entradasEvaluadas < circuito.getSize()){
            return emularCircuito(circuito);
        }else{
            return null;
        }
    }
    
    /**
     * Retorna la lista enlazada en la cual se guardaron los diferentes valores de las entradas para el posible circuito a añadirse en la tabla.
     * @param i, indice en el que se desea obtener el valor
     * @return valor, string que contiene un valor de la lista enlazada de valores
     */
    public String getValores(int i){
        return valores.getValor(i);
    }
    
}
