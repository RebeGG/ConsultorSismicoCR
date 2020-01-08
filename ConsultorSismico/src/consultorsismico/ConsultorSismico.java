package consultorsismico;

import consultorsismico.Controlador.Controlador;

import consultorsismico.Vista.VentanaPrincipal;
import javax.swing.SwingUtilities;


public class ConsultorSismico {
    public static void main(String[] args) {
        new ConsultorSismico().init();
  
    }
    
    public void init(){
        Controlador controller = new Controlador();
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal("Mapa Sismológico",
                    controller).init();
        });
    }
    
}
