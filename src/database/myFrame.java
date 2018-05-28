package database;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class myFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4855748719992368689L;
	//private static JFrame frame;
	JComboBox petList;
	JLabel label;
	public myFrame()
	{
		//frame = new JFrame();
		setLayout(new FlowLayout());
		setSize(700, 600);
		setTitle("Booking");
		//frame.setBounds(500, 100, 450, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		/*String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		petList = new JComboBox(petStrings);
		petList.setSelectedIndex(4);
		petList.setBounds(90, 85, 245, 31);*/
		label = new JLabel();
		//add(petList);
		add(label);
		//petList.addActionListener(this);
	}
	
}
