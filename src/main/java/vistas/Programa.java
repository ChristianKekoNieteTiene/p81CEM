package vistas;

import java.sql.SQLException;
import controladores.MascotaDAO;
import controladores.VeterinarioDAO;
import java.util.Iterator;
import java.util.List;
import modelos.MascotaDTO;
import modelos.VeterinarioDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author christian
 */
public class Programa {

    private static MascotaDAO MascotaDAO = new MascotaDAO();
    private static VeterinarioDAO VeterinarioDAO = new VeterinarioDAO();

    public static void main(String[] args) throws SQLException {

        String menu = """
                            ---- Menú ----
                        0. Salir
                        1. Lista de mascotas
                        2. Lista de veterinarios
                        3. Buscar mascota
                        4. Buscar veterinario
                        5. Agregar mascota
                        6. Agregar veterinario
                        7. Asignar veterinario a mascota
                        8. Actualizar mascota
                        9. Actualizar veterinario
                        10. Eliminar mascota
                        11. Eliminar veterinario
                        
                          """;

        int opcion = 12;

        do {
                try{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                }catch(NumberFormatException nfe){}
                
                switch (opcion) {
                    
                    case 0 -> System.out.println("saliendo");
                    case 1 ->
                        listaMascotas();
                    case 2 ->
                        listaVeterinarios();
                    case 3 ->
                        buscarMascota();
                    case 4 ->
                        buscarVeterinario();
                    case 5 ->
                        agregarMascota();
                    case 6 ->
                        agregarVeterinario();
                    case 7 ->
                        asignarVeterinario();
                    case 8 ->
                        actualizarMascota();
                    case 9 ->
                        actualizarVeterinario();
                    case 10 ->
                        eliminarVeterinario();
                    case 11 ->
                        eliminarMascota();
                    case 12 ->
                        JOptionPane.showMessageDialog(null, "Escribe un número valido");
                }

    
        } while (opcion != 0);
        JOptionPane.showMessageDialog(null, "Saliendo... Buena tarde");

    }
    
//-------------------------------------------- metodos ---------------------------------------------------------------
    // Listar todas las mascotas
    private static void listaMascotas() throws SQLException {

        List<MascotaDTO> mascotas = MascotaDAO.getBuscarMascotas();
        JOptionPane.showMessageDialog(null, mascotas);

    }

    // Listar todos los veterinarios
    private static void listaVeterinarios() {
        try {
            List<VeterinarioDTO> veterinarios = VeterinarioDAO.getVeterinarios();

            JOptionPane.showMessageDialog(null, veterinarios);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los veterinarios: " + e.getMessage());
        }
    }

    //busca 1 mascta
    private static void buscarMascota() throws SQLException {
        int idMas;
        idMas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el ID de la mascota"));
        MascotaDTO mascota = MascotaDAO.getBuscarMascota(idMas);

        JOptionPane.showMessageDialog(null, mascota);
    }

    //busca 1 vet
    private static void buscarVeterinario() throws SQLException {
        int idVet;
        idVet = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el ID del Veterinario"));
        VeterinarioDTO vet = VeterinarioDAO.buscarVeterinario(idVet);
        
        JOptionPane.showMessageDialog(null, vet);
    }

    //agrega 1 masc
    private static void agregarMascota() throws SQLException {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID:"));
            
            int numChip = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de chip:"));
                        
            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la mascota:");

            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso de la mascota (double):"));

            String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (yyyy-mm-dd):");
            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);

            String tipo = JOptionPane.showInputDialog("Ingrese tipo de mascota (perro, gato, otros):");

            Integer idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario (escriba 0 si no tiene):"));

            // Crear objeto de Mascota
            MascotaDTO nuevaMascota = new MascotaDTO();
            nuevaMascota.setId(id);
            nuevaMascota.setnChip(numChip);
            nuevaMascota.setNombre(nombre);
            nuevaMascota.setPeso(peso);
            nuevaMascota.setFechNac(fechaNacimiento);
            nuevaMascota.setTipo(tipo);
            nuevaMascota.setIdVet(idVeterinario);

            int mascotaIns = MascotaDAO.insertMasc(nuevaMascota);
            
            if (mascotaIns > 0) {
                JOptionPane.showMessageDialog(null, "Mascota agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la mascota.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID veterinario debe ser un número entero o no existe ese ID.");
        }
    }

    // Agregar un nuevo veterinario
    private static void agregarVeterinario() throws SQLException {

        String nif = JOptionPane.showInputDialog("Ingrese NIF del veterinario:");
        String nombre = JOptionPane.showInputDialog("Ingrese nombre del veterinario:");
        String direccion = JOptionPane.showInputDialog("Ingrese dirección del veterinario:");
        String telefono = JOptionPane.showInputDialog("Ingrese teléfono del veterinario:");
        String email = JOptionPane.showInputDialog("Ingrese email del veterinario:");

        VeterinarioDTO nuevoVeterinario = new VeterinarioDTO();
        nuevoVeterinario.setNifVet(nif);
        nuevoVeterinario.setNomVet(nombre);
        nuevoVeterinario.setDirVet(direccion);
        nuevoVeterinario.setTelVet(telefono);
        nuevoVeterinario.setEmailVet(email);

        int filasInsertadas = VeterinarioDAO.insertVet(nuevoVeterinario);
        if (filasInsertadas > 0) {
            JOptionPane.showMessageDialog(null, "Veterinario agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el veterinario.");
        }

    }

    // Asignar un veterinario a una mascota
    private static void asignarVeterinario() throws SQLException {

        int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a asignar:"));
        int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a asignar:"));
        MascotaDTO mascota = MascotaDAO.getBuscarMascota(idMascota);
        if (mascota != null) {
            mascota.setIdVet(idVeterinario);
            MascotaDAO.updateMasc(idMascota, mascota);
            JOptionPane.showMessageDialog(null, "Veterinario asignado a la mascota.");
        } else {
            JOptionPane.showMessageDialog(null, "La mascota no existe.");
        }
    }

    // Actualizar una mascota
    private static void actualizarMascota() {
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a actualizar:"));
            MascotaDTO mascota = MascotaDAO.getBuscarMascota(idMascota);
            if (mascota != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre para la mascota:");
                double nuevoPeso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo peso para la mascota:"));
                String nuevaFechaStr = JOptionPane.showInputDialog("Ingrese nueva fecha de nacimiento (yyyy-mm-dd):");
                java.sql.Date nuevaFecha = java.sql.Date.valueOf(nuevaFechaStr);
                mascota.setNombre(nuevoNombre);
                mascota.setPeso(nuevoPeso);
                mascota.setFechNac(nuevaFecha);
                MascotaDAO.updateMasc(idMascota, mascota);
                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La mascota no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la mascota: " + e.getMessage());
        }
    }

    // Actualizar un veterinario
    private static void actualizarVeterinario() {
        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a actualizar:"));
            VeterinarioDTO veterinario = VeterinarioDAO.buscarVeterinario(idVeterinario);
            if (veterinario != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre para el veterinario:");
                String nuevaDireccion = JOptionPane.showInputDialog("Ingrese nueva dirección para el veterinario:");
                String nuevoTelefono = JOptionPane.showInputDialog("Ingrese nuevo teléfono para el veterinario:");
                String nuevoEmail = JOptionPane.showInputDialog("Ingrese nuevo email para el veterinario:");
                veterinario.setNomVet(nuevoNombre);
                veterinario.setDirVet(nuevaDireccion);
                veterinario.setTelVet(nuevoTelefono);
                veterinario.setEmailVet(nuevoEmail);
                VeterinarioDAO.updateVet(idVeterinario, veterinario);
                JOptionPane.showMessageDialog(null, "Veterinario actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El veterinario no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el veterinario: " + e.getMessage());
        }
    }

    // Eliminar una mascota
    private static void eliminarMascota() throws SQLException {

        int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a eliminar:"));
        int mascota = MascotaDAO.deleteMasc(idMascota);
        if (mascota > 0) {
            JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la mascota.");
        }

    }

    // Eliminar un veterinario
    private static void eliminarVeterinario() throws SQLException {

        int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a eliminar:"));
        int vet = VeterinarioDAO.deleteVeterinario(idVeterinario);
        if (vet > 0) {
            JOptionPane.showMessageDialog(null, "Veterinario eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el veterinario.");
        }
    }
}
