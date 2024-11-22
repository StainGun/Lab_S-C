package Servlet;

import Mundo.GestionCitas;
import Mundo.Cita;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GestionActualizarCitaSv")
public class GestionActualizarCitaSv extends HttpServlet {
    private GestionCitas gestionCitas;

    @Override
    public void init() throws ServletException {
        gestionCitas = new GestionCitas(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String doctor = request.getParameter("doctor");
        String dia = request.getParameter("dia");
        String especialidad = request.getParameter("especialidad");

        Cita citaActualizada = new Cita(id, doctor, dia, especialidad);
        gestionCitas.actualizarCita(citaActualizada);

        response.sendRedirect("GestionAddCitasSv");
    }
}
