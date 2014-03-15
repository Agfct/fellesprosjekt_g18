package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import model.Appointment;

public class AppointmentCellRenderer implements ListCellRenderer<Appointment>{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Appointment> list, Appointment value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		//Creating the JLabel
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		        isSelected, cellHasFocus);
		
//		if(isSelected){
//			setForeground(list.getSelectionForeground());
//			setBackground(list.getSelectionBackground());			
//		}
//		else{
//			setForeground(list.getForeground());
//			setBackground(list.getBackground());	
//		}
		
		// Time     | Title  | Pending
		// Date     | Room   | hidden ?
		String time = Long.toString(value.getStartTime())+'-'+ value.getEndTime();
		String title = value.getTitle();
//		String Pending = value.get // get that persons invitation+
//		String date = value.getDate // get date and put it correctly into label
		
		// Using Html to create a table for the values
	    renderer.setText("<html>"+
	    				"<table>"+
	    					"<tr>"+
	    						"<th>"+time+"</th>"+
	    						"<th>"+title+"</th>"+
	    						"<th>"+"Pending"+"</th>"+
	    					"</tr>"+
	    					"<tr>"+
	    						"<th>"+"dd.mm.year"+"</th>"+
	    						"<th>"+"Room nr 5"+"</th>"+
	    						"<th>"+"Hidden?"+"</th>"+
	    					"</tr>"+
	    				"</table>"+
	    				"</html>");
//	    renderer.setToolTipText("Height "+value.getHeight());
		
		
		return renderer;
	}

}
