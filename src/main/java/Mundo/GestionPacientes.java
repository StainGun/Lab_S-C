package Mundo;

import Estructuras.ListaEnlazadaDoble;
import java.io.*;
import javax.servlet.ServletContext;

public class GestionPacientes {
    private ListaEnlazadaDoble listaPacientes;
    private String archivo;
    private int siguienteId;

    public GestionPacientes(ServletContext context) {
        listaPacientes = new ListaEnlazadaDoble();
        archivo = context.getRealPath("/data/Pacientes.txt");
        cargarDesdeArchivo();
        siguienteId = listaPacientes.listar().length + 1;
    }

    public void agregarPaciente(Paciente nuevoPaciente) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        nuevoPaciente.setId(siguienteId++);
        listaPacientes.agregar(nuevoPaciente);
        guardarEnArchivo(nuevoPaciente);
    }

    public void eliminarPaciente(int id) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        listaPacientes.eliminar(id);
        guardarTodaLaListaEnArchivo();
    }

    public void actualizarPaciente(Paciente pacienteActualizado) {
        cargarDesdeArchivo();  // Asegura que los datos estén actualizados
        Paciente paciente = listaPacientes.buscar(pacienteActualizado.getId());
        if (paciente != null) {
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setCedula(pacienteActualizado.getCedula());
            paciente.setTelefono(pacienteActualizado.getTelefono());
            paciente.setPass(pacienteActualizado.getPass());
            paciente.setEmail(pacienteActualizado.getEmail());
            guardarTodaLaListaEnArchivo();
        }
    }

    public Paciente[] listarPacientes() {
        cargarDesdeArchivo();  // Recarga antes de devolver la lista
        return listaPacientes.listar();
    }

    private void cargarDesdeArchivo() {
        listaPacientes = new ListaEnlazadaDoble();  // Reinicia la lista antes de cargar
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String cedula = datos[2];
                String telefono = datos[3];
                String pass = datos[4];
                String email = datos[5];
                listaPacientes.agregar(new Paciente(id, nombre, cedula, telefono, pass, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEnArchivo(Paciente paciente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(paciente.getId() + "," + paciente.getNombre() + "," + paciente.getCedula() + "," +
                         paciente.getTelefono() + "," + paciente.getPass() + "," + paciente.getEmail());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarTodaLaListaEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Paciente paciente : listaPacientes.listar()) {
                writer.write(paciente.getId() + "," + paciente.getNombre() + "," + paciente.getCedula() + "," +
                             paciente.getTelefono() + "," + paciente.getPass() + "," + paciente.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
