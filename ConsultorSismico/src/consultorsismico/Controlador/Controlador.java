package consultorsismico.Controlador;

import consultorsismico.Modelo.Conversion;
import consultorsismico.Modelo.Coordenada;
import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Modelo;
import consultorsismico.Modelo.Sismo;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Controlador {

    public Controlador() {
        this(new Modelo());
    }

    public Controlador(Modelo datos) {
        this.datos = datos;
        this.base = null;
        this.conver=new Conversion();
    }

    public Modelo getDatos() {
        return datos;
    }

    public void setDatos(Modelo datos) {
        this.datos = datos;
    }

    public MapaBase getBase() {
        return base;
    }

    public void setBase(MapaBase base) {
        this.base = base;
    }

    public Conversion getConver() {
        return conver;
    }

    public void setConver(Conversion conver) {
        this.conver = conver;
    }
    

    public void registrar(Observer obs) {
        datos.addObserver(obs);
    }

    public void suprimir(Observer actual) {
        System.out.printf("Suprimiendo: %s..%n", actual);
        datos.deleteObserver(actual);
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

        datos.dibujar(g);

    }

    public void leerTxt(File file) {
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNext()) {
                int registro = entrada.nextInt();
                int secAnual = entrada.nextInt();
                String fecha = entrada.next();
                double a = entrada.nextDouble();
                double b = entrada.nextDouble();
                int magnitud = entrada.nextInt();
                int prof = entrada.nextInt();
                datos.getSismos().add(new Sismo(registro, secAnual, fecha, new Coordenada(conver.coordenadatoPixeles(conver.longPix(a), conver.latPix(b)), conver.nuevaCoordenada(a, b)), magnitud, prof));
            }
        } catch (IOException ex) {

        }

    }

    public List<Sismo> buscar(Sismo primero, Sismo segundo) {
        return datos.buscar(primero, segundo);
    }

    private Modelo datos;
    private MapaBase base;
    private Conversion conver;

}
