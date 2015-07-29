package P2I1;

import javax.swing.JButton;

/**
 * Code from the book, This abstract class 
 * helps get rid of conditionals 
 */
@SuppressWarnings("serial")
public abstract class GUIButton extends JButton{

	
	/**
	 * Create button with the proper text
	 * @param string : button text
	 */
	public GUIButton(String string) {
		super(string);
	}
	
	/**
	 * To inform the listener that the button has been clicked
	 * @param display : the GUI
	 */
	public abstract void inform(RefrigeratorDisplay display);
		
	
}
