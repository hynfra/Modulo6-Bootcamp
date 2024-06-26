package sust.libros_autores.services;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import sust.libros_autores.models.Autor;
import sust.libros_autores.models.Libro;

@Component
public class LibroDao {

    @Autowired
    JdbcTemplate tpl; // me devuelve la conexion descrita con el package de conexion y los datos de application-propterties

    public ArrayList<Libro> getAll() throws SQLException{
        Connection conn = tpl.getDataSource().getConnection();// llama a la conexion a bd usando el template
        //2. Creamos una consulta y la ejecutamos
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from libros");
        // creamos una lista de libros vacia
        ArrayList<Libro> libros = new ArrayList<Libro>();
        //vamos llenando la lista con los registros de la respuesta SQL
        while (rs.next()) {
            libros.add(new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("descripcion")

            ));
            
        }
        return libros;
    }

    public Libro getById(int id) throws SQLException{
        Connection conn = tpl.getDataSource().getConnection();// llama a la conexion a bd usando el template
        //2. Creamos una consulta y la ejecutamos
        //Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("select * from libros where id=?");
        stmt.setInt(1, id); // se pone la posicion del signo ? y luego la variable
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Libro(
            rs.getInt("id"),
            rs.getString("titulo"),
            rs.getString("descripcion")
        );
    }
    public ArrayList<Autor>  getAutoresNoRelacionados (int libro_id) throws SQLException{
        Connection conn = tpl.getDataSource().getConnection();// llama a la conexion a bd usando el template
        //2. Creamos una consulta y la ejecutamos
        //Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("select * from autores\r\n" + //
                        "where id not in \r\n" + //
                        "(select id from autores\r\n" + //
                        "join libroautor on autores.id = libroautor.libro_id where id = ?)");
        stmt.setInt(1, libro_id); // se pone la posicion del signo ? y luego la variable
        ResultSet rs = stmt.executeQuery();
        ArrayList<Autor> noRelacionados = new ArrayList<Autor>();
        while(rs.next()){

            noRelacionados.add(new Autor(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("notas")
            ));
        }
        

        return noRelacionados;
    }

    public void create (String titulo, String descripcion){
        String consulta = "INSERT INTO libros (titulo, descripcion) values (?,?)";
        tpl.update(consulta, titulo, descripcion);
    }
    public void addAutor(int libro_id, int autor_id) {
        String consulta = "insert into libroautor (libro_id, autor_id) values (?, ?)";
        tpl.update(consulta, libro_id, autor_id);
      }
    public void delete (int idLibro){
        String consulta = "DELETE FROM libros WHERE id = ?";
        tpl.update(consulta, idLibro);
    }
}
