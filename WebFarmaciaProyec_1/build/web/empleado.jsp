<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <title>Farmacia La Bendicion</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="CtrlEmpleado" method="POST">
                        <div class="form-group">
                            <label>Id</label>
                            <input type="text" name="txtId" value ="${emp.getIdEmpleado()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" name="txtNombre" value ="${emp.getNombreEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>DPI</label>
                            <input type="text" name="txtDpi" value ="${emp.getDpiEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Correo Electronico</label>
                            <input type="text" name="txtCorreo" value ="${emp.getCorreoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Direccion</label>
                            <input type="text" name="txtDireccion"  value ="${emp.getDireccionEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" name="txtTelefono"  value ="${emp.getTelefonoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Fecha de Nacimiento</label>
                            <input type="text" name="txtFechaNacimiento"  value ="${emp.getFecha_nacimientoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Puesto</label>
                            <input type="text" name="txtPuesto"  value ="${emp.getPuestoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Fecha Ingreso</label>
                            <input type="text" name="txtFechaIngreso"  value ="${emp.getFecha_ingresoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" name="txtEstado"  value ="${emp.getEstadoEmpleado()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" name="txtUsuario" value ="${emp.getUserEmpleado()}" class="form-control">
                        </div>
                        </br>
                        <div>
                        <input type="submit" name="accion" value="Guardar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-info">
                         </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-10">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>DPI</th>
                            <th>Correo</th>
                            <th>Dirección</th>
                            <th>Telefono</th>
                            <th>Fecha Nacimiento</th>
                            <th>Puesto</th>
                            <th>Fecha Ingreso</th>
                            <th>Estado</th>
                            <th>Usuario</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <body>
                        <c:forEach var="em" items="${empleado}">
                        <tr>
                            <td>${em.getIdEmpleado()}</td>
                            <td>${em.getNombreEmpleado()}</td>
                            <td>${em.getDpiEmpleado()}</td>
                            <td>${em.getCorreoEmpleado()}</td>
                            <td>${em.getDireccionEmpleado()}</td>
                            <td>${em.getTelefonoEmpleado()}</td>
                            <td>${em.getFecha_nacimientoEmpleado()}</td>
                            <td>${em.getPuestoEmpleado()}</td>
                            <td>${em.getFecha_ingresoEmpleado()}</td>
                            <td>${em.getEstadoEmpleado()}</td>
                            <td>${em.getUserEmpleado()}</td>
                            <td>
                                <a class="btn btn-warning" href="CtrlEmpleado?accion=Editar&Id=${em.getIdEmpleado()}" >Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </body>
                </table>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
    </body>
</html>
