package consultorsismico.Modelo;

import javax.xml.bind.annotation.XmlElement;

public class Latitud {

    private int grados;
    private int minutos;
    private int segundos;
    private String direccion;

    public Latitud(int grados, int minutos, int segundos, String direccion) {
        this.grados = grados;
        this.minutos = minutos;
        this.segundos = segundos;
        this.direccion = direccion;
    }

    public Latitud() {
        this(0, 0, 0, "");
    }

    public int getGrados() {
        return grados;
    }

    @XmlElement(name = "latitude-degrees")
    public void setGrados(int grados) {
        this.grados = grados;
    }

    public int getMinutos() {
        return minutos;
    }

    @XmlElement(name = "minutes")
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    @XmlElement(name = "seconds")
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public String getDireccion() {
        return direccion;
    }

    @XmlElement(name = "latitude-direction")
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("%d° %d' %d'' %s", getGrados(), getMinutos(), getSegundos(), getDireccion());
    }

    public double latitudToDecimal() {
        return getGrados() + (double) getMinutos() / 60 + (double) getSegundos() / 60;
    }
}
