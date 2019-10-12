/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import vista.Formulario;

/**
 *
 * @author franc
 */
public class ControladorPersona implements ActionListener {

    private Formulario formulario = new Formulario();
    private List<Persona> personas = new ArrayList<>();
    private Persona persona = new Persona();
    private String user;
    private String password;
    private String puerto;
    private String catalogo;
    private String host;

    public ControladorPersona() {
        this.listener(this);
        this.formulario.setVisible(true);
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public String getHost() {
        return host;
    }

    private void listener(ActionListener lst) {
        this.formulario.getBtnConsultar().addActionListener(lst);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.formulario.getBtnConsultar())) {
            String consulta = this.formulario.getConsultar().getText();
            
            if (this.formulario.getConsultar().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha ingresado una consulta");

            } else if (consulta.contains("INSERT")) {
                this.persona.insertarPersona(consulta, this.setDatos());
                this.personas = this.persona.traerTodas(this.setDatos());
                this.formulario.getTablaResultados().setModel(this.verPersonas());

            } else if (consulta.contains("SELECT")) {
                this.personas = this.persona.buscarPersona(consulta, this.setDatos());
                this.formulario.getTablaResultados().setModel(this.verPersonas());

            } else if (consulta.contains("UPDATE")) {
                this.persona.actualizarPersona(consulta, this.setDatos());
                this.personas = this.persona.traerTodas(this.setDatos());
                this.formulario.getTablaResultados().setModel(this.verPersonas());

            } else if (consulta.contains("DELETE")) {
                this.persona.eliminarPersona(consulta, this.setDatos());
                this.personas = this.persona.traerTodas(this.setDatos());
                this.formulario.getTablaResultados().setModel(this.verPersonas());

            } else {
                JOptionPane.showMessageDialog(null, "La sintaxis de la consulta es incorrecta. Reintente.");
            }
        }
    }

    public String consulta() {
        return this.formulario.getConsultar().getText();
    }

    public String[] setDatos() {
        this.host = this.formulario.getHost().getText();
        this.catalogo = this.formulario.getdB().getText();
        this.user = this.formulario.getUsuario().getText();
        this.password = new String(this.formulario.getClave().getPassword());
        this.puerto = this.formulario.getPuerto().getText();

        String[] datosConn = {this.host, this.puerto, this.catalogo, this.user, this.password};

        return datosConn;
    }

    public DefaultTableModel verPersonas() {

        String x[][] = {};
        String nombresColumnas[] = {"Nombre", "Apellido", "Edad", "Fecha de Alta"};
        DefaultTableModel tablaPersonas = new DefaultTableModel(x, nombresColumnas);
        if (this.personas != null) {
            int i = 0;
            for (Persona persona1 : this.personas) {
                tablaPersonas.insertRow(i, new Object[]{});
                tablaPersonas.setValueAt(persona1.getNombre(), i, 0);
                tablaPersonas.setValueAt(persona1.getApellido(), i, 1);
                tablaPersonas.setValueAt(persona1.getEdad(), i, 2);
                tablaPersonas.setValueAt(persona1.getFechaAlta().toString(), i, 3);
                i++;
            }
        }
        this.personas.clear();
        return tablaPersonas;
    }

}
