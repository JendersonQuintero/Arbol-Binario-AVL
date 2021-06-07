/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz2;

/**
 *
 * @author Norangel Marín & Jenderson Quintero.
 */
public class Nodo {
    int altura;
    int clave;
    Nodo der;
    Nodo izq;

    /**
     * Constructor del Nodo.
     * @param dato int
     */
    public Nodo(int dato) {
        clave = dato;
        altura = 1;
        izq = null;
        der = null;
    }
    
    /**
     * Esta función retorna un entero que es igual a todos los nodos
     * almacenados en el arbol.
     * @param nodo Nodo
     * @return int
     */
    public int getNodos(Nodo nodo){
        if (nodo == null) {
            return 0;
        }else{
            if (nodo.izq != null && nodo.der != null) {
                return getNodos(nodo.izq) + getNodos(nodo.der) + 1;
            }
            return getNodos(nodo.izq) + getNodos(nodo.der);
        }
    }

    /**
     * Accede al hijo derecho del nodo en cuestion.
     * @return Nodo
     */
    public Nodo getDer() {
        return der;
    }
    
    /**
     * Accede al hijo izquierdo del nodo en cuestion.
     * @return Nodo
     */
    public Nodo getIzq() {
        return izq;
    }

    /**
     * Accede a la clave del nodo en cuestion.
     * @return clave
     */
    public int getClave() {
        return clave;
    }
}
