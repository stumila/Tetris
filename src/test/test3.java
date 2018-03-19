package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controler.Controler;
import model.ModelBoard;
import view.GamePanel;
import view.InformationPanel;
import view.MainView;
public class test3 {

	private ModelBoard mv ;
	private MainView view ;
	private Controler controler;
	GamePanel GP;
	InformationPanel IP;
	
	 @Before
	  public void setUp() {
		 mv = new ModelBoard();
		 view = new MainView();
		controler = new Controler(view,mv);
			
		mv.addObserver(controler);
		view.addObserver(controler);
	    }
	@Test
	public void test(){
		mv.setModelForTest();
		mv.clearRows();
		int score = mv.getScore();
		assertFalse(score==0 );
	}
	
}