package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinates")
public class Coordenada {

    private int degrees;
    private int minutes;
    private int seconds;
    private String direction;

    public Coordenada(int degrees, int minutes, int seconds, String direction) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.direction = direction;
    }

    public Coordenada() {
        this(0, 0, 0, null);
    }

    public int getDegrees() {
        return degrees;
    }

    @XmlElement
    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    @XmlElement
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    
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

}
