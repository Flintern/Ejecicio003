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

    private Vehiculo misVehiculos[];
    private int tam;

    public Vehiculo[] getMisVehiculos() {
        return misVehiculos;
    }

    public void setMisVehiculos(Vehiculo[] misVehiculos) {
        this.misVehiculos = misVehiculos;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

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

    public int busquedaBinariaPorMatricula() {
        String matriculaBuscada = (JOptionPane.showInputDialog(
                "Entre la matricula a buscar: "));
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
            if (matr.equals(misVehiculos[i].getMatricula())) {
                return i;
            }
        }
        return -1;
    }

    public int[] busquedaLinealMarca() {
        String marc = (JOptionPane.showInputDialog(
                "Entre la marca a buscar: "));

        int contador = 0;
        int posiciones[] = new int[misVehiculos.length];

        for (int i = 0; i < misVehiculos.length; i++) {
            if (marc.equals(misVehiculos[i].marca)) {
                posiciones[contador] = i;
                contador++;
            }
        }
        int[] posicionesEncontradas = new int[contador];
        for (int r = 0; r < contador; r++) {
            posicionesEncontradas[r] = posiciones[r];
        }
        return posicionesEncontradas;
    }

    public void getMostrarMarca() {
        int pos[] = busquedaLinealMarca();

        if (pos.length == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay un vehiculo registrado con esa marca.");
        } else {
            for (int posiciones : pos) {
                misVehiculos[posiciones].getMostrarVehiculo();
            }
        }
    }

    public void getMostrarMatricula() {
        int pos = busquedaBinariaPorMatricula();

        if (pos == -1) {
            JOptionPane.showMessageDialog(null,
                    "No hay un vehiculo registrado con esa matricula.");
        } else {
            misVehiculos[pos].getMostrarVehiculo();
        }
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
    
public void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            double pivote = misVehiculos[fin].precio;
            int i = inicio - 1;
            for (int j = inicio; j < fin; j++) {
                if (misVehiculos[j].precio < pivote) {
                    i++;
                    swap(i, j);
                }
            }
            i++;
            swap(i, fin);
            quickSort(inicio, i - 1);
            quickSort(i + 1, fin);
        }
    }
    
    //Método de intercambio para QuickSort
    public void swap(int i, int j) {
        Vehiculo temp = misVehiculos[i];
        misVehiculos[i] = misVehiculos[j];
        misVehiculos[j] = temp;
    }

}
