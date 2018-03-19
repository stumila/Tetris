package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import controler.IObserver;
import model.Tetraminos;


;

public class MainView implements KeyListener {
		GamePanel GP;
		InformationPanel IP;
		public static final int HEIGHT = 440;
		public static final int WIDTH = 380;
		private ArrayList<IObserver> obsList;
	
		public MainView(){
				go();
			
		}
			
		public void updateView(int[][] blackWhteModel){
			GP.sendModel(blackWhteModel);
		}

		public void updateScore(int i) {
			// TODO Auto-generated method stub
			IP.updateScore(i);
			
		}

		public void updateNextTetro(Tetraminos tetraminos) {
			IP.updateNextTetro(tetraminos);
		}
		public void go(){
			JFrame grafika = new JFrame();
			grafika.setLayout(new FlowLayout());
			grafika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			grafika.setSize(WIDTH,HEIGHT);
			grafika.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			grafika.setMinimumSize(new Dimension(WIDTH,HEIGHT));
			grafika.addKeyListener(this);
			//grafika.setBorder(new EmptyBorder(15, 15, 15, 15));
			GP = new GamePanel();
			GP.setPreferredSize(new Dimension(240, 400));
			GP.setMinimumSize(new Dimension(240,400));
			grafika.getContentPane().add(GP);
			IP = new InformationPanel();
			IP.setPreferredSize(new Dimension(110, 400));
			IP.setMinimumSize(new Dimension(110,400));
			grafika.getContentPane().add(IP);
			grafika.setVisible(true);
			
		}
		
		
		
		public void keyPressed(KeyEvent e) 
		   {
			
		      NotifyObservers(e);
		   }

		private void NotifyObservers(KeyEvent e){
			for(IObserver obs : obsList)
		       {
		           try {
					obs.updateModel(e);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       }	
		}
		
		public void keyReleased(KeyEvent e) {}
		   public void keyTyped(KeyEvent e) {}
		
		public void addObserver(IObserver obs)
		   {
			  if (obs != null){
			    	obsList = new ArrayList<IObserver>();
			        obsList.add(obs);
			    }
		   }

		public void updateActiveTetro(Tetraminos tetraminos) {
			GP.updateNextTetro(tetraminos);
		}

		public void updateHighScore(int highScore) {
			IP.updateHighScore(highScore);			
		}  
		
		public GamePanel getGamePanel(){
			return GP;
		}
		public InformationPanel getInformationPanel(){
			return IP;
		}
		  
};
