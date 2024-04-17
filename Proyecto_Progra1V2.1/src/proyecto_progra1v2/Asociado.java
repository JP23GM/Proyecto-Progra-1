package proyecto_progra1v2;

import javax.swing.JOptionPane;

public class Asociado {

    private String nombre;
    private String correo;
    private String telefono;
    private String id;
    private double ahorro;
    private double dividendos = 0;

    // CONSTRUCTORES
    public Asociado() {
        nombre = "";
        correo = "";
        telefono = "";
        ahorro = 0;

    }

    public Asociado(String nombre, String correo, String telefono) {
        this.nombre = nombre.toUpperCase(); // ESTO LO HAGO PARA PODER BUSCAR MÁS FACILMENTE EN EL ARREGLO USANDO NOMBRE
        this.correo = correo;
        this.telefono = telefono;
        this.ahorro = ahorro;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAhorro() {
        return this.ahorro;

    }

    public void setAhorro(double ahorro) {
        if (ahorro > 0) {
            this.ahorro = this.ahorro + ahorro + dividendos;
            JOptionPane.showMessageDialog(null, "Monto ingresado al sistema correctamente");
        }
    }

    public void getPrestamo(double prestamo) {
        if (prestamo != 0 && prestamo <= ahorro) {// DICE EL PROYECTO QUE EL PRÉSTAMO NO PUEDE SER MAYOR A LO QUE SE TIENE AHORRADO
            ahorro -= prestamo; // OTRA FORMA DE VERLO ahorro = ahorro - prestamo;
            JOptionPane.showMessageDialog(null, "Ha solicitado un préstamo de: " + prestamo + "\n\n Su nuevo saldo es de: " + ahorro);
        } else {
            JOptionPane.showMessageDialog(null, "El monto digitado excede el saldo de su cuenta");
        }

    }

    public double getDividendos() {
        return dividendos;
    }

    public void setDividendos(double dividendos) {
        
    }

}
