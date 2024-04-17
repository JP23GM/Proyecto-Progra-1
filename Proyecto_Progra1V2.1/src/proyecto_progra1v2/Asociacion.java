package proyecto_progra1v2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Asociacion {

    private double fondoAhorro;
    private double montoAnual;
    private int tamano;
    private Asociado[] lista;
    File archivo = new File("Asociados.txt");

    // CONSTRUCTOR
    public Asociacion() {
        fondoAhorro = 0;
        montoAnual = 0;
    }

    // ENCAPSULADORES
    public double getFondoAhorro() {
        return fondoAhorro;
    }

    public void setFondoAhorro(double fondoAhorro) {
        this.fondoAhorro = fondoAhorro;
    }

    public double getMontoAnual() {
        return montoAnual;
    }

    public void setMontoAnual(double montoAnual) {
        if (this.montoAnual == 0) {   //ACÁ PREGUNTA SI NO HA RECIBIDO MONTO ANUAL, HAGA ESTO, YA QUE SI ES > 0 ENTONCES YA NO PUEDE RECIBIR EL MONTO ANUAL
            if (montoAnual > 0) {     //ACÁ PREGUNTO SI ES MAYOR A 0 PORQUE NO PUEDO RECIBIR 0 NI MONTOS NEGATIVOS, NO TIENE SENTIDO
                this.montoAnual = montoAnual;
                JOptionPane.showMessageDialog(null, "Monto anual agregado a la Asociación correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "El monto que digitó no es válido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El monto anual ya fue recibido por la Asociación"); //SI EL MONTO YA FUE RECIBIDO, NO HACE NADA
        }
    }

    public void distribuirDividendos(Asociado asociado) {
        asociado.setDividendos((montoAnual / fondoAhorro) * asociado.getAhorro());

    }

    public void crearLista() throws IOException {
        tamano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño de asociados que va a tener la lista"));
        lista = new Asociado[tamano];
        for (int i = 0; i < tamano; i++) {
            lista[i] = new Asociado();
            lista[i].setNombre(JOptionPane.showInputDialog("Ingrese el nombre de asociado #" + (i + 1)));
            lista[i].setCorreo(JOptionPane.showInputDialog("Ingrese el correo"));
            lista[i].setTelefono(JOptionPane.showInputDialog("Ingrese el telefono"));
            lista[i].setId(JOptionPane.showInputDialog("Ingrese la identificación"));
            
            
            FileWriter escritor = new FileWriter(archivo,true);// SE PONE TRUE PARA QUE SIGA AÑADIENDO A LA LISTA HACIA ABAJO
            PrintWriter impresor = new PrintWriter(escritor);
            
            impresor.println(lista[i].getNombre());
            impresor.println(lista[i].getCorreo());
            impresor.println(lista[i].getTelefono());
            impresor.println(lista[i].getId());
            
            impresor.close();
        }
        
        /*  esto es copiado del PDF del profe
        try {
            FileWriter escritor = new FileWriter(new File("Asociados.txt"));
            PrintWriter impresor = new PrintWriter(escritor);
            
            for (int i = 0; i < tamano; i++) {
                impresor.println(lista[i]);
            }
            impresor.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Se ha presentado un error");
        }*/
    }

    public void modificarAsociado() {
        int opcion;
        String id = JOptionPane.showInputDialog("Ingrese la identificación del asociado a modificar");
        for (int i = 0; i < tamano; i++) {
            if (lista[i] != null && lista[i].getId().equals(id)) {//PREGUNTO SI LA LISTA TIENE UN OBJETO Y SI EL ID ES IGUAL AL QUE DIGITÓ EL USUARIO
                do {
                    // ESTA VARA ES UN SUBMENU PARA QUE QUEDE MÁS PICHUDO PREGUNTARLE AL USUARIO QUÉ DESEA MODIFICAR
                    opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                          
                        Ingrese la opción que contenga el dato a modificar                                                
                                                                      
                     1. Modificar nombre.
                     2. Modificar correo.
                     3. Modificar teléfono.
                     4. Modificar identificación.
                                                                      
                                                                                 
                     0. Salir."""));

                    switch (opcion) {
                        case 1: {
                            String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                            lista[i].setNombre(nombre);
                        }
                        break;
                        case 2: {
                            String correo = JOptionPane.showInputDialog("Ingrese el correo");
                            lista[i].setCorreo(correo);
                        }
                        break;
                        case 3: {
                            String telefono = JOptionPane.showInputDialog("Ingrese el teléfono");
                            lista[i].setTelefono(telefono);
                        }
                        break;
                        case 4: {
                            String identificacion = JOptionPane.showInputDialog("Ingrese la identificación");
                            lista[i].setId(identificacion);
                        }
                        break;
                        default:
                            JOptionPane.showMessageDialog(null, "Digite una opción válida");
                    }

                } while (opcion != 0);
            } else {//ESTO ES NADA MÁS POR SI NO ENCUENTRA LA ID QUE EL USUARIO DIGITO
                //ENTONCES MUESTRA UN MENSAJILLO
                JOptionPane.showMessageDialog(null, "La identificación que digitó no corresponde a un asociado");
            }
        }

    }

    public void depositarAhorro() {// aquí hay un error, me muestra las identificaciones de todos los demás del arreglo son incorrectas
        String id = JOptionPane.showInputDialog("Ingrese la identificación del asociado a hacer el depósito");
        for (int i = 0; i < tamano; i++) {
            if (lista[i] != null && lista[i].getId().equals(id)) {//PREGUNTO SI LA LISTA TIENE UN OBJETO Y SI EL ID ES IGUAL AL QUE DIGITÓ EL USUARIO
                double ahorro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar"));
                lista[i].setAhorro(ahorro);//EL MÉTODO EN LA CLASE ASOCIADO HACE LA VERIFICACIÓN Y DESPLIEGA MENSAJE
                if (ahorro > 0) {
                    this.fondoAhorro += ahorro; //LA ASOCIACIÓN TAMBIÉN DEBE SUMAR EL MONTO QUE LE LLEGA AL ASOCIADO
                }
            } else {//ESTO ES NADA MÁS POR SI NO ENCUENTRA LA ID QUE EL USUARIO DIGITO
                //ENTONCES MUESTRA UN MENSAJILLO
                JOptionPane.showMessageDialog(null, "La identificación que digitó no corresponde a un asociado");
            }
        }

    }

    public void solicitarPrestamo() {
        String id = JOptionPane.showInputDialog("Ingrese la identificación del asociado que solicita el préstamo");
        for (int i = 0; i < tamano; i++) {
            if (lista[i] != null && lista[i].getId().equals(id)) {//PREGUNTO SI LA LISTA TIENE UN OBJETO Y SI EL ID ES IGUAL AL QUE DIGITÓ EL USUARIO
                double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a debitar"));
                lista[i].getPrestamo(monto);
                this.fondoAhorro -= monto; //SE LE DEBE RESTAR A LA ASOCIACIÓN SI ALGUIEN SACA PLATA
            } else {//ESTO ES NADA MÁS POR SI NO ENCUENTRA LA ID QUE EL USUARIO DIGITO
                //ENTONCES MUESTRA UN MENSAJILLO
                JOptionPane.showMessageDialog(null, "La identificación que digitó no corresponde a un asociado");
            }
        }

    }

    public void eliminarAsociado() {
        String id = JOptionPane.showInputDialog("Ingrese la identificación del asociado que desea eliminar");
        for (int i = 0; i < tamano; i++) {
            if (lista[i] != null && lista[i].getId().equals(id)) {//PREGUNTO SI LA LISTA TIENE UN OBJETO Y SI EL ID ES IGUAL AL QUE DIGITÓ EL USUARIO
                lista[i] = null;
                JOptionPane.showMessageDialog(null, "Asociado eliminado del sistema correctamente");
            } else {//ESTO ES NADA MÁS POR SI NO ENCUENTRA LA ID QUE EL USUARIO DIGITO
                //ENTONCES MUESTRA UN MENSAJILLO
                JOptionPane.showMessageDialog(null, "La identificación que digitó no corresponde a un asociado");
            }
        }

    }

    public void verListaAsociados() throws FileNotFoundException {
        String contenido = ""; //ESTE ES EL STRING QUE ME PERMITIRÁ VER EL CONTENIDO DE LA "LISTA"
        
        /*
        *PRIMERO DEBEMOS CONTAR LAS LÍNEAS DEL BLOC DE NOTAS. CADA 4 LÍNEAS ES UN ASOCIADO
        */
        int size = 0;
        
        
        try{
            Scanner scanner1 = new Scanner(archivo);  
            while(scanner1.hasNextLine()){
                size = size + 1;
                scanner1.next();
            }
            
            
            tamano = (int)size / 4;
            lista = new Asociado[tamano];
            
            Scanner scanner2 = new Scanner(archivo);
            for(int i = 0; i < tamano; i++){
                lista[i] = new Asociado();
                lista[i].setNombre(scanner2.next());
                lista[i].setCorreo(scanner2.next());
                lista[i].setTelefono(scanner2.next());
                lista[i].setId(scanner2.next());
            }
            
            for (int i = 0; i < tamano; i++) {
            if (lista[i] != null) {
                contenido = contenido + (i + 1) + ": " + lista[i].getNombre() + "\n"; // BÁSICAMENTE MUESTRA LA LISTA ALGO ASÍ 1: DIEGO 2: ARTURO 3: PEDRO...
            } else {
                contenido = contenido + (i + 1) + ": " + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, contenido);
            
            
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
        }
        
        
        
        
        /* lo de abajo es copiado del PDF del profe
        int posicion = 0; 
        
        try{
            DataInputStream entrada = new DataInputStream(new FileInputStream("Asociados.txt"));
            BufferedReader buffer = new BufferedReader(new ImputStreamReader(entrada));
            
            while((contenido = buffer.readLine()) != null){
                lista[posicion] = contenido;
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
        }*/
        
        /*esto es lo que previamente funcionaba
        
          for (int i = 0; i < tamano; i++) {
            if (lista[i] != null) {
                contenido = contenido + (i + 1) + ": " + lista[i].getNombre() + "\n"; // BÁSICAMENTE MUESTRA LA LISTA ALGO ASÍ 1: DIEGO 2: ARTURO 3: PEDRO...
            } else {
                contenido = contenido + (i + 1) + ": " + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, contenido); // MUESTRA LA LISTA COMPLETA DEL ARREGLO
        */
    }

    // ACÁ SIMPLEMENTE ES MOSTRAR UN MENSAJILLO CON EL MONTO ANUAL DE LA ASOCIACIÓN
    // SI NO HA RECIBIDO EL MONTO, ENTONCES DESPLIEGA OTRO MENSAJILLO
    public void agregarMontoAnual() {
        double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto anual que recibirá la Asociación"));
        setMontoAnual(monto);

    }

    //ACÁ QUIERO QUE SE MUESTRE UNA TABLA COMO EN LA PARTE ESCRITA DEL PROYECTO
    //EN OTRAS PALABRAS DEBE MOSTRAR EL NOMBRE DE LOS ASOCIADOS JUNTO CON SU SALDO Y DIVIDENDOS
    //ADEMÁS DE MOSTRAR EL AHORRO TOTAL DE TODOS LOS ASOCIADOS Y EL MONTO ANUAL QUE SE LE DEPOSITÓ A LA
    //ASOCIACIÓN... OBVIAMENTE SI LA ASOCIACIÓN NO HA RECIBIDO EL MONTO LOS DIVIDENDOS Y ESO DEBEN
    //DE MOSTRARSE EN 0 PERO DEBE MOSTRAR EL MONTO TOTAL DE LOS ASOCIADOS Y EL INDIVIDUAL DE CADA
    //ASOCIADO
    public void consultarFondosAhorro() {
        String contenido = "";
        for (int i = 0; i < tamano; i++) {
            if (lista[i] != null) {
                distribuirDividendos(lista[i]);
                contenido = contenido + (i + 1) + ". " + lista[i].getNombre() + "     Ahorro: " + lista[i].getAhorro() + "     Dividendos: " + lista[i].getDividendos() + "\n";
                //ESTE LÍNEA ENORME ES MUY SENCILLA, NADA MÁS ME MUESTRA LO SIGUIENTE: 1. PEDRO AHORRO: 15000 DIVIDENDOS: 500
                                                                                     //2. MARÍA AHORRO: 5000  DIVIDENDOS: 50
            } else {
                contenido = (i + 1) + ". \n";
            }
        }
        JOptionPane.showMessageDialog(null, contenido);
    }

}
/*
for (int i = 0; i < tamano; i++) {
lista[i].setDividendos((montoAnual / fondoAhorro) * lista[i].getAhorro());//ESTA ES LA FORMULILLA QUE SALE EN LA PARTE ESCRITA 
                                                                                             // DEL PROYECTO, LO PUDE HABER HECHO EN 2 LINEAS PERO LO HICE EN 1
                                                                                             // PARA QUE SE VEA MEJOR, NO SÉ, MÁS SIMPLE 
}
*/