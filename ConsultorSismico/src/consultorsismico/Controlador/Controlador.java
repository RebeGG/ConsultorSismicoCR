package consultorsismico.Controlador;

import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Model;
import java.awt.Graphics;
import java.io.File;
import java.util.Observer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
    public void unMarshallXML(){
         try {
            JAXBContext ctx = JAXBContext.newInstance(MapaBase.class);
            Unmarshaller mrs = ctx.createUnmarshaller();
            MapaBase base = (MapaBase) mrs.unmarshal(new File("../map.xml"));
        } catch (JAXBException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }
    
    public void cerrarAplicacion() {
        System.out.println("Aplicación finalizada normalmente..");
        System.exit(0);
    }
    
    public void dibujarModel(Graphics g){
        datos.dibujar(g);
        
    }
    
    public void leerTxt(File file){
        
    }
    
    private Model datos;
    
}
