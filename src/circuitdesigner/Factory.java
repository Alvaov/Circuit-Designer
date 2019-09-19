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

    private static ListLinked<Color> coloresUsados = new ListLinked();
    
    public Factory(String ruta,int cantidadDeEntradas,double x, double y){
        
    }
    /**
     * @see retorna la lista enlazada en la cuál se están guardando todos lo colore utilizados para las líneas.
     * @return ListLinked
     */
    public static ListLinked<Color> getColores(){
        return coloresUsados;
    }
    
}
    
    
