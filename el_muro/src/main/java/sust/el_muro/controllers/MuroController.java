package sust.el_muro.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.ComentarioRepository;
import sust.el_muro.models.Muro;
import sust.el_muro.models.Usuario;
import sust.el_muro.services.MuroDAO;
import sust.el_muro.services.UserDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MuroController {

    @Autowired
    MuroDAO muroDAO;
 


    
    
    @GetMapping("/")
    public Object cargarMuro(HttpSession session) {

        Usuario u = (Usuario) session.getAttribute("user");

        if (u == null) {
           return "redirect:/login";
        }
        System.out.println("llegue aca ");
        List<Muro> messages = muroDAO.findAll();
        System.out.println(messages.get(0));
        ModelAndView vista = new ModelAndView("muro.html");
           vista.addObject("muro", messages);
        
        return vista;
    }

    @PostMapping("/muro/create")
    public String createPublicacion(@RequestParam String publicacion , RedirectAttributes redAt, HttpSession session ) {
      
        
             muroDAO.create(publicacion,session);

            redAt.addFlashAttribute("bien", "publicación creada");
       
            
        
        
        return "redirect:/";
    }
    

}
