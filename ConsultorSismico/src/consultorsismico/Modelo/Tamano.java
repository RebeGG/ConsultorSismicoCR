package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

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
