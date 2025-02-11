package controladores;

import daw.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.IMascota;
import modelos.MascotaDTO;

/**
 *
 * @author christian
 */
public class MascotaDAO implements IMascota {

    private Connection con = null;

    public MascotaDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<MascotaDTO> getBuscarMascotas() throws SQLException {
        List<MascotaDTO> lista = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("SELECT * FROM mascota");
            while (res.next()) {
                MascotaDTO m = new MascotaDTO();
                m.setId(res.getInt("id"));
                m.setnChip(res.getInt("numChip"));
                m.setNombre(res.getString("nombre"));
                m.setPeso(res.getDouble("peso"));
                m.setFechNac(res.getDate("fechaNac"));
                m.setTipo(res.getString("tipo"));
                m.setIdVet((Integer)res.getObject("idVet"));

                lista.add(m);
            }
        }

        return lista;
    }

    @Override
    public MascotaDTO getBuscarMascota(int pkMascota) throws SQLException {

        MascotaDTO mascota = null;
        String sql = "SELECT * FROM mascota WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, pkMascota);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mascota = new MascotaDTO();
                mascota.setId(rs.getInt("id"));
                mascota.setnChip(rs.getInt("numChip"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setFechNac(rs.getDate("fechaNac"));
                mascota.setTipo(rs.getString("tipo"));
                mascota.setIdVet(rs.getInt("idVet"));
            }
        }
        return mascota;
    }

    @Override
    public int insertMasc(MascotaDTO newMasc) throws SQLException {

        int numFilas = 0;
        String sql = "INSERT INTO mascota VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (getBuscarMascota(newMasc.getId()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            try (PreparedStatement insertar = con.prepareStatement(sql)) {

                insertar.setInt(1, newMasc.getId());
                insertar.setInt(2, newMasc.getnChip());
                insertar.setString(3, newMasc.getNombre());
                insertar.setDouble(4, newMasc.getPeso());
                // (esto es por si meten una fecha nula)
                if (newMasc.getFechNac() != null) {
                    //si tiene la inserta
                    insertar.setDate(5, newMasc.getFechNac());
                } else {
                    //sino, asignamos NULL a la base de datos
                    insertar.setNull(5, java.sql.Types.DATE);
                }
                insertar.setString(6, newMasc.getTipo());
                // Manejo del id_veterinario (puede ser null)

                Integer idVeterinario = newMasc.getIdVet();
                
                if (idVeterinario > 0) {
                    
                    insertar.setObject(7, idVeterinario); // Si idVeterinario no es null, lo asignamos como entero
                   
                } else {
                    
                    insertar.setObject(7, null);  // Si idVeterinario es null, asignamos NULL en la base de datos
                }

                numFilas = insertar.executeUpdate();
            }
            return numFilas;
        }
    }

    public Integer insertMascota(List<MascotaDTO> lista) throws SQLException {
        int rows = 0;
        for (MascotaDTO m : lista) {
            rows += insertMascota((List<MascotaDTO>) m);
        }
        return rows;
    }

    @Override
    public int updateMasc(int pk, MascotaDTO nuevosDatos) throws SQLException {

        String sql = "UPDATE mascota SET numChip = ?, nombre = ?, peso = ?, fechaNac = ?, tipo = ?, idVet = ? WHERE id = ?";
        try (PreparedStatement prest = con.prepareStatement(sql)) {
            
            prest.setInt(1, nuevosDatos.getnChip());
            prest.setString(2, nuevosDatos.getNombre());
            prest.setDouble(3, nuevosDatos.getPeso());

            if (nuevosDatos.getFechNac() != null) {
                prest.setDate(4, nuevosDatos.getFechNac());
            } else {
                prest.setNull(4, java.sql.Types.DATE);  //Asignamos null a la fecha
            }

            prest.setString(5, nuevosDatos.getTipo());
            prest.setInt(6, nuevosDatos.getIdVet());
            prest.setInt(7, pk);
            return prest.executeUpdate();
        }

    }

    @Override
    public int deleteMasc(int pkMasc) throws SQLException {
        
        int numFilas=0;
        
        String sql = "delete from mascota where id=?";
        
        if (getBuscarMascota(pkMasc) != null) {
        
            try (PreparedStatement deleteMascota = con.prepareStatement(sql)) {
                
                deleteMascota.setInt(1, pkMasc);
                        
                numFilas = deleteMascota.executeUpdate();
            }   
        }else{
            JOptionPane.showMessageDialog(null, "No se ha encontrado la mascota");
        }
        return numFilas;
    }

    // método que permite obtener todas las mascotas tratadas por un veterinario, según su id.
    @Override
    public List<MascotaDTO> getMascotasVet(int pkVet) throws SQLException {

        List<MascotaDTO> lista = new ArrayList<>();

        String sql = "select * from mascota WHERE idVet = ?";

        try (PreparedStatement mascotasVet = con.prepareStatement(sql)) {
            
            mascotasVet.setInt(1, pkVet);
            ResultSet rs = mascotasVet.executeQuery();
            
            while (rs.next()) {
                MascotaDTO m = new MascotaDTO();
                m.setId(rs.getInt("id"));
                m.setnChip(rs.getInt("numChip"));
                m.setNombre(rs.getString("nombre"));
                m.setPeso(rs.getDouble("peso"));
                m.setFechNac(rs.getDate("fechaNac"));
                m.setTipo(rs.getString("tipo"));
                m.setIdVet(rs.getInt("idVet"));
                lista.add(m);
            }
        }
        return lista;
    }
}
