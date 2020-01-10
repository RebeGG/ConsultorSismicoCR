package consultorsismico.Modelo;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//  (ListaCoordenadas)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class ListaCoordenadas {
    @XmlElement(name = "coordinate")
    private final List<Coordenada> coordenadas;

    public ListaCoordenadas() {
        this.coordenadas = new ArrayList<>();
    }

    public void agregar(Coordenada c){
        coordenadas.add(c);
    }
    
    public Coordenada obtenerCoordenada(int pos){
        return coordenadas.get(pos);
    }

    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < coordenadas.size(); i++) {
            s.append(String.format("***** Coordenada #%d *****%n", i));
            s.append(String.format("--- Image Position -%n"));
            s.append(String.format("%s%n", coordenadas.get(i).getPosI().toString()));
            s.append(String.format("--- Map Position ---%n"));
            s.append(String.format("%s%n", coordenadas.get(i).getPosM().toString()));
        }
        return s.toString();
    }
}
