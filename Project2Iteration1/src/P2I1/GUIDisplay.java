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
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;

public class GUIDisplay extends JFrame implements ActionListener, RefrigeratorDisplay {
	
	private RefrigeratorMachine refrigerator;
	private JLabel lblRoomTemp = new JLabel("Room Temp");
	private JLabel lblDesFridgeTemp = new JLabel("Desired Fridge Temp");
	private JLabel lblDesFreezerTemp = new JLabel("Desired Freezer Temp");
	private JLabel lblStatus = new JLabel("Status");
	private JButton btnSetRoomTemp = new JButton("Set Room Temp");
	private JButton btnSetDesFridgeTemp = new JButton("Set Desired Fridge Temp");
	private JButton btnSetDesFreezerTemp = new JButton("set Desired Freezer Temp");
	private JButton btnOpenFridge = new JButton("Open Fridge Door");
	private JButton btnCloseFridge = new JButton("Close Fridge Door");
	private JButton btnOpenFreezer = new JButton("Open Freezer Door");
	private JButton btnCloseFreezer = new JButton("Close Freezer Door");
	private JTextField txtRoomTemp = new JTextField(40);
	private JTextField txtDesFridgeTemp = new JTextField(40);
	private JTextField txtDesFreezerTemp = new JTextField(40);
	private JLabel lblFridgeLight = new JLabel("Fridge light <off>");
	private JLabel lblFreezerLight = new JLabel("Freezer Light <off>");
	private JLabel lblFridgeTemp = new JLabel("Fridge temp <39>");
	private JLabel lblFreezerTemp = new JLabel("Freezer temp <-4>");
	private JLabel lblFridgeCompStatus = new JLabel("Fridge <idle>");
	private JLabel lblFreezerCompStatus = new JLabel("Freezer <idle>");
	private final JPanel pnlControlButtons = new JPanel();
	private final JPanel pnlReadOuts = new JPanel();
	private final JPanel pnlButtonGridFormat = new JPanel();
	private final JPanel pnlStatus = new JPanel();
	private final JPanel pnlDesiredFields = new JPanel();
			
	public GUIDisplay() {
		setTitle("Refrigerator");
		setPreferredSize(new Dimension(640, 290));
		getContentPane().setLayout(new BorderLayout(15, 15));	
		
		pnlDesiredFields.setBorder(new EmptyBorder(0, 5, 0, 0));
		getContentPane().add(pnlDesiredFields, BorderLayout.NORTH);
		pnlDesiredFields.setLayout(new GridLayout(3, 3, 5, 5));
		pnlDesiredFields.add(lblRoomTemp);
		pnlDesiredFields.add(txtRoomTemp);
		pnlDesiredFields.add(btnSetRoomTemp);
		pnlDesiredFields.add(lblDesFridgeTemp);
		pnlDesiredFields.add(txtDesFridgeTemp);
		pnlDesiredFields.add(btnSetDesFridgeTemp);
		pnlDesiredFields.add(lblDesFreezerTemp);		
		pnlDesiredFields.add(txtDesFreezerTemp);		
		pnlDesiredFields.add(btnSetDesFreezerTemp);
		
		getContentPane().add(pnlControlButtons, BorderLayout.WEST);
		pnlControlButtons.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));		
		pnlControlButtons.add(pnlButtonGridFormat);
		
		pnlButtonGridFormat.setSize(new Dimension(640, 160));
		pnlButtonGridFormat.setLayout(new GridLayout(2, 2, 0, 0));
		pnlButtonGridFormat.add(btnOpenFridge);
		pnlButtonGridFormat.add(btnCloseFridge);
		pnlButtonGridFormat.add(btnOpenFreezer);
		pnlButtonGridFormat.add(btnCloseFreezer);
		
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
		pnlStatus.add(lblFreezerLight);
		lblFreezerLight.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlStatus.add(lblFridgeTemp);
		lblFridgeTemp.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlStatus.add(lblFreezerTemp);
		lblFreezerTemp.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlStatus.add(lblFridgeCompStatus);
		lblFridgeCompStatus.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlStatus.add(lblFreezerCompStatus);
		lblFreezerCompStatus.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		GUIDisplay display = new GUIDisplay();
		
	}

	@Override
	public void turnFridgeLightOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnFridgeLightOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnFreezerLightOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnFreezerLightOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void freezerDoorClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void freezerDoorOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fridgeDoorClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fridgeDoorOpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRefrigerator(RefrigeratorMachine refrigerator) {
		// TODO Auto-generated method stub
		
	}

}
