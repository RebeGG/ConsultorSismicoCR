package consultorsismico.Modelo.tablaModel;

import consultorsismico.Modelo.ConjuntoSismos;
import consultorsismico.Modelo.Latitud;
import consultorsismico.Modelo.Longitud;
import consultorsismico.Modelo.Sismo;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaSismos extends AbstractTableModel {
   
    private final ConjuntoSismos listaSismos;
    
    public ModeloTablaSismos(ConjuntoSismos listaSismos) {
        this.listaSismos = listaSismos;
    }

    @Override
    public int getRowCount() {
        return listaSismos.cantidadSismos();
    }

    @Override
    public int getColumnCount() {
        return Sismo.getFieldCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object r = listaSismos.obtener(rowIndex).toArray()[columnIndex];
        return r;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        super.setValueAt(value, rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return CLASE_COLUMNA[columnIndex];
    }

    public void actualizar() {
        fireTableDataChanged();
    }

    private static final Class<?>[] CLASE_COLUMNA = {
        Integer.class, Integer.class, String.class, Latitud.class, Longitud.class, Double.class, Double.class
    };
    
}
