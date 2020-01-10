package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//       (Tamano)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class Tamano {
    private int ancho;
    private int alto;

    public Tamano(int width, int height) {
        this.ancho = width;
        this.alto = height;
    }

    public Tamano(){
        this(0,0);
    }
    
    public int getAncho() {
        return ancho;
    }

    @XmlElement(name = "width")
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    @XmlElement(name = "height")
    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    @Override
    public String toString(){
        return String.format("%d, %d", getAncho(), getAlto());
    }
}
