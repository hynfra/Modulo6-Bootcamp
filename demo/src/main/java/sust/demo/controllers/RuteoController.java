package sust.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RuteoController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String root(){
        return "redirect:/blogs";
    }

    @RequestMapping(value="/blogs", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "<a href=\"http://localhost:8080/blogs/new\">nuevo</a>\r\n" + //
                        "<a href=\"http://localhost:8080/blogs/create\">crear</a>";
    }

    @RequestMapping(value="/blogs/new", method = RequestMethod.GET)
    @ResponseBody
    public String nuevo(){
        return "<a href=\"http://localhost:8080/blogs/new\">crear nuevo formulario</a>\r\n";
    }

    @RequestMapping(value="/blogs/create", method = RequestMethod.GET)
    public String create(){
        return "redirect:/";
    }

    @RequestMapping(value="/blogs/{number}", method= RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable int number) {
        return "<a href=\"http://localhost:8080/blogs/new\">placeholder para mostrar el numero %s</a>\r\n".formatted(number);
    }
    @RequestMapping(value="/blogs/{number}/edit", method= RequestMethod.GET)
    @ResponseBody
    public String edit(@PathVariable int number) {
        return "<h2>placeholder para editar el blog numero %s</h2>".formatted(number);
    }

    @RequestMapping(value="/blogs/{number}/delete", method = RequestMethod.GET)
    public String destroy(){
        return "redirect:/blogs";
    }
    

    @GetMapping("/blogs/json")
    @ResponseBody
    public Object jsonResponse() {
        Map<String,Object> yeison= new HashMap<>();
        yeison.put("id", 1);
        yeison.put("titulo", "Esto es el titulo");

        return yeison;
    }

}
