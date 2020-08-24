package com.javier.psprogram.controller;

import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class Controller {


    @PostMapping("/articulos")
    public String postArticulos(@PathParam("id") String id, @PathParam("nombre") String nombre, @PathParam("descripcion") String descripcion){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "insert into articulos values (" + id + ", '" + nombre + "', '" + descripcion + "')";
            getStatement.executeUpdate(sql);
            resString = "Articulo agregado";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    @GetMapping("/articulos")
    public String getArticulos(){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            ResultSet res = getStatement.executeQuery("select * from articulos");
            while (res.next()){
                resString = resString + res.getString("id") + ", " + res.getString("nombre") + ", " + res.getString("descripcion") + '\n';
            }
            return resString;
        }
        catch (Exception exc) {
            return exc.toString();
        }
    }

    @PutMapping("/articulos")
    public String putArticulos(@PathParam("id") String id, @PathParam("nombre") String nombre, @PathParam("descripcion") String descripcion){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "update articulos set nombre =" + nombre + ", descripcion = " + descripcion + "where id = " + id;
            getStatement.executeUpdate(sql);
            resString = "Articulo actualizado";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    @DeleteMapping("/articulos")
    public String deleteArticulos(@PathParam("id") String id){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "delete from articulos where id =" + id;
            getStatement.executeUpdate(sql);
            resString = "Articulo eliminado";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PostMapping("/ordenes")
    public String postOrdenes(@PathParam("id") String id, @PathParam("orden_id") String orden_id, @PathParam("nombre") String nombre, @PathParam("descripcion") String descripcion, @PathParam("fecha") String fecha){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "insert into ordenes values (" + id + ", " + orden_id + ", '" + nombre + "', '" + descripcion + "', STR_TO_DATE('" + fecha + "', '%d-%m-%Y'))";
            getStatement.executeUpdate(sql);
            resString = "Orden registrada";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    @GetMapping("/ordenes")
    public String getOrdenes(@PathParam("fechaInicial") String fechaInicial, @PathParam("fechaInicial") String fechaFinal){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "select * from ordenes";
            if (fechaInicial != null) {
                if (fechaFinal != null) {
                    sql = sql + " where fecha >= STR_TO_DATE('" + fechaInicial + "', '%d-%m-%Y') and fecha <= STR_TO_DATE('" + fechaFinal + "', '%d-%m-%Y')";
                } else {
                    sql = sql + " where fecha >= STR_TO_DATE('" + fechaInicial + "', '%d-%m-%Y')";
                }
            }
            ResultSet res = getStatement.executeQuery(sql);
            while (res.next()){
                resString = resString + res.getString("id") + ", " + res.getString("orden_id") + ", " + res.getString("nombre") + ", " + res.getString("descripcion") + ", " + res.getString("fecha") + '\n';
            }
            return resString;
        }
        catch (Exception exc) {
            return exc.toString();
        }
    }

    @PutMapping("/ordenes")
    public String putOrdenes(@PathParam("id") String id, @PathParam("orden_id") String orden_id, @PathParam("nombre") String nombre, @PathParam("descripcion") String descripcion, @PathParam("fecha") String fecha){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "update ordenes set orden_id = " + orden_id + ", nombre = '" + nombre + "', descripcion = '" + descripcion + "', fecha = STR_TO_DATE('" + fecha + "', '%d-%m-%Y') where id = " + id;
            getStatement.executeUpdate(sql);
            resString = "Orden actualizada";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    @DeleteMapping("/ordenes")
    public String deleteOrden(@PathParam("id") String id){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            String sql = "delete from ordenes where id =" + id;
            getStatement.executeUpdate(sql);
            resString = "Orden eliminada";
            return resString;
        }
        catch (Exception exc) {
            return exc.getMessage();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping("/frecuentes")
    public String getArticulos(@PathParam("num") String num){
        try{
            String resString = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","dj3105dj");
            Statement getStatement = con.createStatement();
            ResultSet res = getStatement.executeQuery("select nombre, count(*) as cantidad " +
                                                            "from ordenes " +
                                                            "group by nombre " +
                                                            "order by 2 desc " +
                                                            "limit " + num);
            while (res.next()){
                resString = resString + res.getString("nombre") + ", " + res.getString("cantidad") + '\n';
            }
            return resString;
        }
        catch (Exception exc) {
            return exc.toString();
        }
    }

}
