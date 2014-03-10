package gui;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// Model for the JTable ParticipantsListTable
class ParticipantsTable extends AbstractTableModel {
    private String[] columnNames = {"First Name",
                                    "Last Name",
                                    "Status",
                                    "Remove"};
    

    private ArrayList<ArrayList<Object>> data = new ArrayList();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data.get(row).get(col);
    }
    public void addRow(Object par)
    {	
    	data.add(new ArrayList<Object>());
    // 	data.get(row).get(col).add(somthing);
//    	data[data.length][0] = par.getFirstName();
    	System.out.println(data.size());
    	data.get(data.size()-1).add("Anders");
//    	data[data.length][1] = par.getLastName();
    	data.get(data.size()-1).add("Lunde");
//    	data[data.length][0] = par.getCheckbox ??00 LOLZORName();
    	data.get(data.size()-1).add("");
//    	data[data.length][3] = Killbtn;
    	data.get(data.size()-1).add("");
    	
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 3) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
//        if (true) {
//            System.out.println("Setting value at " + row + "," + col
//                               + " to " + value
//                               + " (an instance of "
//                               + value.getClass() + ")");
//        }

        data.get(row).set(col, value);
        fireTableCellUpdated(row, col);

//        if (true) {
//            System.out.println("New value of data:");
//            printDebugData();
//        }
    }

//    private void printDebugData() {
//        int numRows = getRowCount();
//        int numCols = getColumnCount();
//
//        for (int i=0; i < numRows; i++) {
//            System.out.print("    row " + i + ":");
//            for (int j=0; j < numCols; j++) {
//                System.out.print("  " + data[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");
//    }
}

