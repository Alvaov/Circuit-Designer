/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;


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

public class Facade {
    
    private static ListLinked<Entrada> entradas;
    static int e = 0;
    static int s = 0;
    public Facade(String ruta,int cantidadDeEntradas){
        entradas = new ListLinked<>();
        
        ImageView imagen = new Imagen(ruta,new AndOperator(cantidadDeEntradas),cantidadDeEntradas);
        System.out.println(s);
        System.out.println(entradas.getSize());
    }
    
    public static ListLinked getListaEntradas(){
        return entradas;
    }
    public static int getCantidadDeEntradas(){
        return e;
    }
    public static int getCantidadDeSalidas(){
        return s;
    }
    
    
}
    
    
