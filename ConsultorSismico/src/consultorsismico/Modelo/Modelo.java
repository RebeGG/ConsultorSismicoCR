package consultorsismico.Modelo;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Modelo extends Observable {

    private Sismo sismo;
    private List<Sismo> sismos;
    private Coordenada coordenada;

    public Modelo(Sismo sismo, List sismos) {
        this.sismo = sismo;
        this.sismos = sismos;
    }

    public Modelo(Sismo sismo, List<Sismo> sismos, Coordenada coordenada) {
        this.sismo = sismo;
        this.sismos = sismos;
        this.coordenada = coordenada;
    }

    public Modelo() {
        this.sismo = null;
        this.sismos = new ArrayList<>();
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

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
        setChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
    }

    public void agregar(Sismo sismo) {
        sismos.add(sismo);
    }

    //PARA USAR ESTE AGREGAR HAY QUE PRIMERO CREAR LAS COORDENADAS
    public void agregar(int registro, int secuenciaAnual, String fecha, Coordenada coordenada, double magnitud, double profundidad) {
        agregar(new Sismo(registro, secuenciaAnual, fecha, coordenada, magnitud, profundidad));
    }

    public void dibujar(Graphics bg) {
        synchronized (sismos) {
            for (Sismo m : sismos) {
                m.dibujar(bg);
            }
        }
    }

}
