package sust.el_muro.controllers;

import org.springframework.web.bind.annotation.RestController;

import sust.el_muro.models.Comentario;
import sust.el_muro.models.ComentarioRepository;
import sust.el_muro.services.ComentarioDAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/comments")
public class CommentRestController {


    @Autowired
    ComentarioRepository comentarioRepo;

    @Autowired
    ComentarioDAO comentarioDao;
    @GetMapping("/{publicacion_id}")
    public List<Comentario> getAll(@PathVariable Long publicacion_id ) {
        List<Comentario> comments = comentarioDao.getCommentsFromMuro(publicacion_id);

    return comments;
    }
    
   
    

}
