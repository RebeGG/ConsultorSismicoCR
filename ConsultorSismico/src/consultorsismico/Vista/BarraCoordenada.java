package consultorsismico.Vista;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//   (BarraCoordenadas)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class BarraCoordenada extends JPanel {
    public BarraCoordenada() {
        super();
        configurar();
    }
 
    private void configurar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(coordenadaLbl = new JLabel());
    }
 
    public void init() {
        mostrarMensaje();
    }
 
    public void mostrarMensaje(String msj) {
        coordenadaLbl.setText(String.format("%s ", msj));
    }
 
    public void mostrarMensaje() {
        mostrarMensaje("");
    }
 
    private JLabel coordenadaLbl;
}
