package Servlet;

import Mundo.GestionReportes;
import Mundo.Reporte;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/GenerarReporteServlet")
public class GenerarReporteServlet extends HttpServlet {
    private GestionReportes gestionReportes;

    @Override
    public void init() throws ServletException {
        // Obtener el contexto de la aplicación
        ServletContext context = getServletContext();

        // Obtener las rutas de los archivos necesarios
        String archivoPacientes = context.getRealPath("/data/Pacientes.txt");
        String archivoCitas = context.getRealPath("/data/Citas.txt");
        String archivoReportes = context.getRealPath("/data/Reportes.txt");

        // Inicializar GestionReportes con las rutas correspondientes
        gestionReportes = new GestionReportes(archivoReportes, archivoPacientes, archivoCitas);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Generar el reporte
            Reporte nuevoReporte = gestionReportes.generarReporte();

            // Establecer el reporte en el atributo de la solicitud
            request.setAttribute("reporte", nuevoReporte);
        } catch (IllegalStateException e) {
            // Si ocurre un error, mostrar mensaje
            request.setAttribute("mensaje", "Error al generar reporte: " + e.getMessage());
        }

        // Redirigir al JSP para mostrar el reporte
        request.getRequestDispatcher("reporte.jsp").forward(request, response);
    }
}
