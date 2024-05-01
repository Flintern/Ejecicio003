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
    
    Vehiculo miVehiculo[];
    int tam;
    
    VectorVehiculo(JTextField CajaTam){
           int i;
        try{
            
            tam = Integer.parseInt(CajaTam.getText());
            //Declarar el vector
            miVehiculo = new Vehiculo[tam];
            //Instanciar el vector por cada posición de forma
            //vacía
            for( i=0; i<miVehiculo.length; i++ ){
                miVehiculo[i] = new Vehiculo("", "", "", 0,false);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                "Error: "+e+".  El vector no fue creado! Intente nuevamente.");
            CajaTam.setText("");
            CajaTam.requestFocus();
            miVehiculo = null;
        }
    }
    
    
    
}
