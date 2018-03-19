package start;

import controler.Controler;
import model.ModelBoard;
import view.MainView;

public class Game {

	public static void main(String[] args) {
		ModelBoard model = new ModelBoard();
		MainView view = new MainView();
		Controler controler = new Controler(view,model);
		
		model.addObserver(controler);
		view.addObserver(controler);
		model.init();
		

	}

}
