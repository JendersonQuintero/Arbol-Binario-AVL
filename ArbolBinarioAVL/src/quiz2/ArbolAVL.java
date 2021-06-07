package quiz2;

/**
 *
 * @author Norangel Marín & Jenderson Quintero.
 */
public class ArbolAVL {
    
Nodo raiz;
    
    /**
     * Constructor de la clase ArbolAVL.
     */
    public ArbolAVL(){
        raiz = null;
    }
    
    /**
     * Funcion para obtener la raiz del arbol.
     * @return Nodo
     */
    public Nodo getRaiz() {
        return raiz;
    }
    
    /**
     * Vacia el arbol existente.
     */
    public void vaciarArbol() {
        raiz = null;
    }

    /** 
     * Inserta un elemento asegurandose de que sea bajo las condiciones AVL. (Uso practico).
     * @param clave int
     */
    public void Insertar(int clave) {
        raiz = insertarAVL(raiz, clave);
    }

    /**
     * Funcion recursiva que inserta y organiza bajo las condiciones AVL los nodos del arbol.
     * @param nodo Nodo
     * @param clave int
     * @return Nodo
     */
    public Nodo insertarAVL(Nodo nodo, int clave){
        
      // Compara el valor de la clave para insertar el elemento
      if(nodo == null){
        return (new Nodo(clave));
      }
      if(clave<nodo.clave){
        nodo.izq = insertarAVL(nodo.izq, clave);
      }
      else if(clave>nodo.clave){
        nodo.der = insertarAVL(nodo.der, clave);  
      }
      else{
          // Por si la clave está duplicada...
          return nodo;
      }
      
      // Cuenta la altura
      nodo.altura = 1 + Maximo(Altura(nodo.izq), Altura(nodo.der));
      
      // Verificador de equilibrio
      int equilibrio = Equilibrio(nodo);
      
      //Rotaciones
      
        //Rotación simple a la derecha
        if ((equilibrio > 1) && (clave < nodo.izq.clave)){
            return rotacionDerecha(nodo);
        }
        
        //Rotación simple a la izquierda
        if ((equilibrio < -1) && (clave > nodo.der.clave)){
            return rotacionIzquierda(nodo);
        }
        
        //Rotación compleja a la derecha
        if ((equilibrio > 1) && (clave > nodo.izq.clave)){
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
        
        //Rotación compleja a la izquierda
        if ((equilibrio < -1) && (clave < nodo.der.clave)){
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }
    
    /** 
     * Elimina un elemento asegurandose de que sea bajo las condiciones AVL. (Uso practico).
     * @param clave int
     */
    public void Eliminar(int clave){
        raiz = eliminarAVL(raiz, clave);
    }
    
    /**
     * Funcion recursiva que elimina y organiza bajo las condiciones AVL los nodos del arbol.
     * @param nodo Nodo
     * @param clave int
     * @return Nodo
     */
    public Nodo eliminarAVL(Nodo nodo, int clave){
       if(nodo == null){
        return nodo;
       } 
       if(clave < nodo.clave){
          nodo.izq = eliminarAVL(nodo.izq, clave);
       }
       else if(clave > nodo.clave){
           nodo.der = eliminarAVL(nodo.der, clave);
       }
       else{
           // Se encontró el nodo con la clave buscada.
           
            //Si solo tiene un unico hijo ó es hoja
            if((nodo.izq == null)||(nodo.der == null)){
                Nodo aux = null;
                
                if(nodo.izq == null){
                    aux = nodo.der;
                }
                else{
                    aux = nodo.izq;
                }
                
                //Si solo tiene un unico hijo
                if(aux!=null){
                    nodo=aux;
                }
                //Si es hoja
                else{
                    nodo=null;
                }
            }
            else{
                // Si tiene dos hijos
                Nodo aux = valorMax(nodo.izq);
                
                nodo.clave= aux.clave;
                
                nodo.izq = eliminarAVL(nodo.izq, aux.clave);
            }
        }
            
            //Si es solo un nodo
            if(nodo == null){
                return nodo;
            }
            
            //Cuenta la altura
            nodo.altura = Maximo(Altura(nodo.izq),Altura(nodo.der))+1;
            
            // Verificador de equilibrio
            int equilibrio = Equilibrio(nodo);

            //Rotaciones

              //Rotación simple a la derecha
              if ((equilibrio > 1) && (Equilibrio(nodo.izq) >= 0)){
                  return rotacionDerecha(nodo);
              }

              //Rotación simple a la izquierda
              if ((equilibrio < -1) && (Equilibrio(nodo.der) <= 0)){
                  return rotacionIzquierda(nodo);
              }

              //Rotación compleja a la derecha
              if ((equilibrio > 1) && (Equilibrio(nodo.izq) < 0)){
                  nodo.izq = rotacionIzquierda(nodo.izq);
                  return rotacionDerecha(nodo);
              }

              //Rotación compleja a la izquierda
              if ((equilibrio < -1) && (Equilibrio(nodo.der) > 0)){
                  nodo.der = rotacionIzquierda(nodo.der);
                  return rotacionIzquierda(nodo);
              }
              return nodo;
    }
    
    /**
     * Realiza una rotación simple hacia la derecha.
     * @param nodo Nodo
     * @return Nodo
     */
    public Nodo rotacionDerecha(Nodo nodo){
        Nodo aux = nodo.izq;
        Nodo aux2 = aux.der;
        
        aux.der = nodo;
        nodo.izq = aux2;
        
        nodo.altura = Maximo(Altura(nodo.izq), Altura(nodo.der))+1;
        aux.altura = Maximo(Altura(aux.izq), Altura(aux.der))+1;
        
        return aux;
    }
    
    /**
     * Realiza una rotación simple hacia la izquierda.
     * @param nodo Nodo
     * @return Nodo
     */
    public Nodo rotacionIzquierda(Nodo nodo){
        Nodo aux = nodo.der;
        Nodo aux2 = aux.izq;
        
        aux.izq = nodo;
        nodo.der = aux2;
        
        nodo.altura = Maximo(Altura(nodo.izq), Altura(nodo.der))+1;
        aux.altura = Maximo(Altura(aux.izq), Altura(aux.der))+1;
        
        return aux;
    }
    
    /**
     * Retorna el nodo con el valor (o clave) más alto del arbol.
     * @param nodo Nodo
     * @return Nodo
     */
    public Nodo valorMax(Nodo nodo){
        Nodo aux = nodo;
        while(aux.der!=null){
            aux = aux.der;
        }
        return aux;
    }
    
    /**
     * Nos ayuda a determinar si el arbol está equilibrado si retorna un entero entre -1 y 1.
     * @param nodo Nodo
     * @return int
     */
    public int Equilibrio(Nodo nodo){
        if(nodo==null){
            return 0;
        }
        return Altura(nodo.izq)-Altura(nodo.der);
    }
    
    /**
     * Retorna la altura del arbol.
     * @param nodo Nodo
     * @return int 
     */
    public int Altura(Nodo nodo){
        if(nodo==null){
            return 0;
        }
        return nodo.altura;
    }
    
    /** 
     * Compara dos claves para indicar cual es mayor.
     * @param primero int
     * @param segundo int
     * @return int
     */
    public int Maximo(int primero, int segundo){
        if (primero > segundo){
            return  primero;
        } 
        else{
            return segundo;
        }
    }
    
    /** 
     * Muestra el arbol por consola. (Uso practico)
     */
    public void mostrarArbolAVL(){
        System.out.println("Arbol AVL");
        mostrarArbol(raiz,0);
    }
    
    /**
     * Ordena e imprime el arbol para que se pueda observar y entender por consola.
     * @param nodo Nodo
     * @param num int
     */
    public void mostrarArbol(Nodo nodo, int num){
        if (nodo.der!=null){
            mostrarArbol(nodo.der, num+1);
        }
        for (int i = 0; i < num; i++) {
            System.out.print("    ");  
        }
        System.out.println("("+nodo.clave+")");
        if (nodo.izq!=null){
            mostrarArbol(nodo.izq, num+1);
        }
    }
    
    /**
     * Si se encuentra el elemento retorna true.
     * @param clave int
     * @return boolean
     */
    public boolean Buscar(int clave){
        return buscarClave(raiz, clave);
    }
    
    /** 
     * Busca en el arbol de manera recursiva una clave para verificar si existe o no.
     * @param nodo Nodo
     * @param clave int
     * @return boolean
     * Si la clave está en el arbol retorna true : false.
     */
    public boolean buscarClave(Nodo nodo, int clave){
        if(nodo == null){
            return false;
        }
        else if(clave == nodo.clave){
            return true;
        }
        else if (clave < nodo.clave){
            return buscarClave(nodo.izq, clave);
        }
        else{
            return buscarClave(nodo.der, clave);
        }
    }
}
