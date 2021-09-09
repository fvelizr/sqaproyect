package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import modelo.empleado;
import modelo.empleadoDAO;
import modelo.facturaDAO;
import modelo.producto;
import modelo.productoDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlRecarga"})
public class CtrlRecarga extends HttpServlet {

    Cliente clie = new Cliente();
    ClienteDAO edaoc = new ClienteDAO();
    empleadoDAO edao = new empleadoDAO();
    empleado em = new empleado();
    producto pr = new producto();
    Reportes rep = new Reportes();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("accion");
        List<producto> lista = new ArrayList();
        HttpSession session = request.getSession();

        float total = 0;

        empleado em = (empleado) session.getAttribute("emple");

        productoDAO p = new productoDAO();
        List list = p.listar("S");

        if (action.equals("Grabar")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaClienteId(request.getParameter("nit").toString());
            enc.setFacturaEmpleadoId(session.getAttribute("idEmp").toString());
            enc.setFacturaTipoPago("E");
            enc.setFacturaUsuarioEmite(session.getAttribute("usrEmp").toString());

            facturaDAO fac = new facturaDAO();
            productoDAO prDao = new productoDAO();

            pr = prDao.listarId(Integer.parseInt(request.getParameter("cbxProducto")));
            pr.setPro_cantidad("1");

            enc.setFacturaTotal(pr.getPro_precio_compra());
            enc.setFacturaRecibido(pr.getPro_precio_compra());

            fac.InsertarEncabezado(enc, "F");

            lista.add(pr);

            for (int i = 0; i < lista.size(); i++) {
                fac.InsertaDetalle(lista.get(i),"F");
            }

            Cliente cl = new Cliente();
            cl.setCl_Nombre("");
            cl.setCl_id("");

            lista.clear();

            String strName = getServletContext().getRealPath("rpt");

            byte[] bytes = null;

            try {
                bytes = rep.PDF(fac.BuscarEncabezadoFactura(), strName);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlRecarga.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();
            outputStream.close();

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("recargas.jsp");
            request.setAttribute("cliente", cl);
            req.include(request, response);
        }

        if (action.equals("GuardarCliente")) {
            String nombre = request.getParameter("txtNombre");
            String nit = request.getParameter("txtNit");
            String direccion = request.getParameter("txtDireccion");
            String telefono = request.getParameter("txtTelefono");

            clie.setCl_Nombre(nombre);
            clie.setCl_nit(nit);
            clie.setCl_direccion(direccion);
            clie.setCl_telefono(telefono);

            edaoc.agregar(clie);

            Cliente cl = edaoc.buscar(nit);

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("recargas.jsp");
            request.setAttribute("cliente", cl);
            request.setAttribute("listP", list);
            req.include(request, response);
        }

        if (action.equals("BuscarCliente")) {
            String nit = request.getParameter("nit");
            clie.setCl_nit(nit);
            Cliente cl = edaoc.buscar(nit);
           
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("recargas.jsp");
            request.setAttribute("cliente", cl);
            request.setAttribute("listP", list);
            req.include(request, response);

        }

        if (action.equals("Inicio")) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("recargas.jsp");
            request.setAttribute("listP", list);
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
