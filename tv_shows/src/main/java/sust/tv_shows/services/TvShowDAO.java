package sust.tv_shows.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import sust.tv_shows.models.Canal;
import sust.tv_shows.models.CanalRepository;
import sust.tv_shows.models.TvShowRepository;
import sust.tv_shows.models.Tvshow;


@Component
public class TvShowDAO {

    @Autowired
    JdbcTemplate tpl;

    @Autowired
    TvShowRepository repo;

    @Autowired
    CanalRepository canalRepo;
    

    


      public void create(String titulo, String fecha, Long canal_id, String descripcion , TvShowRepository repo){
        
        // recuperamos el canal
       Canal c = canalRepo.findById(canal_id).get();

        Tvshow tvshow = new Tvshow();
       
        tvshow.setTitulo(titulo);
        tvshow.setFecha(fecha);
        tvshow.setCanal(c);
        tvshow.setDescripcion(descripcion);
        repo.save(tvshow);
      }

      public List<Tvshow> findAll() {
        List<Tvshow> shows = repo.findAll();
    
        for (Tvshow show : shows) {
          show.setNombreCanal(show.getNombreCanal());
          show.setCanal(null);
        }
        return shows;
      }

}
