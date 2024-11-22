package Servlet;

import Mundo.GestionPacientes;
import Mundo.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionActualizarPacienteSv")
public class GestionActualizarPacienteSv extends HttpServlet {
    private GestionPacientes gestionPacientes;

    @Override
    public void init() throws ServletException {
        gestionPacientes = new GestionPacientes(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String telefono = request.getParameter("telefono");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");

        Paciente pacienteActualizado = new Paciente(id, nombre, cedula, telefono, pass, email);
        gestionPacientes.actualizarPaciente(pacienteActualizado);

        response.sendRedirect("GestionAddPacientesSv");
    }
}
