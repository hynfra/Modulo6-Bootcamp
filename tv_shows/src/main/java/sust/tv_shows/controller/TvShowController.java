package sust.tv_shows.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sust.tv_shows.models.Canal;
import sust.tv_shows.models.CanalRepository;
import sust.tv_shows.models.TvShowRepository;
import sust.tv_shows.models.Tvshow;
import sust.tv_shows.services.TvShowDAO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TvShowController {


    @Autowired
    TvShowRepository repo;

    @Autowired
    TvShowDAO tvshowDAO;

    @Autowired
    CanalRepository canalRepo;

    // los controller son la conexion entre la vista y los dao, donde se genera toda la logica
    

    @GetMapping("/shows")
    public ModelAndView pantallaShows(){
      System.out.println("qwerty");
      ModelAndView vista = new ModelAndView("shows.html");
      List<Tvshow> shows = tvshowDAO.findAll();
      vista.addObject("shows", shows);
    
    
    return vista;
    }

    @GetMapping("/shows/new")
    public ModelAndView pantallaCrearShow(){
      System.out.println("qwerty");
      ModelAndView vista = new ModelAndView("crearshow.html");

      List<Canal> canales = canalRepo.findAll();

      vista.addObject("canales", canales);
     
    
    return vista;
    }

    @GetMapping("/shows/{id}/edit")
    public ModelAndView pantallaEditarShow(@PathVariable long id){
      System.out.println("qwerty");
      ModelAndView vista = new ModelAndView("editarshow.html");
     Tvshow show = repo.getReferenceById(id);
    vista.addObject("show", show);
    return vista;
    }

    @GetMapping("/shows/{id}")
    public ModelAndView pantallaDetalleShow(@PathVariable long id){
      System.out.println("qwerty");
      ModelAndView vista = new ModelAndView("detalleshow.html");
      Tvshow show = repo.getReferenceById(id);
      vista.addObject("show", show);
      return vista;
    }

    @PostMapping("/shows/create")
    public String createShow(@RequestParam String titulo, @RequestParam String fecha,
    @RequestParam Long canal_id, @RequestParam String descripcion, RedirectAttributes redAt ) {
      
       // crea el nuevo show
       tvshowDAO.create(titulo, fecha, canal_id, descripcion, repo);

       redAt.addFlashAttribute("bien", "El show ha sido creado correctamente");
       
        
        
        return "redirect:/shows";
    }
    @PostMapping("/shows/{id}/edit")
    public String editShow(@PathVariable long id, @RequestParam String titulo, @RequestParam String fecha,
    @RequestParam String canal, @RequestParam String descripcion, RedirectAttributes redAt) {
      
      //1.- Recupero el show a partir de su ID
      Tvshow s = repo.getReferenceById(id);

     
       

      //2. lo modifico
      s.setTitulo(titulo);
      s.setFecha(fecha);
      //s.setCanal(canal);
      s.setDescripcion(descripcion);

      //3. lo guardo
      repo.save(s);
      //mensaje de realizado

      redAt.addFlashAttribute("bien", "El show ha sido editado correctamente");
      //4 redirijo a /shows
      return "redirect:/shows";
    }
    

    //borrar
     @GetMapping("/shows/{id}/destroy")
    public String borrarShow(@PathVariable int id) {

      
      long idShow = (long) id;
      repo.deleteById(idShow);
        return "redirect:/shows";
    }
    
    
}
