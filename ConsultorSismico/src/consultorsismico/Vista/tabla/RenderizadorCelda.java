package consultorsismico.Vista.tabla;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//  Universidad Nacional
//  Facultad de Ciencias Exactas y Naturales
//  Escuela de Informática
//  
//      I Proyecto
//  (RenderizadorCelda)
//
//  Autores: Joel Agüero Campos
//           Rebecca Garita Gutiérrez
//           María Fernanda González Arias
//
//  III Ciclo 2019

public class RenderizadorCelda extends DefaultTableCellRenderer {
    public RenderizadorCelda() {
        configurar();
    }

    private void configurar() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = (JComponent) super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus,
                row, column);
        return c;
    }

    public static final DefaultTableCellRenderer DEFAULT_RENDERER
            = new DefaultTableCellRenderer();

    protected static final Color COLOR_FILA_PAR = new Color(218, 209, 237);
    protected static final Color COLOR_FILA_IMPAR = new Color(255, 255, 255);
    protected static final Color COLOR_FILA_SELECCIONADA = new Color(255, 237, 196);
}
