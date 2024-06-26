package sust.tv_shows.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "canales")
public class Canal {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @OneToMany(mappedBy = "canal") // esto hace referencia al nombre de la viariable generada en Tvshow
    List<Tvshow> tvshow;

}
