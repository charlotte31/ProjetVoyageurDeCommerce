/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Charlotte
 */
public class ModelTable extends AbstractTableModel {

    List<String> data;

    public ModelTable() {
        data = new ArrayList<>();
    }

    public void addData(String s) {
        data.add(s);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] tab = data.toArray();
        String p = (String) tab[rowIndex];
        switch (columnIndex) {
            case 0:
                return p;
            case 1:
                return p;
            case 2:
                return p;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int idC) {
        switch (idC) {
            case 0:
                return "Ville 1";
            case 1:
                return "Ville 2";
            case 2:
                return "Distance";
            default:
                return null;
        }
    }

}
