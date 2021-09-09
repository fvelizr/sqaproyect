package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.FacturaMd;
import modelo.facturaDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlReportes"})
public class CtrlReportes extends HttpServlet {

    Cliente clie = new Cliente();
    ClienteDAO edao = new ClienteDAO();
    int ide;
    Reportes rep = new Reportes();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("accion");
        HttpSession session = request.getSession();


        if (action.equals("Corte")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaEmpleadoId(request.getParameter("CodigoEmpleado"));
            enc.setFacturaEmpleadoNombre(request.getParameter("NombreEmpleado"));
            enc.setFacturaUsuarioEmite(request.getParameter("UsuarioEmpleado"));
            
            enc.setFacturaFechaEmision(request.getParameter("fecha"));           
              
            String strName = getServletContext().getRealPath("rpt");

            byte[] bytes = null;

            try {
                bytes = rep.CorteCaja(enc, strName);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();
            outputStream.close();

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("reportes.jsp");
            req.include(request, response);
        }
        
        if (action.equals("Inventario")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaEmpleadoId(session.getAttribute("idEmp").toString());
            enc.setFacturaEmpleadoNombre(session.getAttribute("nomEmp").toString());
            enc.setFacturaUsuarioEmite(session.getAttribute("usrEmp").toString());
              
            String strName = getServletContext().getRealPath("rpt");

            byte[] bytes = null;

            try {
                bytes = rep.Inventario(enc, strName);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();
            outputStream.close();

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("reportes.jsp");
            req.include(request, response);
        }
        
        if (action.equals("Minimos")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaEmpleadoId(session.getAttribute("idEmp").toString());
            enc.setFacturaEmpleadoNombre(session.getAttribute("nomEmp").toString());
            enc.setFacturaUsuarioEmite(session.getAttribute("usrEmp").toString());
              
            String strName = getServletContext().getRealPath("rpt");

            byte[] bytes = null;

            try {
                bytes = rep.Minimos(enc, strName);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();
            outputStream.close();

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("reportes.jsp");
            req.include(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
    

