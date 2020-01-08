package consultorsismico.Modelo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable{
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
    
    public void setBase(MapaBase base){
        this.base = base;
        setChanged();
        notifyObservers();
    }
    
    public MapaBase getBase(){
        return base;
    }

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

    //public void dibujar(Graphics bg) {
    //    synchronized (sismos) {
    //        for (Sismo s : sismos) {
    //            s.dibujar(bg);
    //        }
    //    }
    //}

}
