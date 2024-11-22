package Servlet;

import Mundo.GestionCitas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionEliminarCitaSv")
public class GestionEliminarCitaSv extends HttpServlet {
    private GestionCitas gestionCitas;

    @Override
    public void init() throws ServletException {
        gestionCitas = new GestionCitas(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gestionCitas.eliminarCita(id);
        response.sendRedirect("GestionAddCitasSv");
    }
}
