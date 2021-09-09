<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>  <title>Farmacia La Bendicion</title>
        <title>Farmacia La Bendicion</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-3">
                <div class="card-body">
                    <form action="CtrlClientes" method="POST">
                        <p><strong class="form-group text-center">Agregar Cliente</strong></p>
                        <div class="form-group">
                            <label>Codigo:</label>
                            <input type="text" name="txtId" value ="${clie.getCl_id()}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombre" value ="${clie.getCl_Nombre()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>NIT:</label>
                            <input type="text" name="txtNit" value ="${clie.getCl_nit()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Direccion:</label>
                            <input type="text" name="txtDireccion" value ="${clie.getCl_direccion()}"  class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono:</label>
                            <input type="text" name="txtTelefono" value ="${clie.getCl_telefono()}"  class="form-control">
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
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>NIT</th>
                            <th>Direccion</th>
                            <th>Telefono</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <body>
                        <c:forEach var="clie" items="${cliente}">
                        <tr>
                            <td>${clie.getCl_id()}</td>
                            <td>${clie.getCl_Nombre()}</td>
                            <td>${clie.getCl_nit()}</td>
                            <td>${clie.getCl_direccion()}</td>
                            <td>${clie.getCl_telefono()}</td>
                            <td>
                                <a class="btn btn-warning" href="CtrlClientes?accion=Editar&Id=${clie.getCl_id()}" >Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </body>
                </table>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
    </body>
</html>