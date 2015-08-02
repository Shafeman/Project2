/**
 * Barbarians: Douglas Brian Shaffer, Jonathan Franco
 * The GUIDisplay (revised to remove conditionals) controls the display 
 * of the refrigerator. It extends RefrigeratorDispaly
 * and implements ActionListener.
 */

package P2I1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUIDisplay extends RefrigeratorDisplay implements ActionListener {
	
	/**
	 * Settings to be used when calling properties from settings file
	 * @author Chester Copperpot
	 *
	 */
	public final class Settings {
		public static final String FRIDGE_LOW = "FridgeLow";
		public static final String FRIDGE_HIGH = "FridgeHigh";
		public static final String ROOM_LOW = "RoomLow";
		public static final String ROOM_HIGH = "RoomHigh";
		public static final String FRIDGE_RATE_LOSS_DOOR_CLOSED = "FridgeRateLossDoorClosed";
		public static final String FRIDGE_RATE_LOSS_DOOR_OPEN = "FridgeRateLossDoorOpen";
		public static final String FRIDGE_COMPRESSOR_START_DIFF = "FridgeCompressorStartDiff";
		public static final String FRIDGE_COOL_RATE = "FridgeCoolRate";
		
		private Settings() {
		}		
	}
	private static SimpleDisplay frame;
	private RefrigeratorDisplay display;
	private static Properties defaults;

	/**
	 * Creates the frame and displays it.
	 */
	private GUIDisplay() {
		frame = new SimpleDisplay();
		initialize();
	}
	
	/**
	 * Inner class because the outer class extends MicrowaveDisplay.
	 *
	 */
	private class SimpleDisplay extends JFrame {
		private JLabel lblRoomTemp = new JLabel("Room Temp");
		private JLabel lblDesFridgeTemp = new JLabel("Desired Fridge Temp");		
		private JLabel lblStatus = new JLabel("Status");		
		private JTextField txtRoomTemp = new JTextField(40);
		private JTextField txtDesFridgeTemp = new JTextField(40);		
		private JLabel lblFridgeLight = new JLabel("Fridge light <off>");		
		private JLabel lblFridgeTemp = new JLabel("Fridge temp <>");		
		private JLabel lblFridgeCompStatus = new JLabel("Fridge <cooling>");
		private final JPanel pnlControlButtons = new JPanel();
		private final JPanel pnlReadOuts = new JPanel();
		private final JPanel pnlButtonGridFormat = new JPanel();
		private final JPanel pnlStatus = new JPanel();
		private final JPanel pnlDesiredFields = new JPanel();
		
		private GUIButton btnSetRoomTemp = new SetRoomTempButton("Set Room Temp");
		private GUIButton btnSetDesFridgeTemp = new SetDesiredFridgeTempButton("Set Desired Fridge Temp");
		private GUIButton btnOpenFridge = new DoorOpenButton("Open Door");
		private GUIButton btnCloseFridge = new DoorCloseButton("Close Door");		

		/**
		 * Constructor. Creates the Display.
		 */
		private SimpleDisplay() {
			super("Refrigerator");
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					System.exit(0);
				}
			});
			
			setPreferredSize(new Dimension(640, 290));
			getContentPane().setLayout(new BorderLayout(15, 15));	
			
			pnlDesiredFields.setBorder(new EmptyBorder(0, 5, 0, 0));
			getContentPane().add(pnlDesiredFields, BorderLayout.NORTH);
			pnlDesiredFields.setLayout(new GridLayout(2, 2, 5, 5));
			pnlDesiredFields.add(lblRoomTemp);
			pnlDesiredFields.add(txtRoomTemp);
			pnlDesiredFields.add(btnSetRoomTemp);
			pnlDesiredFields.add(lblDesFridgeTemp);
			pnlDesiredFields.add(txtDesFridgeTemp);
			pnlDesiredFields.add(btnSetDesFridgeTemp);
			
			getContentPane().add(pnlControlButtons, BorderLayout.WEST);
			pnlControlButtons.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));		
			pnlControlButtons.add(pnlButtonGridFormat);
			
			pnlButtonGridFormat.setSize(new Dimension(640, 160));
			pnlButtonGridFormat.setLayout(new GridLayout(2, 2, 0, 0));
			pnlButtonGridFormat.add(btnOpenFridge);
			pnlButtonGridFormat.add(btnCloseFridge);
			
			getContentPane().add(pnlReadOuts, BorderLayout.SOUTH);
			pnlReadOuts.setBorder(new EmptyBorder(0, 5, 0, 0));
			pnlReadOuts.setLayout(new BorderLayout(0, 0));
			pnlReadOuts.add(lblStatus, BorderLayout.NORTH);
			pnlReadOuts.add(pnlStatus, BorderLayout.WEST);
			
			pnlStatus.setBorder(new EmptyBorder(0, 20, 0, 0));
			pnlStatus.setLayout(new GridLayout(3, 2, 15, 0));
			lblFridgeLight.setBorder(new EmptyBorder(2, 2, 2, 2));
			pnlStatus.add(lblFridgeLight);
			lblFridgeLight.setBorder(new EmptyBorder(2, 2, 2, 2));
			pnlStatus.add(lblFridgeTemp);
			lblFridgeTemp.setBorder(new EmptyBorder(2, 2, 2, 2));
			pnlStatus.add(lblFridgeCompStatus);
			lblFridgeCompStatus.setBorder(new EmptyBorder(2, 2, 2, 2));
			
			btnSetRoomTemp.addActionListener(GUIDisplay.this);
			btnSetDesFridgeTemp.addActionListener(GUIDisplay.this);
			btnOpenFridge.addActionListener(GUIDisplay.this);
			btnCloseFridge.addActionListener(GUIDisplay.this);
			
			int roomTemperature = Integer.parseInt(defaults.getProperty("RoomHigh"));
			txtRoomTemp.setText("" + roomTemperature);
			int desiredFridgeTemperature = Integer.parseInt(defaults.getProperty("FridgeHigh"));
			txtDesFridgeTemp.setText("" + desiredFridgeTemperature);
			int fridgeTemperature = roomTemperature;
			lblFridgeTemp.setText("Fridge temp <" + fridgeTemperature +">");
			
			pack();
			setVisible(true);
		}
		
		/**
		 * Gets the current room temperature
		 * @return
		 */
		public int getRoomTemperature(){
			int temp = Integer.parseInt(txtRoomTemp.getText());
			return temp;
		}

		/**
		 * gets the current desired fridge temperature
		 * @return
		 */
		public int getFridgeTemperatureSetting() {
			int temp = Integer.parseInt(txtDesFridgeTemp.getText());
			return temp;
		}

		/**
		 * sets the current room temperature
		 * @param value
		 */
		public void setRoomTemperature(String value) {
			txtRoomTemp.setText(value);			
		}

		/**
		 * sets the desired fridge temperature
		 * @param value
		 */
		public void setDesiredFridgeTemperature(String value) {
			txtDesFridgeTemp.setText(value);			
		}
	}

	/**
	 * Sets the text for the lblFridgeLight to show the
	 * light is on.
	 */
	@Override
	public void turnFridgeLightOn() {
		frame.lblFridgeLight.setText("Fridge light <on>");
	}

	/**
	 * Sets the text for the lblFridgeLight to show the
	 * light is off.
	 */
	@Override
	public void turnFridgeLightOff() {
		frame.lblFridgeLight.setText("Fridge light <off>");
	}

	/**
	 * Sets the text for the lblFridgeCompStatus to show the
	 * fridge is cooling.
	 */
	@Override
	public void fridgeCooling() {
		frame.lblFridgeCompStatus.setText("Fridge <cooling>");
	}

	/**
	 * Sets the text for the lblFridgeCompStatus to show the
	 * fridge is idle.
	 */
	@Override
	public void fridgeIdle() {
		frame.lblFridgeCompStatus.setText("Fridge <idle>");		
	}

	/**
	 * updates the fridge temp with the correct value
	 */
	@Override
	public void updateFridgeTemp(int temp) {
		frame.lblFridgeTemp.setText("Fridge temp <" + temp +">");
	}
	
	/**
	 * gets the room temperature from the GUI
	 * @return
	 */
	@Override
	public int getRoomTemperatureSetting(){
		return frame.getRoomTemperature();
	}
	
	/**
	 * gets the desired room temperature from the GUI
	 * @return
	 */
	@Override
	public int getDesiredFridgeTemperature(){
		return frame.getFridgeTemperatureSetting();
	}
	
	/**
	 * resets the room temperature text box
	 * @param value
	 */
	@Override
	public void resetRoomTemperatureDisplay(int value){
		frame.setRoomTemperature("" + value);
	}
	
	/**
	 * Updates the desired fridge temperature text box
	 * @param value
	 */
	@Override
	public void resetDesiredFridgeTemperature(int value){
		frame.setDesiredFridgeTemperature("" + value);
	}

	/**
	 * No more conditionals, Let the clicked button do the work
	 */
	@Override
	public void actionPerformed(ActionEvent event) {		
		((GUIButton) event.getSource()).inform(this);		
	}
	
	/**
	 * returns the int value of the property requested.
	 * @param property
	 * @return
	 */
	public int getProperty(String property){
		int value = Integer.parseInt(defaults.getProperty(property));
		return value;
	}
	
	/**
	 * Start the whole show
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1){
			System.err.println("Please only enter one argument - the full name with the default properties");
		} else {
			String fileName = args[0];
			defaults = new Properties();
			FileReader file;
			try {
				file = new FileReader(fileName);
				BufferedReader in = new BufferedReader(file);
				defaults.load(in);
				file.close();
				in.close();
				RefrigeratorDisplay display = new GUIDisplay();
			} catch (IOException e) {
				System.err.println(fileName + " not found in system.");
			}			
		}
	}
}
