package quiz2;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Norangel Marín & Jenderson Quintero.
 */
public class FuncionalidadesInterfaz {
    private final MostrarArbolAVL mArbol;
    private final ArbolAVL arbol;
    
    /**
     * Constructor de la clase. Inicializa el Arbol y el panel donde se mostrará el Arbol.
     * @param mArbol MostrarArbolAVL.
     * @param arbol ArbolAVL.
     */
    public FuncionalidadesInterfaz(MostrarArbolAVL mArbol, ArbolAVL arbol){
        this.mArbol = mArbol;
        this.arbol = arbol;
    }
    
    /**
     * Realiza las verificaciones correspondientes antes de insertar
     * una nueva clave en el Arbol.
     */
    public void insertarClave(){
        try {
            int clave = Integer.parseInt(JOptionPane.showInputDialog("Escriba la clave a ingresar: "));
            if (arbol.Buscar(clave)) {
                JOptionPane.showMessageDialog(null, "Esta clave ya existe.", "ERROR", 0);
            } else {
                arbol.Insertar(clave);
                JOptionPane.showMessageDialog(null, "Clave insertada exitosamente.", "Inserción exitosa!", 1);
                actualizarArbol();
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Clave invalida", "ERROR: " + e, 0);
        }
    }
    
    /**
     * Realiza las verificaciones correspondientes antes de eliminar
     * una clave del arbol.
     */
    public void eliminarClave(){
        try {
            int clave = Integer.parseInt(JOptionPane.showInputDialog("Escriba la clave a eliminar: "));
            if (arbol.Buscar(clave)) {
                arbol.Eliminar(clave);
                JOptionPane.showMessageDialog(null, "Clave eliminada exitosamente.", "Eliminación exitosa", 1);
                actualizarArbol();
            } else {
                JOptionPane.showMessageDialog(null, "Esta clave no existe.", "ERROR", 0);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Clave invalida", "ERROR: " + e, 0);
        }
    }
    
    /**
     * Método que actualiza el Arbol que se muestra en el Panel.
     */
    public void actualizarArbol(){
        mArbol.setArbol(arbol);
    }
}
