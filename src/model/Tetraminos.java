package model;

import java.awt.Point;

public class Tetraminos {

	int colorNumber=0;
	int rotationCordinates;
	Point orientationPoint;
	Point[] all;
	
	public Tetraminos(Point block1,Point block2,Point block3,Point block4,int  cord){
		//zamiana int na string i ten string zmienia sie na rotatotionCordnates
		all = new Point[4];
		all[0] = block1;
		all[1] = block2;
		all[2] = block3;
		all[3] = block4;	
		rotationCordinates=cord;
		
	}
	public void rotate(){
		//Clockwise rotation
		if(rotationCordinates!=-1){
			for(int i = 1 ; i < 4; i ++){
				int tempx=all[i].x;
				all[i].x= all[i].y + all[0].x - all[0].y; 
				all[i].y= all[0].x + all[0].y-tempx;
			}
		}
		
	}
	
	public void moveDown(){
		for(int i = 0 ; i < 4; i ++){
			all[i].y++;
		}
	}
	
	public void moveHorizontal(int direction){
		for(int i = 0 ; i < 4; i ++){
			all[i].x+=direction;
		}
	}
	
	public Point[] getTetro(){
		return all;
	}
	
	public void setColor(int color){
		this.colorNumber=color;
	}
	public int getColor(){
		return this.colorNumber;
	}
	
	
}
