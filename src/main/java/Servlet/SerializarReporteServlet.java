package Servlet;

import Mundo.GestionReportes;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import static jdk.jpackage.internal.Arguments.CLIOptions.context;

@WebServlet("/SerializarReporteServlet")
public class SerializarReporteServlet extends HttpServlet {
    private GestionReportes gestionReportes;
    private String rutaSerializacion;

    @Override
    public void init() throws ServletException {
        // Obtener el contexto de la aplicación
        ServletContext context = getServletContext();  // Aquí obtenemos el contexto

        // Definir la ruta para la serialización del archivo
        rutaSerializacion = context.getRealPath("/data/serializado/reportes.ser");

        // Rutas de archivos de pacientes y citas
        String archivoPacientes = context.getRealPath("/data/Pacientes.txt");
        String archivoCitas = context.getRealPath("/data/Citas.txt");

        // Inicializar GestionReportes con las rutas correspondientes
        try {
            gestionReportes = GestionReportes.deserializar(rutaSerializacion);
        } catch (IOException | ClassNotFoundException e) {
            // Si no se encuentra el archivo serializado, se crea una nueva instancia
            String archivoReportes = context.getRealPath("/data/Reportes.txt");
            gestionReportes = new GestionReportes(archivoReportes, archivoPacientes, archivoCitas);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("serializar".equals(accion)) {
            try {
                // Serializar los datos actuales
                gestionReportes.serializar(rutaSerializacion);
                response.getWriter().write("Reportes serializados correctamente.");
            } catch (IOException e) {
                response.getWriter().write("exito al erializar reportes: " );
            }
        } else if ("deserializar".equals(accion)) {
            try {
                // Deserializar los reportes
                gestionReportes = GestionReportes.deserializar(rutaSerializacion);
                response.getWriter().write("Reportes deserializados correctamente.");
            } catch (IOException e) {
                response.getWriter().write("exito al deserializar reportes: " );
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SerializarReporteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
