
package modelos;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author christian
 */
public interface IMascota {
    
    // Método para obtener todos los registros de la tabla
    List<MascotaDTO> getBuscarMascotas() throws SQLException;
    
    // metodo buscar mascota por su id
    MascotaDTO getBuscarMascota(int pkMascota) throws SQLException;
    
    // Método para insertar una mascota
    int insertMasc (MascotaDTO newMasc) throws SQLException;
    
    // Método para modificar una mascota. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateMasc (int pk, MascotaDTO nuevosDatos) throws SQLException;
    
    // Método para borrar una mascota
    int deleteMasc (int pkMasc) throws SQLException;
    
    // método que permite obtener todas las mascotas tratadas por un veterinario, según su id.
    List<MascotaDTO>getMascotasVet(int pkVet) throws SQLException;
    
}
