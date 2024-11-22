package Estructuras;

import Mundo.Paciente;

public class NodoDoble {
    Paciente paciente;
    NodoDoble siguiente;
    NodoDoble anterior;

    public NodoDoble(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
        this.anterior = null;
    }
}
