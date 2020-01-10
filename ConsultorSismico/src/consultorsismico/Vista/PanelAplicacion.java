package consultorsismico.Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public abstract class PanelAplicacion extends JPanel {
    private BufferedImage buffer;

    
    public PanelAplicacion(Color fondo, BufferedImage buffer) {
        this.buffer = buffer;
        configurar(fondo);
    }

    private void configurar(Color fondo) {
//        ajustarComponentes();
        setBackground(fondo);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public PanelAplicacion(BufferedImage buffer) {
        this(null, buffer);
    }
    
//    public abstract void ajustarComponentes();

    @Override
    public void paint(Graphics g) {
        Graphics graph = buffer.getGraphics();
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
