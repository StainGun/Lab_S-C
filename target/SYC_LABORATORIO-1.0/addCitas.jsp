<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Cita"%>
<%@page import="java.util.List"%>
<%@include file="../lib/header.jsp" %>


<html lang="es">

    <body>
        <%@include file="../lib/navbarA.jsp" %>

        <div class="container">
            <!-- Formulario -->
            <div class="form-container">
                <h2>Formulario de Registro de Citas</h2>
                <form action="GestionAddCitasSv" method="post" id="form-cita">
                    <label for="doctor">Doctor:</label>
                    <input type="text" class="form-control" id="doctor" name="doctor" required>

                    <label for="especialidad">Especialidad:</label>
                    <input type="text" class="form-control" id="especialidad" name="especialidad" required>

                    <label for="fecha">Fecha:</label>
                    <div class="input-group">
                        <input type="date" id="dia" name="dia" class="form-control" required>


                        </button>
                    </div>

                    <input type="submit" class="btn btn-success mt-3" value="Agregar Cita">
                </form>
            </div>

            <!-- Lista de citas -->
            <div class="table-container mt-4">
                <h2>Lista de Citas</h2>
                <form action="GestionAddCitasSv" method="get" class="mb-4">
                    <button type="submit" class="btn btn-primary">Recargar Lista</button>
                </form>
                <table class="table table-dark content-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Doctor</th>
                            <th>Día</th>
                            <th>Especialidad</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            Cita[] citas = (Cita[]) request.getAttribute("listaCitas");
                            if (citas != null) {
                                for (Cita cita : citas) {
                        %>
                        <tr>
                            <td><%= cita.getId() %></td>
                            <td><%= cita.getDoctor() %></td>
                            <td><%= cita.getDia() %></td>
                            <td><%= cita.getEspecialidad() %></td>
                            <td>
                                <!-- Contenedor para los botones -->
                                <div style="display: flex; gap: 10px;">
                                    <!-- Botón de Editar -->
                                    <div class="button-icon edit" onclick="editarCita(<%= cita.getId() %>, '<%= cita.getDoctor() %>', '<%= cita.getDia() %>', '<%= cita.getEspecialidad() %>')">
                                        <i class="fas fa-pencil-alt"></i>
                                    </div>

                                    <!-- Botón de Eliminar -->
                                    <form action="GestionEliminarCitaSv" method="post" style="display: flex; flex-direction: column; align-items: center;" onsubmit="mostrarToast()">
                                        <input type="hidden" name="id" value="<%= cita.getId() %>">
                                        <button type="submit" class="button-icon delete">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>


                        </tr>
                        <% 
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Modal para Editar Cita -->
        <div class="modal fade" id="modalEditarCita" tabindex="-1" aria-labelledby="modalEditarCitaLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalEditarCitaLabel">Editar Cita</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formEditarCita" action="GestionActualizarCitaSv" method="post">
                            <input type="hidden" id="idEditarCita" name="id">
                            <label for="doctorEditar">Doctor:</label>
                            <input type="text" class="form-control" id="doctorEditarCita" name="doctor" required>
                            <label for="diaEditar">Día:</label>
                            <input type="text" class="form-control" id="diaEditarCita" name="dia" required>
                            <label for="especialidadEditar">Especialidad:</label>
                            <input type="text" class="form-control" id="especialidadEditarCita" name="especialidad" required>
                            <input type="submit" class="btn btn-success mt-3" value="Actualizar">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Toast de eliminación -->
        <div class="toast" id="toastEliminar" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-body">
                ¡Cita eliminada exitosamente!
            </div>
        </div>

        <script>
            function mostrarToast() {
                // Mostrar el toast de eliminación
                var toastEl = document.getElementById('toastEliminar');
                var toast = new bootstrap.Toast(toastEl, {
                    delay: 5000 // Duración del toast (5 segundos)
                });
                toast.show();
            }
        </script>
        <script>
            function editarCita(id, doctor, dia, especialidad) {
                document.getElementById("idEditarCita").value = id;
                document.getElementById("doctorEditarCita").value = doctor;
                document.getElementById("diaEditarCita").value = dia;
                document.getElementById("especialidadEditarCita").value = especialidad;
                var modal = new bootstrap.Modal(document.getElementById('modalEditarCita'));
                modal.show();
            }

            function mostrarToast() {
                var toastEl = document.getElementById('toastEliminar');
                var toast = new bootstrap.Toast(toastEl, {
                    delay: 10000 // Duración en milisegundos (10 segundos)
                });
                toast.show();
            }

        </script>
        <script>
            function formatearFecha(input) {
                const fecha = new Date(input.value);
                const dia = String(fecha.getDate()).padStart(2, '0');
                const mes = String(fecha.getMonth() + 1).padStart(2, '0');
                const anio = fecha.getFullYear();
                input.value = `${dia}/${mes}/${anio}`;
                    }
        </script>

        <!-- Incluimos los scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
