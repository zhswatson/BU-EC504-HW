package bu.ec504.hw4Q4;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.Timer;

import bu.ec504.hw4Q4.uisnake.TCPClient;

import java.awt.geom.*;
 
 
public class MoveLabel extends JPanel implements KeyListener{
     
    double x, y;
    double velX = 0, velY = 0;
    
    public MoveLabel() {
        //Timer t = new Timer(50, this);
        //t.start();
        addKeyListener(this);
        setFocusable(true);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Shape circle = new Ellipse2D.Double(x, y, 20, 20);
        Shape circle1 = new Rectangle.Double(500, 500, 20, 20);
        g2.setColor(Color.BLUE);
        g2.fill(circle);
        g2.fill(circle1);
    }
    

   
    public void keyPressed(KeyEvent e) {
    }
 
 
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            y -= 10;
            repaint();
        }
        if (code == KeyEvent.VK_S) {
            y += 10;
            repaint();
        }
        if (code == KeyEvent.VK_A) {
            x -= 10;
            repaint();
        }
        if (code == KeyEvent.VK_D) {
            x += 10;
            repaint();
        }
     
    }
 
    public void keyTyped(KeyEvent e) {
         
    }
    
	public static void main(String args[]) throws Exception {
		JFrame f = new JFrame();
		MoveLabel x = new MoveLabel();
		f.add(x);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		
		TCPClient tcpc = new uisnake.TCPClient();
		tcpc.fetch();
		tcpc.send("BOARD");
		tcpc.send("FOOD");
		tcpc.send("SNAKES");
		tcpc.send("POINTS");
		tcpc.show();

	}

}