/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;


import java.util.Observable;
import java.util.Observer;
import listlinked.ListLinked;
import operadores.Operadores;
import operadores.AndOperator;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Factory{

    private static ListLinked<Compuerta> circuito = new ListLinked();
    private static ListLinked<Color> coloresUsados = new ListLinked();
    
    public Factory(String ruta,int cantidadDeEntradas,double x, double y){
        
        Compuerta compuerta = new Compuerta(ruta,cantidadDeEntradas,x,y);
        
        for (int i = 0; i < compuerta.getEntradas().getSize(); i++){
            Entrada entrada = (Entrada) compuerta.getEntradas().getValor(i);
            entrada.getEndE().setCompuertaPadre(compuerta);
        }
        
        circuito.añadirFinal(compuerta);
    }

    /**
     * @see retorna la lista enlazada en la cual se está almacenando el circuito que se muestra en pantalla
     * @return ListLinked
     */
    public static ListLinked getCircuito(){
        return circuito;
    }

    /**
     * @see retorna la lista enlazada en la cuál se están guardando todos lo colore utilizados para las líneas.
     * @return ListLinked
     */
    public static ListLinked getColores(){
        return coloresUsados;
    }
    
}
    
    
