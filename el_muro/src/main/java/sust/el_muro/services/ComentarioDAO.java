package sust.el_muro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.Comentario;
import sust.el_muro.models.ComentarioRepository;
import sust.el_muro.models.Muro;
import sust.el_muro.models.MuroRepository;
import sust.el_muro.models.UserRepository;
import sust.el_muro.models.Usuario;

@Component
public class ComentarioDAO {

    @Autowired
    ComentarioRepository comentarioRepo;
    @Autowired
    MuroRepository muroRepo;

    @Autowired
    UserRepository usuarioRepo;

    public void create(String comentario, Long usuario_id, Long publicacion_id) {
         

       Usuario u = usuarioRepo.findById(usuario_id).get();
       Muro m = muroRepo.findById(publicacion_id).get();
         Comentario c = new Comentario();
         c.setComentario(comentario);
         c.setUsuario(u);
         c.setMuro(m);
        
         comentarioRepo.save(c);

    }

     public List<Comentario> getCommentsFromMuro(Long publicacion_id){
        
        Muro m = muroRepo.findById(publicacion_id).get(); // recibe la publicacion
    List<Comentario> comments = comentarioRepo.findByMessage(m); // recupera todos los comentarios
    List<Comentario> retorno = new ArrayList<Comentario>();
    //limpia los comentarios
    for (Comentario com : comments) {
        
        Comentario c = new Comentario();
        c.setId(com.getId());
      c.setComentario(com.getComentario());
      c.setCreado_en(com.getCreado_en());
      c.setNombreCreador(com.getUsuario().getName());
      retorno.add(c);
    }
    return comments;
    }


}
