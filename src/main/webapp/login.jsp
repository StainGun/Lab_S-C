<!DOCTYPE html>
<html>
    <head>
        <title>Slide Navbar</title>
        <link rel="stylesheet" type="text/css" href="styles/logins.css">
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
        
    </head>
    <body>
        <div class="logo-container">
            <a href="index.jsp">
                <img src="img/Icono.png" alt="Logo" class="logo">
            </a>
        </div>
        <div class="main">  	
            <input type="checkbox" id="chk" aria-hidden="true">

            <div class="signup">
                <form>
                    <label for="chk" aria-hidden="true">Registrate</label>
                    <div class="subtitle">Unete a la comunidad S&C</div>
                    <a href="nuevopaciente.jsp">
                        <button type="button">Unete</button>
                    </a>
                </form>

            </div>

            <div class="login">
                <form id="loginForm" method="post" action="LoginServlet">
                    <label for="chk" aria-hidden="true">Login</label>
                    <!-- Input para usuario -->
                    <input type="text" name="username" placeholder="Usuario" required>
                    <!-- Input para contraseña -->
                    <input type="password" name="password" placeholder="Contraseña" required>
                    <!-- Selección de tipo de usuario -->
                    <select id="userType" name="userType" required>
                        <option value="" disabled selected>Seleccione tipo de usuario</option>
                        <option value="paciente">Paciente</option>
                        <option value="admin">Administrador</option>
                    </select>
                    <!-- Botón de login -->
                    <button type="submit">Ingresar</button>
                </form>
            </div>

            <!-- Script para manejar redirección según tipo de usuario -->
            <script>
                document.getElementById('loginForm').addEventListener('submit', function (event) {
                    const userType = document.getElementById('userType').value;
                    if (userType === "admin") {
                        const username = document.querySelector('[name="username"]').value;
                        const password = document.querySelector('[name="password"]').value;
                        if (username === "admin" && password === "admin") {
                            window.location.href = "admin.jsp";
                            event.preventDefault(); // Prevent form submission
                        }
                    }
                });
            </script>
    </body>
</html>
