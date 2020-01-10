package consultorsismico.Vista;

import consultorsismico.Controlador.Controlador;
import consultorsismico.Modelo.tablaModel.ModeloColumnas;
import consultorsismico.Modelo.tablaModel.ModeloTablaSismos;
import consultorsismico.Vista.tabla.TablaSismos;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//    (VentanaTabla)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class VentanaTabla extends JFrame implements Observer{
    
    private Controlador controlador;
    private JPanel panelPrincipal;
    private JScrollPane controlDesplazamientoTabla;
    private TablaSismos tablaSismos;

    public VentanaTabla(String titulo, Controlador nuevoGestor) {
        super(titulo);
        this.controlador = nuevoGestor;
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        setResizable(true);
        pack();
        setMinimumSize(getSize());
        setSize(getWidth()*7 / 3, getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        controlDesplazamientoTabla = new JScrollPane(
                tablaSismos = new TablaSismos(
                        new ModeloTablaSismos(controlador.getDatos().obtenerModeloTabla()),
                        new ModeloColumnas())
        );

        panelPrincipal.add(BorderLayout.CENTER, controlDesplazamientoTabla);

        c.add(BorderLayout.CENTER, panelPrincipal);
    }

    public void init() {
        controlador.registrarTabla(this);
        setVisible(true);
    }

    @Override
    public void update(Observable m, Object evt) {
        tablaSismos.actualizar();
    }
    
}
