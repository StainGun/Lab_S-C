<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Cita"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@include file="../lib/header.jsp" %>

<body>
    <%@include file="../lib/navbarP.jsp" %>




    <!-- Mensajes de éxito o error -->
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        String error = (String) request.getAttribute("error");
        if (mensaje != null) {
    %>
    <div class="alert alert-success"><%= mensaje %></div>
    <% } if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>
    <div class="container"style="text-align: center; padding-top: 5%;">
        <!-- Tabla de citas -->
        <div class="table-container p-4 rounded">
            <div>
                <h1>Reservar Citas</h1>

                <div >

                    <form action="ReservarCitaServlet" method="get">
                        <button type="submit" class="btn btn-primary">Recargar Lista</button>
                    </form>
                </div>
            </div>
            <h2>Lista de Citas</h2>
            <table class="table table-striped table-dark ">
                <thead >
                    <tr>
                        <th>ID</th>
                        <th>Doctor</th>
                        <th>Día</th>
                        <th>Especialidad</th>
                        <th>Paciente (cedula)</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Cita[] citas = (Cita[]) request.getAttribute("listaCitas");
                        if (citas != null && citas.length > 0) {
                            for (Cita cita : citas) {
                    %>
                    <tr>
                        <td><%= cita.getId() %></td>
                        <td><%= cita.getDoctor() %></td>
                        <td><%= cita.getDia() %></td>
                        <td><%= cita.getEspecialidad() %></td>
                        <td><%= cita.getCedula() != null ? cita.getCedula() : "Sin asignar" %></td>
                        <td>
                            <% if (cita.getCedula() == null) { %>
                            <button class="btn btn-primary" onclick="reservarCita(<%= cita.getId() %>)">Reservar</button>
                            <% } else { %>
                            <button class="btn btn-secondary" disabled>Reservado</button>
                            <% } %>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No hay citas disponibles.</td>
                    </tr>
                    <% } %>
                </tbody>

            </table>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalReservarCita" tabindex="-1" aria-labelledby="modalReservarCitaLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <!-- Cabecera del modal -->
                <div class="modal-header">
                    <h5 class="modal-title" id="modalReservarCitaLabel">Reservar Cita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- Cuerpo del modal -->
                <div class="modal-body">
                    <form action="ReservarCitaServlet" method="post">
                        <input type="hidden" id="idCita" name="idCita">
                        <label for="cedulaPaciente">Cédula del Paciente:</label>
                        <input type="text" id="cedula" name="cedula" class="form-control" required>
                        <button type="submit" class="btn btn-success mt-3">Reservar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script>
        function reservarCita(id) {
            document.getElementById("idCita").value = id;
            var modal = new bootstrap.Modal(document.getElementById('modalReservarCita'));
            modal.show();
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
