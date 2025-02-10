package daw;

import controladores.MascotaDAO;
import controladores.VeterinarioDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.VeterinarioDTO;
import java.time.LocalDate;
import modelos.MascotaDTO;

/**
 *
 * @author christian
 */
public class MainPruebas {

    public static void main(String[] args) throws SQLException {

        VeterinarioDAO vetDao = new VeterinarioDAO();
        List<VeterinarioDTO> listaVet = new ArrayList<>();
//
//        listaVet.add(new VeterinarioDTO(10, "hugo", "niffff", "direcc", "teleff", "email@gmail.com"));
//
//        List<VeterinarioDTO> nuevaLista = vetDao.getAll();
//
//        System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
//        nuevaLista.forEach(System.out::println);
//
//        System.out.println("");
//
//        VeterinarioDTO veterinario = vetDao.buscarVeterinario(1);
//        System.out.println("-------- Veterinario con datos recogidos del id 1 -------------");
//        System.out.println(veterinario);
//
//        System.out.println("");
//
//        System.out.println("Nº personas insertadas " + vetDao.insertVet((new VeterinarioDTO(10, "hugo", "niffff", "direcc", "teleff", "email@gmail.com"))));
//
//        System.out.println("");
// System.out.println("Modificación de la persona con pk 10")
        System.out.println("Modificación de la persona con pk 10");
        VeterinarioDTO v1 = new VeterinarioDTO(10, "nif", "NuevoNombre", "direc", "tel", "@gmailsss");
        System.out.println("Nº Personas modificadas "
                + vetDao.updateVet(10, v1));
//
//        System.out.println("");
//
//        System.out.println("Se va a borrar la persona con pk 10");
//        System.out.println("Nº personas borradas "
//                + vetDao.deleteVeterinario(10));

    //        Date fechaNac = Date.valueOf(LocalDate.EPOCH);
    //
    //        MascotaDAO masDao = new MascotaDAO();
    //
    //        List<MascotaDAO> listaMas = new ArrayList<>();
    //        listaMas.add(masDao);

//           // mostrar 1 mascota
//           MascotaDTO mascota = masDao.getBuscarMascota(1);
//           System.out.println(mascota);
//           
//           // todas las mascotas
//           List<MascotaDTO> nuevaLista = masDao.getBuscarMascotas();
//           
//           System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
//           nuevaLista.forEach(System.out::println);
        // insertar mascota
//        System.out.println("insertando: " + masDao.insertMasc(new MascotaDTO(11, "nombre", 100, 2.2, null, "tipo", null)));
//        System.out.println(masDao.getBuscarMascota(11));
        
    }
}
