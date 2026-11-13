public class Pila {
    private Nodo cima;
    private int tamanio;

    public Pila() {
        this.cima = null;
        this.tamanio = 0;
    }

    // 1. Apilar (entra por la cima - LIFO)
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    // 2. Desapilar (sale el último que entró - LIFO)
    public Object desapilar() {
        if (esVacia()) {
            return null;
        }
        Object dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

    // 3. Ver el elemento en la cima sin sacarlo
    public Object peek() {
        if (esVacia()) {
            return null;
        }
        return cima.getDato();
    }

    // 4. Tamaño de la pila
    public int tamanio() {
        return tamanio;
    }

    // 5. Verificar si está vacía
    public boolean esVacia() {
        return cima == null;
    }

    // 6. Verificar si contiene un elemento
    public boolean contiene(Object dato) {
        return buscar(dato) != null;
    }

    // 7. Limpiar la pila
    public void limpiar() {
        cima = null;
        tamanio = 0;
    }

    // 8. Mostrar historial desde el más reciente al más antiguo
    public void mostrar() {
        if (esVacia()) {
            System.out.println("El historial de servicios atendidos está vacío.");
            return;
        }
        Nodo actual = cima;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());
            actual = actual.getSiguiente();
            posicion++;
        }
    }

    // 9. Buscar un elemento en la pila
    public Object buscar(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}