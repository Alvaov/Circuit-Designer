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
    private Group compuertaCompleta = new Group();
      
    private double orgSceneX, orgSceneY;
    private Valores salida;
    private Circulo start, startE;
    private CirculoSalida end;
    private Line line;
    private ListLinked<Entrada> entradas = new ListLinked<>();
    private boolean isConected;
    private int y;  
    
    public Imagen(String ruta, int cantidadDeEntradas){
          salida = Valores.Default;
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);
          end      = new CirculoSalida("o<"+Facade.s+">",salida);
          Facade.s++;
          
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
                  isConected = true;
                  
              }
              ((CirculoEntrada)event.getGestureSource()).getParent().setMouseTransparent(false);
          });
          
          end.setLayoutX(60);
          end.setLayoutY(23);
          start    = new Circulo("",null);
          start.setLayoutX(40);
          start.setLayoutY(23);
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
              Entrada entrada = new Entrada(imagenVista,startE,Facade.e);
              entrada.getEndE().setLayoutY(y);
              entradas.añadirFinal(entrada); 
              compuertaCompleta.getChildren().addAll(entrada.getLinea(),entrada.getEndE(),entrada.getEndE().getEtiqueta());
              
              Facade.e++;
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
      
    public void colisiónS(){
        ListLinked<Imagen> circuito = Facade.getCircuito();
          
          for (int c = 0; c < circuito.getSize(); c++){
              Imagen imagen = circuito.getValor(c);

              CirculoSalida fin = imagen.getEnd();
              for(int i = 0; i < imagen.getEntradas().getSize(); i++){
                  
                  Entrada entrada = imagen.getEntrada(i);
                  if (fin == this.end){ 
                      continue;
                        }
                  if (fin.getCenterX()+4 >= this.end.getCenterX()&& fin.getCenterX()-4 <= this.end.getCenterX()){
                      if(fin.getCenterY()+4 >= this.end.getCenterY() && fin.getCenterY()-4 <= this.end.getCenterY()){

                      System.out.println("Error: no se pueden conectar dos salidas");
                      }
                  }
                    else{
                      if (entrada.getEndE().getCenterX()+4 >= this.end.getCenterX()&& entrada.getEndE().getCenterX()-4 <= this.end.getCenterX()){
                      if(entrada.getEndE().getCenterY()+4 >= this.end.getCenterY() && entrada.getEndE().getCenterY()-4 <= this.end.getCenterY()){

                         this.end.setCenterX(entrada.getEndE().getCenterX());
                         this.end.setCenterY(entrada.getEndE().getCenterY());

                      }
                    }
                 }
              }
         }
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
        Entrada entrada = entradas.buscarElemento(i);
        return entrada;
    }
    
    public CirculoSalida getEnd(){
        return end;
    }
    
    public ListLinked getEntradas(){
        return entradas;
    }
    
    public Valores getSalida(){
        return salida;
    }
    public ImageView getImagen(){
        return imagenVista;
    }
    public void operarSalida(){
        salida = compuerta.operación(entradas);
        end.setValor(salida);
    }
    
    public void revisarEntradas(){
      for (int i = 0; i < entradas.getSize(); i++){
          if(entradas.getValor(i).getValor() != null && entradas.getValor(i).getValor() != Valores.Default){
              continue;
          }else{
              return;
          }
      }
      operarSalida();
    }
    
    public Color colorLinea(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        ListLinked<Color> coloresUsados = Facade.getColores();
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorLinea();
            }
        }
        return color;
    }
  }