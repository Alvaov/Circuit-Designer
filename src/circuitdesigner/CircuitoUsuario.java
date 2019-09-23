/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import listlinked.ListLinked;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class CircuitoUsuario{
    private ListLinked<Compuerta> circuito = new ListLinked<Compuerta>();
    private ListLinked<Entrada> entradas ;
    private ListLinked<CirculoSalida> salidas;
    private Group compuertaCompleta;
    private Rectangle imagenCompuerta;// = new Rectangle(10,25);
    private Rectangle imagenPaleta;
    private Circulo startE, start;
    
    public CircuitoUsuario(Rectangle imagen,ListLinked<Compuerta> compuerta, ListLinked<Entrada> entradas, ListLinked<CirculoSalida> salidas){
        this.entradas = new ListLinked<>();//entradas;
        this.salidas = new ListLinked<>();
      //  this.imagenCompuerta = imagen;
       // this.imagenCompuerta = new Rectangle(10,25,imagenPaleta.getFill());
        
        imagenPaleta = new Rectangle(10,25,imagen.getFill());
        Label label = new Label(""+entradas.getSize());
        //imagenPaleta.setFill(colorCompuerta());
        this.imagenCompuerta = new Rectangle(10,25,imagenPaleta.getFill());
        
        imagenPaleta.setOnDragDetected(event->{
            System.out.println("inicio drag");
            imagenPaleta.startFullDrag();
            Rectangle imagenAnchor = new Rectangle(10,25,imagenPaleta.getFill());
            imagenAnchor.setUserData(imagenPaleta.getUserData());
            Main.getControlador().setRentángulo(imagenAnchor);
        });
        //Main.getControlador().getPaleta().getChildren().add(imagenPaleta);
        compuertaCompleta = new Group();
        startE   = new Circulo(null);
        startE.setLayoutX(25);
        startE.setLayoutY(25);
        start = new Circulo(null);
        start.setLayoutX(25);
        start.setLayoutY(25);
       int indiceSalidas = salidas.getSize();
       for (int i = 0; i < indiceSalidas; i++){
       //     this.salidas.añadirFinal(salidas.getValor(i));
            CirculoSalida end = new CirculoSalida(Valores.Default);
            end.setValorConectado(salidas.getValor(i));
            Line line     = new Line();
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
       //System.out.println(compuerta.getValor(0));
       //System.out.println(compuerta.getValor(0).getEntrada(0).getEndE().getCompuertaPadre());
       for(int i= 0; i < tamañoCircuito; i++){
           circuito.añadirFinal(compuerta.getValor(i));
       }
      // for(int i = 0; i < tamañoCircuito;i++){
      //     Main.getControlador().getAnchor().getChildren().remove(Main.getControlador().getCircuito().getValor(0).getCompuerta());
     //      Main.getControlador().getCircuito().eliminarInicio();
      // }
       //System.out.println(circuito.getSize());
       //System.out.println(Main.getControlador().getCircuito().getSize());
      // System.out.println(circuito.getValor(0));
      // System.out.println(circuito.getValor(0).getEntrada(0).getEndE().getCompuertaPadre());
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
              for(int i = 0; i < Main.getControlador().getCircuito().getSize(); i++){
                  for(int j = 0; j < circuito.getSize(); j++){
                      if(Main.getControlador().getCircuito().getValor(i).equals(circuito.getValor(0))){
                          System.out.println(Main.getControlador().getCircuito().getValor(0));
                    //      Main.getControlador().getCircuito().eliminarEnPosición(0);
                    //      circuito.eliminarEnPosición(0);
                      }
                  }
              }
              Main.getControlador().getAnchor().getChildren().remove(compuertaCompleta);
           }
       });
        
       compuertaCompleta.getChildren().addAll(startE,start,imagenCompuerta);
       compuertaCompleta.setLayoutX(20);
      // Main.getControlador().getAnchor().getChildren().addAll(compuertaCompleta);
        
    }
    
    public Rectangle getImagenPaleta(){
        return imagenPaleta;
    }
    public ListLinked<Compuerta> getCircuitoUsuario(){
        return circuito;
    }
    public Group getCompuertaCompleta(){
        return compuertaCompleta;
    }
    public ListLinked<Entrada> getEntradas(){
        return entradas;
    }
    public ListLinked<CirculoSalida> getSalidas(){
        return salidas;
    }
    /**
     * @see Método que calcula un nuevo color para cada línea, y verifica que este color no esté ya asignado a ninguna 
     * otra línea del circuito.
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
