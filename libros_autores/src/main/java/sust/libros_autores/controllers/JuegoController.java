package sust.libros_autores.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class JuegoController {

     @GetMapping("/juego")
    public ModelAndView pantallaJuego(HttpSession session){

        //generamos un numero entero al azar entre 1 y 100
        int random = generarNum();

        ModelAndView view = new ModelAndView("juego.html");
        System.out.println(random);
        return view;
        
    }
    private int generarNum(){
        // este metodo retorna un numero al azar entre 0 y 100
        return (int) Math.floor(Math.random() * 101);
    }

    @PostMapping(value = "/juego/jugar")
  public ModelAndView jugar(HttpSession session, @RequestParam int intento) {
    // 1. Recuperamos el número generado al azar
    int num = (int) session.getAttribute("num");
    // 2. Creo la vista
    ModelAndView vista = new ModelAndView("juego.html");
    // 3. Le paso a la vista el mensaje
    if (intento < num) {
      vista.addObject("mensaje", "El número es muy chico");
    } else if (intento > num) {
      vista.addObject("mensaje", "El número es muy grande");
    } else {
      vista.addObject("mensaje", "FELICIDADES, GANASTE");
    }
    // 4. Retornamos la vista
    return vista;
  }
}
