package consultorsismico.Controlador;

import consultorsismico.Modelo.Model;
import java.util.Observer;

public class Controlador {

    public Controlador() {
        this(new Model());
    }

    public Controlador(Model datos) {
        this.datos = datos;
    }
    
    public void registrar(Observer obs) {
        datos.addObserver(obs);
    }
    
    public void suprimir(Observer actual) {
        System.out.printf("Suprimiendo: %s..%n", actual);
        datos.deleteObserver(actual);
    }
    public void actualizar() {
        datos.update();
    }
    
    public void cerrarAplicacion() {
        System.out.println("Aplicación finalizada normalmente..");
        System.exit(0);
    }
    
    private Model datos;
    
}
