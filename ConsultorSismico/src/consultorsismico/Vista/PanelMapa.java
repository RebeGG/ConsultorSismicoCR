package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PanelMapa extends PanelAplicacion{
    
    private Controlador controlador;
    private Image mapa;
    private Model modelo;
    private BarraCoordenada estado;
    private Point posicionInicio, posicionRaton;
    private boolean botonOprimido;
    private boolean botonDerecho;
    private static String MAPA_PATH;
    private BufferedImage buffer;
    private MapaBase base;
    private JLabel background;
    private static final int S = 6;
    private final float[] patronPunteado = {4.0f, 4.0f};
    private Stroke lineaGuia = new BasicStroke(1f);
    private final Stroke lineaDelgada = new BasicStroke(1.0f);
    private final Stroke lineaPunteada = new BasicStroke(1.0f,
            BasicStroke.CAP_SQUARE, 1, BasicStroke.JOIN_MITER, patronPunteado, 0);
    private final Stroke lineaGruesa = new BasicStroke(1.5f);
    
    PanelMapa(Color fondo, BarraCoordenada estado, Controlador controlador, Image mapa, BufferedImage buffer, MapaBase base){
        super(fondo, buffer);
        this.mapa = mapa;
        this.base = base;
        this.controlador = controlador;
        this.estado = estado;
        this.posicionInicio = null;
        this.posicionRaton = null;
        this.background = new JLabel();
        background.setIcon(new ImageIcon(mapa));
        background.setSize(base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAncho());
        this.add(background);
        setPreferredSize(new Dimension(base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAncho()));
    }
    
    public PanelMapa(BarraCoordenada estado, Controlador controlador, Image mapa, BufferedImage buffer, MapaBase base) {
        this(null, estado, controlador, mapa, buffer, base);
    }
    
    @Override
    public void init(){
        super.init();

        MouseAdapter ma = new MouseAdapter() {
            
             @Override
            public void mousePressed(MouseEvent e) {
                posicionInicio = posicionRaton = e.getPoint();
                botonOprimido = true;
                // botonDerecho = ((e.getModifiersEx() & MouseEvent.BUTTON2) == MouseEvent.BUTTON2);
                botonDerecho = e.isMetaDown();
                //controlador.actualizar(posicionRaton);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                posicionRaton = e.getPoint();
                botonOprimido = false;

                //controlador.actualizar(posicionRaton);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                posicionRaton = e.getPoint();
                //controlador.actualizar(posicionRaton);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                posicionRaton = e.getPoint();
                //controlador.actualizar(posicionRaton);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                posicionRaton = e.getPoint();
                //controlador.actualizar(posicionRaton);
            }

        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    @Override
    public void actualizar() {
        
    }

    @Override
    public void redibujar(Graphics bg) {
        Graphics2D g = (Graphics2D) bg;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.BLUE.darker());
                g.setStroke(lineaGuia);
                
                for(int i = 0; i < 2; i++){
                    int x = base.getCoordenadas().obtenerCoordenada(i).getPosI().getX();
                    int y = base.getCoordenadas().obtenerCoordenada(i).getPosI().getY();
                    g.drawLine(0, y, getWidth(), y);
                    g.drawLine(x, 0, x, getHeight());
                    //g.drawString(base.getCoordenadas().obtenerCoordenada(i).getPosM().toString(), x + 5, y - 10);
                    g.drawString(base.getCoordenadas().obtenerCoordenada(i).getPosM().getLongitud().toString(), x + 5, y - 18);
                    g.drawString(base.getCoordenadas().obtenerCoordenada(i).getPosM().getLatitud().toString(), x + 5, y - 5);
                }
                
//                if (modelo != null) {
//                   // modelo.dibujar(g);
                     // controlador.dibujarSismos(g);
//                }

        // Si se está arrastrando el ratón, se dibuja un "recuadro de selección" para mostrar
        // el rango de arrastre.
        if (botonOprimido && (posicionInicio != null) && (posicionRaton != null)) {
            g.setColor(Color.RED.darker());
            g.setStroke(lineaPunteada);

            int x = Math.min(posicionInicio.x, posicionRaton.x);
            int y = Math.min(posicionInicio.y, posicionRaton.y);
            int w = Math.abs(posicionRaton.x - posicionInicio.x);
            int h = Math.abs(posicionRaton.y - posicionInicio.y);
            g.drawLine(0, posicionRaton.y, getSize().width, posicionRaton.y);
            g.drawLine(posicionRaton.x, 0, posicionRaton.x, getSize().height);
            g.drawRect(x, y, w, h);
        }

        // Dibuja los ejes de posición del ratón y
        if (!botonOprimido && posicionRaton != null) {
            g.setColor((botonOprimido) ? Color.BLACK : getBackground().darker());
            g.setStroke(lineaDelgada);
            g.drawLine(0, posicionRaton.y, getSize().width, posicionRaton.y);
            g.drawLine(posicionRaton.x, 0, posicionRaton.x, getSize().height);
        }

        //estado.mostrarMensaje(modelo.getCoordenada().toString());
    }      
}
