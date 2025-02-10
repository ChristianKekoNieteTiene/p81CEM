
package controladores;

import daw.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.IVeterinario;
import modelos.VeterinarioDTO;

/**
 *
 * @author christian
 */
public class VeterinarioDAO implements IVeterinario {
    
    private Connection con = null;
        
    public VeterinarioDAO() {
        con = Conexion.getInstance();
    }

    public VeterinarioDAO(int i, String a, String b, String c, String d, String e) throws SQLException {
        
    }

    @Override
    public List<VeterinarioDTO> getVeterinarios() throws SQLException {
        List<VeterinarioDTO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from veterinario");
            
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                
                VeterinarioDTO vet = new VeterinarioDTO();
                
                // Recogemos los datos del veterinario, guardamos en un objeto
                vet.setIdVet(res.getInt("id"));
                vet.setNomVet(res.getString("nombre"));
                vet.setNifVet(res.getString("nif"));
                vet.setDirVet(res.getString("direccion"));
                vet.setTelVet(res.getString("telefono"));
                vet.setEmailVet(res.getString("email"));
                
                //Añadimos el objeto a la lista
                lista.add(vet);
            }
        }

        return lista;
    } 
    
    @Override
    public VeterinarioDTO buscarVeterinario(int pkVet) throws SQLException {
        
        ResultSet res = null;
        VeterinarioDTO vet = new VeterinarioDTO();

        String sql = "select * from veterinario where id=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pkVet);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                vet.setIdVet(res.getInt("id"));
                vet.setNomVet(res.getString("nombre"));
                vet.setNifVet(res.getString("nif"));
                vet.setDirVet(res.getString("direccion"));
                vet.setTelVet(res.getString("telefono"));
                vet.setEmailVet(res.getString("email"));
                return vet;
            }

            return null;
        }
    }

    @Override
    public int insertVet(VeterinarioDTO newVet) throws SQLException {
        
        int numFilas = 0;
            String sql = "insert into veterinario values (?,?,?,?,?,?)";

            if (buscarVeterinario(newVet.getIdVet()) != null) {
                // Existe un registro con esa pk
                // No se hace la inserción
                return numFilas;
            } else {
                // Instanciamos el objeto PreparedStatement para inserción
                // de datos. Sentencia parametrizada
                try (PreparedStatement prest = con.prepareStatement(sql)) {

                    // Establecemos los parámetros de la sentencia
                    prest.setInt(1, newVet.getIdVet());
                    prest.setString(2, newVet.getNomVet());
                    prest.setString(3, newVet.getNifVet());
                    prest.setString(4, newVet.getDirVet());
                    prest.setString(5, newVet.getTelVet());
                    prest.setString(6, newVet.getEmailVet());

                    numFilas = prest.executeUpdate();
                }
                return numFilas;
            }
    }

    @Override
    public int updateVet(int pkVet, VeterinarioDTO nuevosDatos) throws SQLException {
        
        int numFilas = 0;
        String sql = "update veterinario set nif=?, nombre=?, direccion=?, telefono=?,email=? where id=?";

        if (buscarVeterinario(pkVet) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            
            try (PreparedStatement prest = con.prepareStatement(sql)) {
                
                prest.setString(1, nuevosDatos.getNifVet());
                prest.setString(2, nuevosDatos.getNomVet());
                prest.setString(3, nuevosDatos.getDirVet());
                prest.setString(4, nuevosDatos.getTelVet());
                prest.setString(5, nuevosDatos.getEmailVet());
                prest.setInt(6, pkVet);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int deleteVeterinario(int pkVet) throws SQLException {
        
        int numFilas = 0;

        String sql = "delete from veterinario where id = ?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {

            prest.setInt(1, pkVet);
            
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }
}
