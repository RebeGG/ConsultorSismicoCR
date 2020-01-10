package consultorsismico;

import consultorsismico.Controlador.Controlador;

import consultorsismico.Vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//   (ConsultorSismico)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

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
