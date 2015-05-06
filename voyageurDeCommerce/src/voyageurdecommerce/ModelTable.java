/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JTable;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Charlotte
 */
public class ModelTable extends AbstractTableModel {

    private final String[] entetes;
    private final ArrayList<Arc> donnees;

    public ModelTable() {
        super();
        donnees = new ArrayList<Arc>();
        entetes = new String[]{"Ville 1", "Ville 2", "Distance"};

    }

    public void addData(Arc a) {
        if (!donnees.contains(a) == true) {
            donnees.add(a);
            fireTableRowsInserted(donnees.size() - 1, donnees.size() - 1);
        }
        //fireTableDataChanged();
    }

    public void removeData(int rowIndex) {
        donnees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);

    }

    @Override
    public int getRowCount() {
        return donnees.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] tab = donnees.toArray();
       
        Arc p = (Arc) tab[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getNomV1();
            case 1:
                return p.getNomV2();
            case 2:

                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(p.getDistance());

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int idC) {
        if (idC <= entetes.length) {
            return entetes[idC];
        } else {
            return null;
        }
        //switch (idC) {
        //case 0:
        //return "Ville 1";
        // case 1:
        // return "Ville 2";
        //case 2:
        //  return "Distance";
        //default:
        //return null;
    }

}
