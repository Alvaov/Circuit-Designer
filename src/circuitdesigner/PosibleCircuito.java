/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import listlinked.ListLinked;
/**
 *
 * @author allva
 */
public class PosibleCircuito {
    
    private ListLinked<String> valores = new ListLinked<>();
    private static ListLinked<Integer> particiones = new ListLinked<>();
    private static ListLinked<Integer> contadores = new ListLinked<>();
    private int filas;
    
    public PosibleCircuito(int entradas,int filas){
        this.filas = filas;
        for(int i = 0; i < entradas;i++){
            String valor = new String();
            valores.añadirFinal(valor);
        }
        if(particiones.getSize() < entradas){
            while(this.filas > 1){
                int partición = this.filas/2;
                System.out.println(partición);
                particiones.añadirFinal(partición);
                contadores.añadirFinal(1);
                this.filas = this.filas/2;
            }
        }
        System.out.println(particiones.getSize());
        asignarValores();
    }
    
    public void asignarValores(){
        System.out.println("Asigna valores");
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
            System.out.println(valor);
        }
    }
    
    public String getValores(int i){
        return valores.getValor(i);
    }
    
}
