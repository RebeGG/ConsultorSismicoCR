package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

public class Coordenada {
    
    private PosicionImagen posI;
    private PosicionMapa posM;
    private String etiqueta; 
    
    public Coordenada(String etiqueta, PosicionImagen posI, PosicionMapa posM) {
        this.etiqueta = etiqueta;
        this.posI = posI;
        this.posM = posM;
    }

    public Coordenada() {
        this("", null, null);
    }
    
    public String getEtiqueta() {
        return etiqueta;
    }

    @XmlElement(name = "label")
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public PosicionImagen getPosI() {
        return posI;
    }

    @XmlElement(name = "image-position")
    public void setPosI(PosicionImagen posI) {
        this.posI = posI;
    }

    public PosicionMapa getPosM() {
        return posM;
    }
<<<<<<< HEAD

    @XmlElement
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getDirection() {
        return direction;
    }

    @XmlElement
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("%d°%d'%d''%s", degrees, minutes, seconds, direction);
    }
=======
    
    @XmlElement(name = "map-position")
    public void setPosM(PosicionMapa posM) {
        this.posM = posM;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", getPosI(), getPosM());
    }

// Esto lo dejo por acá en caso de que logremos una mejor forma de leer de xml con menos clases
//    private int degrees;
//    private int minutes;
//    private int seconds;
//    private String direction;
//
//    public Coordenada(int degrees, int minutes, int seconds, String direction) {
//        this.degrees = degrees;
//        this.minutes = minutes;
//        this.seconds = seconds;
//        this.direction = direction;
//    }
//
//    public Coordenada() {
//        this(0, 0, 0, null);
//    }
//
//    public int getDegrees() {
//        return degrees;
//    }
//
//    @XmlElement
//    public void setDegrees(int degrees) {
//        this.degrees = degrees;
//    }
//
//    public int getMinutes() {
//        return minutes;
//    }
//
//    @XmlElement
//    public void setMinutes(int minutes) {
//        this.minutes = minutes;
//    }
//
//    public int getSeconds() {
//        return seconds;
//    }
//    
//    @XmlElement
//    public void setSeconds(int seconds) {
//        this.seconds = seconds;
//    }
//
//    public String getDirection() {
//        return direction;
//    }
//    
//    @XmlElement
//    public void setDirection(String direction) {
//        this.direction = direction;
//    }

>>>>>>> master
}
