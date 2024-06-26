package sust.libros_autores.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class SesionController {

    @GetMapping(value="/count")
    public ModelAndView count(HttpSession session){

        Integer count = (Integer) session.getAttribute("count");
        if(count == null){
            count = 1;
        }else{
            count++;
        }
        session.setAttribute("count",count);

        ModelAndView view = new ModelAndView("count.html");

        view.addObject("count", count);

        return view;
    }


    @GetMapping(value="/count/reset")
    public String reset(HttpSession session){
        session.setAttribute("count", null);

        return "redirect:/count";

    }


}
