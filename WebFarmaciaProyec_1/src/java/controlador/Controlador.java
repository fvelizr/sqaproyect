package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.empleado;
import modelo.empleadoDAO;

/**
 *
 * @author jvict
 */
public class Controlador extends HttpServlet {

    empleadoDAO edao = new empleadoDAO();
    empleado em = new empleado();
    int ide;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("accion");
        PrintWriter out = response.getWriter();
        String opcion = request.getParameter("opcion");

        HttpSession session = request.getSession();

        if (action.equals("Ingresar")) {
            String User = request.getParameter("txtuser");
            String Pass = request.getParameter("txtpass");
            em = edao.getBuscarEmpleado(User, Pass);
            //edao.Crear(em);

            if (em != null) {
                session.setAttribute("idEmp", em.getIdEmpleado());
                session.setAttribute("nomEmp", em.getNombreEmpleado());
                session.setAttribute("usrEmp", em.getUserEmpleado());

                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher req = request.getRequestDispatcher("principal.jsp");

                req.include(request, response);

            } else {
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher req = request.getRequestDispatcher("index.jsp");
                out.println("<script type=\"text/javascript\"> alert('USUARIO O CLAVE INVALIDOS');</script>");
                req.include(request, response);
            }

            //}
        }

        if (action.equals("Inicio")) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("NuevaContraseña.jsp");
            req.include(request, response);

        }

        if (action.equals("Cambio")) {
            String pass = request.getParameter("pass");
            String pass2 = request.getParameter("pass2");

            if (pass.equals(pass2)) {
                edao.CambioClave(session.getAttribute("idEmp").toString(), pass);
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher req = request.getRequestDispatcher("NuevaContraseña.jsp");
                out.println("<script type=\"text/javascript\"> alert('CAMBIO DE CLAVE REALIZADO CON EXITO');</script>");
                req.include(request, response);
            } else {
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher req = request.getRequestDispatcher("NuevaContraseña.jsp");
                out.println("<script type=\"text/javascript\"> alert('LAS CLAVES NO SON LAS MISMAS');</script>");
                req.include(request, response);
            }

        }

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
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
