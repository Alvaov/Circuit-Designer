/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

/**
 *
 * @author allva
 */

import static circuitdesigner.CircuitDesigner.getControlador;
import listlinked.ListLinked;
import operadores.Operadores;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import operadores.Valores;

public class ControllerCircuito implements Initializable{
    

    @FXML
    private Button EncapsularButton;
    @FXML
    private Button SimulateButton;
    @FXML
    private ImageView ANDimage;
    @FXML
    private ImageView NANDimage;
    @FXML
    private ImageView ORimage;
    @FXML
    private ImageView NORimage;
    @FXML
    private ImageView XORimage;
    @FXML
    private ImageView XNORimage;
    @FXML
    private ImageView NOTimage;
    @FXML
    private AnchorPane AnchorCircuito;
    @FXML
    private ImageView papelera;
    @FXML
    private Button tablaDeVerdad;

    ImageView nuevaCompuerta;
    String stringCompuerta;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        ANDimage.setOnDragDetected(event->{
            ANDimage.startFullDrag();
            nuevaCompuerta = new ImageView("AND2.png");
            stringCompuerta = "AND.png";
        });
        
        NANDimage.setOnDragDetected(event->{
            NANDimage.startFullDrag();
            nuevaCompuerta = new ImageView("NAND2.png");
            stringCompuerta = "NAND.png";
        });
        
        ORimage.setOnDragDetected(event->{
            ORimage.startFullDrag();
            nuevaCompuerta = new ImageView("OR2.png");
            stringCompuerta = "OR.png";
        });
        
        NORimage.setOnDragDetected(event->{
            NORimage.startFullDrag();
            nuevaCompuerta = new ImageView("NOR2.png");
            stringCompuerta = "NOR.png";
        });
        
        XORimage.setOnDragDetected(event->{
            NANDimage.startFullDrag();
            nuevaCompuerta = new ImageView("XOR2.png");
            stringCompuerta = "XOR.png";
        });
        XNORimage.setOnDragDetected(event->{
            NANDimage.startFullDrag();
            nuevaCompuerta = new ImageView("XNOR2.png");
            stringCompuerta = "XNOR.png";
        });
        NOTimage.setOnDragDetected(event->{
            NANDimage.startFullDrag();
            nuevaCompuerta = new ImageView("NOT2.png");
            stringCompuerta = "NOT.png";
        });
        papelera.setOnDragDetected(event ->{
           papelera.startFullDrag(); 
        });
        papelera.setOnMouseDragReleased(event->{
            System.out.println("Eliminar");
            for(int i = 0; i < Factory.getCircuito().getSize(); i++){
                if(Factory.getCircuito().getValor(i).equals(event.getGestureSource())){
                    Factory.getCircuito().eliminarEnPosición(i);
                    AnchorCircuito.getChildren().remove(Factory.getCircuito().getValor(i));
                }
            }
            if(event.getGestureSource() instanceof CirculoEntrada){
                System.out.println("otro grupo");
            
            }
        });
        AnchorCircuito.setOnMouseDragEntered(event ->{
            if(nuevaCompuerta != null){
                nuevaCompuerta.setFitWidth(65.0);
                nuevaCompuerta.setFitHeight(40.0);
                nuevaCompuerta.setLayoutX(350);
                nuevaCompuerta.setLayoutY(0);
                AnchorCircuito.getChildren().add(nuevaCompuerta);
                nuevaCompuerta.toFront();
            }
        });

        AnchorCircuito.setOnMouseDragOver(event ->{
            
            if(event.getGestureSource() instanceof ImageView && nuevaCompuerta != null){
                double x = event.getX();
                double y = event.getY();
                nuevaCompuerta.relocate(x-30,y-20);
                nuevaCompuerta.toString();
            }
            
            if (event.getGestureSource() instanceof CirculoEntrada){
                Point2D punto = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(event.getX(),event.getY());
                ((CirculoEntrada) event.getGestureSource()).setLayoutX(punto.getX());
                ((CirculoEntrada) event.getGestureSource()).setLayoutY(punto.getY());
            } 
            
            
            else if (event.getGestureSource() instanceof CirculoSalida){
                Point2D punto = ((CirculoSalida)event.getGestureSource()).getParent().parentToLocal(event.getX(),event.getY());
                ((CirculoSalida) event.getGestureSource()).setLayoutX(punto.getX());
                ((CirculoSalida) event.getGestureSource()).setLayoutY(punto.getY());
                
                
            }else if(event.getGestureSource() instanceof Group){
                
                Point2D punto = ((Group) event.getGestureSource()).parentToLocal(event.getX(), event.getY());
                ((Group) event.getGestureSource()).setLayoutX(event.getX());
                ((Group) event.getGestureSource()).setLayoutY(event.getY());
            }
        });
        AnchorCircuito.setOnMouseDragReleased(event ->{
            if(event.getGestureSource() instanceof ImageView){
                try {
                    crearVentana(stringCompuerta,event.getX(),event.getY());
                    AnchorCircuito.getChildren().remove(nuevaCompuerta);
                    stringCompuerta = null;
                    nuevaCompuerta = null;
                } catch (IOException ex) {
                    Logger.getLogger(ControllerCircuito.class.getName()).log(Level.SEVERE, null, ex);
                };
                
            }
           if (event.getGestureSource() instanceof CirculoSalida){ 
               System.out.println("salida pane");
               ((CirculoSalida) event.getGestureSource()).getParent().setMouseTransparent(false);
            }
           else if(event.getGestureSource() instanceof CirculoEntrada){
               System.out.println("entrada pane");
               ((CirculoEntrada) event.getGestureSource()).getParent().setMouseTransparent(false);
           }else if(event.getGestureSource() instanceof Group){
               System.out.println("grupo");
           }
            
        });
        
        SimulateButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                validarEntradas();
            }
            
        });
        tablaDeVerdad.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                tablaDeVerdad();
            }
            
        });
    }

    public AnchorPane getAnchor(){
        return AnchorCircuito;
    }
    
    public void crearVentana(String ruta,double x, double y) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDefinirEntradas.fxml"));
        Parent root1 = (Parent) loader.load();
        ControllerCantEntradas controladorEntradas = (ControllerCantEntradas)loader.getController();
        controladorEntradas.valoresImagen(ruta,x,y);
        Stage stage = new Stage();
        controladorEntradas.enviarStage(stage);
        stage.setScene(new Scene(root1));
        
        stage.show();
    }
    public void validarEntradas(){ //Cuando se presione botón de Simulate
        ListLinked<Imagen> circuito = Factory.getCircuito();
        System.out.println("Tamaño del circuito");
        System.out.println(circuito.getSize());
        for(int i = 0; i < circuito.getSize(); i++){
            Imagen compuerta = circuito.getValor(i);
            System.out.println("Entradas por compuerta");
            System.out.println(compuerta.getEntradas().getSize());
            for (int j = 0; j < compuerta.getEntradas().getSize(); j++){
                System.out.println(j);
                if (((Entrada)compuerta.getEntradas().getValor(j)).getValor() == null){
                    System.out.println("Error: faltan valores");
                    return;
                }
                
            }
        }
        emularCircuito();
    }
    private int entradasEvaluadas = 0;
    
    public String emularCircuito(){
        ListLinked<Valores> salidas = new ListLinked<>();
        ListLinked<Imagen> circuito = Factory.getCircuito();
        
        for(int i = 0; i < circuito.getSize(); i++){
            
            Imagen compuerta = circuito.getValor(i);
            
            if (compuerta.revisarEntradas()== true && compuerta.getSalida() == Valores.Default){
                compuerta.operarSalida();
                entradasEvaluadas +=1;
                System.out.println("Entradas evaluadas");
                System.out.println(entradasEvaluadas);
                System.out.println("Salida de la compuerta");
                System.out.println(compuerta.getSalida());
                if(compuerta.getEnd().conectada()== true){
                    System.out.println("Está conectada");
                    ListLinked<CirculoEntrada> entradasConectadas = compuerta.getEnd().getEntradasConectadas();
                    
                    for(int e = 0; e < entradasConectadas.getSize(); e++){
                        entradasConectadas.getValor(e).setValor(compuerta.getEnd().getValor());
                    }
                    
                }else{
                    salidas.añadirFinal(compuerta.getEnd().getValor());
                    //Aquí habría una salida final;
                }
            }
        }
        if(entradasEvaluadas < circuito.getSize()){
            System.out.println("Emula de nuevo");
            return emularCircuito();
        }else{
            return mostrarSalidas(salidas);
        }
    }
    
    public String mostrarSalidas(ListLinked<Valores> salidas){
        VBox salidasAMostrar = new VBox();
        Scene escena = new Scene(new Group());
        for (int i =0; i < salidas.getSize(); i++){
            Label label = new Label("Salidas "+i+" "+salidas.getValor(i).toString());
            salidasAMostrar.getChildren().add(label);
        }
        ((Group) escena.getRoot()).getChildren().add(salidasAMostrar);
        Stage stage = new Stage();
        stage.setTitle("Salidas del circuito");
        stage.setScene(escena);
        System.out.println("Salidas");
        stage.show();
        return null;
    }
    
    /**
     * @see Método llamado al tocar el botón de Tabla de Verdad en la GUI.
     * Calcula los posibles resultados que puede tener cada circuito con todas sus posibles
     * entradas.
     */
    public void tablaDeVerdad(){
        Scene escena = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Tabla de Verdad");
        TableView tablaDeVerdad = new TableView();
        tablaDeVerdad.setEditable(true);
        int filas = 1; //posible circuito
        int entradas = 0;
        ListLinked<TableColumn> columnas = new ListLinked<>();
        ListLinked<PosibleCircuito> valoresTabla = new ListLinked<>();
        ListLinked<Imagen> circuito = Factory.getCircuito();
        
        for(int i = 0; i< circuito.getSize(); i++){
            Imagen compuerta = circuito.getValor(i);
            for (int j = 0; j< compuerta.getEntradas().getSize(); j++){
                if(compuerta.getEntrada(j).getEndE().getIsConected() == false){
                    TableColumn columna = new TableColumn("i<"+entradas+">");
                    columnas.añadirFinal(columna);
                    entradas +=1;
                    filas *=2;
                    
                }
            }
        }
        for (int o = 0; o <columnas.getSize(); o++){
                tablaDeVerdad.getColumns().add(columnas.getValor(o));
            }
        for(int j= 0; j < filas; j++){
                System.out.println(entradas);
                System.out.println(filas);
                System.out.println("crea posible circuito");
                PosibleCircuito posibleCircuito = new PosibleCircuito(entradas,filas);
                valoresTabla.añadirFinal(posibleCircuito);
            }
        
        //ObservableList<PosibleCircuito> datos = (ObservableList) valoresTabla;
        
        
        System.out.println(filas);
        VBox elementos = new VBox();
        elementos.getChildren().addAll(tablaDeVerdad);
        
        ((Group) escena.getRoot()).getChildren().add(elementos);
        stage.setScene(escena);
        stage.show();
    }
}
