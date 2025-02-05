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
    private static VeterinarioDAO VeterinarioDAO= new VeterinarioDAO();
    
    public static void main(String[] args) throws SQLException {
        while (true) {
            String menu = """
                            ---- Menú ----
                        1. Lista de mascotas
                        2. Lista de veterinarios
                        3. Agregar mascota
                        4. Agregar veterinario
                        5. Asignar veterinario a mascota
                        6. Actualizar mascota
                        7. Actualizar veterinario
                        8. Eliminar mascota
                        9. Eliminar veterinario
                        0. Salir
                          
                          """;

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.INFORMATION_MESSAGE);
            int opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1 -> listaMascotas();
                case 2 -> listaVeterinarios();
                case 3 -> agregarMascota();
                case 4 -> agregarVeterinario();
                case 5 -> asignarVeterinario();
                case 6 -> eliminarMascota();
                case 7 -> eliminarVeterinario();
                case 8 -> actualizarMascota();
                case 9 -> actualizarVeterinario();
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo... Buena tarde");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    // Listar todas las mascotas
    private static void listaMascotas() {
        try {
            List<MascotaDTO> mascotas = MascotaDAO.getBuscarMascotas();
            StringBuilder message = new StringBuilder("Mascotas:\n");
            for (MascotaDTO m : mascotas) {
                message.append(m.getId()).append(": ").append(m.getNombre()).append(" (").append(m.getTipo()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las mascotas: " + e.getMessage());
        }
    }

    // Listar todos los veterinarios
    private static void listaVeterinarios() {
        try {
            List<VeterinarioDTO> veterinarios = VeterinarioDAO.getVeterinarios();
            StringBuilder message = new StringBuilder("Veterinarios:\n");
            for (VeterinarioDTO v : veterinarios) {
                message.append(v.getIdVet()).append(": ").append(v.getNomVet()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los veterinarios: " + e.getMessage());
        }
    }

    private static void agregarMascota() throws SQLException {
        try {
            int numChip = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de chip:"));
            
            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la mascota:");
            
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese peso de la mascota (double):"));
            
            String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (yyyy-mm-dd):");
            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
            
            String tipo = JOptionPane.showInputDialog("Ingrese tipo de mascota (perro, gato, otros):");
            
            Integer idVeterinario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del veterinario (deje vacio si no tiene):"));

            // Crear objeto de Mascota
            MascotaDTO nuevaMascota = new MascotaDTO();
            nuevaMascota.setNombre(nombre);
            nuevaMascota.setTipo(tipo);
            nuevaMascota.setPeso(peso);
            nuevaMascota.setFechNac(fechaNacimiento);
            nuevaMascota.setnChip(numChip);
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
}
