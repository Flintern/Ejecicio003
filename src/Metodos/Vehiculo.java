/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Vehiculo {

    String matricula;
    String marca;
    String modelo;
    double precio;
    boolean estado;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Vehiculo(String matricula, String marca, String modelo, double precio, boolean estado) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.estado = estado;
    }

    public void getMostrarVehiculo() {
        String info = "Los datos de el vehiculo son: \n";
        info += "Matricula: " + matricula + "\n";
        info += "Marca: " + marca + "\n";
        info += "Modelo: " + modelo + "\n";
        info += "Precio: " + precio + "\n";
        if (estado == true) {
            info += "Estado: Vendido\n";
        } else {
            info += "Estado: Disponible para venta\n";
        }
        JOptionPane.showMessageDialog(null, info);

    }
}
