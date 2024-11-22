package Servlet;

import Mundo.GestionPacientes;
import Mundo.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionAddPacientesSv")
public class GestionAddPacientesSv extends HttpServlet {
    private GestionPacientes gestionPacientes;

    @Override
    public void init() throws ServletException {
        gestionPacientes = new GestionPacientes(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String telefono = request.getParameter("telefono");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");

        Paciente nuevoPaciente = new Paciente(0, nombre, cedula, telefono, pass, email);
        gestionPacientes.agregarPaciente(nuevoPaciente);

        response.sendRedirect("GestionAddPacientesSv");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setAttribute("listaPacientes", gestionPacientes.listarPacientes());
        request.getRequestDispatcher("addPaciente.jsp").forward(request, response);
    }
}
