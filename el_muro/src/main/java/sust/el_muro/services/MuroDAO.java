package sust.el_muro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.Muro;
import sust.el_muro.models.MuroRepository;
import sust.el_muro.models.Usuario;


@Component
public class MuroDAO {

    @Autowired
    MuroRepository muroRepo;
 
    public List<Muro> findAll() {
        List<Muro> publicaciones = muroRepo.findAll();

        for (Muro m : publicaciones) {
            m.setNombreCreador(m.getUsuario().getName());
            m.setUsuario(null);
            m.setComentarios(null);
          }

          return publicaciones;
      }

    public void create(String publicacion, HttpSession session){
        Usuario yo = (Usuario)session.getAttribute("user");

         Muro m = new Muro();
         m.setPublicacion(publicacion);
         m.setUsuario(yo);
        
         muroRepo.save(m);

       
    }

    

}
