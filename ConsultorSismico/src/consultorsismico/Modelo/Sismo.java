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
<<<<<<< HEAD
    private Color color;
    private int secuenciaAnual;

=======
    private int secuenciaAnual;
    public static final int POS1= 100;
    public static final int POS2= 300;
    public static final int POS3= 600;
    public static final int POS4= 900;
    
>>>>>>> Modelo-Fernanda
    public Sismo(int registro, int secuenciaAnual, String fecha, Coordenada coordenada, double magnitud, double profundidad) {
        this.coordenada = coordenada;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.registro = registro;
        this.fecha = fecha;
        this.secuenciaAnual = secuenciaAnual;
<<<<<<< HEAD
        this.color = new Color(0f, 1f, .1f, .3f);
=======
>>>>>>> Modelo-Fernanda
    }

    public Sismo() {
        this(0, 0, null, null, 0.0, 0.0);
<<<<<<< HEAD
        this.color = new Color(0f, 1f, .1f, .3f);
=======
>>>>>>> Modelo-Fernanda
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

<<<<<<< HEAD
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
=======
    public void dibujar(Graphics bg) {
        
        Graphics2D g = (Graphics2D) bg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (coordenada.getPosI().getX() < POS1 && coordenada.getPosI().getX() >= 0) {
            g.setColor(new Color(0f, 1f, 1f, .3f));
            g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 45, 45);
            g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 45, 45);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.drawString(String.valueOf(magnitud), coordenada.getPosI().getX() + 14, coordenada.getPosI().getY() + 26);
        }
        if (coordenada.getPosI().getX() < 300 && coordenada.getPosI().getX() >= 100) {
            g.setColor(new Color(1f,.7f,.1f,.3f));
            g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
            g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 40, 40);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.drawString(String.valueOf(magnitud), coordenada.getPosI().getX() + 13, coordenada.getPosI().getY() + 24);
        }
        if (coordenada.getPosI().getX() < 600 && coordenada.getPosI().getX() >= 300) {
            g.setColor(new Color(0f,1f,.1f,.3f));
            g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 30, 30);
            g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 30, 30);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.drawString(String.valueOf(magnitud), coordenada.getPosI().getX() + 9, coordenada.getPosI().getY() + 21);
        }
        if (coordenada.getPosI().getX() < 1000 && coordenada.getPosI().getX() >= 600) {
            g.setColor(new Color(1f,0f,1f,.3f));
            g.drawOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 35, 35);
            g.fillOval(coordenada.getPosI().getX(), coordenada.getPosI().getY(), 35, 35);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.drawString(String.valueOf(magnitud), coordenada.getPosI().getX() + 10, coordenada.getPosI().getY() + 22);
        }
<<<<<<< Updated upstream
        
//        g.setColor(new Color(0f, 1f, .1f, .3f));
//        g.drawOval(coordenada.getX(), coordenada.getY(), 40, 40);
//        g.fillOval(coordenada.getX(), coordenada.getY(), 40, 40);
//        g.setColor(Color.BLACK);
//        g.setFont(new Font("TimesRoman", Font.BOLD, 12));
//        g.drawString(String.valueOf(magnitud), coordenada.getX() + 15, coordenada.getY() + 24);
>>>>>>> Modelo-Fernanda
=======
>>>>>>> Stashed changes
    }
    
    @Override
    public String toString(){
        return String.format("%d %d %s %s %s %f %fKM", registro, secuenciaAnual,fecha, coordenada.getPosM().getLatitud().toString(), 
                coordenada.getPosM().getLongitud().toString(), magnitud, profundidad);
    }
<<<<<<< HEAD

=======
>>>>>>> Modelo-Fernanda
}
