package Servlet;

import Mundo.GestionPacientes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionEliminarPacienteSv")
public class GestionEliminarPacienteSv extends HttpServlet {
    private GestionPacientes gestionPacientes;

    @Override
    public void init() throws ServletException {
        gestionPacientes = new GestionPacientes(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gestionPacientes.eliminarPaciente(id);
        response.sendRedirect("GestionAddPacientesSv");
    }
}
