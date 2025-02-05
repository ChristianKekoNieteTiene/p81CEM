package controladores;

import daw.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    public List<MascotaDTO> buscarMascotas() throws SQLException {
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
                m.setIdVet(res.getInt("idVet"));

                lista.add(m);
            }
        }

        return lista;
    }

    @Override
    public MascotaDTO buscarMascota(int pkMascota) throws SQLException {

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
        
        String sql = "INSERT INTO mascota (numChip, nombre, peso, fechaNac, tipo, idVet) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            
            prest.setInt(1, newMasc.getnChip());
            prest.setString(2, newMasc.getNombre());
            prest.setDouble(3, newMasc.getPeso());
            // (esto es por si meten una fecha nula)
                if (newMasc.getFechNac()!= null) {
                    java.sql.Date sqlDate = new java.sql.Date(newMasc.getFechNac().getTime());
                    prest.setDate(4, sqlDate);  
                } else {
                    prest.setNull(4, java.sql.Types.DATE);  // Si la fecha es null, asignamos NULL a la base de datos
                }

            prest.setString(5, newMasc.getTipo());

            // Manejo del id_veterinario (puede ser null)
            Integer idVeterinario = newMasc.getIdVet();
            if (idVeterinario != null) {
                prest.setInt(6, idVeterinario); // Si idVeterinario no es null, lo asignamos como entero
            } else {
                prest.setNull(6, java.sql.Types.INTEGER);  // Si idVeterinario es null, asignamos NULL en la base de datos
            }

            // Ejecutar la inserción en la base de datos y devolver el número de filas afectadas
            return prest.executeUpdate();
        }
    }
    
    public Integer insertMascota(List<MascotaDTO> lista) throws SQLException {
        int rows = 0;
        for (MascotaDTO m : lista) {
            rows += insertMascota((List<MascotaDTO>) m); //hago casting a mascota
        }
        return rows;
    }
    
    @Override
    public int updateMasc(int pk, MascotaDTO nuevosDatos) throws SQLException {
        
        String sql = "UPDATE mascota SET numChip = ?, nombre = ?, peso = ?, fechaNa = ?, tipo = ?, idVet = ? WHERE id = ?";
        try (PreparedStatement prest = con.prepareStatement(sql)) {
           
            prest.setInt(1, nuevosDatos.getnChip());
            prest.setString(2, nuevosDatos.getNombre());
            prest.setDouble(3, nuevosDatos.getPeso());
            
            if (nuevosDatos.getFechNac()!= null) {
                java.sql.Date sqlDate = new java.sql.Date(nuevosDatos.getFechNac().getTime());
                prest.setDate(4, sqlDate);
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
        
        String sql = "delete from mascota where pkMasc";
        try (Statement stmt = con.createStatement()) {
            return stmt.executeUpdate(sql);
        }
        
    }

    // método que permite obtener todas las mascotas tratadas por un veterinario, según su id.
    public List<MascotaDTO> getMascotasVet(Integer idVeterinario) throws SQLException {
        List<MascotaDTO> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM mascota WHERE idVet = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idVeterinario);
            ResultSet rs = stmt.executeQuery();
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
