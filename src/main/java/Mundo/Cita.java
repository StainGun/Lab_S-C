package Mundo;

public class Cita {
    private int id;
    private String doctor;
    private String dia;
    private String especialidad;
    private String cedula; // Nuevo atributo

    // Constructor
    public Cita(int id, String doctor, String dia, String especialidad) {
        this.id = id;
        this.doctor = doctor;
        this.dia = dia;
        this.especialidad = especialidad;
        this.cedula = null; // Inicialmente sin paciente asignado
    }

    // Getters
    public int getId() { return id; }
    public String getDoctor() { return doctor; }
    public String getDia() { return dia; }
    public String getEspecialidad() { return especialidad; }
    public String getCedula() { return cedula; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setDoctor(String doctor) { this.doctor = doctor; }
    public void setDia(String dia) { this.dia = dia; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setCedula(String cedula) { this.cedula = cedula; }
}
