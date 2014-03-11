package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Used to create a List of ParticipantsViews
public class ParticipantsListPanel extends JPanel {
	GridBagLayout layout;
	Integer rows;
	JScrollPane scrollPane;
	
	public ParticipantsListPanel(){
		
			//setting layout
			layout = new GridBagLayout();
			setLayout(layout);
//			layout.setColumns(1);
			rows = 0;
			
			
			//TEST
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
            bottomEmptyLabel.setBackground(( Color.pink ) );
            setCompSize(bottomEmptyLabel, 3, 3 );
            this.add( bottomEmptyLabel , c);

	}
	
	//adding a new View to the Panel
	public void addParticipantView(ParticipantsView newView){
//		layout.setRows(rows+1);
		GridBagConstraints cLabel = new GridBagConstraints();
		cLabel.gridy = rows;
//		cLabel.weightx = 1d;
		cLabel.weighty = 1d;
		cLabel.anchor = GridBagConstraints.PAGE_START;
		newView.setParticipantsListPanel(this);
		this.add(newView,cLabel);
		rows += 1;
	}
	public void removeParticipantView(ParticipantsView oldView){
		this.remove(oldView);
//		if (rows-1 != -1){
////			layout.setRows(rows-1);
//			rows -= 1;
//		}
		System.out.println("Rows " + rows);
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
	
	//test
	public static void setCompSize(Component comp , int width, int height)
    {
        Dimension size = new Dimension( width, height );
         
        comp.setSize(size);
        comp.setPreferredSize(size);
        comp.setMinimumSize(size);
    }


}