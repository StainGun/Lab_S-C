package Excepcion;

public class PacienteNoRegistradoException extends Exception {
    public PacienteNoRegistradoException(String mensaje) {
        super(mensaje);
    }
}
