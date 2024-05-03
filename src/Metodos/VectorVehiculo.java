/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import javax.swing.JComboBox;
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

    VectorVehiculo(JTextField tamVector) {
        int i;
        try {

            tam = Integer.parseInt(tamVector.getText());
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
            tamVector.setText("");
            tamVector.requestFocus();
            misVehiculos = null;
        }

    }

    //Implementación del método que busca lineal o secuencialmente sobre los datos del vector.
    public int busquedaLinealMarca(String marc) {
        int i; //Variable para controlar el ciclo while.
        int posicion; //Variable que devuelve la posición en la que se encuentra el elemento buscado.
        //Se Asigna el valor de -1 a la variable posición para devolver este valor en el supuesto caso de
        //que no se encuentre el valor buscado dentro del vector.
        posicion = -1;
        i = 0;
        //Mientras que no se llegue al final del vector y no se haya encontrado el dato buscado en el vector.
        while ((i <= misVehiculos.length - 1) && (posicion == -1)) {
            //Si el contenido del vector en la posición i-esima es igual al dato que se está buscando, entonces
            //el dato si está en el vector y se devuelve la posición en donde se encuentra el dato dentro del
            //vector. Si no, es porque el dato no se encuentra en esa posición, y se incrementa la posición (i)
            //para una nueva comparación.
            if (marc.equals(misVehiculos[i].marca)) {
                posicion = i;
            } else {
                i = i + 1;
            }
        }
        return posicion;
    }

    public boolean llenarVector(JTextField matricula1, JTextField marca1, JTextField modelo1, JTextField precio1, JComboBox estado1, int pos) {
        int b;
        try {
            if ((pos < 0) || (pos >= tam)) {
                JOptionPane.showMessageDialog(null, "Posición invalida!");
            } else {
                do {
                    b = EVELINHASELMETODOBINADRIO(matricula1.getText());
                    if (b != -1) {
                        JOptionPane.showMessageDialog(null, "La matricula ya existe.  Intente " + "nuevamente!");
                        matricula1.setText("");
                        matricula1.requestFocus();
                    }
                } while (b != -1);
                boolean est;
                if (estado1.getSelectedIndex() == 0) {
                    est = true;
                } else {
                    est = false;
                }
                misVehiculos[pos].matricula = matricula1.getText();
                misVehiculos[pos].marca = marca1.getText();
                misVehiculos[pos].modelo = modelo1.getText();
                misVehiculos[pos].precio = Double.parseDouble(precio1.getText());
                misVehiculos[pos].estado = est;
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e + ". No fue registrado.");
            return false;
        }

    }
    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
        int pFila, int pVec){
        
        miModelo.setValueAt(misVehiculos[pVec].matricula, pFila, 0);
        miModelo.setValueAt(misVehiculos[pVec].marca, pFila, 1);
        miModelo.setValueAt(misVehiculos[pVec].modelo, pFila, 2);
        miModelo.setValueAt(misVehiculos[pVec].precio, pFila, 3);
        miModelo.setValueAt(misVehiculos[pVec].estado, pFila, 3);
        
    }    

}
