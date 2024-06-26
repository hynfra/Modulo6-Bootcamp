package sust.el_muro.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity // este tag sirve para que se genere la tabla
@Table(name = "muros")
public class Muro {

     @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String publicacion;

    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creado_en;

    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date actualizado_en;

    // al momento de crear un mensaje en la base de datos, se fija automaticamente la fecha actual
    @PrePersist
    public void onCreate(){
        this.creado_en = new Date();
    }

    @PreUpdate
    public void onUpdate(){
        this.actualizado_en = new Date();
    }
    
    @ManyToOne // se establece el tipo de relacion entre las tablas
  @JoinColumn(name = "user_id", referencedColumnName = "id") // se asigna un nombre y luego la columna a la que hace referencia dentro de la bd (columna id)
  Usuario usuario; // se usa en la relacion de Usuario 

  @OneToMany(mappedBy = "muro")// se hace referencia al nombre de la variable de la clase Comentario
  List <Comentario> comentarios;

  //atributos no columnas
  String nombreCreador;

}
    
