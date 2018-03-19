package controler;

import java.awt.event.KeyEvent;

public interface IObserver {
	public void updateModel(KeyEvent keyEvent) throws InterruptedException;
	public void updateView(int nr);
}
