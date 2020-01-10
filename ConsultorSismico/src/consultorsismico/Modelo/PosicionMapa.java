package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//    (PosicionMapa)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class PosicionMapa {
    
    private Latitud latitud;
    private Longitud longitud;
    
    public PosicionMapa( Latitud latitud, Longitud longitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }
    
    public PosicionMapa(){
        this(null, null);
    }

    public Latitud getLatitud() {
        return latitud;
    }

    @XmlElement(name = "latitude")
    public void setLatitud(Latitud latitud) {
        this.latitud = latitud;
    }

    public Longitud getLongitud() {
        return longitud;
    }

    @XmlElement(name = "longitude")
    public void setLongitud(Longitud longitud) {
        this.longitud = longitud;
    }
    
    @Override
    public String toString(){
        return String.format("(%s, %s)", getLongitud(), getLatitud());
    }
}
