package Mundo;

import Estructuras.ListaCircular;
import Estructuras.ListaEnlazadaDoble;
import Estructuras.ListaEnlazadaSimple;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import static jdk.jpackage.internal.Arguments.CLIOptions.context;

public class GestionReportes implements Serializable {
    private static final long serialVersionUID = 1L;
    private ListaCircular<Reporte> listaReportes;
    private String archivo;
    private transient String archivoPacientes;
    private transient String archivoCitas;

    private transient Paciente[] pacientes;
    private transient Cita[] citas;
    
    

    public GestionReportes(String archivo, String archivoPacientes, String archivoCitas) {
        this.listaReportes = new ListaCircular<>();
        this.archivo = archivo;
        this.archivoPacientes = archivoPacientes;
        this.archivoCitas = archivoCitas;
        cargarListas();
        
    }

    public GestionReportes(String archivo) {
        this.listaReportes = new ListaCircular<>();
        this.archivo = archivo;
    }

    private void cargarListas() {
        this.pacientes = cargarPacientesDesdeArchivo();
        this.citas = cargarCitasDesdeArchivo();
    }

    private Paciente[] cargarPacientesDesdeArchivo() {
        ListaEnlazadaDoble pacientesLista = new ListaEnlazadaDoble();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoPacientes))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String cedula = datos[2];
                String telefono = datos[3];
                String pass = datos[4];
                String email = datos[5];
                pacientesLista.agregar(new Paciente(id, nombre, cedula, telefono, pass, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientesLista.listar();
    }

    private Cita[] cargarCitasDesdeArchivo() {
        ListaEnlazadaSimple citasLista = new ListaEnlazadaSimple();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCitas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String doctor = datos[1];
                String dia = datos[2];
                String especialidad = datos[3];
                String nombrePaciente = datos.length > 4 ? datos[4] : null;

                Cita cita = new Cita(id, doctor, dia, especialidad);
                cita.setCedula(nombrePaciente);
                citasLista.agregar(cita);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return citasLista.listar();
    }

    public Reporte generarReporte() {
        if (pacientes == null || citas == null) {
            cargarListas(); // Asegura que las listas estén cargadas
        }

        int totalPacientes = pacientes.length;
        int pacientesConCita = contarPacientesConCita();
        int totalDoctores = contarDoctoresUnicos();
        int totalCitas = citas.length;
        int totalEspecialidades = contarEspecialidadesUnicas();

        Reporte reporte = new Reporte(
                totalPacientes,
                pacientesConCita,
                totalDoctores,
                totalCitas,
                pacientesConCita,
                totalEspecialidades
        );

        listaReportes.agregar(reporte);
        return reporte;
    }

    private int contarPacientesConCita() {
        int count = 0;
        for (Cita cita : citas) {
            if (cita.getCedula() != null) {
                count++;
            }
        }
        return count;
    }

    private int contarDoctoresUnicos() {
        Set<String> doctores = new HashSet<>();
        for (Cita cita : citas) {
            doctores.add(cita.getDoctor());
        }
        return doctores.size();
    }

    private int contarEspecialidadesUnicas() {
        Set<String> especialidades = new HashSet<>();
        for (Cita cita : citas) {
            especialidades.add(cita.getEspecialidad());
        }
        return especialidades.size();
    }

    public void serializar(String rutaSerializacion) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaSerializacion))) {
            out.writeObject(this);
        }
    }

    public static GestionReportes deserializar(String rutaSerializacion)
            throws IOException, ClassNotFoundException {
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaSerializacion))) {
            GestionReportes gestionReportes = (GestionReportes) in.readObject();
            
            gestionReportes.cargarListas();
            return gestionReportes;
        }
    }
}
