/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import listlinked.ListLinked;
import operadores.Valores;

/**
 * Clase que crea todos los componentes necesarios para los diferentes circuitos de usuario así como sus funciones para que estos sean
 * manejables.
 * @author allva
 */
public class CircuitoUsuario{
    private ListLinked<Compuerta> circuito = new ListLinked<>();
    private ListLinked<Entrada> entradas ;
    private ListLinked<CirculoSalida> salidas;
    private Group compuertaCompleta;
    private Rectangle imagenCompuerta;
    private Rectangle imagenPaleta;
    private Circulo startE, start;
    
    /**
     * Constructor, recibe un rectángulo, una lista de compuertas, lista de las entradas de usuario, y de salidas generales del circuito,
     * se encarga de crear tantos componentes como sean necesarios y relacionarlos con su respectivo par en el circuito interno de cada circuito encapsulado
     * para que cumpla con normalidad las funciones que se soliciten.
     * Sintaxis de lambda obtenida de https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html.
     * ChangeListener https://docs.oracle.com/javafx/2/api/javafx/beans/value/ChangeListener.html
     * Eventos de mouse https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/MouseDragEvent.html
     * @param imagen, imagen de la compuerta
     * @param compuerta, lista de compuertas encapsuladas
     * @param entradas, entradas del usuario
     * @param salidas, salidas generales del circuito
     */
    public CircuitoUsuario(Rectangle imagen,ListLinked<Compuerta> compuerta, ListLinked<Entrada> entradas, ListLinked<CirculoSalida> salidas){
        this.entradas = new ListLinked<>();
        this.salidas = new ListLinked<>();
        
        imagenPaleta = new Rectangle(10,25,imagen.getFill());
        this.imagenCompuerta = new Rectangle(10,25,imagenPaleta.getFill());
        
        imagenPaleta.setOnDragDetected(event->{
            imagenPaleta.startFullDrag();
            Rectangle imagenAnchor = new Rectangle(10,25,imagenPaleta.getFill());
            imagenAnchor.setUserData(imagenPaleta.getUserData());
            Main.getControlador().setRentángulo(imagenAnchor);
        });
        compuertaCompleta = new Group();
        startE   = new Circulo(null);
        startE.setLayoutX(25);
        startE.setLayoutY(25);
        start = new Circulo(null);
        start.setLayoutX(25);
        start.setLayoutY(25);
       int indiceSalidas = salidas.getSize();
       for (int i = 0; i < indiceSalidas; i++){
            CirculoSalida end = new CirculoSalida(Valores.Default);
            end.setValorConectado(salidas.getValor(i));
            Line line     = new Line();
            line.setStroke(colorCompuerta());
            line.startXProperty().bind(start.layoutXProperty());
            line.startYProperty().bind(start.layoutYProperty());
            line.endXProperty().bind(end.layoutXProperty());
            line.endYProperty().bind(end.layoutYProperty());
            compuertaCompleta.getChildren().addAll(line,end,end.getEtiqueta());
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
              }
          });
             this.salidas.añadirFinal(end);
        }
       int y = 0;
       for (int i = 0; i < entradas.getSize(); i++){
           Entrada entrada = new Entrada(startE);
           entrada.getEndE().setValorConectado(entradas.getValor(i).getEndE());
           entrada.getEndE().setLayoutY(y);
           this.entradas.añadirFinal(entrada);
           y=+7; 
      }
       int indiceEntradas = this.entradas.getSize();
       for(int i =0; i < indiceEntradas;i++){
           compuertaCompleta.getChildren().addAll(this.entradas.getValor(i).getEndE(),this.entradas.getValor(i).getLinea());
       }
       int tamañoCircuito = compuerta.getSize();
       for(int i= 0; i < tamañoCircuito; i++){
           circuito.añadirFinal(compuerta.getValor(i));
       }
       imagenCompuerta.setLayoutX(20);
       imagenCompuerta.setLayoutY(10);
       
       imagenCompuerta.setOnDragDetected(event->{
             start.startFullDrag();
             startE.startFullDrag();
             compuertaCompleta.startFullDrag();
         });
       imagenCompuerta.setOnMouseClicked(event->{
           if (event.getClickCount()==2){
              int tamañoCircuitoGeneral = Main.getControlador().getCircuito().getSize();
              int tamañoCircuitoLocal = circuito.getSize();
              for(int i = 0; i < circuito.getSize();i++){
                  for(int j=0; j< Main.getControlador().getCircuito().getSize();j++){
                      if(Main.getControlador().getCircuito().getValor(j).equals(circuito.getValor(i))){
                          Main.getControlador().getCircuito().eliminarEnPosición(j+1);
                      }
                  }
              }
              circuito = null;
              this.entradas = null;
              this.salidas = null;
              Main.getControlador().getAnchor().getChildren().remove(compuertaCompleta);
           }
       });
        
       compuertaCompleta.getChildren().addAll(startE,start,imagenCompuerta);
       compuertaCompleta.setLayoutX(20);
        
    }
    
    /**
     * Método que permite obtener la imagen de la paleta.
     * @return imagenPaleta
     */
    public Rectangle getImagenPaleta(){
        return imagenPaleta;
    }

    /**
     * Retorna la lista enlazada que contiene todas las compuertas pertenecientes al circuito encapsulado en cuestión
     * @return circuito
     */
    public ListLinked<Compuerta> getCircuitoUsuario(){
        return circuito;
    }

    /**
     * Retorna el group que contiene todos los elementos pertenecientes al circuito encapsulado en cuestión
     * @return compuertaCompleta
     */
    public Group getCompuertaCompleta(){
        return compuertaCompleta;
    }

    /**
     * Retorna el lista enlazada de entradas del circuito encapsulado en cuestión
     * @return entradas
     */
    public ListLinked<Entrada> getEntradas(){
        return entradas;
    }

    /**
     * Retorna la lista enlazada que contiene cada salida respectiva contenida en el circuito encapsulado
     * @return salidas
     */
    public ListLinked<CirculoSalida> getSalidas(){
        return salidas;
    }
    /**
     * Método que calcula un nuevo color para cada línea, y verifica que este color no esté ya asignado a ninguna 
     * otra línea del circuito. Código base tomado de https://www.quora.com/How-can-I-randomize-colors-in-Java
     * @return Color
     */
    public Color colorCompuerta(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        ListLinked<Color> coloresUsados = Main.getControlador().getColores();
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorCompuerta();
            }
        }
        coloresUsados.añadirFinal(color);
        return color;
    }
}
