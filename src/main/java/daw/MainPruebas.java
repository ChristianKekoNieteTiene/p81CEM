
package daw;

import controladores.VeterinarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.VeterinarioDTO;

/**
 *
 * @author christian
 */
public class MainPruebas {

    public static void main(String[] args) throws SQLException {
        
        VeterinarioDAO vetDao = new VeterinarioDAO();
        
        List<VeterinarioDTO> listaVet = new ArrayList<>();
       
        List<VeterinarioDTO> nuevaLista = vetDao.getAll();
        
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
    }
}
