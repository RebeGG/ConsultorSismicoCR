package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

public class PosicionImagen {
    private int x;
    private int y;

    public PosicionImagen(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public PosicionImagen(){
        this(0,0);
    }

    public int getX() {
        return x;
    }

    @XmlElement
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    @XmlElement
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public String toString(){
        return String.format("X: %d%nY: %d%n", getX(), getY());
    }
}
