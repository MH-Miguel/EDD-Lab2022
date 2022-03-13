package Clases;
import java.util.Iterator;

public class Practica1 {
    


    /* 
    *Dado un ejemplar de nuestra de Lista ordenada necesitamos agregamos un elemento
    *de manera ordenada. 
    *Tiene complejidad de Tiempo: O(n), ya que en el peor caso, nuestro metodo sólo recorrera la 
    *lista una vez con nuestro iterador.
    *Espacio: O(1), ya que no se crean listas nuevas, solo utilizamos el espacio que ya se tiene de 
    *la lista, más un nodo que contiene el nuevo elemento que se añade.
    */ 
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        int contador=0;
        IteradorLista<Integer> iter = lista.iteradorLista();
        while(iter.hasNext()){
            
            if(nuevo>=iter.next()){
                //comparar= iter.next();
                contador++;
            }else{
                lista.insert(contador+1, nuevo);
                return lista;
            }
        }
        lista.insert(contador+1, nuevo);
        return lista;
        //Tu codigo aqui
    }

    /**
     *Dadas dos listas, se obtiene la union de sus elementos, sin importar el orden y
     *quitando duplicados.
     *Tiempo: O(n * m). Ya que la lista de tamaño mayor se recorre el número de elementos
     *que contiene la segunda lista.
     *Espacio: O(n + m). Ya que se utila el espacio de una de las listas más los nodos que ocupa
     *la segunda lista.
     *Creo que para mejorar el tiempo de ejecución, se tendría que utilizar una información extra 
     *para crear un método que elimine elementos repetidos en un tiempo más eficaz y de esta manera 
     *ya solo unir sus elementos. 
     *con listas que no tengan ningun elemento repetido.
    */
    public static void Union(Lista<Integer> lista1,Lista<Integer> lista2) {
        if( lista1.size()>lista2.size() ){
            IteradorLista iteraLu = lista1.iteradorLista();
            while(iteraLu.hasNext()){
                Integer elemento = (Integer)iteraLu.next();
            if(lista2.contains(elemento)){
                lista1.delete(elemento);
            }
            }
            lista1.append(lista2);		    
        }else{
            IteradorLista iteraLu = lista2.iteradorLista();
            while(iteraLu.hasNext()){
                Integer elemento = (Integer)iteraLu.next();
                if(lista1.contains(elemento)){
                    lista2.delete(elemento);
                }
            }
            lista2.append(lista1);	
        }
         return ;
    }

    /**
     *Dadas dos listas, se obtiene la interseccion de sus elementos.
     *Tiempo: O(n * m). Ya que el iterador recorre la primera lista y 
     * otro iterador recorre la segunda lista, dandonos un tiempo O(n+m)
     *Espacio: O(n + m). Ya que se utila el espacio de una de las listas más los nodos que ocupa
     *la segunda lista.
     *Para mejorar el tiempo de ejecución O(n*m), se puede hacer como en nuestro método, que tiene O(m+n) 
     */
    public static void Interseccion(Lista<Integer> lista1,Lista<Integer> lista2) {

        if( lista1.size()>lista2.size() ){
            IteradorLista iteraLi = lista1.iteradorLista();
            iteraLi.start();
            while(iteraLi.hasNext()){
                Integer elemento = (Integer)iteraLi.next();
                if(lista2.contains(elemento)==false){
                lista1.delete(elemento);
                }
            }
        }else{
            IteradorLista iteraLi = lista2.iteradorLista();
            while(iteraLi.hasNext()){
                Integer elemento = (Integer)iteraLi.next();
                if(lista1.contains(elemento)==false){
                lista2.delete(elemento);
                }
            }	    
        }

    }



    public static void main(String[] args) {
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();
        
        
        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }
        
        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }
            
        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }
      
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");    
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            
        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }   
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {
            
                tercera.add(i);

        }


        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }


        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);
        
       
        
        tercera = AgregaOrdenado(primera,9);
        //System.out.println(primera);
        //System.out.println(tercera);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }
        
        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }
        
        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);

        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }
        
    }   
       
}
