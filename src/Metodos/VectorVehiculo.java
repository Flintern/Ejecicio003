/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class VectorVehiculo {

    Vehiculo misVehiculos[];
    int tam;

    VectorVehiculo(JTextField CajaTam) {
        int i;
        try {

            tam = Integer.parseInt(CajaTam.getText());
            //Declarar el vector
            misVehiculos = new Vehiculo[tam];
            //Instanciar el vector por cada posición de forma
            //vacía
            for (i = 0; i < misVehiculos.length; i++) {
                misVehiculos[i] = new Vehiculo("", "", "", 0, false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e + ".  El vector no fue creado! Intente nuevamente.");
            CajaTam.setText("");
            CajaTam.requestFocus();
            misVehiculos = null;
        }

    }

    //Implementación del método que busca lineal o secuencialmente sobre los datos del vector.
    public int busquedaLineal(int dato) {
        int i; //Variable para controlar el ciclo while.
        int posicion; //Variable que devuelve la posición en la que se encuentra el elemento buscado.
    //Se Asigna el valor de -1 a la variable posición para devolver este valor en el supuesto caso de
    //que no se encuentre el valor buscado dentro del vector.
        posicion = -1;
        i = 0;
    //Mientras que no se llegue al final del vector y no se haya encontrado el dato buscado en el vector.
        while ((i <= getNumElementos() - 1) && (posicion == -1)) {
    //Si el contenido del vector en la posición i-esima es igual al dato que se está buscando, entonces
    //el dato si está en el vector y se devuelve la posición en donde se encuentra el dato dentro del
    //vector. Si no, es porque el dato no se encuentra en esa posición, y se incrementa la posición (i)
    //para una nueva comparación.
            if (getVectorDatos(i) == dato) {
                posicion = i;
            } else {
                i = i + 1;
            }
        }
        return posicion;
    }

}
