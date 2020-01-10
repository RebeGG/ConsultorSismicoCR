package consultorsismico.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//     (ConjuntoSismos)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class ConjuntoSismos extends Observable {
    
    public ConjuntoSismos() {
        sismos = new ArrayList<>();
    }
    
    public ConjuntoSismos(List<Sismo> lista){
        sismos = lista;
    }
    
    public void setSismos(List<Sismo> sismos){
        this.sismos = sismos;
    }

    public List<Sismo> getSismos(){
        return sismos;
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

    public void agregar(Sismo s) {
        sismos.add(s);
        setChanged();
        notifyObservers(s);
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

    public ConjuntoSismos obtenerModelo() {
        return this;
    }

    private List<Sismo> sismos;
}
