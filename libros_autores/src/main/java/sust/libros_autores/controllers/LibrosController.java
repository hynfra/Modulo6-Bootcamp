package sust.libros_autores.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.websocket.server.PathParam;
import sust.libros_autores.models.Autor;
import sust.libros_autores.models.Libro;
import sust.libros_autores.services.LibroDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LibrosController {

    @Autowired // al usar esto pone la clase con un constructor vacio y uno con parametros (si es que tiene)
    LibroDao dao;

    @GetMapping("/libros")
    @ResponseBody
    public ModelAndView pantallaLibros(){
      ArrayList<Libro> libros;
      ModelAndView vista = new ModelAndView("libros");
    try {
      libros = dao.getAll();
     vista.addObject("libros", libros);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return vista;
    }

    @PostMapping("/libros")
    public String addLibro(@RequestParam String titulo ,  @RequestParam String descripcion) {
        //TODO: process POST request
        dao.create(titulo, descripcion);
        // redirige a la vista
        return "redirect:/libros";
    }
    //libros/borrar/5 -> @Pathvariable
    //libros/borrar?id=6 -> @RequestParam parametros
    // los enlaces href por defecto mandan get, por eso se usa getmapping
    @GetMapping("/libros/borrar/{id}")
    public String deleteLibro(@PathVariable int id) { // la variable del pathvariable debe llamarse igual al de la ruta de getmapping
        //TODO: process POST request
        dao.delete(id);
        
        // redirige a la vista
        return "redirect:/libros";
    }
    @GetMapping("/libros/detalle/{id}")
    public ModelAndView detalleLibro(@PathVariable int id) throws SQLException { // la variable del pathvariable debe llamarse igual al de la ruta de getmapping
        ModelAndView vista = new ModelAndView("detallelibros");
        Libro l;
        l = dao.getById(id);
        ArrayList<Autor> autoresNoRelacionados = new ArrayList< Autor>();
        autoresNoRelacionados = dao.getAutoresNoRelacionados( id);
        vista.addObject("libro", l);
        vista.addObject("autoresNoRelacionados", autoresNoRelacionados);
        // redirige a la vista
        return vista;
    }

    @PostMapping("/libros/{libro_id}/agregarautor")
    public String agregarAutor(@PathVariable int libro_id, @RequestParam int autor_id){
      //
      System.out.println("el libro es: " + libro_id);
      System.out.println("El autor es: " + autor_id);

      return "redirect:/libros";
    }
    
    

}
