package Mundo;

public class Paciente {
    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String pass;
    private String email;

    // Constructor
    public Paciente(int id, String nombre, String cedula, String telefono, String pass, String email) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.pass = pass;
        this.email = email;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getTelefono() { return telefono; }
    public String getPass() { return pass; }
    public String getEmail() { return email; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setPass(String pass) { this.pass = pass; }
    public void setEmail(String email) { this.email = email; }
}
