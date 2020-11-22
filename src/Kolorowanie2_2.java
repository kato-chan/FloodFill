import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Kolorowanie2_2 {
	private JFrame frame;
	private JPanel buttonPanel;
	boolean clickEight = false;
	boolean clickSix = false;
	boolean clickFour = false;
	boolean clickStart = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kolorowanie2_2 window = new Kolorowanie2_2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Kolorowanie2_2() {
		startGUI();
	}
	
	private void startGUI() {
		frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());		
		frame.setTitle("Kolorowanie");
		frame.setSize(1080, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyCanvas canvas = new MyCanvas();
		frame.getContentPane().add(canvas);
		canvas.addMouseListener(canvas);
		frame.setVisible(true);	
		
		buttonPanel = new JPanel(new GridLayout(2,1));	
		
		frame.getContentPane().add(buttonPanel, BorderLayout.EAST);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnStart = new JMenu("Start");
		menuBar.add(mnStart);
		
		JMenuItem mntmStart = new JMenuItem("Start");
		mnStart.add(mntmStart);
		mntmStart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            clickSix = false;
            clickFour = false;
            clickEight = false;
            clickStart = true;
            canvas.repaint();
            }
            });
		
		JMenu mnSasiedztwo = new JMenu("No. of djacent cells");
		menuBar.add(mnSasiedztwo);
		
		JMenuItem mntmOsiem = new JMenuItem("Eight");
		mnSasiedztwo.add(mntmOsiem);
		mntmOsiem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            clickEight = true;
            clickSix = false;
            clickFour = false;
            clickStart = false;
            canvas.repaint();
            }
            });
		
		JMenuItem mntmSze = new JMenuItem("Six");
		mnSasiedztwo.add(mntmSze);
		mntmSze.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            clickSix = true;
            clickEight = false;
            clickFour = false;
            clickStart = false;
            canvas.repaint();
            }
            });
		
		JMenuItem mntmCztery = new JMenuItem("Four");
		mnSasiedztwo.add(mntmCztery);
		mntmCztery.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            clickFour = true;
            clickSix = false;
            clickEight = false;
            clickStart = false;
            canvas.repaint();
            }
            });
		
	}
	
	class MyCanvas extends JPanel implements MouseListener { 

		private int x,y;
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> points2 = new ArrayList<Point>();
		ArrayList<Point> points3 = new ArrayList<Point>();
	
		
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		
		@Override
		 public void mouseEntered(MouseEvent e) {
		  // TODO Auto-generated method stub
		 }

		 @Override
		 public void mouseExited(MouseEvent e) {
		  // TODO Auto-generated method stub
		 }		 
		
		 @Override
		    public void mousePressed(MouseEvent e) {
		        x = e.getX();
		        y = e.getY();
		      
		        int xSiatki = x/20;
		        int ySiatki = y/20;
		        System.out.println("Siatka x: " + xSiatki + " " + "y: " + ySiatki );
		        
		        if(clickEight){
		        points.add(new Point(xSiatki,ySiatki));
		        }
		        if(clickSix){
			    points2.add(new Point(xSiatki,ySiatki));
		        }
		        if(clickFour){
		        points3.add(new Point(xSiatki,ySiatki));
		        }
		        repaint();	        
		        }
		 
		 @Override
		 public void mouseReleased(MouseEvent e) {
		  // TODO Auto-generated method stub
		 }
		    
		 @Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				BufferedImage img = Rysunek();
			    g.drawImage(img, 0,0,this);
				
	            if (clickStart){
				    g2d.drawImage(img, 0,0,this);
	            	repaint();
	            	points.clear();
	            	points2.clear();
	            	points3.clear();}
	            
	            if (clickEight){
	            	points2.clear();
	            	points3.clear();
				    g2d.drawImage(img, 0,0,this);
	            	
	            	for(Point p: points) {
	            		int x,y;
	    				x = (int)p.getX();
	    				y = (int)p.getY();	  
	    				Color target = new Color(0,0,0);
	    				
	    				System.out.println(target.getRGB());
	    				System.out.println(img.getRGB(20*x,20*y)); 				
	    				
	    			try {
						drawEightFlood(g2d, x, y, target, img);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    			}
	        	    
	

	           }
	            if (clickSix){	   
	            	points.clear();
	            	points3.clear();
	            	g2d.drawImage(img, 0,0,this);
	            	
	            	for(Point p: points2) {
	            		int x,y;
	    				x = (int)p.getX();
	    				y = (int)p.getY();	  
	    				Color target = new Color(0,0,0);
	    				
	    				System.out.println(target.getRGB());
	    				System.out.println(img.getRGB(20*x,20*y)); 				
	    				
	    			drawSixFlood(g2d, x, y, target, img);
	    			}
			}
	            if(clickFour){
	            	points.clear();
	            	points2.clear();
			    g2d.drawImage(img, 0,0,this);
            	
            	for(Point p: points3) {
            		int x,y;
    				x = (int)p.getX();
    				y = (int)p.getY();	  
    				Color target = new Color(0,0,0);
    				
    				System.out.println(target.getRGB());
    				System.out.println(img.getRGB(20*x,20*y)); 				
    				
    			drawFourFlood(g2d, x, y, target, img);
    			}
	        }
		 }
			
			private void drawEightFlood (Graphics2D g2d, int x, int y, Color target, BufferedImage img) throws InterruptedException{
				
				//Timer timer = new Timer(10, new ActionListener() {
				//public void actionPerformed(ActionEvent e) {
			    if (img.getRGB(20*x,20*y) != target.getRGB() && x>0 && y>0 && (x+1) < img.getWidth()/20 && (y+1) < img.getHeight()/20){					
			    	
			    img.setRGB(20*x, 20*y, target.getRGB());
				g2d.setColor(target);
				g2d.fillRect(20*x, 20*y, 20, 20);
				g2d.fillRect (20*x, 20*(y+1), 20, 20);
				g2d.fillRect (20*(x+1), 20*y, 20, 20);									
				g2d.fillRect (20*(x-1), 20*y, 20, 20);
				g2d.fillRect (20*x, 20*(y-1), 20, 20);									
				g2d.fillRect (20*(x-1), 20*(y+1), 20, 20);
				g2d.fillRect (20*(x+1), 20*(y-1), 20, 20);
				g2d.fillRect (20*(x-1), 20*(y-1), 20, 20);
				g2d.fillRect (20*(x+1), 20*(y+1), 20, 20);
			    
			    drawEightFlood (g2d, x, (y+1), target, img);
				drawEightFlood (g2d, (x+1), y, target, img);
				drawEightFlood (g2d, (x+1), (y+1), target, img);
				drawEightFlood (g2d, (x-1), (y+1), target, img);
				drawEightFlood (g2d, x, (y-1), target, img);
				drawEightFlood (g2d, (x+1), (y-1), target, img);
				drawEightFlood (g2d, (x-1), (y-1), target, img);
				drawEightFlood (g2d, (x-1), y, target, img);
				}		
			    
    //else {
       // ((Timer)e.getSource()).stop();
    //}
	   // }
        //});
	//timer.start();
			}
			
	private void drawSixFlood (Graphics2D g2d, int x, int y, Color target, BufferedImage img){
				
				
				//Timer timer = new Timer(10, new ActionListener() {
				//public void actionPerformed(ActionEvent e) {
				    	
			    if (img.getRGB(20*x,20*y) != target.getRGB() && x>0 && y>0 && (x+1) < img.getWidth()/20 && (y+1) < img.getHeight()/20){
					
				img.setRGB(20*x, 20*y, target.getRGB());
				g2d.setColor(target);
				g2d.fillRect(20*x, 20*y, 20, 20);
				g2d.fillRect (20*x, 20*(y+1), 20, 20);
				g2d.fillRect (20*(x+1), 20*y, 20, 20);									
				g2d.fillRect (20*(x-1), 20*y, 20, 20);
				g2d.fillRect (20*x, 20*(y-1), 20, 20);									
				g2d.fillRect (20*(x-1), 20*(y+1), 20, 20);
				g2d.fillRect (20*(x+1), 20*(y-1), 20, 20);
		    					
				drawSixFlood (g2d, x, (y+1), target, img);
				drawSixFlood (g2d, (x+1), y, target, img);
				drawSixFlood (g2d, (x-1), (y+1), target, img);
				drawSixFlood (g2d, x, (y-1), target, img);
				drawSixFlood (g2d, (x+1), (y-1), target, img);
				drawSixFlood (g2d, (x-1), y, target, img);
			
				}
			    //else {
	            //    ((Timer)e.getSource()).stop();
	            //}
				//    }
			    //    });
				//timer.start();
			}
	
	private void drawFourFlood (Graphics2D g2d, int x, int y, Color target, BufferedImage img){
		
		
		//Timer timer = new Timer(10, new ActionListener() {
		//public void actionPerformed(ActionEvent e) {
		    	
	    if (img.getRGB(20*x,20*y) != target.getRGB() && x>0 && y>0 && (x+1) < img.getWidth()/20 && (y+1) < img.getHeight()/20){
			
		img.setRGB(20*x, 20*y, target.getRGB());
		g2d.setColor(target);
		g2d.fillRect(20*x, 20*y, 20, 20);
		g2d.fillRect (20*x, 20*(y+1), 20, 20);
		g2d.fillRect (20*(x+1), 20*y, 20, 20);									
		g2d.fillRect (20*(x-1), 20*y, 20, 20);
		g2d.fillRect (20*x, 20*(y-1), 20, 20);									
    					
		drawFourFlood (g2d, x, (y+1), target, img);
		drawFourFlood (g2d, (x+1), y, target, img);
		drawFourFlood (g2d, x, (y-1), target, img);
		drawFourFlood (g2d, (x-1), y, target, img);
	
		}
	    //else {
        //    ((Timer)e.getSource()).stop();
        //}
		//    }
	    //    });
		//timer.start();
	}
		
			private BufferedImage Rysunek(){
			BufferedImage bufferedImage = new BufferedImage(1060,520,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bufferedImage.createGraphics();
			Color black = new Color (0,0,0);
			
			for (int i=0; i<54;i++){
				for (int j=0; j<34; j++){
					
					g2d.setColor(Color.GRAY);
					g2d.drawRect (20*i, 20*j, 20, 20); 					
				
					if ((i == 0 || i == 52) && (j>12 && j<21 )){
						g2d.setColor(black);
						g2d.fillRect(20*i, 20*j, 20, 20);
				}
					if (((i>0 && i<11) || (i>42 && i<53)) &&(j == 13 || j == 20)){
							g2d.setColor(black);
						g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if ((i > 16 && i < 37) && (j == 7 || j == 20)){		
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}						
					if ((i > 10 && i < 17) && (j == (23-i))){		
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if ((i > 9 && i < 16) && (j == (22-i))){			
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if ((i > 36 && i < 43) && (j == (i-30))){			
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if ((i > 37 && i < 44) && (j == (i-31))){					
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if (((i > 10 && i < 17) || (i>36 && i<43)) && (j==23)){		
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
				}
					if ((i == 11 || i == 16 || i == 37 || i == 42) && (j>19 && j<23)){		
							g2d.setColor(black);
							g2d.fillRect(20*i, 20*j, 20, 20);	
					}
				}			
		}
			g2d.dispose();
			return bufferedImage;
	}	
}	
	}

