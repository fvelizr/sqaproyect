package modelo;

import config.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvict
 */
public class productoDAO {

    PreparedStatement prod = null;
    Connection conn;
    conexion conex = new conexion();
    ResultSet rs = null;
    String sql = null;
    int r = 0;

    public List<producto> listar(String p) {
        String sql = "";

        if (!p.equals("")) {
            sql += "{call ConsultaProductoRecarga()}";
        } else {
            sql += "{call ConsultaProducto(0)}";
        }

        List<producto> lista = new ArrayList();
        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);
            rs = smt2.executeQuery();
            while (rs.next()) {
                producto pr = new producto();
                pr.setPro_id(rs.getString(1));
                pr.setPro_Nombre(rs.getString(2));
                pr.setPro_precio_compra(rs.getString(3));
                pr.setPro_stock(rs.getString(4));
                pr.setPro_marca(rs.getString(5));
                pr.setPro_fechaIngreso(rs.getString(6));
                pr.setPro_minimo(rs.getString(7));

                lista.add(pr);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(producto modelo) {

        int resp = 0;

        sql = "{call InsertarProducto(?," + modelo.getPro_precio_compra() + "," + modelo.getPro_stock() + ",?," + modelo.getPro_minimo() + ")}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getPro_Nombre());
            smt2.setString(2, modelo.getPro_marca());

            resp = smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

    public producto listarId(int Id) {
        producto pr = new producto();
        String sql = "{call ConsultaProducto(" + Id + ")}";
        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);
            rs = smt2.executeQuery();
            while (rs.next()) {

                pr.setPro_id(rs.getString(1));
                pr.setPro_Nombre(rs.getString(2));
                pr.setPro_precio_compra(rs.getString(3));
                pr.setPro_stock(rs.getString(4));
                pr.setPro_marca(rs.getString(5));
                pr.setPro_fechaIngreso(rs.getString(6));
                pr.setPro_minimo(rs.getString(7));
            }

        } catch (Exception e) {

        }
        return pr;
    }

    public int actualizar(producto modelo) {

        int resp = 0;

        sql = "{call ActualizaProducto(?," + modelo.getPro_precio_compra() + "," + modelo.getPro_stock() + ",?,?," + modelo.getPro_minimo() + "," + modelo.getPro_id() + ")}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getPro_Nombre());
            smt2.setString(2, modelo.getPro_marca());
            smt2.setString(3, modelo.getPro_fechaIngreso());

            smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;

    }

}
