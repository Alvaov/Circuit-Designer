/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import listlinked.ListLinked;
import operadores.*;
import java.util.Observer;
import java.util.Observable;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.shape.Rectangle;
/**
 * @see clase que crea todos los elementos que requiere una compuerta lógica
 * incluidos tanto los elementos gráficos como los lógicos propiamente de funcionamiento
 * @author allva
 */
class Compuerta{
    private Operadores compuerta;
    private ImageView imagenVista;
    private Group compuertaCompleta;
      
    private double orgSceneX, orgSceneY;
    private Valores salida;
    private Circulo start, startE;
    private CirculoSalida end;
    private Line line;
    private ListLinked<Entrada> entradas = new ListLinked<>();
    private ListLinked<CirculoSalida> salidas = new ListLinked<>();
    private ListLinked<Compuerta> circuitoCompuesto = new ListLinked<>();
    private Rectangle figura;
    private int y;
    
    public Compuerta(ListLinked<Entrada> entradas, ListLinked<Compuerta> circuito, ListLinked<CirculoSalida> salidas){
        this.entradas = entradas;
        this.salidas = salidas;
        this.circuitoCompuesto = circuito;
    }
    /**
     * @see Constructor de la clase Compuerta, para cuando estas son compuertas preestablecidas,
     * crea las entradas, la salida, compuerta lógica, y demás elementos necesarios para la implementación
     * correcta de cada compuerta que el usuario requiera, además la posiciona el en lugar donde el usuario decidió colocar
     * la imagen arrastrada de la paleta.
     * @param String ruta
     * @param int cantidadDeEntradas
     * @param double x
     * @param double x
     * 
     */
    public Compuerta(String ruta, int cantidadDeEntradas,double x, double y){
          compuertaCompleta = new Group();
          compuertaCompleta.setLayoutX(x-20);
          compuertaCompleta.setLayoutY(y-20);
          salida = Valores.Default;
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);
          end      = new CirculoSalida(salida);

          
          end.setOnDragDetected(event -> {
              end.startFullDrag();
              //System.out.println(event.getTarget());
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
                  ((CirculoEntrada) event.getGestureSource()).setValor(salida);
                  ((CirculoEntrada) event.getGestureSource()).setIsConected(true);
                  //end.setEntradasConectadas(((CirculoEntrada) event.getGestureSource()));
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
          
          end.setOnMouseClicked(event ->{
             if(event.getClickCount() == 2){
                 System.out.println("desconcetar salida"); 
                 for(int i =0; i < end.getEntradasConectadas().getSize(); i++){
                     System.out.println(end.entradasConectadas.getSize());
                     ((CirculoEntrada) end.getEntradasConectadas().getValor(i)).setIsConected(false);
                     ((CirculoEntrada) end.getEntradasConectadas().getValor(i)).getParent().layoutXProperty().removeListener((ChangeListener)compuertaCompleta.getUserData());
                     ((CirculoEntrada) end.getEntradasConectadas().getValor(i)).getParent().layoutYProperty().removeListener((ChangeListener)compuertaCompleta.getUserData());
                 }
                 end.getEntradasConectadas().eliminarLista();
                 
                 end.layoutXProperty().removeListener((ChangeListener) end.getUserData());
                 end.layoutYProperty().removeListener((ChangeListener) end.getUserData());
                 compuertaCompleta.layoutXProperty().removeListener((ChangeListener) end.getUserData());
                 compuertaCompleta.layoutYProperty().removeListener((ChangeListener) end.getUserData());
             } 
          });
          
          end.setLayoutX(60);
          end.setLayoutY(20);
          end.getEtiqueta().layoutXProperty().bind(end.layoutXProperty());
          end.getEtiqueta().layoutYProperty().bind(end.layoutYProperty());
          start    = new Circulo(null);
          start.setLayoutX(40);
          start.setLayoutY(20);
          line     = new Line();
          line.startXProperty().bind(start.layoutXProperty());
          line.startYProperty().bind(start.layoutYProperty());
          line.endXProperty().bind(end.layoutXProperty());
          line.endYProperty().bind(end.layoutYProperty());
          line.setStroke(colorLinea());
          startE   = new Circulo(null);
          startE.setLayoutX(25);
          startE.setLayoutY(20);

          y = 0;
          for (int i = 0; i < cantidadDeEntradas; i++){
              Entrada entrada = new Entrada(imagenVista,startE,0);
              entrada.getEndE().setLayoutY(y);
              entradas.añadirFinal(entrada); 
              compuertaCompleta.getChildren().addAll(entrada.getLinea(),entrada.getEndE());
              y += 7;
          }
          
          crearCompuerta(ruta);
          compuertaCompleta.getChildren().addAll(start,end,startE,line,imagenVista,end.getEtiqueta());
          Main.getControlador().getAnchor().getChildren().add(compuertaCompleta);
          imagenVista.setOnDragDetected(event->{
              start.startFullDrag();
              startE.startFullDrag();
              compuertaCompleta.startFullDrag();
          });
          imagenVista.setOnMouseClicked(event->{
              if(event.getClickCount()==2){
                  for(int i = 0; i < Main.getControlador().getCircuito().getSize(); i++){
                      if(Main.getControlador().getCircuito().getValor(i).getImagen().equals(this.imagenVista)){
                          Main.getControlador().getCircuito().eliminarEnPosición(i);
                          
                      }
                  }
              }
          });
      }
    /**
     * @see método que se utiliza para asignar una compuerta lógica 
     * al atributo respectivo de cada objeto de la clase compuerta, a través
     * del string ingresado al crear la instancia.
     * @param String ruta
     */
    private void crearCompuerta(String ruta){
        switch (ruta) {
            case "AND.png":
                AndOperator and = new AndOperator(entradas);
                compuerta = and;
                break;
            case "OR.png":
                OrOperator or = new OrOperator(entradas);
                compuerta = or;
                break;
            case "XOR.png":
                XOROperator xor = new XOROperator(entradas);
                compuerta = xor;
                break;
            case "XNOR.png":
                NegaciónOperador xnor = new NegaciónOperador(entradas,3);
                compuerta = xnor;
                break;
            case "NAND.png":
                NegaciónOperador nand = new NegaciónOperador(entradas,1);
                compuerta = nand;
                break;
            case "NOR.png":
                NegaciónOperador nor = new NegaciónOperador(entradas,2);
                compuerta = nor;
                break;
            case "NOT.png":
                NegaciónOperador not = new NegaciónOperador(entradas,4);
                compuerta = not;
                break;
        }
    }
    /**
     * @see método que retorna una entrada específica perteneciente a la compuerta desde la cual
     * se llamó al método.
     * @param int i
     * @return Entrada 
     */
    public Entrada getEntrada(int i){
        Entrada entrada = entradas.getValor(i);
        return entrada;
    }
    /**
     * @see método que retorna el círculo de salida de cada compuerta 
     * @return CirculoSalida end
     */
    public CirculoSalida getEnd(){
        return end;
    }
    /**
     * @see método que retorna en su totalidad la lista enlazada que posee la compuerta.
     * @return ListLinked
     */
    public ListLinked getEntradas(){
        return entradas;
    }
    /**
     * @see método que retorna el valor actual del círculo de salida
     * @return Valores
     */
    public Valores getSalida(){
        return end.getValor();
    }
    /**
     * @see método que retorna la ImageView que se utiliza en la compuerta
     * @return ImageView
     */
    public ImageView getImagen(){
        return imagenVista;
    }
    /**
     * @see método que retorna el grupo dentro del cual están todos los elementos que componen la compuerta.
     * @return Group
     */
    public Group getCompuerta(){
        return compuertaCompleta;
    }
    /**
     * @see función que realiza la operación de cada compuerta con las entradas que posee, asigna este valor al atributo salida
     * y al círculo salida como tal.
     * 
     */
    public void operarSalida(){
        System.out.println("Operación");
        salida = compuerta.operación(entradas);
        System.out.println(salida);
        System.out.println("asignar salida");
        end.setValor(salida);
        System.out.println(salida);
    }
    /**
     * @see método que se utiliza para evaluar la lista de entradas que posee la compuerta. Retorna true o false
     * dependiendo si todas las entradas poseen un valor diferente de nulo o no.
     * @param Boolean
     */
    public boolean revisarEntradas(){
      System.out.println("revisa entradas");
      for (int i = 0; i < entradas.getSize(); i++){
          if(entradas.getValor(i).getValor() != null && entradas.getValor(i).getValor() != Valores.Default){
              continue;
          }else{
              return false;
          }
      }
      return true;
    }
    /**
     * @see Método que calcula un nuevo color para cada línea, y verifica que este color no esté ya asignado a ninguna 
     * otra línea del circuito.
     * @return Color
     */
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        ListLinked<Color> coloresUsados = Factory.getColores();
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorLinea();
            }
        }
        coloresUsados.añadirFinal(color);
        return color;
    }
  }