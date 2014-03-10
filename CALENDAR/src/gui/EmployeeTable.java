package gui;

import javax.swing.table.AbstractTableModel;

class EmployeeTable extends AbstractTableModel {
    private String[] columnNames = {"First Name",
                                    "Last Name",
                                    "#",
                                    "Add"};
    

    private Object[][] data = {
    {"Kathy", "Smith", new Integer(1), new Boolean(false)},
    {"John", "Doe", new Integer(3), new Boolean(false)},
    {"Sue", "Black", new Integer(2), new Boolean(false)},
    {"Jane", "White", new Integer(20), new Boolean(false)},
    {"Joe", "Brown", new Integer(10), new Boolean(false)},
    {"Kathy", "Smith", new Integer(5), new Boolean(false)},
    {"John", "Doe", new Integer(3), new Boolean(false)},
    {"Sue", "Black", new Integer(2), new Boolean(false)},
    {"Jane", "White", new Integer(20), new Boolean(false)},
    {"Joe", "Brown", new Integer(10), new Boolean(false)},
    {"Kathy", "Smith", new Integer(5), new Boolean(false)},
    {"John", "Doe", new Integer(3), new Boolean(false)},
    {"Sue", "Black", new Integer(2), new Boolean(false)},
    {"Jane", "White",new Integer(20), new Boolean(false)},
    {"Joe", "Brown", new Integer(10), new Boolean(false)},
    {"Kathy", "Smith", new Integer(5), new Boolean(false)},
    {"John", "Doe", new Integer(3), new Boolean(false)},
    {"Sue", "Black",new Integer(2), new Boolean(false)},
    {"Jane", "White", new Integer(20), new Boolean(false)},
    {"Joe", "Brown", new Integer(10), new Boolean(false)}
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
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

        data[row][col] = value;
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

