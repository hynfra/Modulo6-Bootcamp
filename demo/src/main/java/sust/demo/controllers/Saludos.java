package sust.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Saludos {

/* 
    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String hola(){
        return "<h2>hola mundo</h2>";
    }

    */

    //contenido dinamico en el path
    @RequestMapping(value="/hola2/{nombre}", method= RequestMethod.GET)
    @ResponseBody
    public String hola2(@PathVariable String nombre) {
        return "<h2>hola %s</h2>".formatted(nombre);
    }

    //contenido dinamico en el path
    @RequestMapping(value="/tablero", method= RequestMethod.GET)
    @ResponseBody
    public String tablero(@RequestParam int fila, @RequestParam int columna) {
        String celdas = "";
        for(int i = 1 ; i <= columna ; i++){
            celdas += "<div class = \"row\">";
            for(int j = 1; j <= fila ; j++){

                celdas += "<div>Fila %s Columna %s".formatted(i,j);
                
            }
            celdas +="</div>";
            
        }
        return celdas;
    }
    

}
