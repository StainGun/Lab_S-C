package Estructuras;

import java.util.ArrayList;
import java.util.List;

public class ListaCircular<T> {
    private Nodo<T> head;
    private Nodo<T> tail;

    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (head == null) {
            head = tail = nuevoNodo;
            tail.setSiguiente(head);
        } else {
            tail.setSiguiente(nuevoNodo);
            tail = nuevoNodo;
            tail.setSiguiente(head);
        }
    }

    public List<T> listar() {
        List<T> elementos = new ArrayList<>();
        if (head != null) {
            Nodo<T> actual = head;
            do {
                elementos.add(actual.getElemento());
                actual = actual.getSiguiente();
            } while (actual != head);
        }
        return elementos;
    }
}
