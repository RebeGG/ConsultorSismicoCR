package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends JFrame {
    
    private BarraCoordenada coordenadas;
    private Controlador controlador;
    private Image mapaImage;
    private JPanel panelPrincipal;
    private JLabel backgroundLbl;
    private JMenuBar menuPrincipal;
    private static final String MAPA_PATH = "/media/MapaCR.png";
    
    public VentanaPrincipal(String titulo, Controlador controlador){
        super(titulo);
        this.controlador = controlador;
        try {
            mapaImage = ImageIO.read(getClass().getResourceAsStream(MAPA_PATH));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        configurar();
    }
    
    private void configurar(){
        ajustarComponentes(getContentPane());
        pack();
        setResizable(true);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void ajustarComponentes(Container contenedor){
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
        contenedor.add(new JScrollPane(panelPrincipal,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
        
    }
    
    private void ajustarMenu() {
        menuPrincipal = new JMenuBar();
 
//        menuPrincipal.add(menuVentana = new JMenu("Ventana"));
//        menuVentana.add(itemSalir = new JMenuItem("Salir"));
// 
//        menuPrincipal.add(menuSeleccion = new JMenu("Seleccion"));
//        menuSeleccion.add(itemSeleccion = new JMenuItem("Abrir Ventana"));
 
        setJMenuBar(menuPrincipal);
 
//        itemSalir.addActionListener((ActionEvent e) -> {
//            cerrarVentana();
//        });
// 
//        itemVentana.addActionListener((ActionEvent e) -> {
//            abrirVentanaInfo();
//        });
//        itemVentana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
 
    }
    
    public void init() {
        //controlador.registrar(this);
        setVisible(true);
    }
}
