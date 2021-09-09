<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/> 
        <title>Farmacia La Bendicion</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-5">
                <div class="card">
                    <form action="CtrlRecarga">
                        <div class="card-body">
                            <div class="form-group d-flex">
                                <div class="col-sm-2 ">
                                    <label>Codigo</label>
                                    <input type="text" name="CodigoEmpleado" readonly="true" value="<% out.println(session.getAttribute("idEmp")); %>" class="form-control">
                                </div>
                                <div class="col-sm-4 ">
                                    <label>Empleado</label>
                                    <input type="text" name="NombreEmpleado" readonly="true" value="<% out.println(session.getAttribute("nomEmp")); %>" class="form-control" >
                                </div>
                                <div  class="col-sm-2">
                                    <label>Usuario</label>
                                    <input type="text" name="UsuarioEmpleado" readonly="true" value="<% out.println(session.getAttribute("usrEmp"));%>"  class="form-control">
                                </div>
                            </div>
                            </br>
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-2 d-flex">
                                    <input type="text" name="nit" value="${cliente.getCl_nit()}" class="form-control" placeholder="Nit">
                                </div>
                                <div class="col-sm-2">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">

                                </div>
                                <div class="col-sm-6" style = "margin-left: 40px">
                                    <input type="text" name="nombrecliente" value="${cliente.getCl_Nombre()}" placeholder="Nombre Cliente" class="form-control">
                                </div>
                            </div>
                            </br>
                            <div class="form-group ">
                                <label>Datos Recargas</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-11 d-flex"> 
                                    <select style="width:100%" name="cbxProducto"><font></font>
                                        <c:forEach items="${listP}" var="lista"><font></font>
                                            <option value="${lista.getPro_id()}">${lista.getPro_Nombre()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            </br>
                            <div class="form-group d-flex">
                                <div class="col-sm-3 d-flex">
                                    <label>Numero Celular</label>
                                </div>
                                <div class="col-sm-7" style = "margin-left: 40px">
                                    <input type="text" name="celular" placeholder="Numero Celular" style="width:100%" class="form-control">
                                </div>
                            </div>
                            </br>
                            <div class="form-group d-flex text-center">
                                <div class="col-sm-3">
                                    <input type="submit" name="accion" value="Grabar" style ="width: 90%; font-size:16px" class="btn btn-success">  
                                </div>
                                <div>
                                    <button type="button" class="btn btn-warning btn-lg active" style ="width: 100%; font-size:16px" data-toggle="modal" data-target="#exampleModal">
                                        Agregar Cliente
                                    </button>
                                </div>

                            </div>
                        </div>

                    </form>
                </div>

            </div> 


        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Mantenimiento Cliente</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="CtrlRecarga" method="post">
                            <hr/>
                            <p><strong class="form-group text-center">Agregar Cliente</strong></p>
                            <div class="form-group">
                                <label>Codigo:</label>
                                <input type="text" name="txtId" readonly="true"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Nombre:</label>
                                <input type="text" name="txtNombre"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label>NIT:</label>
                                <input type="text" name="txtNit"    class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Direccion:</label>
                                <input type="text" name="txtDireccion"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Telefono:</label>
                                <input type="text" name="txtTelefono"  class="form-control">
                            </div>
                            </br>

                            <hr/>

                            <center>
                                <div>
                                    <input type="submit" name="accion" value="GuardarCliente" class="btn btn-primary">
                                </div>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            </center>
                            <br/><br/>
                        </form>
                    </div>

                </div>
            </div>
        </div>




        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
