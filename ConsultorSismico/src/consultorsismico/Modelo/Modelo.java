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
        setChanged();
        notifyObservers();
    }

    //PARA USAR ESTE AGREGAR HAY QUE PRIMERO CREAR LAS COORDENADAS
    public void agregar(int registro, int secuenciaAnual, String fecha, Coordenada coordenada, double magnitud, double profundidad) {
        agregar(new Sismo(registro, secuenciaAnual, fecha, coordenada, magnitud, profundidad));
    }
    
    public List buscar(Sismo primero, Sismo segundo){
        List<Sismo> aux = new ArrayList<>();
        
        return aux;
    }
    
    public int cantidadSismos() {
        return sismos.size();
    }
    
    public Sismo obtener(int i) {
        return sismos.get(i);
    }

    public void borrar() {
        sismos.clear();

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("[\n");
        for (Sismo s : sismos) {
            r.append(String.format("\t%s,%n", s));
        }
        r.append("]");
        return r.toString();
    }

    public Modelo obtenerModelo() {
        return this;
    }
    
    public void dibujar(Graphics bg) {
        synchronized (sismos) {
            for (Sismo m : sismos) {
                m.dibujar(bg);
            }
        }
    }
    

    public void update(){
        
    }
}
