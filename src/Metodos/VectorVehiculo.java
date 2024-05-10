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

    public void setIntercambio(int posA, int posS) {
        Vehiculo temp;
        //Copiamos la información de la 
        //posicion anterior
        temp = new Vehiculo(
                misVehiculos[posA].matricula,
                misVehiculos[posA].marca,
                misVehiculos[posA].modelo,
                misVehiculos[posA].precio,
                misVehiculos[posA].estado
        );
        //Cambiamos la informacion del anterior
        //por la información del siguiente
        misVehiculos[posA].matricula = misVehiculos[posS].matricula;
        misVehiculos[posA].marca = misVehiculos[posS].marca;
        misVehiculos[posA].modelo = misVehiculos[posS].modelo;
        misVehiculos[posA].precio = misVehiculos[posS].precio;
        misVehiculos[posA].estado = misVehiculos[posS].estado;
        //Cambiamos la información del siguiente
        //por la informacion que tenía (temp) el anterior
        misVehiculos[posS].matricula = temp.matricula;
        misVehiculos[posS].marca = temp.marca;
        misVehiculos[posS].modelo = temp.modelo;
        misVehiculos[posS].precio = temp.precio;
        misVehiculos[posS].estado = temp.estado;
    }

    //ordenamiento por seleccion
    public void ordenarVehiculos() {
        for (int i = 0; i < misVehiculos.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < misVehiculos.length; j++) {
                if (misVehiculos[j].matricula.compareTo(misVehiculos[indiceMenor].matricula) < 0) {
                    indiceMenor = j;
                }
            }
            setIntercambio(indiceMenor, i);

        }
    }
// Implementación de búsqueda binaria por matrícula

    public int busquedaBinariaPorMatricula(String matriculaBuscada) {
        int izquierda = 0;
        int derecha = misVehiculos.length - 1;
        int centro;

        ordenarVehiculos(); // Asegurarse de que los vehículos están ordenados por matrícula.

        while (izquierda <= derecha) {
            centro = (izquierda + derecha) / 2;

            // Compara la matrícula buscada con la matrícula en la posición centro.
            int resultadoComparacion = matriculaBuscada.compareTo(misVehiculos[centro].matricula);

            if (resultadoComparacion == 0) {
                // La matrícula fue encontrada en la posición centro.
                return centro;
            } else if (resultadoComparacion < 0) {
                // La matrícula buscada es menor que la del centro, buscar en la mitad izquierda.
                derecha = centro - 1;
            } else {
                // La matrícula buscada es mayor que la del centro, buscar en la mitad derecha.
                izquierda = centro + 1;
            }
        }

        // La matrícula no fue encontrada.
        return -1;
    }

    public int getBuscarMatricula(String matr) {
        int i;
        for (i = 0; i < misVehiculos.length; i++) {
            if (matr.equals(misVehiculos[i].matricula)) {
                return i;
            }
        }
        return -1;
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

    public void ordenamientoShellDescendente() {
        int n = misVehiculos.length;

        for (int salto = n / 2; salto > 0; salto /= 2) {
            for (int i = salto; i < n; i++) {
                Vehiculo temp = misVehiculos[i];
                int j;
                for (j = i; j >= salto && misVehiculos[j - salto].marca.compareTo(temp.marca) < 0; j -= salto) {
                    misVehiculos[j] = misVehiculos[j - salto];
                }
                misVehiculos[j] = temp;
            }
        }
    }

    public boolean llenarVector(JTextField matricula1, JTextField marca1, JTextField modelo1, JTextField precio1, JComboBox estado1, int pos) {
        int b;
        try {
            if ((pos < 0) || (pos >= tam)) {
                JOptionPane.showMessageDialog(null, "Posición invalida!");
                return false;
            } else {
                do {
                    b = getBuscarMatricula(matricula1.getText());
                    if (b != -1) {
                        JOptionPane.showMessageDialog(null, "La matricula ya existe.  Intente " + "nuevamente!");
                        matricula1.setText("");
                        matricula1.requestFocus();
                        return false;
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
            int pFila, int pVec) {

        miModelo.setValueAt(misVehiculos[pVec].matricula, pFila, 0);
        miModelo.setValueAt(misVehiculos[pVec].marca, pFila, 1);
        miModelo.setValueAt(misVehiculos[pVec].modelo, pFila, 2);
        miModelo.setValueAt(misVehiculos[pVec].precio, pFila, 3);
        miModelo.setValueAt(misVehiculos[pVec].estado, pFila, 4);

    }

    public void llenarJTable(JTable tab) {
        int posTabla = 0; //Este índice recorre los elementos de la fila Tabla
        int posVec = 0;  //Este índice para ubicar posición del vector
        DefaultTableModel miModelo = new DefaultTableModel();

        //Creamos los nombres de las columnas de la tabla
        miModelo.addColumn("Matricula");
        miModelo.addColumn("Marca");
        miModelo.addColumn("Modelo");
        miModelo.addColumn("Precio");
        miModelo.addColumn("Estado");

        //Recorremos el vector para tomar sus datos
        //y pasarlos al JTable
        while (posVec < misVehiculos.length) {
            miModelo.addRow(new Object[]{"", "", "", "", ""});
            setRegistrarFilaJTable(miModelo, posTabla, posVec);
            posVec++;
            posTabla++;
        }
        tab.setModel(miModelo);
    }

}
