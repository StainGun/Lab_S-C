<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Mundo.Paciente"%>
<%@page import="java.util.List"%>
<%@include file="../lib/header.jsp" %>

<body>
    

    <div class="container">
        <!-- Formulario para agregar un nuevo paciente -->
        <div class="form-container p-4 rounded">
            <h2>Formulario de Registro de Pacientes</h2>
            <form action="NuevoPaciente" method="post">
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

                <input type="submit" class="btn btn-success mt-3" value="Registrarse" href="nuevopaciente.jsp">
            </form>
        </div>
    </div>
</body></html>