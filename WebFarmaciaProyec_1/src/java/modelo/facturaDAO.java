package modelo;

import config.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class facturaDAO {

    PreparedStatement smt = null;
    Connection conn;
    conexion conex = new conexion();
    String sql = null;
    ResultSet result = null;
    int r = 0;

    public List<DetalleFacturaMd> BuscaDetallesFactura() throws SQLException {
        PreparedStatement smt = null;
        Connection conn;
        conexion conex = new conexion();
        conn = conex.Conexion();
        ResultSet result = null;

        List<DetalleFacturaMd> lista = new ArrayList<DetalleFacturaMd>();

        DetalleFacturaMd Buscar = null;

   
        try {
            String sql = "{call ConsultaDetalles()}";

            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            result = smt2.executeQuery();

            while (result.next()) {
                Buscar = new DetalleFacturaMd();

                Buscar.setDetProductoId(result.getString(1));
                Buscar.setDetProductoDescripcion(result.getString(2));
                Buscar.setDetProductoMarca(result.getString(3));
                Buscar.setDetProductoPrecioVenta(result.getString(4));
                Buscar.setProductoCantidad(result.getString(5));

                lista.add(Buscar);

            }
        } catch (Exception e) {
        } finally {
            if (smt != null) {
                smt.close();
            }
           if (result != null) {
                result.close();
                result = null;
            }
            if (conn != null) {
                conex.cerrar();
                conn.close();
                conn = null;
            }
        }
        return lista;
    }

    public FacturaMd BuscarEncabezadoFactura() throws SQLException {
        PreparedStatement smt = null;
        Connection conn;
        conexion conex = new conexion();
        conn = conex.Conexion();
        String sql = "";
        ResultSet result = null;
        FacturaMd Busqueda = null;

        try {
            sql = "{call ConsultaEncabezadoFactura()}";

            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            result = smt2.executeQuery();

            while (result.next()) {
                Busqueda = new FacturaMd();
                Busqueda.setFacturaNumero(result.getString(1));
                Busqueda.setFacturaFechaEmision(result.getString(2));
                Busqueda.setFacturaEstado(result.getString(3));
                Busqueda.setFacturaTipoPago(result.getString(4));
                Busqueda.setFacturaTotal(result.getString(5));

                Busqueda.setFacturaEmpleadoId(result.getString(6));
                Busqueda.setFacturaEmpleadoNombre(result.getString(7));
                Busqueda.setFacturaUsuarioEmite(result.getString(8));

                Busqueda.setFacturaClienteId(result.getString(9));
                Busqueda.setFacturaClienteNombre(result.getString(10));
                Busqueda.setFacturaClienteNit(result.getString(11));
                Busqueda.setFacturaClienteDireccion(result.getString(12));
                Busqueda.setFacturaRecibido(result.getString(13));
                Busqueda.setFacturaCambio(result.getString(14));

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

    public List<FacturaMd> CorteCaja(String fecha, String empleado) throws SQLException {
        PreparedStatement smt = null;
        Connection conn;
        conexion conex = new conexion();
        conn = conex.Conexion();
        String sql = null;
        ResultSet result = null;
        FacturaMd Busqueda = null;

        List<FacturaMd> lista = new ArrayList<FacturaMd>();

        try {
            sql = "{call ConsultaCorteCaja(?," + empleado + ")}";

            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);
            smt2.setString(1, fecha);
            
            result = smt2.executeQuery();
            while (result.next()) {
                Busqueda = new FacturaMd();
                Busqueda.setFacturaNumero(result.getString(1));
                Busqueda.setFacturaFechaEmision(result.getString(2));
                Busqueda.setFacturaEstado(result.getString(3));
                Busqueda.setFacturaTipoPago(result.getString(4));
                Busqueda.setFacturaTotal(result.getString(5));

                Busqueda.setFacturaEmpleadoId(result.getString(6));
                Busqueda.setFacturaEmpleadoNombre(result.getString(7));
                Busqueda.setFacturaUsuarioEmite(result.getString(8));

                Busqueda.setFacturaClienteId(result.getString(9));
                Busqueda.setFacturaClienteNombre(result.getString(10));
                Busqueda.setFacturaClienteNit(result.getString(11));
                Busqueda.setFacturaClienteDireccion(result.getString(12));
                lista.add(Busqueda);
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
        return lista;
    }

    public FacturaMd BuscarEncabezadoCotizacion() throws SQLException {
        PreparedStatement smt = null;
        Connection conn;
        conexion conex = new conexion();
        conn = conex.Conexion();
        String sql = null;
        ResultSet result = null;
        FacturaMd Busqueda = null;

        try {
            sql = "{call ConsultaEncabezadoCotizacion()}";

            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            result = smt2.executeQuery();

            while (result.next()) {
                Busqueda = new FacturaMd();
                Busqueda.setFacturaNumero(result.getString(1));

                Busqueda.setFacturaEmpleadoId(result.getString(2));
                Busqueda.setFacturaEmpleadoNombre(result.getString(3));

                Busqueda.setFacturaClienteId(result.getString(4));
                Busqueda.setFacturaClienteNombre(result.getString(5));
                Busqueda.setFacturaClienteNit(result.getString(6));
                Busqueda.setFacturaClienteDireccion(result.getString(7));

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

    public int InsertarEncabezado(FacturaMd modelo, String tipo) {

        int resp = 0;

        if (tipo.equals("F")) {
            sql = "{call InsertaEncabezadoFactura(?," + modelo.getFacturaEmpleadoId() + "," + modelo.getFacturaTotal() + ",?,?," + modelo.getFacturaRecibido() + ")}";
        } else {
            sql = "{call InsertaEncabezadoCotizacion(?," + modelo.getFacturaEmpleadoId() + ")}";
        }

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

            smt2.setString(1, modelo.getFacturaClienteId());

            if (tipo.equals("F")) {
                smt2.setString(2, modelo.getFacturaTipoPago());
                smt2.setString(3, modelo.getFacturaUsuarioEmite());
            }

            resp = smt2.executeUpdate();

        } catch (Exception e) {

        }
        return resp;
    }

    public int InsertaDetalle(producto modelo,String tipo) {

        int resp = 0;

        sql = "{call InsertaDetallesPrefactura(" + modelo.getPro_id() + "," + modelo.getPro_cantidad() + "," + modelo.getPro_precio_compra() + ",'"+tipo+"')}";

        try {
            conn = conex.getConnection();
            CallableStatement smt2 = (CallableStatement) conn.prepareCall(sql);

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

    

}
