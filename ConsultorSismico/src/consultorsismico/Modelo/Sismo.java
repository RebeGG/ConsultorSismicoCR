package consultorsismico.Modelo;

import java.awt.Color;
import java.awt.Font;
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
    private int secuenciaAnual;

    public Sismo(int registro, int secuenciaAnual, String fecha, Coordenada coordenada, double magnitud, double profundidad) {
        this.coordenada = coordenada;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.registro = registro;
        this.fecha = fecha;
        this.secuenciaAnual = secuenciaAnual;
        this.color = new Color(0f, 1f, .1f, .3f);
    }

    public Sismo() {
        this(0, 0, null, null, 0.0, 0.0);
        this.color = new Color(0f, 1f, .1f, .3f);
    }

    public int getSecuenciaAnual() {
        return secuenciaAnual;
    }

    public void setSecuenciaAnual(int secuenciaAnual) {
        this.secuenciaAnual = secuenciaAnual;
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

    public void dibujar(Graphics bg, Coordenada coordenada) {
        Graphics2D g = (Graphics2D) bg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
        g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 12));
        g.drawString(String.valueOf(magnitud), coordenada.getPosI().getX() + 15, coordenada.getPosI().getY() + 24);
    }
    
    @Override
    public String toString(){
        return String.format("%d %d %s %s %s %f %fKM", registro, secuenciaAnual,fecha, coordenada.getPosM().getLatitud().toString(), 
                coordenada.getPosM().getLongitud().toString(), magnitud, profundidad);
    }

}
