package consultorsismico.Controlador;

import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Modelo;
import java.io.File;
import java.util.Observer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Controlador {
    
    public Controlador() {
        this(new Modelo());
    }

    public Controlador(Modelo datos) {
        this.datos = datos;
   }
    
    public void actualizar() {
        datos.update();
    }
    
    public void cerrarAplicacion() {
        System.out.println("Aplicación finalizada normalmente..");
        System.exit(0);
    }
    
    public void registrar(Observer obs) {
        datos.addObserver(obs);
    }
    
    public void suprimir(Observer actual) {
        System.out.printf("Suprimiendo: %s..%n", actual);
        datos.deleteObserver(actual);
    }
    
    public Modelo obtenerModelo(){
        return datos.obtenerModelo();
    }
    
//    public void unMarshallXML(){
//        try {
//            JAXBContext ctx = JAXBContext.newInstance(MapaBase.class);
//            Unmarshaller mrs = ctx.createUnmarshaller();
//            datos.setBase((MapaBase) mrs.unmarshal(new File("./map.xml")));
//        } catch (JAXBException ex) {
//            System.err.printf("Excepción: '%s'%n", ex.getMessage());
//        }
//    }

    private Modelo datos;
}
