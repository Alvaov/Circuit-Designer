/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import operadores.Valores;

/**
 * Clase Círculo, extiende de Círculo y es la clase con la que se crean los círculos que representan tanto
 * entradas como salidas en las compuertas.
 * @author allva
 */
class Circulo extends Circle {
    protected Label etiqueta;
    protected Valores valor;
    protected boolean isConected;
    protected Compuerta compuertaPadre;
    
    /**
     * Constructor de la clase círculo, donde asigna vamores a su etiqueta, 
     * valor y variable booleana isConected
     * @param valor, valor con el que inicia el círculo
     */
    public Circulo(Valores valor) {
      super(3);
      this.etiqueta = new Label();
      this.valor = valor;
      this.isConected = false;
    }
    /**
     * Método que permite retornar la etiqueta respectiva del círculo
     * @return etiqueta, la etiqueta que identifica la salida
     */
    public Label getEtiqueta(){
        return etiqueta;
    }
    /**
     * Método que retorna el valor del círculo
     * @return valor, el valor actual de la salida
     */
    public Valores getValor(){
        return valor;
    }
    /**
     * Método que permite cambiar el valor actual del círculo
     * @param valor, valor que se va a asignar a la salida
     */
    public void setValor(Valores valor){
        this.valor = valor;
    }
    /**
     * Método desde el cuál se puede modificar el atributo isConected
     * del círculo
     * @param valor, valor que indica si está conectada o no
     */
    public void setIsConected(boolean valor){
        isConected = valor;
    }
    /**
     * Método que permite obtener el valor de isConected del círculo
     * @return isConected, valor que indica si está o no conectada
     */
    public boolean getIsConected(){
        return isConected;
    }
    /**
     * Método que permite modificar la compuerta a la que pertenece el círculo
     * @param compuertaPadre, compuerta a la que pertenece
     */
    public void setCompuertaPadre(Compuerta compuertaPadre){
        this.compuertaPadre = compuertaPadre;
    }
    /**
     * Método que permite obtener la compuerta a la cual pertenece el círculo
     * @return compuertaPadre, compuerta a la que pertenece
     */
    public Compuerta getCompuertaPadre(){
        return compuertaPadre;
    }
}
