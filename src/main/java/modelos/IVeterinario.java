
package modelos;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author christian
 */
public interface IVeterinario {
    
    // Método para obtener todos los registros de la tabla
    List<VeterinarioDTO> getAll() throws SQLException;
    
    // metodo buscar veterinario por su id
    VeterinarioDTO buscarVeterinario(int pkVet) throws SQLException;
    
    // Método para insertar un veterinario
    int insertVet (VeterinarioDTO newVet) throws SQLException;
    
    // Método para modificar un veterinario. Se modifica el veterinario que tenga esa 'pk'
    // con los nuevos datos que traiga el veterinario 'nuevosDatos'
    int updateVet (int pkVet, VeterinarioDTO nuevosDatos) throws SQLException;
    
    // Método para borrar un veterinario
    int deleteVeterinario (int pkVet) throws SQLException;
}
