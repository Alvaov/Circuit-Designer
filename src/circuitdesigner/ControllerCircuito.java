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

import listlinked.ListLinked;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import operadores.Valores;

/**
 * lase que funciona como el controller principal de la aplicación, y principal puente entre las diferentes funcionalidades de la aplicación
 * @author allva
 */
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
    private Button tablaDeVerdad;
    @FXML
    private VBox paleta;

    private ImageView nuevaCompuerta;
    private String stringCompuerta;
    private Rectangle nuevaCompuertaUsuario;
    private static ListLinked<Compuerta> circuito = new ListLinked<>();
    private static ListLinked<Color> coloresUsados = new ListLinked<>();
    private static ControllerCircuito controlador;
    private ControllerCircuito(){    
    }

    /**
     * Método basado en el patrón Singleton, código base tomado de https://www.arquitecturajava.com/ejemplo-de-java-singleton-patrones-classloaders/
     * se garantiza que solo se pueda crear una única insancia de esta clase, ya que no conviene tener más de un objeto Controller.
     * @return controlador
     */
    public static ControllerCircuito getControlador(){
        if(controlador == null){
            controlador = new ControllerCircuito();
        }
        return controlador;
    }
    /**
     * Método que inicializa todos lo necesario para la correcta ejecución del programa, incluyendo acciones sobre botontes, imágenes, compuertas como tal
     * y permitiendo el desarrolo viable del programa. Sintaxis lambda obtenida de 
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html.
     * Eventos de mouse https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/MouseDragEvent.html
     * Código base definición de evento de boton http://www.java2s.com/Code/Java/JavaFX/ButtonOnAction.htm
     */
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
        AnchorCircuito.setOnMouseDragEntered(event ->{
            if(nuevaCompuerta != null){
                nuevaCompuerta.setFitWidth(65.0);
                nuevaCompuerta.setFitHeight(40.0);
                nuevaCompuerta.setLayoutX(350);
                nuevaCompuerta.setLayoutY(0);
                AnchorCircuito.getChildren().add(nuevaCompuerta);
                nuevaCompuerta.toFront();
            }
            else if(nuevaCompuertaUsuario != null){
                AnchorCircuito.getChildren().add(nuevaCompuertaUsuario);
                nuevaCompuertaUsuario.toFront();
            }
        });

        AnchorCircuito.setOnMouseDragOver(event ->{
            
            if(event.getGestureSource() instanceof Rectangle && nuevaCompuertaUsuario != null){
                double x = event.getX();
                double y = event.getY();
                nuevaCompuertaUsuario.relocate(x-30,y-20);
            }
            
            if(event.getGestureSource() instanceof ImageView && nuevaCompuerta != null){
                double x = event.getX();
                double y = event.getY();
                nuevaCompuerta.relocate(x-30,y-20);
                nuevaCompuerta.toString();
            }
            
            if (event.getGestureSource() instanceof CirculoEntrada && ((CirculoEntrada)event.getGestureSource()).getIsConected() == false){
                Point2D punto = ((CirculoEntrada)event.getGestureSource()).getParent().parentToLocal(event.getX(),event.getY());
                ((CirculoEntrada) event.getGestureSource()).setLayoutX(punto.getX());
                ((CirculoEntrada) event.getGestureSource()).setLayoutY(punto.getY());
            } 
            
            
            else if (event.getGestureSource() instanceof CirculoSalida && ((CirculoSalida)event.getGestureSource()).getIsConected() == false){
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
            
            if(event.getGestureSource() instanceof Rectangle){
                CircuitoUsuario circuito = (CircuitoUsuario) ((Rectangle)event.getGestureSource()).getUserData();
                CircuitoUsuario nuevoCircuito = new CircuitoUsuario(circuito.getImagenPaleta(),circuito.getCircuitoUsuario(),circuito.getEntradas(),circuito.getSalidas());
                AnchorCircuito.getChildren().remove(nuevaCompuertaUsuario);
                nuevaCompuertaUsuario = null;
                AnchorCircuito.getChildren().add(nuevoCircuito.getCompuertaCompleta());
                for (int i = 0; i < nuevoCircuito.getCircuitoUsuario().getSize(); i++){
                    this.circuito.añadirFinal(nuevoCircuito.getCircuitoUsuario().getValor(i));
                }
            }
            if(event.getGestureSource() instanceof ImageView){
                try {
                    if (stringCompuerta.equalsIgnoreCase("NOT.png")){
                        Compuerta compuerta = new Compuerta(stringCompuerta,1,event.getX(),event.getY());
                        Entrada entrada = (Entrada) compuerta.getEntradas().getValor(0);
                        entrada.getEndE().setCompuertaPadre(compuerta);
                        Main.getControlador().getCircuito().añadirFinal(compuerta);
                        actualizarEtiquetas();
                        AnchorCircuito.getChildren().remove(nuevaCompuerta);
                        stringCompuerta = null;
                        nuevaCompuerta = null;
                    }else{
                    crearVentana(stringCompuerta,event.getX(),event.getY());
                    AnchorCircuito.getChildren().remove(nuevaCompuerta);
                    stringCompuerta = null;
                    nuevaCompuerta = null;
                    }
                    actualizarEtiquetas();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerCircuito.class.getName()).log(Level.SEVERE, null, ex);
                };
                
            }
           if (event.getGestureSource() instanceof CirculoSalida){ 
               ((CirculoSalida) event.getGestureSource()).getParent().setMouseTransparent(false);
            }
           else if(event.getGestureSource() instanceof CirculoEntrada){
               ((CirculoEntrada) event.getGestureSource()).getParent().setMouseTransparent(false);
           }else if(event.getGestureSource() instanceof Group){
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
        EncapsularButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                encapsular();
            }
            
        });
    }
    
    /**
     * Método que retorna el VBox donde se colocan los distintos elementos de la paleta
     * @return paleta, retorna la paleta de compuertas
     */
    public VBox getPaleta(){
        return paleta;
    }
    
    /**
     * Método para asignar el rectángulo respectivo a la variable nuevaCompuertaUsuario que permite mantener 
     * el mismo tamaño y color del rectángulo de la paleta que va a ser cargado en la pantalla de trabajo
     * @param nuevoRectangulo, asigna el valor del nuevo rectángulo a colocar en pantalla
     */
    public void setRentángulo(Rectangle nuevoRectangulo){
        nuevaCompuertaUsuario = nuevoRectangulo;
    }

    
    /**
     * Método que retorna el AnchorPane donde se colocan los distintos elementos de la GUI
     * @return AnchorCircuito, retorna el AnchorPane donde se colocan las compuertas
     */
    public AnchorPane getAnchor(){
        return AnchorCircuito;
    }
    
    /**
     * Método que retorna la lista enlazada que contiene todas las compuertas que se estén utilizando en pantalla
     * @return circuito, retorna la lista de compuertas que forman el circuito
     */
    public ListLinked<Compuerta> getCircuito(){
        return circuito;
    }

    /**
     * Método que retorna la lista enlazada en la cuál se están guardando todos lo colores utilizados para las líneas.
     * @return coloresUsados, retorna la lista con los colores ya usados
     */
    public static ListLinked<Color> getColores(){
        return coloresUsados;
    }
    
    /**
     * Método que crea la ventana que permite elegir la cantidad de entradas por compuerta
     * Código base tomado de https://youtu.be/3G8nTLujI5U.
     * @param ruta, ruta de la imagen
     * @param x, coordenada en x donde se debe colocar
     * @param y, coordenada en y donde se debe colocar
     * @throws IOException error
     */
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

    /**
     * Método que se encarga de actualizar las etiquetas de las diferentes entradas y salidas según se modifique la cantidad de
     * entradas que el usuario puede modificar y las salidas generales del circuito
     */
    public void actualizarEtiquetas(){
        int entradas =0;
        int salidas = 0;
            for(int i = 0; i < circuito.getSize(); i++){
                Compuerta compuerta = circuito.getValor(i);
                for(int j = 0; j < compuerta.getEntradas().getSize();j++){
                    Entrada entrada = compuerta.getEntrada(j);
                    if(entrada.getEndE().getIsConected() == false || entrada.getEndE().getValorEntradaConectado() == true){ //Es porque es una entrada de usuario
                        entrada.getEndE().getEtiqueta().setText("i<"+entradas+">");
                        entradas++;
                    }else{
                        entrada.getEndE().getEtiqueta().setText("");
                    }
                }
                if(compuerta.getEnd().getIsConected() == false){
                    compuerta.getEnd().getEtiqueta().setText("o<"+salidas+">");
                    salidas++;
                }else{
                    compuerta.getEnd().getEtiqueta().setText("");
                }
            }
    }
    
    /**
     * Método que revisa que todas las entradas tengan un valor para que sea posible calcular las
     * respectivas salidas del circuito.
     */
    public void validarEntradas(){ 
        ListLinked<Compuerta> circuito = this.circuito;
        
        for(int i = 0; i < circuito.getSize(); i++){
            Compuerta compuerta = circuito.getValor(i);
            for (int j = 0; j < compuerta.getEntradas().getSize(); j++){
                if (((Entrada)compuerta.getEntradas().getValor(j)).getValor() == null){
                    return;
                }
                
            }
        }
        emularCircuito();
    }
    private int entradasEvaluadas = 0;
    
    /**
     * Método que se encarga de emular el funcionamiento del circuito
     * y retorna una lista enlazada de strings en la que se almacenan las salidas del
     * circuito ya emulado.
     * @return salidas, retorna la lista que contiene la salidas que se deben mostrar al usuario
     */
    public String emularCircuito(){
        ListLinked<Valores> salidas = new ListLinked<>();
        ListLinked<Compuerta> circuito = this.circuito;
        if(circuito.getSize()>0){
            for(int i = 0; i < circuito.getSize(); i++){

                Compuerta compuerta = circuito.getValor(i);

                if (compuerta.revisarEntradas()== true && compuerta.getSalida() == Valores.Default){
                    compuerta.operarSalida();
                    entradasEvaluadas +=1;
                    if(compuerta.getEnd().conectada()== true){
                        ListLinked<CirculoEntrada> entradasConectadas = compuerta.getEnd().getEntradasConectadas();

                        for(int e = 0; e < entradasConectadas.getSize(); e++){
                            entradasConectadas.getValor(e).setValor(compuerta.getEnd().getValor());
                            compuerta.getEnd().setValor(Valores.Default);
                        }

                    }else{
                        salidas.añadirFinal(compuerta.getEnd().getValor());
                        compuerta.getEnd().setValor(Valores.Default);
                    }
                }
            }
            if(entradasEvaluadas < circuito.getSize()){
                return emularCircuito();
            }else{
                return mostrarSalidas(salidas);
            }
        }
        return null;
    }
    /**
     * Método que muestra las salidas finales calculadas en el circuito actual y las muestra en una nueva ventana.
     * @param salidas, recibe las salidas que debe mostrar
     * @return null
     */
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
        stage.show();
        return null;
    }
    
    /**
     * Método llamado al tocar el botón de Tabla de Verdad en la GUI.
     * Calcula los posibles resultados que puede tener cada circuito con todas sus posibles
     * entradas.
     * Referencia para la implementación de TableColumn y TableView https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html
     * https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
     */
    public void tablaDeVerdad(){
        Scene escena = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Tabla de Verdad");
        TableView<PosibleCircuito> tablaDeVerdad = new TableView<>();
        ListLinked<Entrada> entradasLista = new ListLinked<>();
        ListLinked<Entrada> salidasLista = new ListLinked<>();
        tablaDeVerdad.setEditable(true);
        int filas = 1;
        int entradas = 0;
        int salidas = 0;
        ListLinked<TableColumn> columnas = new ListLinked<>();
        ListLinked<PosibleCircuito> valoresTabla = new ListLinked<>();
        ListLinked<Compuerta> circuito = this.circuito;
        for(int i = 0; i< circuito.getSize(); i++){
            int q = i;
            Compuerta compuerta = circuito.getValor(i);
            for (int j = 0; j< compuerta.getEntradas().getSize(); j++){
                int p = j;
                if(compuerta.getEntrada(j).getEndE().getIsConected() == false || compuerta.getEntrada(j).getEndE().getValorEntradaConectado() == true){
                    entradasLista.añadirFinal(compuerta.getEntrada(j));
                    entradas +=1;
                    filas *=2;
                }
            }
            if(compuerta.getEnd().getIsConected() == false){
                salidas+=1;
            }
        }
        for(int j= 0; j < filas; j++){
                PosibleCircuito posibleCircuito = new PosibleCircuito(entradas,filas,entradasLista);
                valoresTabla.añadirFinal(posibleCircuito);
            }
        for(int e = 0; e < entradas; e++){
            int a= e;
            TableColumn<PosibleCircuito,String> columna = new TableColumn<>("i<"+e+">");
            columna.setCellValueFactory(E-> new SimpleStringProperty(E.getValue().getValores(a)));
            columnas.añadirFinal(columna);
        }
        for(int p = 0; p < salidas; p++){
            int r = p;
            int q =p+entradas;
            TableColumn<PosibleCircuito,String> columna = new TableColumn<>("o<"+r+">");
            columna.setCellValueFactory(E-> new SimpleStringProperty(E.getValue().getValores(q)));
            columnas.añadirFinal(columna);
        }
        for (int o = 0; o <columnas.getSize(); o++){
                tablaDeVerdad.getColumns().add(columnas.getValor(o));
            }
        for(int u = 0; u < valoresTabla.getSize(); u++){
            tablaDeVerdad.getItems().add(valoresTabla.getValor(u));
        }
        VBox elementos = new VBox();
        elementos.getChildren().addAll(tablaDeVerdad);
        ((Group) escena.getRoot()).getChildren().add(elementos);
        stage.setScene(escena);
        stage.show();
    }
    
    /**
     * Método que se ejecuta con el botón encapsular, almacena en la paleta la nueva compueta, así como los diferentes componentes
     * asociados a cada compuerta.
     */
    public void encapsular(){
        
        ListLinked<Compuerta> circuito = this.circuito;
        ListLinked<Entrada> entradas = new ListLinked<>();
        ListLinked<CirculoSalida> salidas = new ListLinked<>();
        if(circuito.getSize()>= 1){
            
            for(int i = 0; i< circuito.getSize(); i++){
                Compuerta compuerta = circuito.getValor(i);
                for (int j = 0; j< compuerta.getEntradas().getSize(); j++){
                    CirculoEntrada entrada = compuerta.getEntrada(j).getEndE();
                    if((entrada.getValorEntradaConectado() == true) || entrada.getIsConected() == false){
                        entradas.añadirFinal(compuerta.getEntrada(j));
                    }
                }
                if(compuerta.getEnd().getIsConected() == false){
                    salidas.añadirFinal(compuerta.getEnd());
                }
            }
            Rectangle imagenUsuario = new Rectangle(10,25);
            imagenUsuario.setFill(colorCompuerta());
            CircuitoUsuario nuevoCircuito = new CircuitoUsuario(imagenUsuario,circuito,entradas,salidas);
            nuevoCircuito.getImagenPaleta().setUserData(nuevoCircuito);
            paleta.getChildren().add(nuevoCircuito.getImagenPaleta());
            int tamañoCircuito = circuito.getSize();
            while(circuito.getSize() >0){
                Main.getControlador().getCircuito().eliminarInicio();
            }
            Main.getControlador().getAnchor().getChildren().clear();
       }
    }
    /**
     * Método que calcula un nuevo color para cada línea, y verifica que este color no esté ya asignado a ninguna 
     * otra línea del circuito.
     * Código base tomado de https://www.quora.com/How-can-I-randomize-colors-in-Java
     * @return Color, color a asignar
     */
    public Color colorCompuerta(){
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        
        Color color =Color.color(red, green, blue);
        for(int i = 0; i < coloresUsados.getSize(); i++){
            if (coloresUsados.buscarElemento(i).equals(color)){
                return colorCompuerta();
            }
        }
        coloresUsados.añadirFinal(color);
        return color;
    }
}
