package sust.libros_autores.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sust.libros_autores.models.Autor;

public class AutorMapper  implements RowMapper{
// mapper permite acceder  a la bd usando las columnas
// se usa esto en el metodo para recibir todos los autores
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Autor a = new Autor(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("notas")

        );
        return a;
    }

}
