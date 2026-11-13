public class Lista {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    // 1. Agregar al final
    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }

    // 2. Agregar al inicio
    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    // 3. Agregar en posición específica (base 0)
    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == tamanio) {
            agregar(dato);
            return;
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        Nodo nuevo = new Nodo(dato);
        Nodo previo = actual.getAnterior();

        previo.setSiguiente(nuevo);
        nuevo.setAnterior(previo);
        nuevo.setSiguiente(actual);
        actual.setAnterior(nuevo);
        tamanio++;
    }

    // 4. Eliminar primero
    public Object eliminarPrimero() {
        if (cabeza == null) {
            return null;
        }
        Object datoEliminado = cabeza.getDato();
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        tamanio--;
        return datoEliminado;
    }

    // 5. Eliminar último
    public Object eliminarUltimo() {
        if (cola == null) {
            return null;
        }
        Object datoEliminado = cola.getDato();
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamanio--;
        return datoEliminado;
    }

    // 6. Eliminar en posición (base 0)
    public Object eliminarEnPosicion(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) {
            return eliminarPrimero();
        }
        if (indice == tamanio - 1) {
            return eliminarUltimo();
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        Object datoEliminado = actual.getDato();
        Nodo previo = actual.getAnterior();
        Nodo siguiente = actual.getSiguiente();

        previo.setSiguiente(siguiente);
        siguiente.setAnterior(previo);
        tamanio--;
        return datoEliminado;
    }

    // 7. Buscar dato por índice
    public Object buscarDato(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    // 8. Buscar dato por objeto (usa equals)
    public Object buscarDato(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    // 9. Verificar si contiene un dato
    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    // 10. Contar elementos
    public int cuentaElementos() {
        return tamanio;
    }

    // 11. Limpiar lista
    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    // 12. Mostrar de cabeza a cola
    public void mostrarAdelante() {
        if (cabeza == null) {
            System.out.println("No hay elementos registrados en la lista.");
            return;
        }
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    // 13. Mostrar de cola a cabeza
    public void mostrarAtras() {
        if (cola == null) {
            System.out.println("No hay elementos registrados en la lista.");
            return;
        }
        Nodo actual = cola;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }
}