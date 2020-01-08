package consultorsismico.Controlador;

import consultorsismico.Modelo.Coordenada;
import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Model;
import consultorsismico.Modelo.Sismo;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observer;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Controlador {

    public Controlador() {
        this(new Model());
    }

    public Controlador(Model datos) {
        this.datos = datos;
        this.base = null;
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

    public void unMarshallXML() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(MapaBase.class);
            Unmarshaller mrs = ctx.createUnmarshaller();
            base = (MapaBase) mrs.unmarshal(new File("../map.xml"));
        } catch (JAXBException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    public void cerrarAplicacion() {
        System.out.println("Aplicación finalizada normalmente..");
        System.exit(0);
    }

    public void dibujarModel(Graphics g) {
//------------depende dibujar en el model pero sin coordenada------------
//        datos.dibujar(g);

    }

    public void leerTxt(File file) throws FileNotFoundException {
        Coordenada cord = base.getCoordenadas().obtenerCoordenada(0);//sólo para no dejar en blanco

        //new Sismo(registro,secuenciaAnual,fecha, new Coordenada(conver.coordenadatoPixeles(double a,double b),
        //conver.nuevaCoordenada(a,b)), magnitud, profundidad)
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNext()) {
                int registro = entrada.nextInt();
                int secAnual = entrada.nextInt();
                String fecha = entrada.next();
                double a = entrada.nextDouble();
                double b = entrada.nextDouble();
                int magnitud = entrada.nextInt();
                int prof = entrada.nextInt();
                datos.getSismos().add(new Sismo(registro, secAnual, fecha, new Coordenada(conver.coordenadatoPixeles(a, b), conver.nuevaCoordenada(a, b)), magnitud, prof));
            }
        } catch (IOException ex) {

        }

    }

    private Model datos;
    private MapaBase base;
    private Conversion conver;

}
