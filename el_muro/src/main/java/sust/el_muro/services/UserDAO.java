package sust.el_muro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.UserRepository;
import sust.el_muro.models.Usuario;

@Component
public class UserDAO {

    @Autowired
    UserRepository userRepo;

    public boolean create(String username, String name, String password, String passwordConfirm , RedirectAttributes redAt, HttpSession session){

    
        Usuario u = new Usuario();
        u.setName(name);
        u.setUsername(username);
        u.setPassword(password);
        userRepo.save(u);

         // 3. Lo guardamos en base de datos
    try {
        userRepo.save(u);
      } catch (Exception e) {
        redAt.addFlashAttribute("mal", "Ese nombre de usuario ya existe");
        return false;
      }
      // 4. Creamos la sesión
      session.setAttribute("user", u);

        return true;

        

    }

    public Usuario login(String username,  String password, HttpSession session ){
        
        Usuario u = userRepo.findByUsername(username);

        
          session.setAttribute("user", u);
        
        return u;

        

    }

}
