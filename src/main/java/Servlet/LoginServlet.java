package Servlet;

import Mundo.GestionPacientes;
import Mundo.Paciente;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private GestionPacientes gestionPacientes;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        gestionPacientes = new GestionPacientes(context);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        if ("paciente".equals(userType)) {
            Paciente[] pacientes = gestionPacientes.listarPacientes();
            for (Paciente paciente : pacientes) {
                if (paciente.getNombre().equals(username) && paciente.getPass().equals(password)) {
                    request.getSession().setAttribute("paciente", paciente);
                    response.sendRedirect("paciente.jsp");
                    return;
                }
            }
            response.sendRedirect("login.jsp?error=Usuario o contraseña incorrectos");
        } else if ("admin".equals(userType)) {
            if ("admin".equals(username) && "admin".equals(password)) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Credenciales de administrador incorrectas");
            }
        }
    }
}
