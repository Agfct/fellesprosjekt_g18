package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CalendarView extends JPanel {
	private Image backgroundImg;
	
	public CalendarView(){
		backgroundImg = new ImageIcon(this.getClass().getResource("/backgrounds/background2.png")).getImage();
	}
	
	// Overriding the paintComponent to get GraidientPaint
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //background Image
        g2d.drawImage(backgroundImg, 0, 0, this);
        
        //GRADIENT BACKGROUND
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        int w = getWidth();
//        int h = getHeight();
//        Color color1 = Color.BLUE;
//        Color color2 = Color.WHITE;
//        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h/2, color2);
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
    }
}
