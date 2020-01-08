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

//    @Override
//    public String toString() {
//        return String.format("%d°%d'%d''%s", degrees, minutes, seconds, direction);
//    }
    
    @XmlElement(name = "map-position")
    public void setPosM(PosicionMapa posM) {
        this.posM = posM;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", getPosI(), getPosM());
    }
}
