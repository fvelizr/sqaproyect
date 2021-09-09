package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.proveedores;
import modelo.proveedoresDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlProveedores"})
public class CtrlProveedores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    proveedores pr = new proveedores();
    proveedoresDAO edao = new proveedoresDAO();
    int ide;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("accion");

        if (action.equals("Listar")) {
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("proveedores.jsp");
            request.setAttribute("proveedores", lista);
            req.include(request, response);

        }

        if (action.equals("Guardar")) {
            String nombre = request.getParameter("txtNombre");
            String Nit = request.getParameter("txtNit");
            String Direccion = request.getParameter("txtDireccion");
            String telefono = request.getParameter("txtTelefono");
            String fechaIngreso = request.getParameter("txtfechaIngreso");

            pr.setProv_Nombre(nombre);
            pr.setProv_nit(Nit);
            pr.setProv_direccion(Direccion);
            pr.setProv_telefono(telefono);
            pr.setProv_fechaIngreso(fechaIngreso);

            edao.agregar(pr);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("proveedores.jsp");
            request.setAttribute("proveedores", lista);
            req.include(request, response);

        }

        if (action.equals("Actualizar")) {
            String codigo = request.getParameter("txtId");
            String nombre = request.getParameter("txtNombre");
            String Nit = request.getParameter("txtNit");
            String Direccion = request.getParameter("txtDireccion");
            String telefono = request.getParameter("txtTelefono");
            String fechaIngreso = request.getParameter("txtfechaIngreso");

            pr.setProv_id(codigo);
            pr.setProv_Nombre(nombre);
            pr.setProv_nit(Nit);
            pr.setProv_direccion(Direccion);
            pr.setProv_telefono(telefono);
            pr.setProv_fechaIngreso(fechaIngreso);

            edao.actualizar(pr);

            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("proveedores.jsp");
            request.setAttribute("proveedores", lista);
            req.include(request, response);
        }

        if (action.equals("Editar")) {
            List<proveedores> lista = buscar();
            String Pro_id = request.getParameter("Id");
            int val = 0;

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getProv_id().equals(Pro_id)) {
                    val = i;
                }
            }

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("proveedores.jsp");
            request.setAttribute("pr", lista.get(val));
            request.setAttribute("proveedores", lista);
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

    private List buscar() {
        List lista = edao.listar();
        return lista;
    }
}
