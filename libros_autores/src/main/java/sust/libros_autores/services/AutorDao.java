package sust.libros_autores.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import sust.libros_autores.models.Autor;
import sust.libros_autores.models.Libro;



@Component
public class AutorDao {

    @Autowired
    JdbcTemplate tpl;

    public List<Autor> getAll(){

        return tpl.query("select * from autores", new AutorMapper());
        
    }

    public void create(String nombre, String apellido, String notas) {
        String consulta = "insert into autores (nombre, apellido, notas) values (?, ?, ?)";
        tpl.update(consulta, nombre, apellido, notas);
      }

    public void delete(int id) {
        String consulta = "delete from autores where id=?";
        tpl.update(consulta, id);
    }

     public Autor getById(int id) throws SQLException{
        Connection conn = tpl.getDataSource().getConnection();// llama a la conexion a bd usando el template
        //2. Creamos una consulta y la ejecutamos
        //Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("select * from autores where id=?");
        stmt.setInt(1, id); // se pone la posicion del signo ? y luego la variable
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Autor(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("notas")
        );
    }

}
