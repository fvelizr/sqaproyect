package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.producto;
import modelo.productoDAO;

/**
 *
 * @author jvict
 */
@WebServlet(urlPatterns = {"/CtrlProducto"})
public class CtrlProducto extends HttpServlet {

    producto pr = new producto();
    productoDAO edao = new productoDAO();
    int ide;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("accion");

        if (action.equals("Listar")) {
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("productos.jsp");
            request.setAttribute("producto", lista);
            req.include(request, response);

        }

        if (action.equals("Guardar")) {
            String nombre = request.getParameter("txtNombre");
            String marca = request.getParameter("txtmarca");
            String stock = request.getParameter("txtStock");
            String precioCompra = request.getParameter("txtprecioCom");
            String minimo = request.getParameter("txtMinimo");
            String fechaIngreso = request.getParameter("txtFechaIngre");

            pr.setPro_Nombre(nombre);
            pr.setPro_marca(marca);
            pr.setPro_stock(stock);
            pr.setPro_precio_compra(precioCompra);
            pr.setPro_minimo(minimo);
            pr.setPro_fechaIngreso(fechaIngreso);

            edao.agregar(pr);
            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("productos.jsp");
            request.setAttribute("producto", lista);
            req.include(request, response);

        }

        if (action.equals("Actualizar")) {
            String codigo = request.getParameter("txtid");
            String nombre = request.getParameter("txtNombre");
            String marca = request.getParameter("txtmarca");
            String stock = request.getParameter("txtStock");
            String precioCompra = request.getParameter("txtprecioCom");
            String minimo = request.getParameter("txtMinimo");
            String fechaIngreso = request.getParameter("txtFechaIngre");

            pr.setPro_id(codigo);
            pr.setPro_Nombre(nombre);
            pr.setPro_marca(marca);
            pr.setPro_stock(stock);
            pr.setPro_precio_compra(precioCompra);
            pr.setPro_minimo(minimo);
            pr.setPro_fechaIngreso(fechaIngreso);

            edao.actualizar(pr);

            List lista = buscar();
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("productos.jsp");
            request.setAttribute("producto", lista);
            req.include(request, response);
        }

        if (action.equals("Editar")) {
            List<producto> lista = buscar();
            String Pro_id = request.getParameter("Id");
            int val = 0;

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getPro_id().equals(Pro_id)) {
                    val = i;
                }
            }

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("productos.jsp");
            request.setAttribute("pr", lista.get(val));
            request.setAttribute("producto", lista);
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
        List lista = edao.listar("");
        return lista;
    }
}
