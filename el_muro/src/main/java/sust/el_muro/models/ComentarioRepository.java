package sust.el_muro.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByMessage(Muro muro);
}
