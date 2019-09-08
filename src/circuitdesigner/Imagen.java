/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

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

/**
 *
 * @author allva
 */
class Imagen extends ImageView{
    private Operadores compuerta;
    private ImageView imagenVista;

      
    private double orgSceneX, orgSceneY, newX, newY;
    private Valores salida;
    private Anchor start, startE;
    private Anchor end;
    private Line line;
    private static Delta dragDelta = new Delta();
    private ListLinked<Entrada> entradas = new ListLinked<>();

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
          imagenVista.setOnMousePressed(MousePressed);
          imagenVista.setOnMouseDragged(MouseDragged);
     
          end      = new Anchor(Color.TOMATO,    endX,   endY,"o<"+Facade.s+">");
          Facade.s++;
          end.setOnMousePressed(MousePressedE);
          end.setOnMouseDragged(MouseDraggedE);
          start    = new Anchor(Color.PALEGREEN, inicioX, inicioY,"");
          line     = new BoundLine(inicioX,inicioY, endX, endY);
          startE   = new Anchor(Color.PALEGREEN, startx, starty,"");
          
          
          y = 0;
          for (int i = 0; i < cantidadDeEntradas; i++){
              Entrada entrada = new Entrada(imagenVista,startx,starty,Facade.e);
              entrada.getEndE().setCenterY(y);
              entradas.añadirFinal(entrada);   
              Facade.e++;
              y += 7;
          }
          crearCompuerta(ruta);
          CircuitDesigner.getControlador().getRoot().getChildren().addAll(imagenVista,end,start,line);
          
      }
      EventHandler<MouseEvent> MousePressed = 
        new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            dragDelta.x = imagenVista.getX() - t.getX();
            dragDelta.y = imagenVista.getY() - t.getY();
        }
        };
     
    EventHandler<MouseEvent> MouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {

            newX = t.getX() + dragDelta.x;
            newY = t.getY() + dragDelta.y;
            
            imagenVista.setX(newX);
            start.setCenterX(newX+45);

            imagenVista.setY(newY);
            start.setCenterY(newY+23);
            
            startE.setCenterY(newY+22);
            startE.setCenterX(newX+23);
        }
    };
    
    public static Delta getDelta(){
        return dragDelta;
    }
    EventHandler<MouseEvent> MousePressedE = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {

          dragDelta.x = end.getCenterX() - t.getX();
          dragDelta.y = end.getCenterY() - t.getY();

        }
      };
        EventHandler<MouseEvent> MouseDraggedE = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent t) {
            newX = t.getX() + dragDelta.x;
            end.etiqueta.setLayoutX(newX);
            end.setCenterX(newX);
            
            newY = t.getY() + dragDelta.y;
            end.etiqueta.setLayoutY(newY);
            end.setCenterY(newY);

            }
        };
        
        
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
    
    public Anchor getEnd(){
        return end;
    }
    
    public ListLinked getEntradas(){
        return entradas;
    }
    
    public Valores getSalida(){
        return salida;
    }
  }