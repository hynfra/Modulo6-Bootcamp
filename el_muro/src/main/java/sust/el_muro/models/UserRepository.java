package sust.el_muro.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    
    Usuario findByUsername(String username);
}
