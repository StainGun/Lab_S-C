package Estructuras;

import Estructuras.NodoDoble;
import Mundo.Paciente;

public class ListaEnlazadaDoble {
    private NodoDoble cabeza;

    public ListaEnlazadaDoble() {
        cabeza = null;
    }

    public void agregar(Paciente paciente) {
        NodoDoble nuevoNodo = new NodoDoble(paciente);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoDoble actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
            nuevoNodo.anterior = actual;
        }
    }

    public void eliminar(int id) {
        NodoDoble actual = cabeza;
        while (actual != null && actual.paciente.getId() != id) {
            actual = actual.siguiente;
        }
        if (actual != null) {
            if (actual.anterior != null) {
                actual.anterior.siguiente = actual.siguiente;
            } else {
                cabeza = actual.siguiente; // Si es el primero
            }
            if (actual.siguiente != null) {
                actual.siguiente.anterior = actual.anterior;
            }
        }
    }

    public Paciente buscar(int id) {
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.paciente.getId() == id) {
                return actual.paciente;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Paciente[] listar() {
        int contador = 0;
        NodoDoble actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        Paciente[] pacientes = new Paciente[contador];
        actual = cabeza;
        contador = 0;
        while (actual != null) {
            pacientes[contador] = actual.paciente;
            contador++;
            actual = actual.siguiente;
        }
        return pacientes;
    }
}
