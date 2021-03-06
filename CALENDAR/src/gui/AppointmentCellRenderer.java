package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import model.Appointment;
import model.Invitation;
import model.InvitationStatus;

public class AppointmentCellRenderer implements ListCellRenderer<Appointment>{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Appointment> list, Appointment value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		//Creating the JLabel
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		        isSelected, cellHasFocus);
		
		// Time     | Title  | Pending
		// Date     | Room   | hidden ?
		String room;
		if(value.getRoom() != null && value.isInternal()){
			 room = value.getRoom().toString();
		}
		else if(value.isInternal()){
			 room = "TBA";
		}
		else{
			 room = value.getLocation();
		}
		String time = value.getStartTime()+"-"+value.getEndTime();
		String title = value.getTitle();
		if(value.isDeleted()){ //if the appointment is deleted
			title = "<font color=\"RED\">DELETED</font>";
		}else if(value.getInvitation(MainWindow.getUser()).isDeleted()){ // if you where removed
			title = "<font color=\"RED\">UNINVITED</font>";
		}
		Invitation invitation = value.getInvitation(MainWindow.getUser()); // get that persons invitation+
		InvitationStatus status;
		if(invitation != null){
			 status  = invitation.getStatus();
		}
		else{
			 status = InvitationStatus.PENDING;
		}
		String hide;
		boolean hidden = invitation.isHidden();
		if (hidden){
			hide = "hidden";
		}else{
			hide = "visible";
		}
		
		// Using Html to create a table for the values
	    renderer.setText("<html>"+
	    					"<style>"+"table.fixed {table-layout:fixed;} th.long {text-align:right;}"+"</style>"+
	    				"<table class='fixed'>"+
	    					"<tr>"+
	    						"<th width='40'px>"+time+"</th>"+
	    						"<th width='240'px>"+title+"</th>"+
	    						"<th class='long' width='20'px>"+status.getStatus()+"</div>"+
	    					"</tr>"+
	    					"<tr>"+
	    						"<th width='40'px>"+value.getDateString()+"</th>"+
	    						"<th width='40'px>"+room+"</th>"+
	    						"<th class='long' width='20'px>"+hide+"</div>"+
	    					"</tr>"+
	    				"</table>"+
	    				"</html>");
	    
	    if(value.getInvitation(MainWindow.getUser()).isEdited() == true && isSelected){
	    	value.getInvitation(MainWindow.getUser()).setEdited(false);
	    	MainWindow.getRequestHandler().editInvitation(value.getInvitation(MainWindow.getUser()));
	    }
	    if(value.getInvitation(MainWindow.getUser()).isNew() == true && isSelected){
	    	value.getInvitation(MainWindow.getUser()).setNew(false);
	    	MainWindow.getRequestHandler().editInvitation(value.getInvitation(MainWindow.getUser()));
	    }
	    if(value.getInvitation(MainWindow.getUser()).isEdited() || value.getInvitation(MainWindow.getUser()).isNew()){
//	    	renderer.setBorder(BorderFactory.createLineBorder(Color.RED));
	    	renderer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
	    	
	    }
	    else{
	    	renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));	    	
	    }
//	    renderer.setToolTipText("Height "+value.getHeight());
		
		return renderer;
	}

}
