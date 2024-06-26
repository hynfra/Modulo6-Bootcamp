package sust.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.websocket.server.PathParam;
import sust.demo.models.Tarea;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TareasController {

    ArrayList <Tarea> tareas = new ArrayList<Tarea>(Arrays.asList(
        new Tarea(1,"hacer la cama", false, new Date()),
        new Tarea(2,"pasear al perro", false, new Date())
    ));
    

    @GetMapping("/tareas")
    public ModelAndView paginaTareas(){
        System.out.println(tareas);
        //Creo un objeto del tipo ModelAndView
        ModelAndView vista = new ModelAndView("tareas");
        // le agrego contenido dinamico
        vista.addObject("titulo", "mi lista de tareas pendientes");
        vista.addObject("tareas", tareas);
        // devuelve el objeto
        return vista;
    }
    @PostMapping("/tareas")
    public RedirectView  agregarTarea(@RequestParam String texto) {
        int nuevo_id = 0;
        if(tareas.size() > 0){
            Tarea ultima = tareas.get(tareas.size() -1);
             nuevo_id = ultima.getId() + 1;
        }
        

        tareas.add(new Tarea(nuevo_id,texto,false, new Date()));
        System.out.println("Se agrego una nueva tarea");
        return new RedirectView ("http://localhost:8080/tareas");
        //return "redirect:/tareas";
    }
    @GetMapping("/tareas/check/{id}")
    public String chequearTarea(@PathVariable int id){
        System.out.println(""+id);
        for (Tarea tarea : tareas) {
            if(tarea.getId() == id){
                if(!tarea.isCompletada()){
                    tarea.setCompletada(true);
                }else{
                    tarea.setCompletada(false);
                }
            }
        }
        return "redirect:/tareas";
    }

   

    @GetMapping("/tareas/eliminar/{id}")
    public String eliminarTarea(@PathVariable int id){
        System.out.println("he llegado a eliminar" + id);
        tareas.removeIf(tarea -> tarea.getId() == id);
        return "redirect:/tareas";
        
    }
    

}
