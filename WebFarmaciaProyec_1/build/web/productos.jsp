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
        <div class="d-flex ">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form action="CtrlProducto" method="POST">
                        <div>
                            <label>Catalogo de Producto</label>
                        </div>
                        </br>
                        <div class="d-flex ml-auto">
                            <div class="form-group  col-sm-2 ml-auto">
                                <label>Codigo: </label>
                                <input type="text" name="txtid" value ="${pr.getPro_id()}"  class="form-control">
                            </div>
                            <div class="form-group  col-sm-5 ml-auto">
                                <label>Nombre: </label>
                                <input type="text" name="txtNombre" value ="${pr.getPro_Nombre()}" class="form-control">
                            </div>
                            <div class="form-group  col-sm-3">
                                <label>Marca</label>
                                <input type="text" name="txtmarca"  value ="${pr.getPro_marca()}" class="form-control">
                            </div>
                            <div class="form-group  col-sm-2">
                                <label>Stock</label>
                                <input type="text" name="txtStock" value ="${pr.getPro_stock()}" class="form-control">
                            </div>
                        </div>
                        <div class="d-flex ml-auto">
                            <div class="form-group  col-sm-3">
                                <label>Precio Compra</label>
                                <input type="text" name="txtprecioCom" value ="${pr.getPro_precio_compra()}" class="form-control">
                            </div>
                            <div class="form-group  col-sm-3">
                                <label>Minimo</label>
                                <input type="text" name="txtMinimo"  value ="${pr.getPro_minimo()}" class="form-control">
                            </div>
                            <div class="form-group col-sm-3">
                                <label>Fecha Ingreso</label>
                                <input type="text" name="txtFechaIngre"  value ="${pr.getPro_fechaIngreso()}" class="form-control">
                            </div>
                        </div>
                        </br>                            
                        <div>
                            <input type="submit" name="accion" value="Guardar" class="btn btn-info">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-info ml-auto">
                        </div>

                    </form>

                </div>

            </div>

        </div>
        <div class="col-sm-10" >
            <table  class="table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Precio Compra</th>
                        <th>Stock</th>
                        <th>Marca</th>
                        <th>Fecha Ingreso</th>
                        <th>Minimo</th>
                        <th>Accion</th>

                    </tr>
                </thead>
                <body>
                    <c:forEach var="pr" items="${producto}">
                    <tr>
                        <td>${pr.getPro_id()}</td>
                        <td>${pr.getPro_Nombre()}</td>
                        <td>${pr.getPro_precio_compra()}</td>
                        <td>${pr.getPro_stock()}</td>
                        <td>${pr.getPro_marca()}</td>
                        <td>${pr.getPro_fechaIngreso()}</td>
                        <td>${pr.getPro_minimo()}</td>
                        <td>
                            <a class="btn btn-warning" href="CtrlProducto?accion=Editar&Id=${pr.getPro_id()}" >Editar</a>
                        </td>
                    </tr>
                </c:forEach>
                </body>
            </table>
        </div>

        <div class="m-4" style="height: 500px;">
            <iframe name="myFrame" style="height: 150%; width: 100%"></iframe>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
    </body>
</html>
