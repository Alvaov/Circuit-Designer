/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import listlinked.ListLinked;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class CirculoSalida extends Circulo{
    
    private ListLinked<CirculoEntrada> entradasConectadas = new ListLinked<>();
    private CirculoSalida valorConectado;
    
    public CirculoSalida(Valores valor) {
        super(valor);
    }
    @Override
    public void setValor(Valores valor){
        this.valor = valor; 
         
    }
    
    public ListLinked getEntradasConectadas(){
        return entradasConectadas; 
         
    }
    
    public void setEntradasConectadas(CirculoEntrada nuevaEntrada){
        entradasConectadas.añadirFinal(nuevaEntrada);
        isConected = true;
         
    }
    
    public ListLinked<CirculoEntrada> getEntradasConectadas(CirculoEntrada nuevaEntrada){
        return entradasConectadas;
         
    }
    
    public void eliminarEntrada(CirculoEntrada entrada){
        for (int i = 0; i < entradasConectadas.getSize(); i++){
            if(entradasConectadas.getValor(i).equals(entrada)){
                entradasConectadas.eliminarEnPosición(i);
            }
        }
    }
    
    /**
     *
     * @return
     */
    public boolean conectada(){
        if (entradasConectadas.getSize() < 1){
            isConected = false;
        }else{
            isConected = true;
        }
        return isConected;
    }

    public void setValorConectado(CirculoSalida conectado){
        valorConectado = conectado;
    }
}
