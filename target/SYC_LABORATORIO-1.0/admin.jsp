<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Opciones de Administrador</title>
    <link rel="stylesheet" href="styles/index.css"> <!-- Vincula el CSS externo -->
</head>
<body>

    <div class="fondo-opaco"></div>

    <div class="contenido">
        <!-- Título y botones -->
        <div class="opciones">
            <h1>Opciones de Administrador</h1>
            <div class="botones">
                <a href="addCitas.jsp" class="boton">Crear y Administrar Citas</a>
                <p>Crea nuevas citas, elimina y edita citas.</p>
                
                <a href="addPaciente.jsp" class="boton">Administrar Pacientes</a>
                <p>Crea nuevos pacientes, elimina y edita pacientes.</p>
                
                <a href="asignarcita.jsp" class="boton">Asignar Citas</a>
                <p>Asigna manualmente una cita.</p>
                
                <a href="eliminarcita.jsp" class="boton">Administrar Citas</a>
                <p>Asigna, elimina o libera una cita.</p>
                
                <a href="reporte.jsp" class="boton">Generar Reportes</a>
                <p>Genera un reporte de las citas actuales usando serialización.</p>
            </div>
        </div>

        <!-- Imagen con enlace -->
        <div class="logo">
            <a href="index.jsp">
                <img src="img/Logo.png" alt="Logo S&C">
            </a>
        </div>
    </div>

</body>
</html>
