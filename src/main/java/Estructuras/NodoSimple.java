package Estructuras;

import Mundo.Cita;

public class NodoSimple {
    Cita cita;
    NodoSimple siguiente;

    public NodoSimple(Cita cita) {
        this.cita = cita;
        this.siguiente = null;
    }
}
