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
 * @see clase Círculo, extiende de Círculo y es la clase con la que se crean los círculos que representan tanto
 * entradas como salidas en las compuertas.
 * @author allva
 */
class Circulo extends Circle {
    protected Label etiqueta;
    protected Valores valor;
    protected boolean isConected;
    protected Compuerta compuertaPadre;
    
    /**
     *@see constructor de la clase círculo, donde asigna vamores a su etiqueta, 
     * valor y variable booleana isConected
     * @param etiqueta
     * @param valor
     */
    public Circulo(Valores valor) {
      super(3);
      this.etiqueta = new Label();
      this.valor = valor;
      this.isConected = false;
    }
    /**
     * @see Método que permite retornar la etiqueta respectiva del círculo
     * @return etiqueta
     */
    public Label getEtiqueta(){
        return etiqueta;
    }
    /**
     *@see Método que retorna el valor del círculo
     * @return valor
     */
    public Valores getValor(){
        return valor;
    }
    /**
     * @see método que permite cambiar el valor actual del círculo
     * @param Valores valor
     */
    public void setValor(Valores valor){
        this.valor = valor;
        System.out.println("Setea valor circulo");
    }
    /**
     *@see Método desde el cuál se puede modificar el atributo isConected
     * del círculo
     * @param boolean valor
     */
    public void setIsConected(boolean valor){
        isConected = valor;
    }
    /**
     * @see Método que permite obtener el valor de isConected del círculo
     * @return isConected
     */
    public boolean getIsConected(){
        return isConected;
    }
    /**
     * @see método que permite modificar la compuerta a la que pertenece el círculo
     * @param Compuerta compuerta padre
     */
    public void setCompuertaPadre(Compuerta compuertaPadre){
        this.compuertaPadre = compuertaPadre;
    }
    /**
     * @see método que permite obtener la compuerta a la cual pertenece el círculo
     * @return compuertaPadre
     */
    public Compuerta getCompuertaPadre(){
        return compuertaPadre;
    }
}
