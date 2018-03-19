package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import controler.IObserver;




public class ModelBoard   { 
	ArrayList<Tetraminos> tetros;
	Tetraminos nextTetro;
	Tetraminos activeTetro;
	private ArrayList<IObserver> obsList;
	int BlackWhiteModel[][]= new int[20][12];
	Point[] check = new Point[4];
	int score=0;
	int highScore;
	int dropingTime=500;
	int deletedRow=0;
	boolean isSet=false;

	boolean isPaused;
	void gameLoop(){
		while(isPaused==false){
			try {
				moveDownTetro();
				Thread.sleep(dropingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void init(){
		//incjalizacja 
		createTetraminosList();
		clearBoard();
		randomTetro();
		setActiveTetro();
		isPaused=false;
		gameLoop();
		
		
		//moveTetroHorizontal(-1);
		rotateTetro();	
	}
	
	public int getHighScore(){
		return highScore;
	}	
	public int getScore(){
		return score;
	}	
	
	public Tetraminos getNextTetro(){
		return nextTetro;
	}
	public void randomTetro(){
		//Random kolor //random shape 
		Random generator = new Random();
		int color=generator.nextInt(5);
		nextTetro = new Tetraminos(null, null, null, null, 0);
		Tetraminos chosen=tetros.get(generator.nextInt(tetros.size()));
		for(int i =0; i< 4 ;i++){
			nextTetro.all[i]=(Point)chosen.all[i].clone();
			}
		
		nextTetro.setColor(color+1);
		notifyObserver(3);
	}
	public void dropDownTetro(){
		isSet=false;
		while(isSet==false){
			moveDownTetro();
		}
		
	}
	void moveDownTetro(){
		for(int i =0; i< 4 ;i++){
			check[i]=(Point) activeTetro.all[i].clone();
			}
		activeTetro.moveDown();
		if(checkColision()){
			isSet=true;
			for(int i =0; i< 4 ;i++){
				activeTetro.all[i]=check[i];
			}
			updateBlackWhiteModel();
			clearRows();
			setActiveTetro();
		}else notifyObserver(4);
	}
	public void rotateTetro(){
		for(int i =0; i< 4 ;i++){
			check[i]=(Point) activeTetro.all[i].clone();
			}
		activeTetro.rotate();
		if(checkColision()){
			for(int i =0; i< 4 ;i++){
				activeTetro.all[i]=check[i];
			}
		}else notifyObserver(4);
	}
	public void moveTetroHorizontal(int direction){
		for(int i =0; i< 4 ;i++){
			check[i]=(Point) activeTetro.all[i].clone();
			}
		activeTetro.moveHorizontal(direction);
		if(checkColision()){
			for(int i =0; i< 4 ;i++){
				activeTetro.all[i]=check[i];
			}
		}else notifyObserver(4);
	}
	void deleteRow(int j){
		
		for(int i = 0; i < BlackWhiteModel[i].length; i++){
			BlackWhiteModel[j][i]=0;
		}
		displayBWM();
		for (int row = j-1; row  > 0; row-- ){
			for(int i = 0; i < BlackWhiteModel[i].length; i++){
				BlackWhiteModel[row+1][i]= BlackWhiteModel[row][i];
			}
		}
		notifyObserver(1);
		
	}
	
	public void clearRows(){
		int numClears=0;
		boolean gap;
		for (int j = 19; j > 0; j--) {
		gap = false;
			for (int i = 0; i < 12; i++) {
				if (BlackWhiteModel[j][i] == 0) {
					gap = true;
					break;
				}
			}
			if (!gap) {
				deleteRow(j);
				j += 1;
				numClears += 1;
				}
		}
		switch (numClears) {
		case 1:
			score += 50;
			break;
		case 2:
			score += 150;
			break;
		case 3:
			score += 300;
			break;
		case 4:
			score += 600;
			break;
		}
		deletedRow+=numClears;
		if(deletedRow==50){
			score += 2500;
			dropingTime-=0.07*dropingTime;
			deletedRow=0;
		}
		if(numClears>0){
			System.out.println("wynik to " + Integer.toString(score));
			notifyObserver(2);
			
		}
		
	}
	
	public void setActiveTetro(){
		activeTetro = nextTetro;
		randomTetro();
		activeTetro.all[0].setLocation(6, 0);
		for(int i = 1; i < activeTetro.all.length; i++){
			activeTetro.all[i].translate(activeTetro.all[0].x, activeTetro.all[0].y);
		}
		notifyObserver(4);
		
	}
	void updateBlackWhiteModel(){
		
		for(int i = 0; i < activeTetro.all.length; i++){
				BlackWhiteModel[activeTetro.all[i].y][activeTetro.all[i].x]=activeTetro.colorNumber;	
		}
		displayBWM();
		notifyObserver(1);
	}

	public int[][] getBlackWhiteModel(){
		return BlackWhiteModel;
	}
	boolean checkColision() {
	
		for(int i =0; i< 4 ;i++){
			if(activeTetro.all[i].y == 0 && BlackWhiteModel[activeTetro.all[i].y][activeTetro.all[i].x]!=0){
				isPaused=true;
				
				//KONIEC GRY 
			}
			if(activeTetro.all[i].x < 0 || activeTetro.all[i].x>=BlackWhiteModel[1].length || activeTetro.all[i].y>=BlackWhiteModel.length){
				return true;
			}
			if(BlackWhiteModel[activeTetro.all[i].y][activeTetro.all[i].x]!=0){
				return true;
			}
		}
		return false;
		
	}
	public void createTetraminosList(){
		tetros =new ArrayList<Tetraminos>();
		tetros.add(new Tetraminos(new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1),-1));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(-1,0),0));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(-1, 1),0));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(-1, 1),0));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(1, 1), new Point(-1, 0), new Point(1, 0),0));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),0));
		tetros.add(new Tetraminos(new Point(0, 0), new Point(1, 0), new Point(-1, 0), new Point(0, 1),0));
	}
	void clearBoard(){
		initBlackWhiteModel();
		notifyObserver(1);
	}
	void initBlackWhiteModel(){
		for(int i =0; i<BlackWhiteModel.length; i++){
			for(int j =0; j<BlackWhiteModel[i].length; j++){
					BlackWhiteModel[i][j]=0;
				}
		}
	}
	
	void notifyObserver(int nr){
		for(IObserver obs : obsList)
	       {
	           obs.updateView(nr);
	       }
	}
	
	public void addObserver(IObserver obs){
		obsList = new ArrayList<IObserver>();
		if (obs != null){
	    	        obsList.add(obs);
	    }
	}
	
	public void displayBWM(){
		for(int i =0; i<BlackWhiteModel.length; i++){
			for(int j =0; j<BlackWhiteModel[i].length; j++){
				System.out.print(BlackWhiteModel[i][j]);
				
			}
			System.out.println();
		}
		System.out.println(BlackWhiteModel.length);
	}
	public Tetraminos getActiveTetro() {
		return activeTetro;
	}
	
	public void setModelForTest(){
		for(int i = 0; i < BlackWhiteModel[i].length; i++){
			BlackWhiteModel[19][i]=2;
		}
	}
	
	
	
}
