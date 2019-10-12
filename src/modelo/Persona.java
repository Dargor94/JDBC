/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.BaseDeDatos;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author franc
 */
public class Persona {

    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaAlta;
    private BaseDeDatos bd = new BaseDeDatos();

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Persona> traerTodas(String[] datosConn) {
        return this.bd.traerTodas(datosConn);
    }

    public List<Persona> buscarPersona(String consulta, String[] datosConn) {
        return this.bd.buscarPersona(consulta, datosConn);
    }

    public void actualizarPersona(String consulta, String[] datosConn) {
        this.bd.actualizarPersona(consulta, datosConn);
    }

    public void eliminarPersona(String consulta, String[] datosConn) {
        this.bd.eliminarPersona(consulta, datosConn);
    }

    public void insertarPersona(String consulta, String[] datosConn) {
        this.bd.insertarPersona(consulta, datosConn);
    }

}
