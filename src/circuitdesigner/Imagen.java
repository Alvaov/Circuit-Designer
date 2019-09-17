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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseDragEvent;
/**
 *
 * @author allva
 */
class Imagen{
    private Operadores compuerta;
    private ImageView imagenVista;
    private Group compuertaCompleta;
      
    private double orgSceneX, orgSceneY;
    private Valores salida;
    private Circulo start, startE;
    private CirculoSalida end;
    private Line line;
    private ListLinked<Entrada> entradas = new ListLinked<>();
    private int y;  
    
    public Imagen(String ruta, int cantidadDeEntradas,double x, double y){
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
          end      = new CirculoSalida("o<"+Factory.s+">",salida);
          Factory.s++;
          
          end.setOnDragDetected(event -> {
              end.startFullDrag();
              //System.out.println(event.getTarget());
              ((CirculoSalida) event.getSource()).getParent().setMouseTransparent(true);
          });
          
          end.setOnMouseDragReleased(event->{
              if (event.getGestureSource() instanceof CirculoEntrada){
                  System.out.println("release salida");//Conecta entrada con una salida
                  ((CirculoEntrada) event.getGestureSource()).setValor(salida);
                  ((CirculoEntrada) event.getGestureSource()).setIsConected(true);
                  end.setEntradasConectadas(((CirculoEntrada) event.getGestureSource())); //REVISAR //AÑADIENDO ENTRADA A LA LISTA DE ENTRADAS CONECTADAS
                  //CONTEMPLAR CASO DONDE ENTRADA ARRASTRADA CAIGA SOBRE ENTRADA CONECTADA
                  compuertaCompleta.toFront();
                  
                  end.layoutXProperty().addListener((E)->{
                      Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  });
                  end.layoutYProperty().addListener((E)->{
                      Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                  });
                  compuertaCompleta.layoutXProperty().addListener((E)->{
                      Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                      
                  });
                  compuertaCompleta.layoutYProperty().addListener((E)->{
                      Bounds coordenadas = end.getParent().localToParent(end.getBoundsInParent());
                      Bounds nuevasCoordenadas = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(coordenadas);
                      ((CirculoEntrada)event.getGestureSource()).setLayoutX(nuevasCoordenadas.getMinX());
                      ((CirculoEntrada)event.getGestureSource()).setLayoutY(nuevasCoordenadas.getMinY());
                      
                  });
                  ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
              }else{
                  System.out.println("Conexión inválida");
              }
          });
          
          end.setOnMouseClicked(event ->{
             if(event.getClickCount() == 2){
                 System.out.println(end.getEntradasConectadas());
                 end.getEntradasConectadas().eliminarLista();
                 //end.getEntradasConectadas() = null;
                 /*end.layoutXProperty().removeListener(listener);
                 end.layoutYProperty().removeListener(listener);
                 compuertaCompleta.layoutXProperty().removeListener(listener);
                 compuertaCompleta.layoutYProperty().removeListener(listener);*/
                 System.out.println(end.getEntradasConectadas().getValor(3));
             } 
          });
          
          end.setLayoutX(60);
          end.setLayoutY(20);
          start    = new Circulo("",null);
          start.setLayoutX(40);
          start.setLayoutY(20);
          line     = new Line();
          line.startXProperty().bind(start.layoutXProperty());
          line.startYProperty().bind(start.layoutYProperty());
          line.endXProperty().bind(end.layoutXProperty());
          line.endYProperty().bind(end.layoutYProperty());
          line.setStroke(colorLinea());
          startE   = new Circulo("",null);
          startE.setLayoutX(25);
          startE.setLayoutY(20);

          y = 0;
          for (int i = 0; i < cantidadDeEntradas; i++){
              Entrada entrada = new Entrada(imagenVista,startE,Factory.e);
              entrada.getEndE().setLayoutY(y);
              entradas.añadirFinal(entrada); 
              compuertaCompleta.getChildren().addAll(entrada.getLinea(),entrada.getEndE(),entrada.getEndE().getEtiqueta());
              
              Factory.e++;
              y += 7;
          }
          
          crearCompuerta(ruta);
          compuertaCompleta.getChildren().addAll(start,end,startE,line,end.getEtiqueta(),imagenVista);
          CircuitDesigner.getControlador().getAnchor().getChildren().add(compuertaCompleta);
          imagenVista.setOnDragDetected(event->{
              start.startFullDrag();
              startE.startFullDrag();
              compuertaCompleta.startFullDrag();
          });
      }
    
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
    
    public Entrada getEntrada(int i){
        Entrada entrada = entradas.getValor(i);
        return entrada;
    }
    
    public CirculoSalida getEnd(){
        return end;
    }
    
    public ListLinked getEntradas(){
        return entradas;
    }
    
    public Valores getSalida(){
        return end.getValor();
    }
    public ImageView getImagen(){
        return imagenVista;
    }
    public Group getCompuerta(){
        return compuertaCompleta;
    }
    public void operarSalida(){
        System.out.println("Operación");
        salida = compuerta.operación(entradas);
        System.out.println(salida);
        System.out.println("asignar salida");
        end.setValor(salida);
        System.out.println(salida);
    }
    
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
        return color;
    }
  }