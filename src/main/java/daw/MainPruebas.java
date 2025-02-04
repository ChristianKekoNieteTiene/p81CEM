
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
        
        listaVet.add(new VeterinarioDTO(10,"hugo","niffff","direcc","teleff","email@gmail.com"));
        
        List<VeterinarioDTO> nuevaLista = vetDao.getAll();
        
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            
            System.out.println("");
            
        VeterinarioDTO veterinario = vetDao.buscarVeterinario(1);
            System.out.println("-------- Veterinario con datos recogidos del id 1 -------------");
            System.out.println(veterinario);
            
            System.out.println("");
            
            System.out.println("Nº personas insertadas " + vetDao.insertVet((new VeterinarioDTO(10,"hugo","niffff","direcc","teleff","email@gmail.com"))));
            
            
    }
}
