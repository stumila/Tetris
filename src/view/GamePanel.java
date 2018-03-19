package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import model.Tetraminos;

public class GamePanel extends JPanel{

	int [][] bwModel= null;
	public Point[] activeTetro;
	int tetroColor;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		//this.setLayout(null);
		for(int i =0; i<bwModel.length; i++){
			for(int j =0; j<bwModel[i].length; j++){
				if(bwModel[i][j]!=0){
					g.setColor(choseColor((bwModel[i][j])));
					g.fillRect(j*20, i*20, 20, 20);
				}
				
				}
		}
		for(int i = 0; i < activeTetro.length; i++){
				g.setColor(choseColor(tetroColor));
				g.fillRect(activeTetro[i].x*20,activeTetro[i].y*20, 20, 20);
					
	}
	
	}
	
	Color choseColor(int nr){
		Color color= null;
		switch(nr){
		case 1: color = Color.MAGENTA;
			break;
		case 2: color = Color.RED;
			break;
		case 3: color =  Color.YELLOW;
			break;
		case 4: color =  Color.BLUE;
			break;
		case 5: color =  Color.GREEN;
			break;
		default: ;
		}
		return color;
	}

	public void sendModel(int [][] _bwModel) {
		bwModel = _bwModel;
		this.repaint();
	}

	public void updateNextTetro(Tetraminos tetro) {
		activeTetro = tetro.getTetro();
		tetroColor=tetro.getColor();
		this.repaint();
		
	}
	
}
