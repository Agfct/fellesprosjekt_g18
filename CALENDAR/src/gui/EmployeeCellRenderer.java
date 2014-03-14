package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import model.Employee;

public class EmployeeCellRenderer extends JCheckBox implements ListCellRenderer<Employee>{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Employee> list, Employee value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		
		setOpaque(false);
//		if(isSelected){
//			setForeground(list.getSelectionForeground());
//			setBackground(list.getSelectionBackground());			
//			setBorder(new LineBorder(Color.BLUE));
//		}
//		else{
//			setForeground(list.getForeground());
//			setBackground(list.getBackground());	
//		}
//		
		setEnabled(list.isEnabled());
		setSelected(((Employee)value).isSelected());
		setFont(list.getFont());
		setText(value.toString());
		
		
		return this;
	}

}
