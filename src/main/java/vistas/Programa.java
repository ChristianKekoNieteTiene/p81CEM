package vistas;

import java.sql.SQLException;
import controladores.MascotaDAO;
import controladores.VeterinarioDAO;
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
                        3. Lista de Mascotas de 1 Veterinario
                        4. Buscar mascota
                        5. Buscar veterinario
                        6. Agregar mascota
                        7. Agregar veterinario
                        8. Actualizar mascota
                        9. Actualizar veterinario
                        10. Eliminar mascota
                        11. Eliminar veterinario
                        
                          """;

        int opcion = 12;

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException nfe) {
            }

            switch (opcion) {

                case 0 ->
                    System.out.println("saliendo");
                case 1 ->
                    listaMascotas();
                case 2 ->
                    listaVeterinarios();
                case 3 ->
                    listaMascVet();
                case 4 ->
                    buscarMascota();
                case 5 ->
                    buscarVeterinario();
                case 6 ->
                    agregarMascota();
                case 7 ->
                    agregarVeterinario();
                case 8 ->
                    actualizarMascota();
                case 9 ->
                    actualizarVeterinario();
                case 10 ->
                    eliminarMascota();
                case 11 ->
                    eliminarVeterinario();
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

    private static void listaMascVet() throws SQLException {
        
    try {
        Integer idVet = Integer.parseInt(JOptionPane.showInputDialog("Introduce el id de Veterinario"));

        
            List<MascotaDTO> mascotas = MascotaDAO.getMascotasVet(idVet);

            JOptionPane.showMessageDialog(null, mascotas);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has escrito el id de vet");
        }

    }

    //busca 1 mascta
    private static void buscarMascota() throws SQLException {

        try {
            int idMas;
            idMas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el ID de la mascota"));
            MascotaDTO mascota = MascotaDAO.getBuscarMascota(idMas);

            JOptionPane.showMessageDialog(null, mascota);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has escrito el id");
        }
    }

    //busca 1 vet
    private static void buscarVeterinario() throws SQLException {

        try {
            int idVet;
            idVet = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el ID del Veterinario"));
            VeterinarioDTO vet = VeterinarioDAO.buscarVeterinario(idVet);

            JOptionPane.showMessageDialog(null, vet);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has escrito el id");
        }
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

            Integer idVeterinario = Integer.valueOf(JOptionPane.showInputDialog("Ingrese el ID del veterinario (escriba 0 si no tiene):"));

            
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

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has introducido nada");
        }

    }

    // Agregar un nuevo veterinario
    private static void agregarVeterinario() throws SQLException {

        try {

            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID vet:"));
            String nif = JOptionPane.showInputDialog("Ingrese NIF del veterinario:");
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del veterinario:");
            String direccion = JOptionPane.showInputDialog("Ingrese dirección del veterinario:");
            String telefono = JOptionPane.showInputDialog("Ingrese teléfono del veterinario:");
            String email = JOptionPane.showInputDialog("Ingrese email del veterinario:");

            VeterinarioDTO nuevoVeterinario = new VeterinarioDTO();
            nuevoVeterinario.setIdVet(id);
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

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has introducido nada");
        }
    }

    // Actualizar una mascota
    private static void actualizarMascota() throws SQLException {

        JOptionPane.showMessageDialog(null, "Recuerda deja en blanco si no quieres actualzar");
        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Pon el ID de la mascota a actualizar:"));
            if (idMascota <= 0) {
                return;
            }

            MascotaDTO mascota = MascotaDAO.getBuscarMascota(idMascota);
            if (mascota != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Pon nuevo nombre para la mascota:");
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    mascota.setNombre(nuevoNombre);
                }

                String nuevoPesoStr = JOptionPane.showInputDialog("Pon nuevo peso para la mascota:");
                if (nuevoPesoStr != null && !nuevoPesoStr.trim().isEmpty()) {
                    double nuevoPeso = Double.parseDouble(nuevoPesoStr);
                    if (nuevoPeso > 0) {
                        mascota.setPeso(nuevoPeso);
                    }
                }

                String nuevaFechaStr = JOptionPane.showInputDialog("Pon nueva fecha de nacimiento (yyyy-mm-dd):");
                if (nuevaFechaStr != null && !nuevaFechaStr.trim().isEmpty()) {
                    java.sql.Date nuevaFecha = java.sql.Date.valueOf(nuevaFechaStr);
                    mascota.setFechNac(nuevaFecha);
                }

                String nuevoChip = JOptionPane.showInputDialog("Pon el nuevo número de chip para la mascota:");
                if (nuevoChip != null && !nuevoChip.trim().isEmpty()) {
                    int conversionChip = Integer.parseInt(nuevoChip);
                    mascota.setnChip(conversionChip);
                }

                String nuevoTipo = JOptionPane.showInputDialog("Pon el nuevo tipo de mascota (perro, gato, otros):");
                if (nuevoTipo != null && !nuevoTipo.trim().isEmpty()) {
                    if (!nuevoTipo.equalsIgnoreCase("perro") && !nuevoTipo.equalsIgnoreCase("gato") && !nuevoTipo.equalsIgnoreCase("otros")) {
                        JOptionPane.showMessageDialog(null, "El tipo de mascota debe ser 'perro', 'gato' o 'otros'.");
                        return;
                    }
                    mascota.setTipo(nuevoTipo);
                }

                Integer nuevoVet = Integer.parseInt(JOptionPane.showInputDialog("Pon el nuevo Veterinario para la mascota:"));
                if (nuevoVet != null) {

                    mascota.setIdVet(nuevoVet);
                }

                MascotaDAO.updateMasc(idMascota, mascota);
                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La mascota no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la mascota: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No has introducido datos");
        }
    }

    // Actualizar un veterinario
    private static void actualizarVeterinario() throws SQLException {

        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Pon el ID del veterinario a actualizar:"));
            if (idVeterinario <= 0) {
                return;
            }
            VeterinarioDTO veterinario = VeterinarioDAO.buscarVeterinario(idVeterinario);
            if (veterinario != null) {
                // Actualizar nombre
                String nuevoNombre = JOptionPane.showInputDialog("Pon el nuevo nombre:");
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    veterinario.setNomVet(nuevoNombre);
                }

                String nuevaDireccion = JOptionPane.showInputDialog("Pon la nueva dirección:");
                if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
                    veterinario.setDirVet(nuevaDireccion);
                }

                String nuevoTelefono = JOptionPane.showInputDialog("Pon el nuevo teléfono:");
                if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                    veterinario.setTelVet(nuevoTelefono);
                }

                String nuevoEmail = JOptionPane.showInputDialog("Pon el nuevo email:");
                if (nuevoEmail != null && !nuevoEmail.trim().isEmpty()) {
                    veterinario.setEmailVet(nuevoEmail);
                }

                String nuevoNif = JOptionPane.showInputDialog("Pon el nuevo NIF:");
                if (nuevoNif != null && !nuevoNif.trim().isEmpty()) {
                    veterinario.setNifVet(nuevoNif);
                }

                VeterinarioDAO.updateVet(idVeterinario, veterinario);
                JOptionPane.showMessageDialog(null, "Veterinario actualizado.");
            } else {
                JOptionPane.showMessageDialog(null, "El veterinario no existe.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el veterinario");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No has introducido datos");
        }
    
    }

    // Eliminar una mascota
    private static void eliminarMascota() throws SQLException {

        try {
            int idMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a eliminar:"));
            int mascota = MascotaDAO.deleteMasc(idMascota);

            if (mascota > 0) {
                JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la mascota.");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has escrito el id");
        }
    }

    // Eliminar un veterinario
    private static void eliminarVeterinario() throws SQLException {

        try {
            int idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario a eliminar:"));
            int vet = VeterinarioDAO.deleteVeterinario(idVeterinario);

            if (vet > 0) {
                JOptionPane.showMessageDialog(null, "Veterinario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el veterinario.");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "No has escrito el id");
        }
    }
}
