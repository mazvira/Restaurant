package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // importing event package for event listener
import java.sql.Date;
import java.sql.SQLException;

class RegForm {
	// Creating Static variables
	static JTextField name_txt;
	static JTextField surname_txt;
	static JTextField fathers_txt;
	static JRadioButton male;
	static JRadioButton female;
	static JComboBox day;
	static JComboBox month;
	static JComboBox year;
	static JTextArea add_txtArea;
	static JTextField phone_txt;
	static JTextField email_txt;
	static JPasswordField password_txt;
	static JCheckBox chkbox;
	static JButton submit_btn;
	static JTextArea output_txtArea;
	static JLabel login;
	static JTextField logintext;
	static JFrame frame;
	
	static DatabaseConnectionAndQueries database;

	public RegForm() {
		database = new DatabaseConnectionAndQueries();
		// Step 1 : Creating a frame using JFrame class
		frame = new JFrame("Реєстраційна Форма");
		frame.setVisible(true);
		frame.setSize(1300, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Step 2 : setting background color of Frame.
		Container c = frame.getContentPane();
		c.setLayout(null);
		// c.setBackground(Color.blue);

		/*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */

		Font f = new Font("Arial", Font.BOLD, 20); // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
		JLabel heading_lbl = new JLabel();
		heading_lbl.setBounds(250, 5, 250, 40);
		heading_lbl.setText("<html><font><u><b>Реєстраційна форма</b></u></html>");

		// applying font on heading Label
		heading_lbl.setFont(f);

		/*
		 * ----------------------------------- Creating Global Font style for all
		 * components ------------------------------
		 */

		Font f1 = new Font("Arial", Font.ITALIC, 18);

		/*
		 * ----------------------------------- Creating components for Registration
		 * details ----------------------------------
		 */

		// Step 4 : Creating JLabel for Name
		JLabel name_lbl = new JLabel("Ім'я : ");
		name_lbl.setBounds(50, 80, 100, 30);

		// Creating JTextField for Name
		name_txt = new JTextField();
		name_txt.setBounds(180, 80, 180, 30);

		// Step 5 : Creating JLabel for surname
		JLabel surname_lbl = new JLabel("Прізвище : ");
		surname_lbl.setBounds(50, 120, 150, 30);

		// Creating JTextField for surname
		surname_txt = new JTextField();
		surname_txt.setBounds(180, 120, 180, 30);

		// Step 5 : Creating JLabel for father's name
		JLabel fathers_lbl = new JLabel("По-батькові : ");
		fathers_lbl.setBounds(50, 170, 150, 30);

		// Creating JTextField for Father's name
		fathers_txt = new JTextField();
		fathers_txt.setBounds(180, 170, 180, 30);

		JLabel login = new JLabel("Логін : ");
		login.setBounds(50, 220, 150, 30);

		// Creating JTextField for Father's name
		logintext = new JTextField();
		logintext.setBounds(180, 220, 180, 30);

		// Step 6 : Creating JLabel for Gender
		JLabel gender_lbl = new JLabel("Стать : ");
		gender_lbl.setBounds(50, 260, 150, 30);

		// Setting Cursor for components
		Cursor cur = new Cursor(Cursor.HAND_CURSOR);

		// Creating JRadioButton for the Male
		male = new JRadioButton("Ч");
		male.setBounds(180, 260, 70, 30);
		male.setBackground(Color.yellow);
		male.setCursor(cur);

		// Creating JRadioButton for the Female
		female = new JRadioButton("Ж");
		female.setBounds(280, 260, 80, 30);
		female.setBackground(Color.yellow);
		female.setCursor(cur);

		// Creating ButtonGroup for the JRadioButtons
		ButtonGroup gender_grp = new ButtonGroup();
		gender_grp.add(male); // adding male radio button in the ButtonGroup
		gender_grp.add(female); // adding female radio button in the ButtonGroup

		// Step 7 : Creating JLabel for Date of Birth
		JLabel dob_lbl = new JLabel("Дата Нар : ");
		dob_lbl.setBounds(50, 310, 100, 30);

		// Creating JComboBox for the day
		String day_arr[] = new String[31];
		for (int i = 1; i <= 31; i++)
			day_arr[i - 1] = Integer.toString(i);
		day = new JComboBox(day_arr);
		day.setBounds(180, 310, 40, 30);

		// Creating JComboBox for the month
		String month_arr[] = { "Січ", "Лют", "Бер", "Квіт", "Трав", "Черв", "Лип", "Серп", "Вер", "Жовт", "Лист",
				"Груд" };
		month = new JComboBox(month_arr);
		month.setBounds(230, 310, 60, 30);

		 String year_arr[] = new String[70];
		 for (int i = 1951; i <= 2020; i++)
		   year_arr[i - 1951] = Integer.toString(i);
		 year = new JComboBox(year_arr);
		 year.setBounds(300, 310, 60, 30);

		// Step 8 : Creating JLabel for the Address
		JLabel add_lbl = new JLabel("Адреса : ");
		add_lbl.setBounds(50, 340, 100, 30);

		// Creating JTextArea for the address
		add_txtArea = new JTextArea();
		add_txtArea.setBounds(180, 340, 180, 100);

		// Step 9 : Creating JLabel for the phone
		JLabel phone_lbl = new JLabel("Телефон : ");
		phone_lbl.setBounds(50, 450, 100, 30);

		// Creating JTextField for the phone
		phone_txt = new JTextField();
		phone_txt.setBounds(180, 450, 180, 30);

		// Step 10 : Creating JLabel for the Email
		JLabel email_lbl = new JLabel("Email : ");
		email_lbl.setBounds(50, 490, 100, 30);
		// Creating JTextField for the Email
		email_txt = new JTextField();
		email_txt.setBounds(180, 490, 180, 30);

		JLabel password_lbl = new JLabel("Пароль : ");
		password_lbl.setBounds(50, 540, 100, 30);
		// Creating JTextField for the Email
		password_txt = new JPasswordField();
		password_txt.setBounds(180, 540, 180, 30);

		// Step 11 : Creating JCheckBox for the license agreement
		chkbox = new JCheckBox("Я приймаю умови користування");
		chkbox.setBounds(50, 590, 300, 30);
		chkbox.setBackground(Color.yellow);

		// Step 12 : Creating JButton for submit the details
		submit_btn = new JButton("Відправити");
		submit_btn.setBounds(180, 650, 160, 40);
		submit_btn.setCursor(cur); // Applying hand cursor on the button

		// Step 18 : Adding ActionListener on submit button
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					submit_action(event);
				} finally {

				}
			}
		});

		// Step 17 : Creating JTextArea for output
		output_txtArea = new JTextArea();
		output_txtArea.setBounds(380, 80, 260, 320);

		// Step 13 : Applying Global Font on all the JLabels
		name_lbl.setFont(f1);
		surname_lbl.setFont(f1);
		fathers_lbl.setFont(f1);
		gender_lbl.setFont(f1);
		dob_lbl.setFont(f1);
		add_lbl.setFont(f1);
		phone_lbl.setFont(f1);
		email_lbl.setFont(f1);
		password_lbl.setFont(f1);
		login.setFont(f1);

		// Step 14 : Applying Font on all JTextFields, JRadioButtons, JComboBox and
		// JTextArea
		name_txt.setFont(f1);
		surname_txt.setFont(f1);
		fathers_txt.setFont(f1);
		male.setFont(f1);
		female.setFont(f1);
		add_txtArea.setFont(f1);
		phone_txt.setFont(f1);
		email_txt.setFont(f1);
		password_txt.setFont(f1);
		chkbox.setFont(f1);
		submit_btn.setFont(f1);
		output_txtArea.setFont(f1);
		logintext.setFont(f1);

		// Step 15 : Adding label components to the container
		c.add(heading_lbl);
		c.add(name_lbl);
		c.add(surname_lbl);
		c.add(fathers_lbl);
		c.add(gender_lbl);
		c.add(male);
		c.add(female);
		c.add(dob_lbl);
		c.add(add_lbl);
		c.add(phone_lbl);
		c.add(email_lbl);
		c.add(password_lbl);
		c.add(login);

		// Step 16 : Adding JTextField, JTextArea, JComboBox, JCheckBox, JRadioButton to
		// the container
		c.add(name_txt);
		c.add(surname_txt);
		c.add(fathers_txt);
		c.add(day);
		c.add(month);
		c.add(year);
		c.add(add_txtArea);
		c.add(phone_txt);
		c.add(email_txt);
		c.add(password_txt);
		c.add(chkbox);
		c.add(submit_btn);
		c.add(output_txtArea);
		c.add(logintext);
	}

	// Step 19 : Reading value from the Registration Form
	public static void submit_action(ActionEvent event) {
		if (chkbox.isSelected() == true) {
			Date date = new Date(year.getSelectedIndex(), month.getSelectedIndex(), day.getSelectedIndex());
			try {
				database.insertIntoClients(name_txt.getText(), surname_txt.getText(), fathers_txt.getText(),
						email_txt.getText(), date, password_txt.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reservation_Ordering_for_Client form = new Reservation_Ordering_for_Client();
			frame.setVisible(false);

			// String name = name_txt.getText();
			// String fname = fname_txt.getText();
			/*
			 * String gender = "Male"; if (female.isSelected() == true) gender = "Female";
			 */
			// String day_name = (String) day.getSelectedItem();
			// String month_name = (String) month.getSelectedItem();
			// String year_name = (String) year.getSelectedItem();
			// String add = add_txtArea.getText();
			// String phone = phone_txt.getText();
			// String email = email_txt.getText();

			// displaying value in the JTextArea
			/*
			 * output_txtArea.setText(" Name :   " + name + "\n Father's Name :  " + fname +
			 * "\n Gender :   " + gender + "\n Date of Birth :   " + day_name + "  " +
			 * month_name + " " + year_name + "\n Address :  " + add + " \n Phone no :  " +
			 * phone + "\n Email :  " + email + "\n ");
			 */

		} else {
			output_txtArea.setText("Please accept the terms and condition");
		}
		
	}
}
