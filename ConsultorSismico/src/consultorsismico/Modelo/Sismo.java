package consultorsismico.Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Sismo {

    private Coordenada coordenada;
    private double magnitud;
    private double profundidad;
    private int registro;
    private String fecha;
    private Color color;

    public Sismo(Coordenada coordenada, Coordenada longitud, double magnitud, double profundidad, int registro, String fecha) {
        this.coordenada = coordenada;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.registro = registro;
        this.fecha = fecha;
        this.color = new Color(0f,1f,.1f,.3f);
    }

    public Sismo() {
        this(null, null, 0.0, 0.0, 0, null);
        this.color = new Color(0f,1f,.1f,.3f);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        if (magnitud <= 10.0 && magnitud > 0.0) {
            this.magnitud = magnitud;
        }
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        if (profundidad <= 70.0 && profundidad >= 0.0) {
            this.profundidad = profundidad;
        }
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void dibujar(Graphics bg){
        Graphics2D g = (Graphics2D) bg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
        g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
    }
}
