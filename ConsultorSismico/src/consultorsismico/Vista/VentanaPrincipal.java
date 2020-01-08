package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import consultorsismico.Modelo.MapaBase;
import consultorsismico.Modelo.Modelo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class VentanaPrincipal extends JFrame implements Observer{

    private BarraCoordenada coordenadas;
    private BufferedImage buffer;
    private Controlador controlador;
    private Image mapaImage;
    private JMenu menuVentana;
    private JMenu menuSeleccion;
    private JMenuBar menuPrincipal;
    private JMenuItem itemTabla;
    private JMenuItem itemArchivo;
    private JMenuItem itemSeleccion;
    private JMenuItem itemSalir;
    private PanelAplicacion panelPrincipal;
    private Modelo modelo = null;
    private VentanaSeleccion ventanaSeleccion;
    private VentanaTabla ventanaTabla;
    private MapaBase base;

    public VentanaPrincipal(String titulo, Controlador controlador) {
        super(titulo);
        try {
            JAXBContext ctx = JAXBContext.newInstance(MapaBase.class);
            Unmarshaller mrs = ctx.createUnmarshaller();
            this.base = (MapaBase) mrs.unmarshal(new File("./map.xml"));
            
        } catch (JAXBException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        this.controlador = controlador;
        this.ventanaTabla = new VentanaTabla("Datos Tabulados",this.controlador);
        this.ventanaSeleccion = new VentanaSeleccion("Consultar Sismos",this.controlador);
        
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        pack();
        setResizable(true);
        setPreferredSize(new Dimension (base.getImagen().getDimension().getAncho(),base.getImagen().getDimension().getAlto()));
        //int x = base.getImagen().getDimension().getAncho() + 25;
        //int y =  base.getImagen().getDimension().getAlto() + 60;
        //setSize(x, y);
        //setMinimumSize(new Dimension(x,y));
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
        
        this.buffer = new BufferedImage(base.getImagen().getDimension().getAncho(),base.getImagen().getDimension().getAlto(),BufferedImage.TYPE_INT_RGB);
        try {
            mapaImage = ImageIO.read(getClass().getResourceAsStream(base.getImagen().getUrl()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Mapa no se cargó correctamente","IMAGEN-ERROR",JOptionPane.ERROR_MESSAGE);
        }

        contenedor.setLayout(new BorderLayout());
      
        contenedor.add(BorderLayout.PAGE_END, coordenadas = new BarraCoordenada());
        contenedor.add(BorderLayout.CENTER, panelPrincipal = new PanelMapa(coordenadas , controlador, mapaImage, buffer, base));
        contenedor.add(new JScrollPane(panelPrincipal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        
        ajustarMenu();
        
    }

    private void ajustarMenu() {
        menuPrincipal = new JMenuBar();

        menuPrincipal.add(menuVentana = new JMenu("Ventana"));
        menuVentana.add(itemArchivo = new JMenuItem("Archivo"));
        menuVentana.add(itemSalir = new JMenuItem("Salir"));
        
        menuPrincipal.add(menuSeleccion = new JMenu("Seleccion"));
        menuSeleccion.add(itemSeleccion = new JMenuItem("Consultar Sismos"));
        menuSeleccion.add(itemTabla = new JMenuItem("Tabla Coordenadas"));
        setJMenuBar(menuPrincipal);

        itemArchivo.addActionListener((ActionEvent e) -> {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int seleccion = selectorArchivos.showOpenDialog(panelPrincipal);
            if(seleccion == JFileChooser.APPROVE_OPTION){
                File archivo = selectorArchivos.getSelectedFile();
                //controlador.leerArchivo(archivo);
            }
            else{
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún archivo","ARCHIVO-ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        itemSalir.addActionListener((ActionEvent e) -> {
            cerrarVentana();
        });
 
//        itemVentana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        itemSeleccion.addActionListener((ActionEvent e) -> {
            ventanaSeleccion.init();
        });
        
        itemTabla.addActionListener((ActionEvent e) -> {
            ventanaTabla.init();
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
        panelPrincipal.init();
        coordenadas.init();
        //controlador.iniciarModelo();
    }

    @Override
    public void update(Observable o, Object arg) {
        modelo = (Modelo) o;
        panelPrincipal.repaint();
    }
}
