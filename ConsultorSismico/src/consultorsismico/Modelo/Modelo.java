package consultorsismico.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Modelo extends Observable {

    private Sismo sismo;
    private List<Sismo> sismos;
    private ConjuntoSismos listaBusqueda;
    private Coordenada coordenada;

    public Modelo(List sismos) {
        this.sismos = sismos;
    }

    public Modelo(List<Sismo> sismos, Coordenada coordenada) {
        this.sismos = sismos;
        this.coordenada = coordenada;
        this.listaBusqueda = new ConjuntoSismos(sismos);
    }

    public Modelo() {
        this.sismo = null;
        this.sismos = new ArrayList<>();
        this.listaBusqueda = new ConjuntoSismos();
    }

    public Sismo getSismo() {
        return sismo;
    }

    public void setSismo(Sismo sismo) {
        this.sismo = sismo;
        setChanged();
        notifyObservers();
    }

    public List<Sismo> getSismos() {
        return sismos;
    }

    public void setSismos(List<Sismo> sismos) {
        this.sismos = sismos;
        setChanged();
        notifyObservers();
    }

    public void setlistaBusqueda(List<Sismo> sismos) {
        listaBusqueda.setSismos(sismos);
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
        setChanged();
        notifyObservers();
    }

    public LocalDate date(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return LocalDate.parse(fecha, formatter);
    }

    public boolean isFecha(Sismo first, String fecha, Sismo second) {
        // dP <= F <= D && MP <= F <= M && YP <= F <= Y
        return (date(first.getFecha()).getDayOfMonth() <= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() <= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F <= D && MP > F <= M && YP < F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() > date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() < date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F <= D && MP <= F <= M && YP < F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() <= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() < date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F >= D && MP >= F >= M && YP <= F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() >= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() >= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() >= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F >= D && MP <= F >= M && YP <= F < Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() >= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() <= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() >= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() < date(second.getFecha()).getYear())
                || // DP >= F >= D && MP <= F >= M && YP < F < Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() >= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() <= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() >= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() < date(fecha).getYear()
                && date(fecha).getYear() < date(second.getFecha()).getYear())
                || // DP <= F <= D && MP >= F >= M && YP <= F < Y
                (date(first.getFecha()).getDayOfMonth() <= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() >= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() >= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() < date(second.getFecha()).getYear())
                || // DP >= F <= D && MP <= F <= M && YP <= F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() <= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F >= D && MP >= F <= M && YP <= F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() >= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() >= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear())
                || // DP >= F <= D && MP >= F <= M && YP <= F <= Y
                (date(first.getFecha()).getDayOfMonth() >= date(fecha).getDayOfMonth()
                && date(fecha).getDayOfMonth() <= date(second.getFecha()).getDayOfMonth()
                && date(first.getFecha()).getMonthValue() >= date(fecha).getMonthValue()
                && date(fecha).getMonthValue() <= date(second.getFecha()).getMonthValue()
                && date(first.getFecha()).getYear() <= date(fecha).getYear()
                && date(fecha).getYear() <= date(second.getFecha()).getYear());

    }

    public List<Sismo> buscar(Sismo primero, Sismo segundo) {
        List<Sismo> aux = new ArrayList<>();
        for (Sismo sismo : sismos) {
            if (primero.getMagnitud() < segundo.getMagnitud() && isFecha(primero, sismo.getFecha(), segundo)) {
                if (sismo.getMagnitud() >= primero.getMagnitud() && sismo.getMagnitud() <= segundo.getMagnitud()) {
                    aux.add(sismo);
                }
            }
            if (primero.getMagnitud() == 10.10 && segundo.getMagnitud() == 10.10 && isFecha(primero, sismo.getFecha(), segundo)) {
                aux.add(sismo);
            }
            if (primero.getMagnitud() > segundo.getMagnitud() && isFecha(primero, sismo.getFecha(), segundo)) {
                if (sismo.getMagnitud() <= primero.getMagnitud() && sismo.getMagnitud() >= segundo.getMagnitud()) {
                    aux.add(sismo);
                }
            }
            if (primero.getMagnitud() == segundo.getMagnitud() && isFecha(primero, sismo.getFecha(), segundo)) {
                if (sismo.getMagnitud() == primero.getMagnitud()) {
                    aux.add(sismo);
                }
            }
        }
        return aux;
    }

    public ConjuntoSismos obtenerModeloTabla() {
        return listaBusqueda.obtenerModelo();
    }

    public void dibujar(Graphics2D bg) {
        synchronized (sismos) {
            for (Sismo m : sismos) {
                m.dibujar(bg);
            }
        }
    }
    
    public void update(){
        
    }
}
