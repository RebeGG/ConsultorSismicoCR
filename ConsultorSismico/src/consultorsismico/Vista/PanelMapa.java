package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Modelo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMapa extends PanelAplicacion implements Observer{
    
    private Controlador controlador;
    private Image mapa;
    private Modelo modelo;
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
//        this.modelo = null;
        this.mapa = mapa;
        this.base = base;
        this.controlador = controlador;
        this.estado = estado;
        this.posicionInicio = null;
        this.posicionRaton = null;
        this.background = new JLabel();
//        background.setIcon(new ImageIcon(mapa));
//        background.setSize(base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAncho());
//        this.add(background);
        setPreferredSize(new Dimension(base.getImagen().getDimension().getAncho(), base.getImagen().getDimension().getAncho()));
        controlador.registrar(this);
    }
    
    public PanelMapa(BarraCoordenada estado, Controlador controlador, Image mapa, BufferedImage buffer, MapaBase base) {
        this(null, estado, controlador, mapa, buffer, base);
    }
    
//    @Override
//    public void ajustarComponentes(){
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
////        gbc.gridx = 0;
////        gbc.gridy = 0;
////        gbc.fill = GridBagConstraints.NONE;
////        this.add(background, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 855;
//        gbc.gridwidth = 2;
//        gbc.weighty=0.1;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        this.add(new JPanel(), gbc);
//        gbc.gridx = 855;
//        gbc.gridy = 0;
//        gbc.gridheight = 2;
//        gbc.weightx=0.1;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        this.add(new JPanel(), gbc);
//    }
    
    @Override
    public void init(){
        super.init();

        MouseAdapter ma = new MouseAdapter() {
            
             @Override
            public void mousePressed(MouseEvent e) {
                posicionInicio = posicionRaton = e.getPoint();
                botonOprimido = true;
                botonDerecho = e.isMetaDown();
                controlador.actualizaCoordenadasModel(posicionRaton);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                posicionRaton = e.getPoint();
                botonOprimido = false;
                controlador.actualizaCoordenadasModel(posicionRaton);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                posicionRaton = e.getPoint();
                controlador.actualizaCoordenadasModel(posicionRaton);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                posicionRaton = e.getPoint();
                controlador.actualizaCoordenadasModel(posicionRaton);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                posicionRaton = e.getPoint();
                controlador.actualizaCoordenadasModel(posicionRaton);
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
            g.drawString(base.getCoordenadas().obtenerCoordenada(i).getPosM().getLongitud().toString(), x + 5, y - 18);
            g.drawString(base.getCoordenadas().obtenerCoordenada(i).getPosM().getLatitud().toString(), x + 5, y - 5);
        }
                
        if (modelo != null) {
            controlador.dibujarModel(g);
            System.out.println("Verifiación para dibujar: NO NULO");
        }
        else{
            System.out.println("Verifiación para dibujar: NULO");
            //esto solo lo pregunta 1 vez, no debería ser, porque este metodo se llama mas veces y funciona para que
            //dibuje las lineas guias y las lineas del mouse.....
        }
                

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

        if (modelo != null) {
            estado.mostrarMensaje(modelo.getCoordenada().getPosM().toString());
            System.out.println("Verifiación para barra: NO NULO");
        }
        else{
            System.out.println("Verifiación para barra: NULO");
        }
    }      
    
    @Override
    public void update(Observable o, Object arg){
        System.out.println("Update PanelMapa");
        modelo = (Modelo) o;
        if(modelo != null){
            System.out.println("Modelo no es nulo");
        }
        else{
            System.out.println("Modelo es nulo");
        }
        this.repaint();
        System.out.println("Se llamó repaint desde PanelMapa");
    }
}