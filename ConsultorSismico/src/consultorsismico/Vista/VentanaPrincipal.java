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
import java.io.File;
import java.util.Observable;
import java.util.Observer;
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

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//   (VentanaPrincipal)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class VentanaPrincipal extends JFrame implements Observer{

    private BarraCoordenada coordenadas;
    private Controlador controlador;
    private JMenu menuVentana;
    private JMenu menuSeleccion;
    private JMenuBar menuPrincipal;
    private JMenuItem itemTabla;
    private JMenuItem itemArchivo;
    private JMenuItem itemBusqueda;
    private JMenuItem itemSalir;
    private PanelAplicacion panelPrincipal;
    private Modelo modelo = null;
    private VentanaBusqueda ventanaBusqueda;
    private VentanaTabla ventanaTabla;
    private MapaBase base;

    public VentanaPrincipal(String titulo, Controlador controlador) {
        super(titulo);
        try {
            JAXBContext ctx = JAXBContext.newInstance(MapaBase.class);
            Unmarshaller mrs = ctx.createUnmarshaller();
            this.base = (MapaBase) mrs.unmarshal(new File("src/configuracion/map.xml"));
            
        } catch (JAXBException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        this.controlador = controlador;
        this.ventanaTabla = new VentanaTabla("Datos Tabulados",this.controlador);
        this.ventanaBusqueda = new VentanaBusqueda("Consultar Sismos",this.controlador);
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        pack();
        setResizable(true);
        setPreferredSize(new Dimension (base.getImagen().getDimension().getAncho(),base.getImagen().getDimension().getAlto()));
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

        contenedor.setLayout(new BorderLayout());
      
        contenedor.add(BorderLayout.PAGE_END, coordenadas = new BarraCoordenada());
        contenedor.add(BorderLayout.CENTER, panelPrincipal = new PanelMapa(coordenadas , controlador, base));
        contenedor.add(new JScrollPane(panelPrincipal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        
        ajustarMenu();
        
    }

    private void ajustarMenu() {
        menuPrincipal = new JMenuBar();

        menuPrincipal.add(menuVentana = new JMenu("Ventana"));
        menuVentana.add(itemArchivo = new JMenuItem("Archivo"));
        menuVentana.add(itemSalir = new JMenuItem("Salir"));
        
        menuPrincipal.add(menuSeleccion = new JMenu("Selección"));
        menuSeleccion.add(itemBusqueda = new JMenuItem("Búsqueda"));
        menuSeleccion.add(itemTabla = new JMenuItem("Tabla Coordenadas"));
        setJMenuBar(menuPrincipal);

        itemArchivo.addActionListener((ActionEvent e) -> {
            JFileChooser selectorArchivos = new JFileChooser();
            selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int seleccion = selectorArchivos.showOpenDialog(panelPrincipal);
            if(seleccion == JFileChooser.APPROVE_OPTION){
                File archivo = selectorArchivos.getSelectedFile();
                controlador.leerTxt(archivo);
            }
            else{
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún archivo","ARCHIVO-ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        itemSalir.addActionListener((ActionEvent e) -> {
            cerrarVentana();
        });

        itemBusqueda.addActionListener((ActionEvent e) -> {
            ventanaBusqueda.init();
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
        coordenadas.init();
        panelPrincipal.init();
    }

    @Override
    public void update(Observable o, Object arg) {
        modelo = (Modelo) o;
        panelPrincipal.repaint();
    }
}
