<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Paciente"%>
<%@page import="java.util.List"%>
<%@include file="../lib/header.jsp" %>

<body>
    <%@include file="../lib/navbarA.jsp" %>


    <div class="container">
        <!-- Formulario para agregar un nuevo paciente -->
        <div class="form-container p-4 rounded">
            <h2>Formulario de Registro de Pacientes</h2>
            <form action="GestionAddPacientesSv" method="post">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>

                <label for="cedula">Cedula:</label>
                <input type="text" class="form-control" id="cedula" name="cedula" required>

                <label for="telefono">Teléfono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" required>

                <label for="pass">Contraseña</label>
                <input type="text" class="form-control" id="pass" name="pass" required>

                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>

                <input type="submit" class="btn btn-success mt-3" value="Agregar Paciente">
            </form>
        </div>

        <!-- Lista de pacientes -->
        <div class="table-container mt-4">
            <h2>Lista de Pacientes</h2>
            <form action="GestionAddPacientesSv" method="get" class="mb-4">
                <button type="submit" class="btn btn-primary">Recargar Lista</button>
            </form>
            <table class="table table-dark content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Cedula</th>
                        <th>Teléfono</th>
                        <th>Pass</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        Paciente[] pacientes = (Paciente[]) request.getAttribute("listaPacientes");
                        if (pacientes != null) {
                            for (Paciente paciente : pacientes) {
                    %>
                    <tr>
                        <td><%= paciente.getId() %></td>
                        <td><%= paciente.getNombre() %></td>
                        <td><%= paciente.getCedula() %></td>
                        <td><%= paciente.getTelefono() %></td>
                        <td><%= paciente.getPass() %></td>
                        <td><%= paciente.getEmail() %></td>
                        <td>
                            <!-- Contenedor para los botones -->
                            <div style="display: flex; gap: 10px;">
                                <!-- Botón de Editar -->
                                <div class="button-icon edit" onclick="editarPaciente(<%= paciente.getId() %>, '<%= paciente.getNombre() %>', '<%= paciente.getCedula() %>', '<%= paciente.getTelefono() %>', '<%= paciente.getPass() %>', '<%= paciente.getEmail() %>')">
                                    <i class="fas fa-pencil-alt"></i>
                                </div>

                                <!-- Botón de Eliminar -->
                                <form action="GestionEliminarPacienteSv" method="post" style="display: flex; flex-direction: column; align-items: center;" onsubmit="mostrarToast()">
                                    <input type="hidden" name="id" value="<%= paciente.getId() %>">
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

    <!-- Modal para Editar Paciente -->
    <div class="modal fade" id="modalEditarPaciente" tabindex="-1" aria-labelledby="modalEditarPacienteLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalEditarPacienteLabel">Editar Paciente</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="formEditarPaciente" action="GestionActualizarPacienteSv" method="post">
                        <input type="hidden" id="idEditarPaciente" name="id">
                        <label for="nombreEditar">Nombre:</label>
                        <input type="text" id="nombreEditarPaciente" name="nombre" required>
                        <label for="cedulaEditar">Cédula:</label>
                        <input type="text" id="cedulaEditarPaciente" name="cedula" required>
                        <label for="telefonoEditar">Teléfono:</label>
                        <input type="text" id="telefonoEditarPaciente" name="telefono" required>
                        <label for="passEditar">Contraseña:</label>
                        <input type="password" id="passEditarPaciente" name="pass" required>
                        <label for="emailEditar">Email:</label>
                        <input type="email" id="emailEditarPaciente" name="email" required>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Actualizar">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>



    <!-- Toast de Eliminación Exitosa -->
    <div id="toastEliminar" class="toast position-fixed bottom-0 end-0 p-3" style="z-index: 11;">
        <div class="toast-body bg-danger text-white">
            Eliminado con éxito
        </div>
    </div>

    <script>
        function editarPaciente(id, nombre, cedula, telefono, pass, email) {
            document.getElementById("idEditarPaciente").value = id;
            document.getElementById("nombreEditarPaciente").value = nombre;
            document.getElementById("cedulaEditarPaciente").value = cedula;
            document.getElementById("telefonoEditarPaciente").value = telefono;
            document.getElementById("passEditarPaciente").value = pass;
            document.getElementById("emailEditarPaciente").value = email;
            var modal = new bootstrap.Modal(document.getElementById('modalEditarPaciente'));
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

    <!-- Incluimos los scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
