<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Reporte"%>
<!DOCTYPE html>
<%@include file="../lib/header.jsp" %>

<body>
<%@include file="../lib/navbarA.jsp" %>
<div class="container">
    <!-- Contenedor principal: Izquierda (Formulario) y Derecha (Reporte) -->
    <div class="form-container p-4 rounded">
        <!-- Encabezado -->
        <h1 class="text-center">Generar Reporte</h1>
        
        <!-- Botón Generar Reporte -->
        <form action="GenerarReporteServlet" method="get" class="mb-3 text-center">
            <button class="btn btn-primary mt-3">Generar Reporte</button>
        </form>
        
        <!-- Título: Serializado -->
        <h2 class="text-center">Serializado</h2>
        
        <!-- Botones Serializar y Deserializar -->
        <div class="text-center">
            <form action="SerializarReporteServlet" method="post" style="display: inline;">
                <input type="hidden" name="accion" value="serializar">
                <button class="btn btn-success mt-3">Serializar</button>
            </form>
            <form action="SerializarReporteServlet" method="post" style="display: inline;">
                <input type="hidden" name="accion" value="deserializar">
                <button class="btn btn-warning mt-3">Deserializar</button>
            </form>
        </div>
    </div>

    <!-- Contenedor del Reporte Generado -->
    <div class="table-container p-4 rounded">
        <h2 class="text-center">Reporte Generado</h2>
        <% 
            Reporte reporte = (Reporte) request.getAttribute("reporte");
            if (reporte != null) { 
        %>
            <!-- Tabla con datos del reporte -->
            <table class="table table-dark content-table">
                <thead>
                    <tr>
                        <th>Descripción</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Total de pacientes</td>
                        <td><%= reporte.getTotalPacientes() %></td>
                    </tr>
                    <tr>
                        <td>Pacientes con cita</td>
                        <td><%= reporte.getPacientesConCita() %></td>
                    </tr>
                    <tr>
                        <td>Doctores diferentes</td>
                        <td><%= reporte.getDoctoresDiferentes() %></td>
                    </tr>
                    <tr>
                        <td>Total de citas</td>
                        <td><%= reporte.getTotalCitas() %></td>
                    </tr>
                    <tr>
                        <td>Citas reservadas</td>
                        <td><%= reporte.getCitasReservadas() %></td>
                    </tr>
                    <tr>
                        <td>Especialidades diferentes</td>
                        <td><%= reporte.getEspecialidadesDiferentes() %></td>
                    </tr>
                </tbody>
            </table>
        <% 
            } else if (request.getAttribute("mensaje") != null) { 
        %>
            <div class="alert alert-warning text-center"><%= request.getAttribute("mensaje") %></div>
        <% } %>
    </div>
</div>
</body>
</html>
