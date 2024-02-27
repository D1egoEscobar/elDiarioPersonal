package diariopersonal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



/**
 *
 * @author Diego Escobar Enriquez
 */

//Variables Privadas

public class DiarioPersonal {
   private ArrayList<Entrada> misEntradas;
    private Scanner lector;

    public DiarioPersonal() {
        misEntradas = new ArrayList<>();
        lector = new Scanner(System.in);
    }


 public static void main(String[] args) {
        DiarioPersonal diario = new DiarioPersonal();
        diario.mostrarMenu();
    }



    public void mostrarMenu() {
        boolean activo = true;
        do {
            System.out.println("--- MENU DE OPCIONES ---\n"
                    + "1.Agregar una Entrada\n"
                    + "2.Consultar Entrada\n"
                    + "3.Modificar una Entrada\n"
                    + "4.Eliminar una Entrada\n"
                    + "5.Terminar Entrada\n");

            System.out.print("Seleccione una opcion: ");
            int opcion = lector.nextInt();
            lector.nextLine(); // Consumir el salto de línea restante

            switch (opcion) {
                case 1:
                    agregarEntrada();
                    break;

                case 2:
                    consultarEntrada();
                    break;

                case 3:
                    modificarEntrada();
                    break;

                case 4:
                    eliminarEntrada();
                    break;

                case 5:
                    activo = false;
                    System.out.println("Programa terminado");
                    break;

                default:
                    System.out.println("Opcion no válida, intentelo nuevamente");
            }
        } while (activo);
    }

    private void agregarEntrada() {
        System.out.print("Ingrese la Entrada que quiere contar: ");
        String descripcion = lector.nextLine();
        System.out.println("--------------------------------------");

           int nuevoIdEntrada = misEntradas.size() + 1;


        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = formatoFecha.format(fechaActual);

        Entrada nuevaEntrada = new Entrada(nuevoIdEntrada, fecha, descripcion);
        misEntradas.add(nuevaEntrada);

        System.out.println("La Entrada ha sido agregada con éxito!");
    }



    private void consultarEntrada() {
    System.out.print("Ingrese la fecha de las Entradas que desea consultar (dd-MM-yyyy): ");
    String fechaBuscada = lector.nextLine(); // El usuario ingresa la fecha

    // Verificar si hay entradas en esa fecha
    boolean encontrada = false;
    for (Entrada entrada : misEntradas) {
        if (entrada.getFecha().equals(fechaBuscada)) {
            // Se encontró al menos una entrada con la fecha especificada
            encontrada = true;
            System.out.println("ID: " + entrada.getIdEntrada() + " | Fecha: " + entrada.getFecha() + " | Descripción: " + entrada.getDescripcion());
            System.out.println("----------------------------------");
        }
    }

    if (!encontrada) {
        System.out.println("No hay Entradas registradas en la fecha proporcionada.");
    }
}


    private void modificarEntrada() {
        System.out.print("Ingrese el ID de la Entrada que desea modificar: ");
        int idEntradaModificar = lector.nextInt();
        lector.nextLine(); // Consumir el salto de línea

        for (Entrada entrada : misEntradas) {
            if (entrada.getIdEntrada() == idEntradaModificar) {
                System.out.print("Ingrese la nueva Entrada: ");
                entrada.setDescripcion(lector.nextLine());
                System.out.println("La Entrada ha sido modificada con éxito.");
                return;
            }
        }

        System.out.println("No se encontro ninguna Entrada con el ID proporcionado.");
    }



    private void eliminarEntrada() {
        System.out.print("Ingrese el ID de la Entrada que desea eliminar: ");
        int idEntradaEliminar = lector.nextInt();
        lector.nextLine(); // Consumir el salto de línea

        Entrada entradaEliminar = null;
        for (Entrada entrada : misEntradas) {
            if (entrada.getIdEntrada() == idEntradaEliminar) {
                entradaEliminar = entrada;
                break;
            }
        }

        if (entradaEliminar != null) {
            System.out.print("¿Esta seguro de eliminar esta Entrada? (S/N): ");
            String confirmacion = lector.nextLine().toUpperCase();
            if (confirmacion.equals("S")) {
                misEntradas.remove(entradaEliminar);
                System.out.println("La Entrada ha sido eliminada correctamente.");
            } else {
                System.out.println("Operacion cancelada.");
            }
        } else {
            System.out.println("No se encontró ninguna Entrada con el ID proporcionado.");
        }
    }  
}
