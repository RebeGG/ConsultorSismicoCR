package consultorsismico.Vista;

import consultorsismico.Modelo.MapaBase;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//   (PanelAplicacion)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public abstract class PanelAplicacion extends JPanel {
    private BufferedImage buffer;
    private MapaBase base;

    
    public PanelAplicacion(Color fondo, MapaBase base) {
        this.base = base;
        this.buffer = new BufferedImage(base.getImagen().getDimension().getAncho(),base.getImagen().getDimension().getAlto(),BufferedImage.TYPE_INT_RGB);
        configurar(fondo);
    }

    private void configurar(Color fondo) {
        setBackground(fondo);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setPreferredSize(new Dimension(base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAncho()));
    }

    public PanelAplicacion(MapaBase base) {
        this(null, base);
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon wallpaper = new ImageIcon(getClass().getResource(base.getImagen().getUrl()));
        Graphics graph = buffer.getGraphics();
        graph.drawImage(wallpaper.getImage(), 0, 0, base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAlto(), null);
        setOpaque(false);
        super.paint(graph);
        redibujar(graph);
        g.drawImage(buffer, 0, 0, null);
    }

    public void init() {
        System.out.println("Iniciando el hilo de control..");

        hiloControl = new Thread() {
            @Override
            public void run() {
                try {
                    while (hiloControl == Thread.currentThread()) {
                        actualizar();
                        repaint();
                        hiloControl.sleep(MAX_ESPERA);
                    }
                } catch (InterruptedException e) {
                }
            }

        };

        if (hiloControl != null) {
            hiloControl.start();
        }
    }

    public abstract void actualizar();

    public abstract void redibujar(Graphics g);

    private Thread hiloControl;
    private static final int MAX_ESPERA = 40;
}
