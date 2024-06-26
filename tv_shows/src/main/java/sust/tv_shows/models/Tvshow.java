package sust.tv_shows.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shows")
public class Tvshow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column
    private String descripcion;
    @Column(nullable = false)
    private String fecha;

    private String nombreCanal;

    @ManyToOne
    @JoinColumn(name="canal_id",
     referencedColumnName = "id") // se debe agregar esto ya que sino se genera una relacionn de muchos a muchos con una tabla intermedia
    Canal canal; //el nombre de la instancia (canal) deberia estar en el mappedby del models Canal

}
