package Mundo;

import Excepcion.PacienteNoRegistradoException;
import Excepcion.CitaNoEncontradaException;
import Estructuras.ListaEnlazadaSimple;
import java.io.*;
import javax.servlet.ServletContext;

public class GestionCitas {

    private ListaEnlazadaSimple listaCitas;
    private String archivo;
    private int siguienteId;

    public GestionCitas(ServletContext context) {
        listaCitas = new ListaEnlazadaSimple();
        archivo = context.getRealPath("/data/Citas.txt");
        cargarDesdeArchivo();
        siguienteId = listaCitas.listar().length + 1;
    }

    public void agregarCita(Cita nuevaCita) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        nuevaCita.setId(siguienteId++);
        nuevaCita.setCedula(null);
        listaCitas.agregar(nuevaCita);
        guardarEnArchivo(nuevaCita);
    }

    public void eliminarCita(int id) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        listaCitas.eliminar(id);
        guardarTodaLaListaEnArchivo();
    }

    public void actualizarCita(Cita citaActualizada) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        Cita cita = listaCitas.buscar(citaActualizada.getId());
        if (cita != null) {
            cita.setDoctor(citaActualizada.getDoctor());
            cita.setDia(citaActualizada.getDia());
            cita.setEspecialidad(citaActualizada.getEspecialidad());
            cita.setCedula(citaActualizada.getCedula());
            guardarTodaLaListaEnArchivo();
        }
    }

    public Cita[] listarCitas() {
        cargarDesdeArchivo();  // Recarga antes de devolver la lista
        return listaCitas.listar();
    }

    public void cargarDesdeArchivo() {
        listaCitas = new ListaEnlazadaSimple();  // Reinicia la lista antes de cargar
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String doctor = datos[1];
                String dia = datos[2];
                String especialidad = datos[3];
                String Cedula = datos.length > 4 ? datos[4] : null;

                Cita cita = new Cita(id, doctor, dia, especialidad);
                cita.setCedula(Cedula);
                listaCitas.agregar(cita);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEnArchivo(Cita cita) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(cita.getId() + "," + cita.getDoctor() + "," + cita.getDia() + ","
                    + cita.getEspecialidad() + ","
                    + (cita.getCedula() != null ? cita.getCedula() : ""));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarTodaLaListaEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Cita cita : listaCitas.listar()) {
                writer.write(cita.getId() + "," + cita.getDoctor() + "," + cita.getDia() + ","
                        + cita.getEspecialidad() + ","
                        + (cita.getCedula() != null ? cita.getCedula() : ""));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asignarCitaAPaciente(int idCita, String cedula, GestionPacientes gestionPacientes)
            throws PacienteNoRegistradoException, CitaNoEncontradaException {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        Cita cita = listaCitas.buscar(idCita);
        if (cita == null) {
            throw new CitaNoEncontradaException("La cita con ID " + idCita + " no existe.");
        }

        if (cita.getCedula() != null) {
            throw new IllegalStateException("La cita ya está reservada.");
        }

        Paciente[] pacientes = gestionPacientes.listarPacientes();
        Paciente pacienteEncontrado = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                pacienteEncontrado = paciente;
                break;
            }
        }
        if (pacienteEncontrado == null) {
            throw new PacienteNoRegistradoException(
                    "El paciente con la cedula '" + cedula + "' no está registrado.");
        }

        cita.setCedula(pacienteEncontrado.getNombre()+ " CC:"+pacienteEncontrado.getCedula() );
        guardarTodaLaListaEnArchivo();
    }
    // Método para eliminar solo el nombre del paciente de la cita

    public void liberarCita(int id) throws CitaNoEncontradaException {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        Cita cita = listaCitas.buscar(id);
        if (cita == null) {
            throw new CitaNoEncontradaException("La cita con ID " + id + " no existe.");
        }
        cita.setCedula(null); // Elimina el nombre del paciente
        guardarTodaLaListaEnArchivo(); // Guarda cambios en el archivo
    }

    public void eliminarCitaConContraseña(int id) {
        
        eliminarCita(id); // Reutiliza el método existente de eliminar
    }

}
