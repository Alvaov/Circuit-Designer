/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import listlinked.ListLinked;
import operadores.Valores;
/**
 * @see es la clase que se encarga de calcular todos los datos posibles según la cantidad de entradas,
 * las filas de la tabla de Verdad.
 * @author allva
 */
public class PosibleCircuito {
    
    private ListLinked<String> valores = new ListLinked<>();
    private static ListLinked<Integer> particiones = new ListLinked<>();
    private static ListLinked<Integer> contadores = new ListLinked<>();
    private int filas;
    private ListLinked<Entrada> entradasLista;
    
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
         //       System.out.println(partición);
                particiones.añadirFinal(partición);
                contadores.añadirFinal(1);
                this.filas = this.filas/2;
            }
        }
        //System.out.println(particiones.getSize());
        asignarValores();
    }
    
    /**
     * @see asigna los valores correspondientes a las entradas que puede tener un circuito de una cierta cantidad de entradas,
     * se asegura que todas las posibilidadas sean calculcadas.
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
        //System.out.println("Valores");
        //System.out.println(valores.getSize());
        //System.out.println(valores.getValor(0));
        //System.out.println(valores.getValor(1));
        asignarEntradas();
    }
    private static int indiceValor = 0;
    public ListLinked<Compuerta> asignarEntradas(){
        ListLinked<Valores> salidas = new ListLinked<>();
        ListLinked<Compuerta> circuito = Main.getControlador().getCircuito();
        int indiceValor = 0;
        int indiceLista = 0;
        
        for(int i = 0; i < circuito.getSize(); i++){

            Compuerta compuerta = circuito.getValor(i);
                
            for(int j = 0; j < compuerta.getEntradas().getSize(); j++){
               //System.out.println("Reiteración"); 
               Entrada entrada = compuerta.getEntrada(j);
               if (indiceLista < entradasLista.getSize() && entrada == entradasLista.getValor(indiceLista)){
                    if (valores.getValor(indiceValor).equalsIgnoreCase("1")){
                 //       System.out.println("Asigna 1");
                        compuerta.getEntrada(j).setValor(Valores.True);
                        compuerta.getEntrada(j).getEndE().setIsConected(true);
                    }else if(valores.getValor(j).equalsIgnoreCase("0")){
                   //     System.out.println("Asigna 0");
                        compuerta.getEntrada(j).setValor(Valores.False);
                        compuerta.getEntrada(j).getEndE().setIsConected(true);
                    }
                    indiceLista++;
                    indiceValor++;
                }
            }
        }
        return emularCircuito(circuito);
    }
    
    private int entradasEvaluadas = 0;
    
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
                        compuerta.getEnd().setValor(Valores.Default);
                    }
                    
                }else{
                    if(compuerta.getEnd().getValor() == Valores.False){
                        System.out.println("asigna 0");
                        valores.añadirFinal("0");
                    }else{
                        System.out.println("asigna 1");
                        valores.añadirFinal("1");
                    }
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
     * @see retorna la lista enlazada en la cual se guardaron los diferentes valores de las entradas para el posible circuito a añadirse en la tabla.
     * @param i
     * @return ListLinked
     */
    public String getValores(int i){
        return valores.getValor(i);
    }
    
}
