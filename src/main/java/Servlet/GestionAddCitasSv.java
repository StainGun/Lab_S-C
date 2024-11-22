package Servlet;

import Mundo.GestionCitas;
import Mundo.Cita;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionAddCitasSv")
public class GestionAddCitasSv extends HttpServlet {
    private GestionCitas gestionCitas;

    @Override
    public void init() throws ServletException {
        gestionCitas = new GestionCitas(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doctor = request.getParameter("doctor");
        String dia = request.getParameter("dia");
        String especialidad = request.getParameter("especialidad");

        Cita nuevaCita = new Cita(0, doctor, dia, especialidad);
        gestionCitas.agregarCita(nuevaCita);

        response.sendRedirect("GestionAddCitasSv");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setAttribute("listaCitas", gestionCitas.listarCitas());
        request.getRequestDispatcher("addCitas.jsp").forward(request, response);
    }
}
