package sust.el_muro.models;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

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

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL) // el nombre hace referencia al puesto en JoinColumn (Usuario usuario) puesto en Muro.java
     List<Muro> messages;

}
