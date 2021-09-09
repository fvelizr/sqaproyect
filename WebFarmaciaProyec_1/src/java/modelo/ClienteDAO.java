package modelo;

import config.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    PreparedStatement cli = null;
    Connection conn;
    conexion conex = new conexion();
    ResultSet rs = null;
    String sql = null;
    int r = 0;

    public List<Cliente> listar() {
        sql = "{call ConsultaCliente(0)}";
        List<Cliente> lista = new ArrayList();
        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);
            rs = smt2.executeQuery();
            while (rs.next()) {
                Cliente clien = new Cliente();
                clien.setCl_id(rs.getString(1));
                clien.setCl_Nombre(rs.getString(2));
                clien.setCl_nit(rs.getString(3));
                clien.setCl_direccion(rs.getString(4));
                clien.setCl_telefono(rs.getString(5));

                lista.add(clien);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(Cliente modelo) {

       int resp = 0;

        sql = "{call InsertarCliente(?,?,?,"+modelo.getCl_telefono()+")}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getCl_Nombre());
            smt2.setString(2, modelo.getCl_nit());
            smt2.setString(3, modelo.getCl_direccion());
      
            resp = smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

    public Cliente listarId(int Id) {
        Cliente clie = new Cliente();
       sql = "{call ConsultaCliente(" + Id + ")}";
        try {
            conn = conex.getConnection();
            //smt = conn.prepareStatement(sql);
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            rs = smt2.executeQuery();
            while (rs.next()) {
                clie.setCl_id(rs.getString(1));
                clie.setCl_Nombre(rs.getString(2));
                clie.setCl_nit(rs.getString(3));
                clie.setCl_direccion(rs.getString(4));
                clie.setCl_telefono(rs.getString(5));
            }

        } catch (Exception e) {

        }
        return clie;
    }

    public int actualizar(Cliente modelo) {

        sql = "{call ActualizaCliente(?,?,?,"+modelo.getCl_telefono()+","+modelo.getCl_id()+")}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getCl_Nombre());
            smt2.setString(2, modelo.getCl_nit());
            smt2.setString(3, modelo.getCl_direccion());
             
            smt2.executeUpdate();

        } catch (Exception e) {

        }
        return r;

    }

    public Cliente buscar(String Nit) {
        Cliente clie = new Cliente();
        sql = "{call ConsultaEmpleadoNit(" + Nit + ")}";
        try {
            conn = conex.getConnection();
            //smt = conn.prepareStatement(sql);
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            rs = smt2.executeQuery();
            while (rs.next()) {
                clie.setCl_id(rs.getString(1));
                clie.setCl_Nombre(rs.getString(2));
                clie.setCl_nit(rs.getString(3));
                clie.setCl_direccion(rs.getString(4));
                clie.setCl_telefono(rs.getString(5));
            }
        } catch (Exception e) {

        }
        return clie;
    }

}
