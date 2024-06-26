package sust.el_muro.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(columnDefinition = "TEXT" , nullable = false) // si se define como text permite tener caracteres indefinido
    private String comentario;

    @Column(updatable = false)
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


    @ManyToOne
    @JoinColumn(name="muro_id",
    referencedColumnName = "id")
    Muro muro;

    @ManyToOne
    @JoinColumn(name="usuario_id",// se hace referencia a como se llamara la llave foranea 
    referencedColumnName = "id")
    Usuario usuario;

    String nombreCreador;

}
