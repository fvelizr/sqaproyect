<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link href="Css/EstiloMenu.css" rel="stylesheet" type="text/css"/>
        <title>Farmacia La Bendicion</title>
    </head>
    <body id="menu">
        <nav style="background-color: red" class="navbar navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <a style="color: #1105FA; font-size: x-large"><strong>Farmacia La Bendicion</strong></a>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light active" aria-current="page" href="principal.jsp"><strong>Inicio</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlVenta?accion=Inicio" target="myFrame"><strong>Ventas</strong></a>
                        </li> 
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlRecarga?accion=Inicio" target="myFrame"><strong>Recarga</strong></a>
                        </li> 
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlProducto?accion=Listar"target="myFrame"><strong>Productos</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlEmpleado?accion=Listar"target="myFrame"><strong>Empleados</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlProveedores?accion=Listar"target="myFrame"><strong>Proveedores</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlCompras?accion=Inicio"target="myFrame"><strong>Compras</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlClientes?accion=Listar"target="myFrame"><strong>Clientes</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="reportes.jsp"target="myFrame"><strong>Corte Caja</strong></a>
                        </li>
                         
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlReportes?accion=Inventario"target="myFrame"><strong>Inventario</strong></a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="CtrlReportes?accion=Minimos"target="myFrame"><strong>Minimos</strong></a>
                        </li>
                         <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?accion=Inicio"target="myFrame"><strong>Cambio Clave</strong></a>
                        </li>
                        
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="#"><strong><% out.println(session.getAttribute("usrEmp").toString().toUpperCase());%></strong></a>
                        </li>
                        
                    </ul>
                </div>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="index.jsp"><strong>Salir</strong></a>
                </li>
            </div>
        </nav>
        <div class="m-4" style="height: 500px;">
            <iframe name="myFrame" style="height: 150%; width: 100%"></iframe>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
    </body>
</html>
