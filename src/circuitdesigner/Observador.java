/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.util.Observable;
import java.util.Observer;
import listlinked.ListLinked;
import operadores.Valores;

/**
 *
 * @author allva
 */
public class Observador implements Observer{

    @Override
    public void update(Observable o, Object o1) {
        System.out.println(o1);
        //if es Anchor
        //actualizarEntradas(circulo con salida cambiada);
        //else
        //Revisar entradas();

        
    }
    public void conexión(Circulo salida){
        ListLinked<Imagen> circuito = Facade.getCircuito();
          
          for (int c = 0; c < circuito.getSize(); c++){
              Imagen imagen = circuito.getValor(c);
              
              for(int i = 0; i < imagen.getEntradas().getSize(); i++){
                  
                  Entrada entrada = imagen.getEntrada(i);

                      if(entrada.getEndE().getCenterX() == salida.getCenterX() && entrada.getEndE().getCenterY() == salida.getCenterY()){
                         entrada.setValor(salida.getValor());

                         imagen.getEntradas().hasChanged();
                         imagen.getEntradas().notifyObservers(entrada);
                         System.out.println(entrada.getValor());
                         
                 }
              }
              
         }
    }
    
    public void revisarEntradas(ListLinked<Entrada> entradas){
        for(int i=0; i < entradas.getSize(); i++){
            if(entradas.buscarElemento(i).getValor() != null && entradas.buscarElemento(i).getValor() != Valores.Default){
                continue;
            }
            else{
                return;
            }
        }
        //OperarSalida();
    }
    
    
}
