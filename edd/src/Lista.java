package Clases;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Collection<T> {

    //----------------------------Nodo---------------------------
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
        
    }

    //----------------------------Iterador---------------------------
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente;
        public Nodo actual;

        public Iterador(){
            actual = cabeza;
            siguiente = cabeza.siguiente;
        }

        

        @Override
        public boolean hasNext(){
            return siguiente != null;
        }

        @Override
        public T next(){
            if(!hasNext())
                throw new NoSuchElementException();

            T regresar = siguiente.elemento;
            
            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;
        }
        
        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }
        
        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }
        
        @Override
        public void end() {
            this.anterior = cola;
            this.siguiente = null;
        }
        
    }
    
    //----------------------------Lista---------------------------
    
    private Nodo cabeza;
    private Nodo cola;
    private int longitud;
    
    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }
    
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.cola = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.cola = nuevo;
        }
        else{
            this.cola.siguiente = nuevo;
            nuevo.anterior = this.cola;
            this.cola = nuevo;
        }
        longitud++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if(elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista.
     * 
     * @param elemento el elemento a eliminar.
     */ 
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);

        if(n==null){
            return false;
        }

        if(longitud == 1){
            empty();
            return true;
        }

        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud --;
            return true;
        }
        if (n == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
            longitud --;
            return true;
        }

        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longitud --;
        return true;
    }    



    /**
     * Regresa un elemento de la lista. (cola)
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = cola.elemento;
        cola = cola.anterior;
        cola.siguiente = null;
        longitud --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longitud;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     * 
     */
    public void empty(){
        cabeza = cola = null;
        longitud = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longitud == 0;
    }

    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }


    
    /**
     * Metodo que invierte el orden de la lista.
     * 
     */
    public void reverse() {
        Lista doubleLinkedList = new Lista<>();

        Nodo aux = cola;
        while(aux != null){
            doubleLinkedList.agregaInicio(aux.elemento);
            aux = aux.anterior;
        }
        clear();
        for(int j = 0; j < doubleLinkedList.size(); j++){
            agregaInicio((T) doubleLinkedList.get(j));
        }
    }

    /**
     * Obtiene el elemento en la posición <i>i</i>.
     *
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    public T get(int i) throws IndexOutOfBoundsException {

        if( i < 0 || i >= longitud){
            throw new IndexOutOfBoundsException();
        }
        if(isEmpty()){
            return null;
        }
        if(i == 0){
            return cabeza.elemento;
        }
        if(i == longitud-1){
            return cola.elemento;
        }
        Nodo aux;
        if(i <= longitud/2){
            aux = cabeza;
            for(int j = 0; j < i; j++){
                aux = aux.siguiente;
            }
        }else{
            aux = cola;
            for(int j = longitud; j > i+1; j--){
                aux = aux.anterior;
            }
        }
        return aux.elemento;
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    public void clear() {
        cabeza = null;
        cola = null;
        longitud = 0;
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString() {
        String listaString = "";
        Nodo nodo = cabeza;

        if(longitud == 0){
            return "";
        }
        
        while(nodo != null){
            listaString = listaString + nodo.elemento + " -> ";
            nodo = nodo.siguiente;
        }

        return listaString.substring(0, listaString.length()-4);
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * @return 
     * 
     */
    public boolean append(Lista<T> lista) {
        // Tu codigo aqui
        if(this.equals(lista)){
            Nodo nodo = lista.cabeza;
            while(nodo != null){
                this.add(nodo.elemento);
                nodo = nodo.siguiente;
            }
        }
        return false;
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * 
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf(T elemento) {
        // Tu codigo aqui

        if (elemento == null){
            throw new IllegalArgumentException("Se está intentando comparar un null");
        }
        Nodo nodo = cabeza;
        int contador = 0;

        while(nodo != null){
            if(nodo.elemento == elemento){
                return contador;
            }
            contador++;
            nodo = nodo.siguiente;
        }


        return -1;

    }
    
    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        // Tu codigo aqui
        if(i<0){
            agregaInicio(elemento);
        }else if(i>=this.longitud){
            agregaFinal(elemento);
        }else{	    
            Nodo aux = this.cabeza;
            Nodo nuevo = new Nodo(elemento);
            int cont = 0;
            while(aux!=null && cont!=i){
            aux = aux.siguiente;
            cont++;
            }
            nuevo.siguiente = aux;
            nuevo.anterior = aux.anterior;	    
            aux.anterior.siguiente = nuevo;
            aux.anterior = nuevo;	    	    
            this.longitud++;
        }
        return ;
    }

    private Nodo getCabeza(){
    return cabeza;
    }
    /* Método get para obtener a ultimo */
    private Nodo getCola(){
    return cola;
    }
    /* Método set para reasignar la cabeza */
    private void setCabeza(Nodo cabeza){
    this.cabeza = cabeza;
    }
    /* Método set para reasignar a ultimo */
    private void setCola(Nodo cola){
    this.cola = cola;
    }    
    // Tu comentario
    public void mezclaAlternada(Lista<T> lista){
        Nodo n = lista.getCabeza(); 
	    int i = 1;                  
 	    while(n!=null){           
	        insert(i,n.elemento);
	        n = n.siguiente;      
	        i = i+2;             
	    }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }


    public char[] Union(Lista<Integer> segunda) {
        return null;
    }
}
