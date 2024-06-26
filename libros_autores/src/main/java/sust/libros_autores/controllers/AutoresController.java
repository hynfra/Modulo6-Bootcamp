package sust.libros_autores.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sust.libros_autores.models.Autor;
import sust.libros_autores.models.Libro;
import sust.libros_autores.services.AutorDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AutoresController {
     @Autowired // al usar esto pone la clase con un constructor vacio y uno con parametros (si es que tiene)
        AutorDao dao;

    @GetMapping("/autores")
    public ModelAndView pantallaAutores(){
      List<Autor> autores;
      ModelAndView vista = new ModelAndView("autores");
    
     autores = dao.getAll();
     System.out.println(autores);
     vista.addObject("autores", autores);
    
    return vista;
    }
    @GetMapping("/autores/detalle/{id}")
    public ModelAndView detalleAutor(@PathVariable int id) throws SQLException { // la variable del pathvariable debe llamarse igual al de la ruta de getmapping
        ModelAndView vista = new ModelAndView("autor");
        Autor a;
        a = dao.getById(id);
        System.out.println(a);
        vista.addObject("autor", a);
        
        // redirige a la vista
        return vista;
    }

    @PostMapping("/autores")
    public String crearAutor(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String notas ) {
        //TODO: process POST request
       dao.create(nombre, apellido, notas);
        return "redirect:/autores";
    }

    @GetMapping("/autores/borrar/{id}")
    public String borrarAutor(@PathVariable int id) {

        System.out.println(id);
      //dao.delete(id);
        return "redirect:/autores";
    }

    
    
    

    
    

}
