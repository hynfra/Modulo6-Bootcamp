package sust.tv_shows.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sust.tv_shows.models.Canal;
import sust.tv_shows.models.CanalRepository;
import sust.tv_shows.models.TvShowRepository;
import sust.tv_shows.models.Tvshow;


@Controller
public class CanalController {

    @Autowired
    CanalRepository repo;

    @GetMapping("/canales")
    public ModelAndView cargarCanales() {


        ModelAndView vista = new ModelAndView("crearCanal.html");
        List<Canal> canales = repo.findAll();
        vista.addObject("canales", canales);
        


        return vista;
    }

    @PostMapping("/canales")
    public String create(@RequestParam String name ) {
       System.out.println(name+"\n\n\n");

       Canal canal = new Canal();
       canal.setName(name);
       
     
       repo.save(canal);

       
       

        
        return "redirect:/canales";
    }
    
}
