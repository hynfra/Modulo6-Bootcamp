package sust.el_muro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.Comentario;
import sust.el_muro.services.ComentarioDAO;

@Controller
public class ComentarioController {

    @Autowired
    ComentarioDAO comentarioDAO;

    @PostMapping("/comentario/create")
    public String createComentario(@RequestParam String comentario,@RequestParam Long publicacion_id ,
    @RequestParam Long usuario_id, RedirectAttributes redAt, HttpSession session ) {
      
        
        
        comentarioDAO.create(comentario, usuario_id, publicacion_id);

        redAt.addFlashAttribute("bien", "publicación creada");
       
            
        
        
        return "redirect:/";
    }

   


}
