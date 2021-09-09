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
public class CtrlVenta extends HttpServlet {

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

        if (session.getAttribute("prod") != null) {
            lista = (List<producto>) session.getAttribute("prod");
            for (int i = 0; i < lista.size(); i++) {
                total += Float.parseFloat(lista.get(i).getPro_total());
            }
        }

        if (action.equals("Grabar")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaClienteId(session.getAttribute("nit").toString());
            enc.setFacturaEmpleadoId(session.getAttribute("idEmp").toString());
            enc.setFacturaTotal(request.getParameter("total"));
            enc.setFacturaRecibido(request.getParameter("recibido"));
            enc.setFacturaTipoPago("E");
            enc.setFacturaUsuarioEmite(session.getAttribute("usrEmp").toString());

            facturaDAO fac = new facturaDAO();
            fac.InsertarEncabezado(enc,"F");

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
                Logger.getLogger(CtrlVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();
            outputStream.close();

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("total", String.valueOf("0"));
            request.setAttribute("prod", lista);
            request.setAttribute("cliente", cl);
            req.include(request, response);
        }

        if (action.equals("Cotizacion")) {
            FacturaMd enc = new FacturaMd();
            enc.setFacturaClienteId(session.getAttribute("nit").toString());
            enc.setFacturaEmpleadoId(session.getAttribute("idEmp").toString());
              
            facturaDAO fac = new facturaDAO();
            fac.InsertarEncabezado(enc,"C");

            for (int i = 0; i < lista.size(); i++) {
                fac.InsertaDetalle(lista.get(i),"C");
            }

            Cliente cl = new Cliente();
            cl.setCl_Nombre("");
            cl.setCl_id("");

            lista.clear();

            String strName = getServletContext().getRealPath("rpt");

            byte[] bytes = null;
            
            try {
                bytes = rep.Cotizacion(fac.BuscarEncabezadoCotizacion(), strName);
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
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("total", String.valueOf("0"));
            request.setAttribute("prod", lista);
            request.setAttribute("cliente", cl);
            req.include(request, response);
        }
        
        if (action.equals("Eliminar")) {
            for (int i = 0; i < lista.size(); i++) {
                if (request.getParameter("Id").equals(lista.get(i).getPro_id())) {
                    total -= Float.parseFloat(lista.get(i).getPro_total());
                    lista.remove(i);
                    break;
                }
            }
            String nit = session.getAttribute("nit").toString();
            clie.setCl_nit(nit);
            Cliente cl = edaoc.buscar(nit);

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("producto", pr);
            request.setAttribute("cliente", cl);
            request.setAttribute("emp", em);
            request.setAttribute("prod", lista);
            request.setAttribute("total", String.valueOf(total));
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

            producto pr = new producto();

            pr.setPro_id(request.getParameter("idproducto"));
            pr.setPro_Nombre(request.getParameter("NombreProducto"));
            pr.setPro_precio_compra(request.getParameter("precio"));
            pr.setPro_minimo(request.getParameter("Cantidad"));
            pr.setPro_stock(request.getParameter("Stock"));

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("cliente", cl);
            request.setAttribute("producto", pr);

            request.setAttribute("total", String.valueOf(total));
            req.include(request, response);
        }

        if (action.equals("BuscarCliente")) {
            producto pr = new producto();

            pr.setPro_id(request.getParameter("idproducto"));
            pr.setPro_Nombre(request.getParameter("NombreProducto"));
            pr.setPro_precio_compra(request.getParameter("precio"));
            pr.setPro_minimo(request.getParameter("Cantidad"));
            pr.setPro_stock(request.getParameter("Stock"));

            String nit = request.getParameter("nitcliente");
            clie.setCl_nit(nit);
            Cliente cl = edaoc.buscar(nit);
            //request.setAttribute("cliente", request.getAttribute("cliente"));

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("cliente", cl);
            request.setAttribute("producto", pr);
            request.setAttribute("total", String.valueOf(total));

            req.include(request, response);

        }

        if (action.equals("BuscarProducto")) {
            String Codigo = request.getParameter("idproducto");
            producto pr = new producto();
            productoDAO prDao = new productoDAO();

            pr = prDao.listarId(Integer.parseInt(Codigo));
            pr.setPro_id(Codigo);

            String nit = request.getParameter("nitcliente");
            clie.setCl_nit(nit);
            Cliente cl = edaoc.buscar(nit);

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("producto", pr);
            request.setAttribute("cliente", cl);

            request.setAttribute("total", String.valueOf(total));
            req.include(request, response);

        }

        if (action.equals("Inicio")) {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("total", String.valueOf(total));
            req.include(request, response);

        }

        if (action.equals("Agregar")) {

            Float Cantidad = Float.parseFloat(request.getParameter("Cantidad"));
            Float Stock = Float.parseFloat(request.getParameter("Stock"));
            PrintWriter out = response.getWriter();

            producto pr = new producto();

            if ((Stock - Cantidad) >= 0) {

                String Codigo = request.getParameter("idproducto");
                productoDAO prDao = new productoDAO();
                producto pr2 = new producto();
                pr2 = prDao.listarId(Integer.parseInt(Codigo));
                pr2.setPro_cantidad(request.getParameter("Cantidad"));
                pr2.setPro_total(String.valueOf(Cantidad * Float.parseFloat(pr2.getPro_precio_compra())));
                lista.add(pr2);
                total += Float.parseFloat(pr2.getPro_total());
                session.setAttribute("prod", lista);
                pr.setPro_Nombre("");
                pr.setPro_precio_compra("");
                pr.setPro_id("");
                pr.setPro_stock("");
                //out.println("<script type=\"text/javascript\"> alert('AGREGADO EXITOSO');</script>");

            } else {
                out.println("<script type=\"text/javascript\"> alert('LA CANTIDAD NO PUEDE SER MAYOR QUE LA EXISTENCIA');</script>");

                String Codigo = request.getParameter("idproducto");
                productoDAO prDao = new productoDAO();

                pr = prDao.listarId(Integer.parseInt(Codigo));
                pr.setPro_id(Codigo);
            }
            session.setAttribute("nit", request.getParameter("nitcliente"));

            String nit = request.getParameter("nitcliente");
            clie.setCl_nit(nit);
            Cliente cl = edaoc.buscar(nit);

            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher req = request.getRequestDispatcher("ventas.jsp");
            request.setAttribute("producto", pr);
            request.setAttribute("cliente", cl);
            request.setAttribute("emp", em);
            request.setAttribute("prod", lista);
            request.setAttribute("total", String.valueOf(total));
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
