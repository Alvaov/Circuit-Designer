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
    

    public static int e = 0;
    public static int s = 0;
    private static ListLinked<Imagen> circuito = new ListLinked();
    private static ListLinked<Color> coloresUsados = new ListLinked();
    
    public Factory(String ruta,int cantidadDeEntradas,double x, double y){
        
        Imagen imagen = new Imagen(ruta,cantidadDeEntradas,x,y);
        
        for (int i = 0; i < imagen.getEntradas().getSize(); i++){
            Entrada entrada = (Entrada) imagen.getEntradas().buscarElemento(i);
            entrada.getEndE().setCompuertaPadre(imagen);
        }
        
        circuito.añadirFinal(imagen);
    }

    public int getCantidadDeEntradas(){
        return e;
    }
    public int getCantidadDeSalidas(){
        return s;
    }
    public static ListLinked getCircuito(){
        return circuito;
    }
    public static ListLinked getColores(){
        return coloresUsados;
    }
    
}
    
    
