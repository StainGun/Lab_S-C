package Estructuras;

import Estructuras.NodoSimple;
import Mundo.Cita;

public class ListaEnlazadaSimple {
    private NodoSimple cabeza;

    public ListaEnlazadaSimple() {
        cabeza = null;
    }

    public void agregar(Cita cita) {
        NodoSimple nuevoNodo = new NodoSimple(cita);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public void eliminar(int id) {
        if (cabeza == null) return;

        if (cabeza.cita.getId() == id) {
            cabeza = cabeza.siguiente;
            return;
        }

        NodoSimple actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.cita.getId() != id) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public Cita buscar(int id) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.cita.getId() == id) {
                return actual.cita;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Cita[] listar() {
        int contador = 0;
        NodoSimple actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        Cita[] citas = new Cita[contador];
        actual = cabeza;
        contador = 0;
        while (actual != null) {
            citas[contador] = actual.cita;
            contador++;
            actual = actual.siguiente;
        }
        return citas;
    }
}
