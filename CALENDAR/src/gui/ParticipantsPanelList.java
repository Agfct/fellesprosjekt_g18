package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Invitation;

// Used to create a List of ParticipantsViews
public class ParticipantsPanelList extends JPanel {
	GridBagLayout layout;
	Integer rows;
	JScrollPane scrollPane;
	NewAppointmentView newAppointmentView;
	ArrayList<ParticipantsPanel> participantsPanels;
	
	public ParticipantsPanelList(){
		
			//setting layout
			layout = new GridBagLayout();
			setLayout(layout);
//			layout.setColumns(1);
			rows = 0;
			setBackground(Color.WHITE);
			
			//Test
			//http://www.java-forums.org/awt-swing/34534-gridbaglayout-anchor-wanna-move-all-components-top-left.html
			GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1000;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.NORTH;//upwards
            c.weightx = 0;
            c.weighty = 1000;
            c.ipadx = 0;
            c.ipady = 0;
            //Insets(top, left, bottom, right);
            c.insets = new Insets( 0,0,0,0 );
 
            Label bottomEmptyLabel = new Label( "" );
//            bottomEmptyLabel.setBackground(( Color.pink ) );
            setCompSize(bottomEmptyLabel, 3, 3 );
            this.add( bottomEmptyLabel , c);
            participantsPanels = new ArrayList<ParticipantsPanel>();
	}
	
	public void addParticipantPanelByInvitation(Invitation invite){
		ParticipantsPanel newView = new ParticipantsPanel(invite.getEmployee());
		newView.setInvitation(invite);
		addParticipantPanel(newView);
	}
	//adding a new View to the Panel
	public void addParticipantPanel(ParticipantsPanel newView){
//		layout.setRows(rows+1);
		GridBagConstraints cLabel = new GridBagConstraints();
		cLabel.gridy = rows;
//		cLabel.weightx = 1d;
		cLabel.weighty = 1d;
		cLabel.anchor = GridBagConstraints.PAGE_START;
		newView.setParticipantsPanelList(this);
		participantsPanels.add(newView);
		this.add(newView,cLabel);
		rows += 1; // WARNING ROWS WILL ALWAYS INCREASE, REMEMBER TO SORT LIST WHEN SAVING.
		repaint();
		invalidate();
		validate();
		// revalidating the Scroll pane
		if (scrollPane != null){
			scrollPane.repaint();
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}
	
	//Methods to respond to the presses in the Views in the ListPanel
	public void removeParticipantPanel(Invitation invite){
		
		for (ParticipantsPanel panel : participantsPanels) {
			if (panel.getInvite().equals(invite)){
				this.remove(panel);
			}
		}
		repaint();
		invalidate();
		validate();
		if (scrollPane != null){
			scrollPane.repaint();
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}
	
	public void setScrollPane(JScrollPane newScrollPane){
		scrollPane = newScrollPane;
	}
	public void setNewAppointmentView(NewAppointmentView view){
		this.newAppointmentView = view;
	}
	public NewAppointmentView getNewAppointmentView(){
		return newAppointmentView;
	}
	public void resetRows(){
		rows = 0;
	}
	
	//test
	public static void setCompSize(Component comp , int width, int height)
    {
        Dimension size = new Dimension( width, height );
         
        comp.setSize(size);
        comp.setPreferredSize(size);
        comp.setMinimumSize(size);
    }


}