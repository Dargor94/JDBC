/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Persona;

/**
 *
 * @author franc
 */
public class BaseDeDatos {

    private Connection conexion = null;
    private PreparedStatement ps = null;
    private List<Persona> personas = new ArrayList<>();
    private Persona persona;

    public Connection estableceConexion(String[] datosConn) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String urlConexion = "jdbc:mysql://" + datosConn[0] + ":" + datosConn[1] + "/" + datosConn[2];
            this.conexion = DriverManager.getConnection(urlConexion, datosConn[3], datosConn[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.conexion;
    }

    public void cierraConexion() {
        try {
            this.conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarPersona(String consulta, String[] datosConn) {
        try {
            this.estableceConexion(datosConn);
            this.ps = this.conexion.prepareStatement(consulta);
            this.ps.execute();
            JOptionPane.showMessageDialog(null, "Inserción exitosa");

        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cierraConexion();
        }
    }

    public void actualizarPersona(String consulta, String[] datosConn) {
        try {
            this.estableceConexion(datosConn);
            PreparedStatement ps = this.conexion.prepareStatement(consulta);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualización existosa");

        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Sintaxis incorrecta, reintente");
        } finally {
            this.cierraConexion();
        }

    }

    public void eliminarPersona(String consulta, String[] datosConn) {
        try {
            this.estableceConexion(datosConn);
            Statement st = this.conexion.createStatement();
            int delete = st.executeUpdate(consulta);

            if (delete == 1) {
                JOptionPane.showMessageDialog(null, "Persona borrada");
            } else {
                JOptionPane.showMessageDialog(null, "Persona no borrada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sintaxis incorrecta, reintente");
        } finally {
            this.cierraConexion();
        }
    }

    public List<Persona> buscarPersona(String consulta, String[] datosConn) {
        ResultSet rs = null;
        try {
            this.estableceConexion(datosConn);
            Statement s = conexion.createStatement();
            rs = s.executeQuery(consulta);
            while (rs.next()) {
                this.persona = new Persona();
                this.persona.setNombre(rs.getString("nombre"));
                this.persona.setApellido(rs.getString("apellido"));
                this.persona.setEdad(rs.getInt("edad"));
                this.persona.setFechaAlta((rs.getDate("fechaAlta").toLocalDate()));
                this.personas.add(this.persona);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sintaxis incorrecta, reintente");
        } finally {
            this.cierraConexion();
        }
        return this.personas;
    }

    public List<Persona> traerTodas(String[] datosConn) {
        ResultSet rs = null;
        try {
            this.estableceConexion(datosConn);
            Statement s = conexion.createStatement();
            rs = s.executeQuery("SELECT * FROM persona");
            while (rs.next()) {
                this.persona = new Persona();
                this.persona.setNombre(rs.getString("nombre"));
                this.persona.setApellido(rs.getString("apellido"));
                this.persona.setEdad(rs.getInt("edad"));
                this.persona.setFechaAlta((rs.getDate("fechaAlta").toLocalDate()));
                personas.add(persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cierraConexion();
        }
        return this.personas;
    }

}
