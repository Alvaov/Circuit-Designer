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
      
    private double orgSceneX, orgSceneY, newX, newY;
    private Valores salida;
    private Circulo start, startE;
    private CirculoSalida end;
    private Line line;
    private ListLinked<Entrada> entradas = new ListLinked<>();
    private boolean isConected;
    private DoubleProperty inicioY = new SimpleDoubleProperty (orgSceneY+23);
    private DoubleProperty inicioX = new SimpleDoubleProperty (orgSceneX+45);
    private DoubleProperty endX   = new SimpleDoubleProperty(orgSceneX+75);
    private DoubleProperty endY   = new SimpleDoubleProperty(orgSceneY+23);
    private DoubleProperty startx = new SimpleDoubleProperty(orgSceneX+23);
    private DoubleProperty starty = new SimpleDoubleProperty(orgSceneY+22);
    private int y;  
    
    public Imagen(String ruta, int cantidadDeEntradas){
          salida = Valores.Default;
          Image imagen = new Image(ruta);
          imagenVista = new ImageView(imagen);
          imagenVista.setFitWidth(65.0);
          imagenVista.setFitHeight(40.0);
          imagenVista.setX(orgSceneX);
          imagenVista.setY(orgSceneY);

          end      = new CirculoSalida(endX,   endY,"o<"+Facade.s+">",salida);
          Facade.s++;
          end.setOnDragDetected(MouseDetected);
          end.setOnMouseDragReleased(event->{
              if (event.getGestureSource() instanceof CirculoEntrada){
                  System.out.println("hola");//Conecta entrada con una salida
              }
          });
          start    = new Circulo( inicioX, inicioY,"",null);
          line     = new BoundLine(inicioX,inicioY, endX, endY);
          startE   = new Circulo( startx, starty,"",null);
          
          
          y = 0;
          for (int i = 0; i < cantidadDeEntradas; i++){
              Entrada entrada = new Entrada(imagenVista,startx,starty,Facade.e);
              entrada.getEndE().setCenterY(y);
              entradas.añadirFinal(entrada); 
              compuertaCompleta.getChildren().addAll(entrada.getEndE(),entrada.getLinea());
              Facade.e++;
              y += 7;
          }
          
          crearCompuerta(ruta);
          compuertaCompleta.getChildren().addAll(imagenVista,end,start,line);
          CircuitDesigner.getControlador().getAnchor().getChildren().add(compuertaCompleta);
          compuertaCompleta.setOnDragDetected(event->{
              compuertaCompleta.startFullDrag();
          });
      }
      EventHandler<MouseEvent>MouseDetected = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            end.startFullDrag();
        }
          
      };
    


      EventHandler<MouseDragEvent> DragRelease = new EventHandler<MouseDragEvent>(){
        @Override
        public void handle(MouseDragEvent event) {
            System.out.println("Ya no");
        }
          
      };
      
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
    
    public void OperarSalida(){
        salida = compuerta.operación(entradas);
        end.setValor(salida);
    }
  }