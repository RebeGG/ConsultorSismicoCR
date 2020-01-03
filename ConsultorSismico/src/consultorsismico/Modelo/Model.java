package consultorsismico.Modelo;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    private Sismo sismo;
    private List<Sismo> sismos;
    private MapaBase base;

    public Model(Sismo sismo, List sismos) {
        this.sismo = sismo;
        this.sismos = sismos;
    }

    public Model(Sismo sismo, List sismos, MapaBase base) {
        this.sismo = sismo;
        this.sismos = sismos;
        this.base = base;
    }

    public Model() {
        this.sismo = null;
        this.sismos = new ArrayList<>();
        this.base = null;

    }

//Esta parte debe ir en el Modelo, creo
//Es para cargar la imagen y las coordenadas principales, pero esto solo debe hacerse una vez en toda la corrida del programa
//    try {
//            JAXBContext ctx = JAXBContext.newInstance(BaseMapa.class);
//            Unmarshaller mrs = ctx.createUnmarshaller();
//            MapaBase base = (MapaBase) mrs.unmarshal(new File("../map.xml"));
//            System.out.println(base); ////esto solo prueba que esté funcionando todo, se quita despues
//        } catch (JAXBException ex) {
//            System.err.printf("Excepción: '%s'%n", ex.getMessage());
//        }
    public Sismo getSismo() {
        return sismo;
    }

    public void setSismo(Sismo sismo) {
        this.sismo = sismo;
        setChanged();
        notifyObservers();
    }

    public List getSismos() {
        return sismos;
    }

    public void setSismos(List sismos) {
        this.sismos = sismos;
        setChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        update();
    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public void dibujar(Graphics bg, Coordenada coordenada) {
        synchronized (sismos) {
            for (Sismo m : sismos) {
                m.dibujar(bg, coordenada);
            }
        }
    }

}
