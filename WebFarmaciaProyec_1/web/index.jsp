<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link href="Css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Farmacia La Bendicion</title>
    </head>
    <body id="con">
        <div class="container">
            <form action="Controlador">
                <div>
                    <div id="img" class="form-group text-center">
                        <img src="imagenes/logo.jpeg" alt="80" width="305"/>
                        </br>
                    </div>
                    <div>
                        </br>
                        <p id="txt1"><strong>Bienvenido al Sistema</strong></p>
                        <label id="txt2">Usuario:</label>
                        <input  class="form-control" type="text" name="txtuser" placeholder="Ingrese su Usuario">
                        <label id="txt2">Contraseña:</label>
                        <input  type="password" name="txtpass" placeholder="Ingrese su Contraseña" class="form-control">
                    </div>
                    </br>
                    <input class="btb btn-danger btn-block" type="submit" name="accion" value="Ingresar">
                    </form>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
                </body>
                </html>
