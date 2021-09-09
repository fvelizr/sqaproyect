
package modelo;

/**
 *
 * @author jvict
 */
public class Cliente {
     
        String cl_id;
        String cl_Nombre;
        String cl_nit;
        String cl_direccion;
        String cl_telefono;

    public Cliente() {
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_Nombre() {
        return cl_Nombre;
    }

    public void setCl_Nombre(String cl_Nombre) {
        this.cl_Nombre = cl_Nombre;
    }

    public String getCl_nit() {
        return cl_nit;
    }

    public void setCl_nit(String cl_nit) {
        this.cl_nit = cl_nit;
    }

    public String getCl_direccion() {
        return cl_direccion;
    }

    public void setCl_direccion(String cl_direccion) {
        this.cl_direccion = cl_direccion;
    }

    public String getCl_telefono() {
        return cl_telefono;
    }

    public void setCl_telefono(String cl_telefono) {
        this.cl_telefono = cl_telefono;
    }

}
