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
            <div class="col-sm-5">
                
                <div class="card">
                    <form action="CtrlReportes">
                        <div class="form-group">
                            <center><h1>Impresion Corte de Caja</h1></center>
                            </div>
                            </br>
                            </br>
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
                            <div class="form-group d-flex">
                                <div class="col-sm-2 d-flex">
                                     <label>Fecha</label>
                                </div>
                                <div class="col-sm-6" >
                                    <input type="text" name="fecha"  class="form-control">
                                </div>
                            </div>
                            </br>
                            
                            </br>
                            </br>
                            <div class="form-group d-flex text-center">
                                <div class="col-sm-3">
                                    <input type="submit" name="accion" value="Corte" style ="width: 90%; font-size:16px" class="btn btn-success">  
                                </div>
                                 

                            </div>
                        </div>

                    </form>
                </div>

            </div> 
            

        </div>

         


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
