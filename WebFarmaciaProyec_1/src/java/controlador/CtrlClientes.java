package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlClientes"})
public class CtrlClientes extends HttpServlet {

    Cliente clie = new Cliente();
    ClienteDAO edao = new ClienteDAO();
    int ide;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("accion");
        
        if (action.equals("Listar")) {
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("clientes.jsp");
            request.setAttribute("cliente", lista);
            req.include(request, response);
            
        }
        
        if (action.equals("Guardar")) {
            String nombre = request.getParameter("txtNombre");
            String nit = request.getParameter("txtNit");
            String direccion = request.getParameter("txtDireccion");
            String telefono = request.getParameter("txtTelefono");
            
            clie.setCl_Nombre(nombre);
            clie.setCl_nit(nit);
            clie.setCl_direccion(direccion);
            clie.setCl_telefono(telefono);
            
            edao.agregar(clie);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("clientes.jsp");
            request.setAttribute("cliente", lista);
            req.include(request, response);
            
        }
        
        if (action.equals("Actualizar")) {
            String cl_id = request.getParameter("txtId");
            String cl_Nombre = request.getParameter("txtNombre");
            String cl_nit = request.getParameter("txtNit");
            String cl_direccion = request.getParameter("txtDireccion");
            String cl_telefono = request.getParameter("txtTelefono");
            
            clie.setCl_id(cl_id);
            clie.setCl_Nombre(cl_Nombre);
            clie.setCl_nit(cl_nit);
            clie.setCl_direccion(cl_direccion);
            clie.setCl_telefono(cl_telefono);
            
            edao.actualizar(clie);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("clientes.jsp");
            request.setAttribute("cliente", lista);
            req.include(request, response);
        }
        
        if (action.equals("Editar")) {
            List<Cliente> lista = buscar();
            String Cl_id = request.getParameter("Id");
            int val = 0;
            
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCl_id().equals(Cl_id)) {
                    val = i;
                }
            }
            
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("clientes.jsp");
            request.setAttribute("clie", lista.get(val));
            request.setAttribute("cliente", lista);
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
