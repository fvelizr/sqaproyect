package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.empleado;
import modelo.empleadoDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlEmpleado"})
public class CtrlEmpleado extends HttpServlet {

    empleadoDAO edao = new empleadoDAO();
    empleado em = new empleado();
    int ide;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("accion");

        if (action.equals("Listar")) {
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("empleado.jsp");
            request.setAttribute("empleado", lista);
            req.include(request, response);
        }

        if (action.equals("Guardar")) {
            String NombreEmpleado = request.getParameter("txtNombre");
            String DpiEmpleado = request.getParameter("txtDpi");
            String CorreoEmpleado = request.getParameter("txtCorreo");
            String DireccionEmpleado = request.getParameter("txtDireccion");
            String TelefonoEmpleado = request.getParameter("txtTelefono");
            String Fecha_nacimientoEmpleado = request.getParameter("txtFechaNacimiento");
            String PuestoEmpleado = request.getParameter("txtPuesto");
            String Fecha_ingresoEmpleado = request.getParameter("txtFechaIngreso");
            String EstadoEmpleado = request.getParameter("txtEstado");
            String UserEmpleado = request.getParameter("txtUsuario");
            em.setNombreEmpleado(NombreEmpleado);
            em.setDpiEmpleado(DpiEmpleado);
            em.setCorreoEmpleado(CorreoEmpleado);
            em.setDireccionEmpleado(DireccionEmpleado);
            em.setTelefonoEmpleado(TelefonoEmpleado);
            em.setFecha_nacimientoEmpleado(Fecha_nacimientoEmpleado);
            em.setPuestoEmpleado(PuestoEmpleado);
            em.setFecha_ingresoEmpleado(Fecha_ingresoEmpleado);
            em.setEstadoEmpleado(EstadoEmpleado);
            em.setUserEmpleado(UserEmpleado);

            edao.agregar(em);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("empleado.jsp");
            request.setAttribute("empleado", lista);
            req.include(request, response);
        }

        if (action.equals("Actualizar")) {
            String IdEmpleado = request.getParameter("txtId");
            String NombreEmpleado = request.getParameter("txtNombre");
            String DpiEmpleado = request.getParameter("txtDpi");
            String CorreoEmpleado = request.getParameter("txtCorreo");
            String DireccionEmpleado = request.getParameter("txtDireccion");
            String TelefonoEmpleado = request.getParameter("txtTelefono");
            String Fecha_nacimientoEmpleado = request.getParameter("txtFechaNacimiento");
            String PuestoEmpleado = request.getParameter("txtPuesto");
            String Fecha_ingresoEmpleado = request.getParameter("txtFechaIngreso");
            String EstadoEmpleado = request.getParameter("txtEstado");

            em.setIdEmpleado(IdEmpleado);
            em.setNombreEmpleado(NombreEmpleado);
            em.setDpiEmpleado(DpiEmpleado);
            em.setCorreoEmpleado(CorreoEmpleado);
            em.setDireccionEmpleado(DireccionEmpleado);
            em.setTelefonoEmpleado(TelefonoEmpleado);
            em.setFecha_nacimientoEmpleado(Fecha_nacimientoEmpleado);
            em.setPuestoEmpleado(PuestoEmpleado);
            em.setFecha_ingresoEmpleado(Fecha_ingresoEmpleado);
            em.setEstadoEmpleado(EstadoEmpleado);

            edao.actualizar(em);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("empleado.jsp");
            request.setAttribute("empleado", lista);
            req.include(request, response);
        }

        if (action.equals("Editar")) {
            List<empleado> lista = buscar();
            String IdEmpleado = request.getParameter("Id");
            int val = 0;

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdEmpleado().equals(IdEmpleado)) {
                    val = i;
                }
            }

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("empleado.jsp");
            request.setAttribute("emp", lista.get(val));
            request.setAttribute("empleado", lista);
            req.include(request, response);

        }
        
        
        
        
        
        
        

    }

    private List buscar() {
        List lista = edao.listar();
        return lista;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(CtrlEmpleado.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(CtrlEmpleado.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
