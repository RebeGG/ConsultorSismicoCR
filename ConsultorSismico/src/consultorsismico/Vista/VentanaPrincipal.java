package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends JFrame implements Observer{

    private BarraCoordenada coordenadas;
    private Controlador controlador;
    private Image mapaImage;
    private JLabel backgroundLbl;
    private JMenu menuVentana;
    private JMenu menuSeleccion;
    private JMenuBar menuPrincipal;
    private JMenuItem itemTabla;
    private JMenuItem itemSeleccion;
    private JMenuItem itemSalir;
    private JPanel panelPrincipal;
    private static final String MAPA_PATH = "/media/MapaCR.png";
    //private static final String MAPA_PATH = model.getImagen().getUrl();
    private VentanaSeleccion ventanaSeleccion;
    private VentanaTabular ventanaTabular;

    public VentanaPrincipal(String titulo, Controlador controlador) {
        super(titulo);
        this.controlador = controlador;
        try {
            mapaImage = ImageIO.read(getClass().getResourceAsStream(MAPA_PATH));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ventanaSeleccion = new VentanaSeleccion("Consultar Sismos",this.controlador);
        this.ventanaTabular = new VentanaTabular("Datos",this.controlador);
        
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        pack();
        setResizable(true);
        setSize(785,585);
        setMinimumSize(new Dimension(785, 585));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarVentana();
            }
 
        });
    }

    private void ajustarComponentes(Container contenedor) {
        backgroundLbl = new JLabel();
        backgroundLbl.setIcon(new ImageIcon(mapaImage));

        contenedor.setLayout(new BorderLayout());
        ajustarMenu();

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.add(backgroundLbl);
        backgroundLbl.setBounds(contenedor.getBounds());

        contenedor.add(BorderLayout.CENTER, panelPrincipal);
        contenedor.add(BorderLayout.PAGE_END, coordenadas = new BarraCoordenada());
        contenedor.add(new JScrollPane(panelPrincipal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

    }

    private void ajustarMenu() {
        menuPrincipal = new JMenuBar();

        menuPrincipal.add(menuVentana = new JMenu("Ventana"));
        menuVentana.add(itemSalir = new JMenuItem("Salir"));
        
        menuPrincipal.add(menuSeleccion = new JMenu("Seleccion"));
        menuSeleccion.add(itemSeleccion = new JMenuItem("Consultar Sismos"));
        menuSeleccion.add(itemTabla = new JMenuItem("Tabla Coordenadas"));
        setJMenuBar(menuPrincipal);

        itemSalir.addActionListener((ActionEvent e) -> {
            cerrarVentana();
        });
 
//        itemVentana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        itemSeleccion.addActionListener((ActionEvent e) -> {
            ventanaSeleccion.init();
        });
        
        itemTabla.addActionListener((ActionEvent e) -> {
            ventanaTabular.init();
        });
    }
    
    public void cerrarVentana() {
        System.out.println("Cerrando la aplicación..");
        controlador.suprimir(this);
        controlador.cerrarAplicacion();
    }

    public void init() {
        controlador.registrar(this);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
