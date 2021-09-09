package modelo;

import config.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class empleadoDAO {

    PreparedStatement smt = null;
    Connection conn;
    conexion conex = new conexion();
    String sql = null;
    ResultSet result = null;
    int r = 0;

    public empleado getBuscarEmpleado(String Usuario, String Clave) throws SQLException {
        conn = conex.getConnection();
        empleado Busqueda = null;

        try {
            sql = "{call AutenticacionUsuario(?,?)}";

            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);
            smt2.setString(1, Usuario);
            smt2.setString(2, Clave);

            result = smt2.executeQuery();

            while (result.next()) {
                Busqueda = new empleado();
                Busqueda.setNombreEmpleado(result.getString("NombreEmpleado"));
                Busqueda.setUserEmpleado(result.getString("UserEmpleado"));
                Busqueda.setPassEmpleado(result.getString("PassEmpleado"));
                Busqueda.setIdEmpleado(result.getString("idEmpleado"));

            }
        } catch (Exception e) {
        } finally {
            if (smt != null) {
                smt.close();
                smt = null;
            }
            if (result != null) {
                result.close();
                result = null;
            }
            if (conn != null) {
                conn.close();
                conex.cerrar();
            }
        }
        return Busqueda;
    }

    public List<empleado> listar() {
        sql = "{call ConsultaEmpleado(0)}";

        List<empleado> lista = new ArrayList();
        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            result = smt2.executeQuery();
            while (result.next()) {
                empleado em = new empleado();
                em.setIdEmpleado(result.getString(1));
                em.setNombreEmpleado(result.getString(2));
                em.setDpiEmpleado(result.getString(3));
                em.setCorreoEmpleado(result.getString(4));
                em.setDireccionEmpleado(result.getString(5));
                em.setTelefonoEmpleado(result.getString(6));
                em.setFecha_nacimientoEmpleado(result.getString(7));
                em.setPuestoEmpleado(result.getString(8));
                em.setFecha_ingresoEmpleado(result.getString(9));
                em.setEstadoEmpleado(result.getString(10));
                em.setUserEmpleado(result.getString(11));

                lista.add(em);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(empleado modelo) {

        int resp = 0;

        sql = "{call InsertarEmpleado(?,?,?,?,?,?,?,?,?)}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getNombreEmpleado());
            smt2.setString(2, modelo.getDpiEmpleado());

            smt2.setString(3, modelo.getCorreoEmpleado());
            smt2.setString(4, modelo.getDireccionEmpleado());
            smt2.setString(5, modelo.getTelefonoEmpleado());

            smt2.setString(6, modelo.getFecha_nacimientoEmpleado());
            smt2.setString(7, modelo.getPuestoEmpleado());
            smt2.setString(8, modelo.getFecha_ingresoEmpleado());
            smt2.setString(9, modelo.getUserEmpleado());

            resp = smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

    public empleado listarId(int Id) {
        empleado emp = new empleado();
        //sql = "select * from empleado where IdEmpleado=" + Id;
        sql = "{call ConsultaEmpleado(" + Id + ")}";
        try {
            conn = conex.getConnection();
            //smt = conn.prepareStatement(sql);
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            result = smt2.executeQuery();
            while (result.next()) {
                emp.setIdEmpleado(result.getString(1));
                emp.setNombreEmpleado(result.getString(2));
                emp.setDpiEmpleado(result.getString(3));
                emp.setCorreoEmpleado(result.getString(4));
                emp.setDireccionEmpleado(result.getString(5));
                emp.setTelefonoEmpleado(result.getString(6));
                emp.setFecha_nacimientoEmpleado(result.getString(7));
                emp.setPuestoEmpleado(result.getString(8));
                emp.setFecha_ingresoEmpleado(result.getString(9));
                emp.setEstadoEmpleado(result.getString(10));
                emp.setUserEmpleado(result.getString(11));
                emp.setPassEmpleado(result.getString(12));
            }

        } catch (Exception e) {

        }
        return emp;
    }

    public int actualizar(empleado modelo) {
       sql = "{call ActualizaEmpleado(?,?,?,?,?,?,?,?,?,?)}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getNombreEmpleado());
            smt2.setString(2, modelo.getDpiEmpleado());
            smt2.setString(3, modelo.getCorreoEmpleado());
            smt2.setString(4, modelo.getDireccionEmpleado());
            smt2.setString(5, modelo.getTelefonoEmpleado());
            smt2.setString(6, modelo.getFecha_nacimientoEmpleado());
            smt2.setString(7, modelo.getPuestoEmpleado());
            smt2.setString(8, modelo.getFecha_ingresoEmpleado());
            smt2.setString(9, modelo.getEstadoEmpleado());
            smt2.setString(10, modelo.getIdEmpleado());
            smt2.executeUpdate();

        } catch (Exception e) {

        }
        return r;

    }

    public int CambioClave(String usuario, String clave) {

        int resp = 0;

        sql = "{call CambioClave(?,?)}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, usuario);
            smt2.setString(2, clave);

            resp = smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

}
