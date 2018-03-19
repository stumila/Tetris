package controler;

import java.awt.event.KeyEvent;

import model.ModelBoard;
import model.Tetraminos;
import view.MainView;


public class Controler implements IObserver {

	ModelBoard MD ;
	MainView MV ;
	
	public Controler(MainView view, ModelBoard model){
		MD=model;
		MV= view;
	}
	
	public void addModelObserver(){
		MD.addObserver(this);
	}
	public void addViewlObserver(){
		MV.addObserver(this);
	}
	
	int[][]getBlackWhiteModel(){
		int temp[][]= MD.getBlackWhiteModel();
		return temp;
	}
	
	int getScore(){
		int score=MD.getScore();
		System.out.println("wynik to " + Integer.toString(score));
		return score;
	}
	int getHighScore(){
		int score=MD.getHighScore();
		System.out.println("wynik to " + Integer.toString(score));
		return score;
	}
	
	Tetraminos getNextTetro(){
		Tetraminos tetro = MD.getNextTetro();
		return tetro;
	}
	
	Tetraminos getActiveTetro(){
		Tetraminos temp = MD.getActiveTetro();
		return temp;
	}
	public void updateView(int nr){
		switch(nr){
		case 1: MV.updateView(getBlackWhiteModel());
			break;
		case 2: MV.updateScore(getScore());
			break;
		case 3: MV.updateNextTetro(getNextTetro());
			break;
		case 4: MV.updateActiveTetro(getActiveTetro());
			break;
		case 5: MV.updateHighScore(getHighScore());
		break;
		default: ;
	}

	}
	 public void updateModel(KeyEvent e) {
		 switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				MD.rotateTetro();
				break;
			case KeyEvent.VK_DOWN:
				MD.dropDownTetro();
				break;
			case KeyEvent.VK_LEFT:
				MD.moveTetroHorizontal(-1);
				break;
			case KeyEvent.VK_RIGHT:
				MD.moveTetroHorizontal(1);
				break;
			case KeyEvent.VK_SPACE:
				
				break;
			} 
	 }
	

	
}
