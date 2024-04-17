package proyecto_progra1v2;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Proyecto_Progra1V2 {

    public static void main(String[] args) throws IOException {

        Asociacion asociacion = new Asociacion();

        int opcion;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                     1. Crear listado de Asociados.
                     2. Modificar datos de Asociados.
                     3. Hacer depósito a alguna cuenta.
                     4. Solicitar préstamo.
                     5. Eliminar Asociado.
                     6. Ver lista de Asociados.
                                                                  
                     7. Agregar monto anual a la Asociación. 
                     8. Consultar fondos de ahorro.
                                                                   
                                                                  
                     0. Salir."""));
            switch (opcion) {
                case 1:
                    asociacion.crearLista();
                    break;

                case 2:
                    asociacion.modificarAsociado();
                    break;

                case 3:
                    asociacion.depositarAhorro();
                    break;
                case 4:
                    asociacion.solicitarPrestamo();
                    break;
                case 5:
                    asociacion.eliminarAsociado();
                    break;
                case 6:
                    asociacion.verListaAsociados();
                    break;
                case 7:
                    asociacion.agregarMontoAnual();
                    break;
                case 8:
                    asociacion.consultarFondosAhorro();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias por ser parte de la Asociación Servicios de Programación");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Digite una opción válida");
            }
        } while (opcion != 0);

    }

}
