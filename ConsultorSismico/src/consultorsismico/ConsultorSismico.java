package consultorsismico;

import consultorsismico.Controlador.Controlador;
import consultorsismico.Modelo.Conversion;
import consultorsismico.Modelo.Coordenada;
import consultorsismico.Modelo.Latitud;
import consultorsismico.Modelo.Longitud;
import consultorsismico.Modelo.PosicionImagen;
import consultorsismico.Modelo.PosicionMapa;
import consultorsismico.Vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class ConsultorSismico {

    public static void main(String[] args) {
        //new ConsultorSismico().init();
        
        PosicionImagen primera = new PosicionImagen(86,67);
        PosicionImagen segunda = new PosicionImagen(663,636);
        Latitud lat = new Latitud(11,0,0,"N");
        Longitud lon = new Longitud (86,0,0, "W");
        Latitud lat2 = new Latitud(8,0,0,"N");
        Longitud lon2 = new Longitud (83,0,0, "W");
        PosicionMapa m1 = new PosicionMapa(lat, lon);
        PosicionMapa m2 = new PosicionMapa(lat2, lon2);
        Coordenada c1 = new Coordenada ("Primera", primera, m1);
        Coordenada c2 = new Coordenada ("Segunda", segunda, m2);
        Conversion a = new Conversion(c1, c2);
        System.out.printf("Primera coordenada: %s\n", c1.toString());
        System.out.printf("Primera latitud: %f\n", lat.latitudToDecimal());
        System.out.printf("Primera longitud: %f\n", lon.longitudToDecimal());
        System.out.printf("Segunda coordenada: %s\n", c2.toString());
        System.out.printf("Segunda latitud: %f\n", lat2.latitudToDecimal());
        System.out.printf("Segunda longitud: %f\n", lon2.longitudToDecimal());
        
        Latitud lat3 = new Latitud(9,0,0,"N");
        Longitud lon3 = new Longitud (84,15,0, "W");
        PosicionMapa m3 = new PosicionMapa(lat3, lon3);
        
//        double a= 84 + (double)15/60 + 0/3600;
//        System.out.println(a);
//        float b= (float)1/2;
//        System.out.println(b);
        double x= a.longitudPix(lon3);
        double y = a.latitudPix(lat3);
        Double nwX= new Double(x);
        int nw = nwX.intValue();
        Double nyX= new Double(y);
        int ny = nyX.intValue();
        PosicionImagen tres = new PosicionImagen(nw,ny);
        Coordenada c3 = new Coordenada ("Tercera", tres, m3);
        
        System.out.printf("Tercera coordenada: %s\n", c3.toString());
        System.out.printf("Tercera latitud: %s\n", lat3.latitudToDecimal());
        System.out.printf("Ancho Pixeles: %d\n", a.anchoPixeles());
        System.out.printf("Altura Pixeles: %d\n", a.alturaPixeles());
        System.out.printf("Ancho Grados: %d\n", a.anchoGrados());
        System.out.printf("Altura Gradods: %d\n", a.alturaGrados());
        System.out.printf("Latitud t: %f\n", a.latitudT(lat3));
        System.out.printf("Longitud T: %f\n", a.longitudT(lon3));
    }
    
    public void init(){
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal("Mapa Sismológico",
                    new Controlador()).init();
        });
    }
    
}
