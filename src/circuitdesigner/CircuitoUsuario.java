/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import listlinked.ListLinked;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class CircuitoUsuario{
    private ListLinked<Compuerta> compuerta;
    private ListLinked<Entrada> entradas;
    private ListLinked<CirculoSalida> salidas;
    private Group compuertaCompleta;
    private Rectangle imagenCompuerta;// = new Rectangle(10,25);
    private Circulo startE, start;
    
    public CircuitoUsuario(Rectangle imagen,ListLinked<Compuerta> compuerta, ListLinked<Entrada> entradas, ListLinked<CirculoSalida> salidas){
        this.compuerta = compuerta;
        this.entradas = new ListLinked<>();//entradas;
        this.salidas = salidas;
        //this.imagen = imagen;
        this.imagenCompuerta = new Rectangle(10,25);
        
        compuertaCompleta = new Group();
        startE   = new Circulo(null);
        startE.setLayoutX(25);
        startE.setLayoutY(25);
        start = new Circulo(null);
        start.setLayoutX(25);
        start.setLayoutY(25);
       for (int i = 0; i < salidas.getSize(); i++){
            CirculoSalida end = new CirculoSalida(Valores.Default);
            
            Line line     = new Line();
            line.startXProperty().bind(start.layoutXProperty());
            line.startYProperty().bind(start.layoutYProperty());
            line.endXProperty().bind(end.layoutXProperty());
            line.endYProperty().bind(end.layoutYProperty());
            compuertaCompleta.getChildren().addAll(end,line,end.getEtiqueta());
            end.setLayoutX(60);
            end.setLayoutY(25);
            end.setOnDragDetected(event -> {
              end.startFullDrag();
              ((CirculoSalida) event.getSource()).getParent().setMouseTransparent(true);
            });
             end.setOnMouseDragReleased(event->{
              
              if (event.getGestureSource() instanceof CirculoEntrada){
                  end.setEntradasConectadas(((CirculoEntrada) event.getGestureSource()));
                  ChangeListener<Number> listener = (observed, oldValue, newValue) -> {
                        Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                        Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                        ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                        ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  };
                  ChangeListener<Number> listenerCompuerta = (observed, oldValue, newValue) -> {
                        Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                        Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                        ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                        ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  };
                  end.setUserData(listener);
                  compuertaCompleta.setUserData(listenerCompuerta);
                  System.out.println("release salida");
                  ((CirculoEntrada) event.getGestureSource()).setValor(end.getValor());
                  ((CirculoEntrada) event.getGestureSource()).setIsConected(true);
                  end.setIsConected(true);
                  Main.getControlador().actualizarEtiquetas();
                  compuertaCompleta.toFront();
                  
                  end.layoutXProperty().addListener(listener);
                  end.layoutYProperty().addListener(listener);
                  
                  compuertaCompleta.layoutXProperty().addListener(listener);
                  compuertaCompleta.layoutYProperty().addListener(listener);
                  
                  ((CirculoEntrada)event.getGestureSource()).getParent().layoutXProperty().addListener(listenerCompuerta);
                  ((CirculoEntrada)event.getGestureSource()).getParent().layoutYProperty().addListener(listenerCompuerta);
             
                  ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
              }else{
                  System.out.println("Conexión inválida");
              }
          });
        }
        int y = 0;
       for (int i = 0; i < entradas.getSize(); i++){
            Entrada entrada = entradas.getValor(i);//new Entrada(startE,0);
            entrada.getEndE().setLayoutY(y);
            this.entradas.añadirFinal(entrada); 
            compuertaCompleta.getChildren().addAll(entrada.getLinea(),entrada.getEndE(),entrada.getEndE().getEtiqueta());
            y += 12;
       }
       
       imagenCompuerta.setLayoutX(20);
       imagenCompuerta.setLayoutY(10);
       imagenCompuerta.setOnDragDetected(event->{
             start.startFullDrag();
             startE.startFullDrag();
             compuertaCompleta.startFullDrag();
         });
       System.out.println();
       compuertaCompleta.getChildren().addAll(startE,start,imagenCompuerta);
       compuertaCompleta.setLayoutX(20);
       Main.getControlador().getAnchor().getChildren().addAll(compuertaCompleta);
        
    }
    
    
}
