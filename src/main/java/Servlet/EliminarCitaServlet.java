package Servlet;

import Mundo.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EliminarCitaServlet")
public class EliminarCitaServlet extends HttpServlet {

    private GestionCitas gestionCitas;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        gestionCitas = new GestionCitas(context);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        gestionCitas.cargarDesdeArchivo(); // Asegura la carga actualizada
        Cita[] citas = gestionCitas.listarCitas();
        if (citas == null || citas.length == 0) {
            request.setAttribute("error", "No hay citas disponibles.");
        } else {
            request.setAttribute("listaCitas", citas);
        }
        request.getRequestDispatcher("eliminarcita.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String idCitaStr = request.getParameter("idCita");
    String accion = request.getParameter("accion");
    String pass = request.getParameter("pass");

    try {
        int idCita = Integer.parseInt(idCitaStr);

        // Verificar contraseña antes de proceder con cualquier acción
        if ("admin".equals(pass)) {
            
            switch (accion) {
                case "eliminar":
                    gestionCitas.eliminarCita(idCita);
                    request.setAttribute("mensaje", "Cita eliminada con éxito.");
                    break;
                case "liberar":
                    gestionCitas.liberarCita(idCita);
                    request.setAttribute("mensaje", "Cita liberada con éxito.");
                    break;
                default:
                    throw new IllegalArgumentException("Acción no válida.");
            }
        }
        else{
            throw new SecurityException("Contraseña incorrecta.");
        }
    } catch (SecurityException e) {
        request.setAttribute("error", e.getMessage());
    } catch (Exception e) {
        request.setAttribute("error", "Error al procesar la solicitud.");
    }

    doGet(request, response); // Recarga la lista para reflejar cambios
}


}
