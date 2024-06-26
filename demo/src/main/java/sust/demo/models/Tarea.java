package sust.demo.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// con lombok se puede escribir los constructores, getter y setter de esta forma
@Getter
@Setter
@AllArgsConstructor // constructor con todos los argumentos
@NoArgsConstructor // constructor sin parametros
@ToString
public class Tarea {

    private int id;
   private String texto;
    private boolean completada;
   private  Date fecha;


    

    
    
}
