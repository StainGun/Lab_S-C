package Servlet;

import Excepcion.ManejadorExcepciones;
import Mundo.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ReservarCitaServlet")
public class ReservarCitaServlet extends HttpServlet {
    private GestionCitas gestionCitas;
    private GestionPacientes gestionPacientes;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        gestionCitas = new GestionCitas(context);
        gestionPacientes = new GestionPacientes(context);
        gestionCitas.listarCitas(); // Asegura la carga inicial de citas
    }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Cargar la lista de citas
    gestionCitas.cargarDesdeArchivo(); // Asegura la carga actualizada
    Cita[] citas = gestionCitas.listarCitas();
    if (citas == null || citas.length == 0) {
        request.setAttribute("error", "No hay citas disponibles.");
    } else {
        request.setAttribute("listaCitas", citas);
    }
    // Redirigir al JSP
    request.getRequestDispatcher("asignarcita.jsp").forward(request, response);
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCitaStr = request.getParameter("idCita");
        String cedula = request.getParameter("cedula");

        try {
            int idCita = Integer.parseInt(idCitaStr);
            gestionCitas.asignarCitaAPaciente(idCita, cedula, gestionPacientes);
            request.setAttribute("mensaje", "Cita reservada con éxito.");
        } catch (Exception e) {
            String mensajeError = ManejadorExcepciones.obtenerMensaje(e);
            request.setAttribute("error", mensajeError);
        }

        Cita[] citas = gestionCitas.listarCitas();
        request.setAttribute("listaCitas", citas);
        request.getRequestDispatcher("asignarcita.jsp").forward(request, response);
    }
}
