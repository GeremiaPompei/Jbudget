package it.unicam.cs.pa.jbudget105333.Controller;

/**
 * Interfaccia responsabile della creazione di un MainController.
 */
public interface MainControllerManager {

    /**
     * Metodo responsabile della generazione di un MainController.
     * @return MainController generato.
     */
    static MainController generateMainController(){
        return new MainControllerBase();
    }
}
