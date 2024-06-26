package sust.el_muro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.Usuario;
import sust.el_muro.services.UserDAO;


@Controller
public class UserController {

    @Autowired
    UserDAO userDAO;
  @GetMapping("/login")
  public String loginPage() {
    return "login.html";
  }
  @PostMapping("/login")
  public String login(@RequestParam String username ,
   @RequestParam String password,  RedirectAttributes redAt,
   HttpSession session) {
     
    Usuario u = userDAO.login(username,password,session);
    if (u == null) {
      redAt.addFlashAttribute("mal", "Usuario inexistente o contraseña incorrecta");
      return "redirect:/login";
      
    }
    if (!u.getPassword().equals(password)) {
      redAt.addFlashAttribute("mal", "Usuario inexistente o contraseña incorrecta");
      return "redirect:/login";
    }
    return "redirect:/";
  }
 
  

  @GetMapping("/register")
  public String registerPage() {
    return "register.html";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username , @RequestParam String name,
   @RequestParam String password, @RequestParam String passwordConfirm, RedirectAttributes redAt,
   HttpSession session) {

    if (!password.equals(passwordConfirm)) {
      redAt.addFlashAttribute("mal", "Las contraseñas no coinciden");
      return "redirect:/register";
    }
    
    boolean resultado = userDAO.create(username, name, password,passwordConfirm,redAt,session);
    if(resultado){
      return "redirect:/";
    }

    return "redirect:/register";

  }

  @GetMapping("/logout")
    public String logout( HttpSession session) {

       session.setAttribute("usuario", null);
       return "redirect:/login";
       
    }
}
