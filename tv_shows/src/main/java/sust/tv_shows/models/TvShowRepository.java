package sust.tv_shows.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository  extends JpaRepository<Tvshow,Long>{

    //save (show s) ; -> Guarda la solicitud
    // findAll(); -> List<Show> lista de shows
    


}
