public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // 1. Encolar (entra por el final - FIFO)
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    // 2. Desencolar (sale por el frente - FIFO)
    public Object desencolar() {
        if (esVacia()) {
            return null;
        }
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    // 3. Ver el siguiente en turno sin sacarlo
    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return frente.getDato();
    }

    // 4. Tamaño de la cola
    public int tamanio() {
        return tamanio;
    }

    // 5. Verificar si está vacía
    public boolean esVacia() {
        return frente == null;
    }

    // 6. Verificar si contiene un elemento
    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // 7. Limpiar la cola
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // 8. Mostrar elementos de la cola en orden de llegada
    public void mostrar() {
        if (esVacia()) {
            System.out.println("No hay clientes pendientes en espera.");
            return;
        }
        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}
