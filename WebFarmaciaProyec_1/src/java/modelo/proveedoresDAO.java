package modelo;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvict
 */
public class proveedoresDAO {

    PreparedStatement pro = null;
    Connection conn;
    conexion conex = new conexion();
    ResultSet rs = null;
    String sql = null;
    int r = 0;

    public List<proveedores> listar() {
        String sql = "select * from proveedor";
        List<proveedores> lista = new ArrayList();
        try {
            conn = conex.getConnection();
            pro = conn.prepareStatement(sql);
            rs = pro.executeQuery();
            while (rs.next()) {
                proveedores pr = new proveedores();
                pr.setProv_id(rs.getString(1));
                pr.setProv_Nombre(rs.getString(2));
                pr.setProv_nit(rs.getString(3));
                pr.setProv_direccion(rs.getString(4));
                pr.setProv_telefono(rs.getString(5));
                pr.setProv_fechaIngreso(rs.getString(6));

                lista.add(pr);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(proveedores modelo) {

        int resp = 0;
        sql = "insert into proveedor (prov_id,prov_Nombre,prov_nit,prov_direccion,prov_telefono,prov_fechaIngreso)"
                + "values ((SELECT IFNULL(MAX(prov_id), 0)+1 FROM proveedor u),?,?,?,?,?)";

        try {
            conn = conex.getConnection();
            pro = conn.prepareStatement(sql);

            pro.setString(1, modelo.getProv_Nombre());
            pro.setString(2, modelo.getProv_nit());
            pro.setString(3, modelo.getProv_direccion());
            pro.setString(4, modelo.getProv_telefono());
            pro.setString(5, modelo.getProv_fechaIngreso());

            resp = pro.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

    public proveedores listarId(String Id, String op) {
        proveedores pr = new proveedores();
        if (op.equals("C")) {
            sql = "select * from proveedor where prov_id=" + Integer.parseInt(Id);
        } else {
            sql = "select * from proveedor where prov_nit=" + Id;
        }

        try {
            conn = conex.getConnection();
            pro = conn.prepareStatement(sql);
            rs = pro.executeQuery();
            while (rs.next()) {
                pr.setProv_id(rs.getString(1));
                pr.setProv_Nombre(rs.getString(2));
                pr.setProv_nit(rs.getString(3));
                pr.setProv_direccion(rs.getString(4));
                pr.setProv_telefono(rs.getString(5));
                pr.setProv_fechaIngreso(rs.getString(6));

            }

        } catch (Exception e) {

        }
        return pr;
    }

    public int actualizar(proveedores modelo) {

        sql = "update  proveedor set prov_Nombre=?,prov_nit=?,prov_direccion=?,prov_telefono=" + modelo.getProv_telefono() + ",prov_fechaIngreso=? where prov_id=?";
        try {
            conn = conex.getConnection();
            pro = conn.prepareStatement(sql);

            pro.setString(1, modelo.getProv_Nombre());
            pro.setString(2, modelo.getProv_nit());
            pro.setString(3, modelo.getProv_direccion());
            // pro.setString(4, modelo.getProv_telefono());
            pro.setString(4, modelo.getProv_fechaIngreso());
            pro.setString(5, modelo.getProv_id());

            pro.executeUpdate();

        } catch (Exception e) {

        }
        return r;

    }

}
