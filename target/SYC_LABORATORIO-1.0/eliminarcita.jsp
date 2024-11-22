<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Cita"%>
<!DOCTYPE html>
<%@include file="../lib/header.jsp" %> <!-- Aquí debes incluir Bootstrap y otros recursos -->

<body>
    <%@include file="../lib/navbarA.jsp" %>
    <div class="container">
        <div class="table-container mt-4">
            <h2>Gestionar Citas</h2>
            <!-- Botón para recargar la lista -->
            <form action="EliminarCitaServlet" method="get" class="mb-4">
                <button type="submit" class="btn btn-primary">Recargar Lista</button>
            </form>
            <table class="table table-dark content-table">
                <thead>
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
                    <% Cita[] citas = (Cita[]) request.getAttribute("listaCitas");
                        if (citas != null && citas.length > 0) {
                            for (Cita cita : citas) { %>
                    <tr>
                        <td><%= cita.getId() %></td>
                        <td><%= cita.getDoctor() %></td>
                        <td><%= cita.getDia() %></td>
                        <td><%= cita.getEspecialidad() %></td>
                        <td><%= cita.getCedula() != null ? cita.getCedula() : "Sin asignar" %></td>
                        <td>
    <% if (cita.getCedula() != null) { %>
    <div class="button-container">
        <button class="btn btn-warning button-icon edit" onclick="abrirModalLiberar(<%= cita.getId() %>)">
            <i class="fas fa-pencil-alt"></i> 
        </button>
        <button class="btn btn-danger button-icon delete" onclick="abrirModalEliminar(<%= cita.getId() %>)">
            <i class="fas fa-trash"></i> 
        </button>
    </div>
    <% } else { %>
    <div class="button-container">
        <button class="btn btn-danger button-icon delete" onclick="abrirModalEliminar(<%= cita.getId() %>)">
            <i class="fas fa-trash"></i> 
        </button>
        <button class="btn btn-primary button-icon" alt="Reservar" onclick="abrirModalReservar(<%= cita.getId() %>)">
            <i class="fas fa-calendar-check"></i> 
        </button>
    </div>
    <% } %>
</td>

                    </tr>
                    <%   }
                        } else { %>
                    <tr>
                        <td colspan="6" class="text-center">No hay citas disponibles.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Modal para Eliminar -->
    <div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-white" id="modalEliminarLabel">Eliminar Cita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="formEliminar" action="EliminarCitaServlet" method="post">
                        <input type="hidden" id="idCitaEliminar" name="idCita">
                        <input type="hidden" name="accion" value="eliminar">
                        <label for="pass" class="text-white">Contraseña de administrador:</label>
                        <input type="password" id="pass" name="pass" class="form-control">
                        <button type="submit" class="btn btn-danger mt-3">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para Liberar -->
    <div class="modal fade" id="modalLiberar" tabindex="-1" aria-labelledby="modalLiberarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-white" id="modalLiberarLabel">Liberar Cita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="formLiberar" action="EliminarCitaServlet" method="post">
                        <input type="hidden" id="idCitaLiberar" name="idCita">
                        <input type="hidden" name="accion" value="liberar">
                        <label for="pass" class="text-white">Contraseña:</label>
                        <input type="password" id="pass" name="pass" class="form-control">
                        <button type="submit" class="btn btn-warning mt-3">Liberar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para Reservar -->
    <div class="modal fade" id="modalReservar" tabindex="-1" aria-labelledby="modalReservarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-white" id="modalReservarLabel">Reservar Cita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="formReservar" action="ReservarCitaServlet" method="post">
                        <input type="hidden" id="idCitaReservar" name="idCita">
                        <label for="cedula" class="text-white">Cédula del Paciente:</label>
                        <input type="text" id="cedula" name="cedula" class="form-control">
                        <button type="submit" class="btn btn-primary mt-3">Reservar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- Scripts -->
    <script>
        function abrirModalEliminar(id) {
            document.getElementById('idCitaEliminar').value = id;
            var modal = new bootstrap.Modal(document.getElementById('modalEliminar'));
            modal.show();
        }

        function abrirModalLiberar(id) {
            document.getElementById('idCitaLiberar').value = id;
            var modal = new bootstrap.Modal(document.getElementById('modalLiberar'));
            modal.show();
        }

        function abrirModalReservar(id) {
            document.getElementById('idCitaReservar').value = id;
            var modal = new bootstrap.Modal(document.getElementById('modalReservar'));
            modal.show();
        }
    </script>
</body>
</html>
