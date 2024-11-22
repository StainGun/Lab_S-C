package Excepcion;

public class ManejadorExcepciones {
    public static String obtenerMensaje(Exception e) {
        if (e instanceof NumberFormatException) {
            return "El ID ingresado no es un número válido.";
        } else if (e.getMessage() != null) {
            return e.getMessage();
        } else {
            return "Ha ocurrido un error inesperado.";
        }
    }
}
