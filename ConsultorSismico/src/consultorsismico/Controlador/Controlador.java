package consultorsismico.Controlador;

import java.util.Observer;

public class Controlador {
    
    public void registrar(Observer obs) {
        //modelo.addObserver(obs);
    }
    
    public void suprimir(Observer actual) {
        System.out.printf("Suprimiendo: %s..%n", actual);
        //modelo.deleteObserver(actual);
    }
    
    public void cerrarAplicacion() {
        System.out.println("Aplicación finalizada normalmente..");
        System.exit(0);
    }
}
