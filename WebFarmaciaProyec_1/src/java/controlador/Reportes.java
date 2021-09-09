/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.DetalleFacturaMd;
//import modelo.DetalleFacturaMd;
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
public class Reportes {

    public byte[] PDF(FacturaMd enc, String dir) throws SQLException {
        //  String ano_arribo="", num_Arribo="";

        UUID uuid = UUID.randomUUID();
        String Serie = uuid.toString().substring(0, 7);
        String Autorizacion = uuid.toString();
        Random r = new Random();
        String numero = String.valueOf(r.nextInt(100000) + 9999999);

        facturaDAO ven = new facturaDAO();
        String buque = "";
        DecimalFormat formato = new DecimalFormat("#.00");

        List<DetalleFacturaMd> lista = ven.BuscaDetallesFactura();
        try {
            com.itextpdf.text.Document detalle = new com.itextpdf.text.Document(PageSize.LEGAL, 0, 390, 0, 0);
            ByteArrayOutputStream badetalle = new ByteArrayOutputStream();
            PdfWriter escritura;
            // Se crea el OutputStream para el fichero donde queremos dejar el pdf.

            FileOutputStream ficheroPdf = new FileOutputStream(dir + "\\factura.pdf");

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(detalle, ficheroPdf).setInitialLeading(20);

            //escritura = PdfWriter.getInstance(detalle, badetalle);
            detalle.open();

            Paragraph predetalle = new Paragraph();
            predetalle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// ENCABEZADO DE BOLETA ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PdfPTable table2 = new PdfPTable(10);
            table2.setWidthPercentage(90);

            PdfPCell cell2;

            cell2 = new PdfPCell(new Phrase("FARMACIA", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("\"LA BENDICION\"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("TU SALUD ES NUESTRO COMPROMISO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT: 1234567-8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("MASAGUA, ESCUINTLA", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FACTURA REGIMEN FEL", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("DOCUMENTO TRIBUTARIO ELECRONICO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("SERIE\n" + Serie.toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NUMERO DE DTE \n" + numero, FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS FACTURA FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Interno: " + enc.getFacturaNumero(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Atendió: " + enc.getFacturaEmpleadoId() + "  " + enc.getFacturaEmpleadoNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT: " + enc.getFacturaClienteNit(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Cliente: " + enc.getFacturaClienteId() + "  " + enc.getFacturaClienteNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Direccion: " + enc.getFacturaClienteDireccion(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS DETALLE FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Producto", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Importe", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(3);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            for (int i = 0; i < lista.size(); i++) {
                cell2 = new PdfPCell(new Phrase(lista.get(i).getDetProductoId() + " " + lista.get(i).getDetProductoDescripcion() + " " + lista.get(i).getDetProductoMarca(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(7);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(""));
                cell2.setColspan(3);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getProductoCantidad() + " x Q." + lista.get(i).getDetProductoPrecioVenta(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(7);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(String.valueOf(formato.format(Float.parseFloat(lista.get(i).getDetProductoPrecioVenta()) * Float.parseFloat(lista.get(i).getProductoCantidad()))), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(3);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                table2.addCell(cell2);
            }

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS TOTALES FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Total: Q." + enc.getFacturaTotal(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("MEDIO DE PAGO: EFECTIVO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("PAGO RECIBIDO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q." + enc.getFacturaRecibido(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CAMBIO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q." + enc.getFacturaCambio(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Es un placer el Servirle, Vuelva Pronto", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("DATOS DE CERTIFICADOR", FontFactory.getFont(FontFactory.TIMES_ROMAN, 13)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT Certificador: ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("545526-6", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Nombre Certificador: ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Certificador", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("AUTORIZACION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(Autorizacion.toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FECHA CERTIFICACION ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(String.valueOf(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);
            predetalle.add(table2);

            detalle.add(predetalle);

            detalle.close();

            File f;

            f = new File(dir + "\\factura.pdf");
            byte[] buffer = new byte[(int) f.length()];
            FileInputStream fs = new FileInputStream(f);

            fs.read(buffer);
            fs.close();

            InputStream is = new ByteArrayInputStream(buffer);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[131072];
            int n = 0;
            while (-1 != (n = is.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            is.close();
//            byte[] response = out.toByteArray();
//            FileOutputStream fos = new FileOutputStream("C:\\Users\\kevin\\Desktop\\v2\\arcd.pdf");
//            fos.write(response);
//            fos.close();

            return buffer;
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }

    }

    public byte[] Cotizacion(FacturaMd enc, String dir) throws SQLException {

        facturaDAO ven = new facturaDAO();
        DecimalFormat formato = new DecimalFormat("#.00");

        List<DetalleFacturaMd> lista = ven.BuscaDetallesFactura();
        try {
            com.itextpdf.text.Document detalle = new com.itextpdf.text.Document(PageSize.LEGAL, 0, 390, 0, 0);
            ByteArrayOutputStream badetalle = new ByteArrayOutputStream();
            PdfWriter escritura;

            FileOutputStream ficheroPdf = new FileOutputStream(dir + "\\cotizacion.pdf");

            PdfWriter.getInstance(detalle, ficheroPdf).setInitialLeading(20);

            //escritura = PdfWriter.getInstance(detalle, badetalle);
            detalle.open();

            Paragraph predetalle = new Paragraph();
            predetalle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// ENCABEZADO DE BOLETA ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PdfPTable table2 = new PdfPTable(10);
            table2.setWidthPercentage(90);

            PdfPCell cell2;

            cell2 = new PdfPCell(new Phrase("FARMACIA", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("\"LA BENDICION\"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("TU SALUD ES NUESTRO COMPROMISO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT: 1234567-8", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("MASAGUA, ESCUINTLA", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COTIZACION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NUMERO \n" + enc.getFacturaNumero(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS FACTURA FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Atendió: " + enc.getFacturaEmpleadoId() + "  " + enc.getFacturaEmpleadoNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT: " + enc.getFacturaClienteNit(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Cliente: " + enc.getFacturaClienteId() + "  " + enc.getFacturaClienteNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Direccion: " + enc.getFacturaClienteDireccion(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS DETALLE FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Producto", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Importe", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
            cell2.setColspan(3);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            Float total = Float.parseFloat("0");

            for (int i = 0; i < lista.size(); i++) {
                cell2 = new PdfPCell(new Phrase(lista.get(i).getDetProductoId() + " " + lista.get(i).getDetProductoDescripcion() + " " + lista.get(i).getDetProductoMarca(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(7);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(""));
                cell2.setColspan(3);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getProductoCantidad() + " x Q." + lista.get(i).getDetProductoPrecioVenta(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(7);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(String.valueOf(formato.format(Float.parseFloat(lista.get(i).getDetProductoPrecioVenta()) * Float.parseFloat(lista.get(i).getProductoCantidad()))), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
                cell2.setColspan(3);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                table2.addCell(cell2);
                total += Float.parseFloat(lista.get(i).getDetProductoPrecioVenta()) * Float.parseFloat(lista.get(i).getProductoCantidad());
            }

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS TOTALES FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Total: Q." + String.valueOf(total), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12)));
            cell2.setColspan(7);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Es un placer el Servirle, Vuelva Pronto", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            predetalle.add(table2);

            detalle.add(predetalle);

            detalle.close();

            File f;

            f = new File(dir + "\\cotizacion.pdf");
            byte[] buffer = new byte[(int) f.length()];
            FileInputStream fs = new FileInputStream(f);

            fs.read(buffer);
            fs.close();

            InputStream is = new ByteArrayInputStream(buffer);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[131072];
            int n = 0;
            while (-1 != (n = is.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            is.close();

            return buffer;
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }

    }

    public byte[] CorteCaja(FacturaMd enc, String dir) throws SQLException {

        facturaDAO ven = new facturaDAO();
        DecimalFormat formato = new DecimalFormat("#.00");

        List<FacturaMd> lista = ven.CorteCaja(enc.getFacturaFechaEmision(), enc.getFacturaEmpleadoId());
        try {
            com.itextpdf.text.Document detalle = new com.itextpdf.text.Document(PageSize.LETTER);
            ByteArrayOutputStream badetalle = new ByteArrayOutputStream();
            PdfWriter escritura;

            FileOutputStream ficheroPdf = new FileOutputStream(dir + "\\corte.pdf");

            PdfWriter.getInstance(detalle, ficheroPdf).setInitialLeading(20);

            //escritura = PdfWriter.getInstance(detalle, badetalle);
            detalle.open();

            Paragraph predetalle = new Paragraph();
            predetalle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// ENCABEZADO DE BOLETA ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PdfPTable table2 = new PdfPTable(10);
            table2.setWidthPercentage(90);

            PdfPCell cell2;

            cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 17, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CORTE DE CAJA", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COLABORADOR ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoId() + "  " + enc.getFacturaEmpleadoNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FECHA ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaFechaEmision(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS DETALLE FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("LISTADO DE FACTURAS EMITIDAS"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NO"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NIT"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CLIENTE"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("DIRECCION"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("HORA"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("TOTAL"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            Float total = Float.parseFloat("0");

            cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            for (int i = 0; i < lista.size(); i++) {
                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaNumero()));
                cell2.setColspan(1);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaClienteNit()));
                cell2.setColspan(1);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaClienteNombre()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaClienteDireccion()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaFechaEmision()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getFacturaTotal()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
                cell2.setColspan(10);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);

                total += Float.parseFloat(lista.get(i).getFacturaTotal());
            }

            cell2 = new PdfPCell(new Phrase("TOTAL"));
            cell2.setColspan(8);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(String.valueOf(total)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CANTIDAD EN BILLETES"));
            cell2.setColspan(10);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.200.00"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.100.00"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.50.00"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.20.00"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.10.00"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.5.00"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.1.00"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.0.50"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("______"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CANTIDAD EN MONEDAS"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.1.00"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.0.50"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.0.25"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.0.10"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("Q.0.05"));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("____________"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoNombre().toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COORDINADOR", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CAJERO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            predetalle.add(table2);

            detalle.add(predetalle);

            detalle.close();

            File f;

            f = new File(dir + "\\corte.pdf");
            byte[] buffer = new byte[(int) f.length()];
            FileInputStream fs = new FileInputStream(f);

            fs.read(buffer);
            fs.close();

            InputStream is = new ByteArrayInputStream(buffer);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[131072];
            int n = 0;
            while (-1 != (n = is.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            is.close();

            return buffer;
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }

    }

    public byte[] Inventario(FacturaMd enc, String dir) throws SQLException {

        productoDAO ven = new productoDAO();
        DecimalFormat formato = new DecimalFormat("#.00");

        List<producto> lista = ven.listar("");
        try {
            com.itextpdf.text.Document detalle = new com.itextpdf.text.Document(PageSize.LETTER);
            ByteArrayOutputStream badetalle = new ByteArrayOutputStream();
            PdfWriter escritura;

            FileOutputStream ficheroPdf = new FileOutputStream(dir + "\\inventario.pdf");

            PdfWriter.getInstance(detalle, ficheroPdf).setInitialLeading(20);

            //escritura = PdfWriter.getInstance(detalle, badetalle);
            detalle.open();

            Paragraph predetalle = new Paragraph();
            predetalle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// ENCABEZADO DE BOLETA ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PdfPTable table2 = new PdfPTable(10);
            table2.setWidthPercentage(90);

            PdfPCell cell2;
            String min = "";

            cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 17, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("REPORTE DE INVENTARIOS " + min, FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COLABORADOR ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoId() + "  " + enc.getFacturaEmpleadoNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FECHA ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS DETALLE FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("LISTADO DE PRODUCTOS Y EXISTENCIAS"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("ID"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NOMBRE"));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("MARCA"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("STOCK"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("PRECIO"));

            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);
            Float total = Float.parseFloat("0");

            cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            for (int i = 0; i < lista.size(); i++) {

                cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_id()));
                cell2.setColspan(1);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_Nombre()));
                cell2.setColspan(3);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_marca()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_stock()));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase("Q." + String.valueOf(formato.format(Float.parseFloat(lista.get(i).getPro_precio_compra())))));
                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

                cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
                cell2.setColspan(10);
                cell2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(cell2);
            

        }

        cell2 = new PdfPCell(new Phrase(" "));
        cell2.setColspan(10);
        cell2.setBorder(Rectangle.NO_BORDER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(" "));
        cell2.setColspan(10);
        cell2.setBorder(Rectangle.NO_BORDER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(2);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoNombre().toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(2);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("COORDINADOR", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("CAJERO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(2);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
        cell2.setColspan(4);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table2.addCell(cell2);

        predetalle.add(table2);

        detalle.add(predetalle);

        detalle.close();

        File f;

        f = new File(dir + "\\inventario.pdf");
        byte[] buffer = new byte[(int) f.length()];
        FileInputStream fs = new FileInputStream(f);

        fs.read(buffer);
        fs.close();

        InputStream is = new ByteArrayInputStream(buffer);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[131072];
        int n = 0;
        while (-1 != (n = is.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        is.close();

        return buffer;
    }
    catch (Exception ex

    
        ) {
            ex.getMessage();
        return null;
    }

}

public byte[] Minimos(FacturaMd enc, String dir) throws SQLException {

        productoDAO ven = new productoDAO();
        DecimalFormat formato = new DecimalFormat("#.00");

        List<producto> lista = ven.listar("");
        try {
            com.itextpdf.text.Document detalle = new com.itextpdf.text.Document(PageSize.LETTER);
            ByteArrayOutputStream badetalle = new ByteArrayOutputStream();
            PdfWriter escritura;

            FileOutputStream ficheroPdf = new FileOutputStream(dir + "\\inventario.pdf");

            PdfWriter.getInstance(detalle, ficheroPdf).setInitialLeading(20);

            //escritura = PdfWriter.getInstance(detalle, badetalle);
            detalle.open();

            Paragraph predetalle = new Paragraph();
            predetalle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// ENCABEZADO DE BOLETA ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            PdfPTable table2 = new PdfPTable(10);
            table2.setWidthPercentage(90);

            PdfPCell cell2;
            String min = "";

             
                min = "MINIMOS";
             

            cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 17, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("REPORTE DE INVENTARIOS " + min, FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COLABORADOR ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoId() + "  " + enc.getFacturaEmpleadoNombre(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FECHA ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(""));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////// DATOS DETALLE FACTURA/////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("LISTADO DE PRODUCTOS Y EXISTENCIAS"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("ID"));
            cell2.setColspan(1);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("NOMBRE"));
            cell2.setColspan(3);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("MARCA"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("STOCK"));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

                 cell2 = new PdfPCell(new Phrase("MINIMO"));

                cell2.setColspan(2);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table2.addCell(cell2);

             Float total = Float.parseFloat("0");

            cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            for (int i = 0; i < lista.size(); i++) {
                if (Float.parseFloat(lista.get(i).getPro_stock()) < Float.parseFloat(lista.get(i).getPro_minimo())) {

                    cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_id()));
                    cell2.setColspan(1);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_Nombre()));
                    cell2.setColspan(3);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_marca()));
                    cell2.setColspan(2);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_stock()));
                    cell2.setColspan(2);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table2.addCell(cell2);

                         cell2 = new PdfPCell(new Phrase(lista.get(i).getPro_minimo()));
                        cell2.setColspan(2);
                        cell2.setBorder(Rectangle.NO_BORDER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        table2.addCell(cell2);
                     
                    cell2 = new PdfPCell(new Phrase("______________________________________________________________________"));
                    cell2.setColspan(10);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell2);
                }

            }

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" "));
            cell2.setColspan(10);
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("__________________________________", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(enc.getFacturaEmpleadoNombre().toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("COORDINADOR", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("CAJERO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            cell2 = new PdfPCell(new Phrase("FARMACIA LA BENDICION", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
            cell2.setColspan(4);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table2.addCell(cell2);

            predetalle.add(table2);

            detalle.add(predetalle);

            detalle.close();

            File f;

            f = new File(dir + "\\inventario.pdf");
            byte[] buffer = new byte[(int) f.length()];
            FileInputStream fs = new FileInputStream(f);

            fs.read(buffer);
            fs.close();

            InputStream is = new ByteArrayInputStream(buffer);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[131072];
            int n = 0;
            while (-1 != (n = is.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            is.close();

            return buffer;
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }

    }

}
