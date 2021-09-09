<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <title>Catalogo Proveedores</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="CtrlProveedores" method="POST">
                        <p><strong class="form-group text-center">Agregar Proveedor</strong></p>
                        <div class="form-group">
                            <label>Codigo:</label>
                            <input type="text" name="txtId" value ="${pr.getProv_id()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombre" value ="${pr.getProv_Nombre()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nit:</label>
                            <input type="text" name="txtNit" value ="${pr.getProv_nit()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Direccion:</label>
                            <input type="text" name="txtDireccion" value ="${pr.getProv_direccion()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono:</label>
                            <input type="text" name="txtTelefono" value ="${pr.getProv_telefono()}"  class="form-control">
                        </div>
                         <div class="form-group">
                            <label>Fecha Ingreso:</label>
                            <input type="text" name="txtfechaIngreso" value ="${pr.getProv_fechaIngreso()}"  class="form-control">
                        </div>
                        </br>
                        <div>
                        <input type="submit" name="accion" value="Guardar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-info">
                         </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-9">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>NIT</th>
                            <th>Direccion</th>
                            <th>Telefono</th>
                            <th>Fecha Ingreso</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <body>
                        <c:forEach var="pr" items="${proveedores}">
                        <tr>
                            <td>${pr.getProv_id()}</td>
                            <td>${pr.getProv_Nombre()}</td>
                            <td>${pr.getProv_nit()}</td>
                            <td>${pr.getProv_direccion()}</td>
                            <td>${pr.getProv_telefono()}</td>
                            <td>${pr.getProv_fechaIngreso()}</td>
                            <td>
                                 <a class="btn btn-warning" href="CtrlProveedores?accion=Editar&Id=${pr.getProv_id()}" >Editar</a>
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