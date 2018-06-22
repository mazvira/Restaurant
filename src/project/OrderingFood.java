package project;

import java.awt.Checkbox;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class OrderingFood {
	JFrame frame;
	static JComboBox citiesNameList;
	// JComboBox restaurantNameList;
	static JComboBox menuNameList;
	static JCheckBox[] choicesOfmenu;
	JCheckBox[] box;
	static JCheckBox[] choicesOfdishes;
	static JTextField address;
	JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
	JLabel pricesAndCalority [];
	JLabel pricesAndCalority2 [];
	JPanel panel;
	static JButton submit_btn;
	static DatabaseConnectionAndQueries database;
	static Date dateOfDel;
	JComboBox[] quantities;
	JComboBox quantities2 [];
	int x, y, width, height;
	int lastY, lastX;
	Font f;
	String[] newDishes, newPicesAndCalority;
	static String selectedCity;

	public OrderingFood() throws SQLException {
		x = 30;
		y = 20;
		width = 200;
		height = 30;
		f = new Font("Arial", Font.ITALIC, 18);
		database = new DatabaseConnectionAndQueries();
		frame = new JFrame();
		frame.setVisible(true);
		// f.setLayout(new FlowLayout());
		// f.setBounds(200, 100, 700, 600);
		frame.setSize(1300, 1000);
		frame.setTitle("Замовлення їжі");
		// f.setBounds(500, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);

		text1 = new JLabel("Виберіть своє місто: ");
		text1.setFont(f);
		text1.setBounds(x, y, width, height);
		y += 40;
		panel.add(text1);

		initializeCitiesOfRestaurants();
		citiesNameList.setBounds(x, y, width, height);
		y += 40;
		citiesNameList.setFont(f);
		panel.add(citiesNameList);
		
		citiesNameList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedCity = (String) citiesNameList.getSelectedItem();
				
			}
		});

		text2 = new JLabel("Виберіть потрібні меню: ");
		text2.setFont(f);
		text2.setBounds(x, y, width + 30, height);
		y += 40;
		panel.add(text2);
		initializeMenusOfRestaurants();

		text3 = new JLabel("Виберіть страви для замовлення: ");
		text3.setBounds(x, y, width + 100, height);
		text3.setFont(f);
		y += 40;
		text4 = new JLabel("Назва страви                Калорійність        Ціна");
		text4.setFont(f);
		text4.setBounds(x, y, width + 200, height);
		text10 = new JLabel("Кількість");
		text10.setFont(f);
		text10.setBounds(450, y, width , height);	
		y += 40;
		lastY = y;
		lastX = x;
		
		panel.add(text3);
		panel.add(text4);
		panel.add(text10);
		initializeDishesOfRestaurants();

		text5 = new JLabel("Введіть адресу доставки: ");
		text5.setBounds(x, y, width + 40, height);
		y += 40;
		text5.setFont(f);
		panel.add(text5);

		address = new JTextField();
		address.setBounds(x, y, width + 60, height);
		address.setFont(f);
		panel.add(address);

		x = 600;
		y = 20;
		text6 = new JLabel("Виберіть дату доставки: ");
		text6.setBounds(x, y, width + 30, height);
		y += 40;
		text6.setFont(f);
		panel.add(text6);

		JXDatePicker picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
		picker.setBounds(x, y, width, height);
		y += 40;
		picker.setFont(f);
		panel.add(picker);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		picker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date date = picker.getDate();
				String str = formater.format(date);
				dateOfDel = Date.valueOf(str);
			}
		});

		text7 = new JLabel("Сума до сплати: ");
		text7.setBounds(x, y, width, height);
		text7.setFont(f);
		y += 40;
		panel.add(text7);
		text8 = new JLabel("Сума за доставку: ");
		text8.setBounds(x, y, width, height);
		text8.setFont(f);
		y += 40;
		panel.add(text8);
		text9 = new JLabel("Знижка: ");
		text9.setBounds(x, y, width, height);
		text9.setFont(f);
		y += 40;
		panel.add(text9);

		submit_btn = new JButton("Замовити");
		submit_btn.setBounds(x, y, width, height);
		submit_btn.setFont(f);
		panel.add(submit_btn);

		// Step 18 : Adding ActionListener on submit button
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					try {
						submit_action(event);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally {

				}
			}
		});

		int len = choicesOfmenu.length;
		for (int i = 0; i < len; ++i) {
			int k = i;
			choicesOfmenu[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedItem = choicesOfmenu[k].getText();
					int len = choicesOfdishes.length;
					for (int i = 0; i < len; ++i) {
						choicesOfdishes[i].setVisible(false);
						panel.remove(choicesOfdishes[i]);
						quantities[i].setVisible(false);
						panel.remove(quantities[i]);
						pricesAndCalority[i].setVisible(false);
						panel.remove(pricesAndCalority[i]);
						panel.updateUI();
					}

					try {
						newDishes = setDishesByTypeOfMenu(selectedItem);
						newPicesAndCalority = setDishesPriceAndCalority(selectedItem);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
					quantities2 = new JComboBox[newDishes.length];
					box = new JCheckBox[newDishes.length];
					pricesAndCalority2 = new JLabel[newDishes.length];
					for (int i = 0; i < newDishes.length; ++i) {
						box[i] = new JCheckBox(newDishes[i]);
						box[i].setBounds(lastX, lastY, width + 50, height);
						box[i].setFont(f);
						panel.add(box[i]);
						quantities2[i] = new JComboBox(numbers);
						quantities2[i].setFont(f);
						quantities2[i].setBounds(450 , lastY, 70, height);
						panel.add(quantities2[i]);
						pricesAndCalority2[i] = new JLabel(newPicesAndCalority[i]);
						pricesAndCalority2[i].setFont(f);
						pricesAndCalority2[i].setBounds(300 , lastY, width, height);
						panel.add(quantities2[i]);
						panel.add(pricesAndCalority2[i]);
						lastY += 40;
					}
					
					int quan = box.length;
					for(int i = 0; i<quan; ++i) {
						int k = i;
						box[i].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String selectedItem = box[k].getText();
								System.out.println(selectedItem);
								
							}
						});
					}
						
					y = lastY + 40;
				}
			});

		}
				
		frame.add(panel);

	}

	public void initializeCitiesOfRestaurants() throws SQLException {
		String[] citiesNames = database.getNameOfCitiesOfRestaurants();
		citiesNameList = new JComboBox(citiesNames);
	}

	public void initializeMenusOfRestaurants() throws SQLException {
		String[] menuNames = database.getNameOfMenus();
		int i = 0;
		int len = menuNames.length;
		choicesOfmenu = new JCheckBox[len];
	
		while (i < len) {
			choicesOfmenu[i] = new JCheckBox(menuNames[i]);
			choicesOfmenu[i].setFont(f);
			choicesOfmenu[i].setBounds(x, y, width, height);
			panel.add(choicesOfmenu[i]);
			
			panel.add(choicesOfmenu[i]);
		
			i++;
			y = y + 40;
		}
	}

	public String[] setDishesByTypeOfMenu(String menu) throws SQLException {
		String[] dishesNames = database.getNameOfDishesBySpecificMenu(menu);
		return dishesNames;
	}
	
	public String[] setDishesPriceAndCalority(String menu) throws SQLException {
		String[] dishesCalorityAndPrice = database.getDishesCalorityAndPrice(menu);
		return dishesCalorityAndPrice;

	}


	public void initializeDishesOfRestaurants() throws SQLException {
		String[] dishesNames = database.getNameOfDishes();
		String[] pricesAndCalorities = database.getDishesCalorityAndPrice();
		String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
		int i = 0;
		int len = dishesNames.length;
		choicesOfdishes = new JCheckBox[len];
		pricesAndCalority = new JLabel[len];
		quantities = new JComboBox[len];
		while (i < len) {
			choicesOfdishes[i] = new JCheckBox(dishesNames[i]);
			choicesOfdishes[i].setFont(f);
			choicesOfdishes[i].setBounds(x, y, width + 50, height);
			pricesAndCalority[i] = new JLabel(pricesAndCalorities[i]);
			pricesAndCalority[i].setBounds(300, y, width, height);
			pricesAndCalority[i].setFont(f);
			quantities[i] = new JComboBox(numbers);
			quantities[i].setFont(f);
			quantities[i].setBounds(450 , y, 70, height);
			panel.add(choicesOfdishes[i]);
			panel.add(quantities[i]);
			panel.add(pricesAndCalority[i]);
			i++;
			y = y + 40;
		}
	}
	
	public void countSumODeliv() {
		
	}
	
	public static void submit_action(ActionEvent event) throws SQLException {

		database.insertIntoDelivery(dateOfDel, address.getText(), selectedCity, 400, 0);
		JOptionPane.showMessageDialog(null, "Ваше замовлення зареєстроване!");
		// insertIntoClients(name_txt.getText(), surname_txt.getText(),
		// fathers_txt.getText(),
		// email_txt.getText(), date);

	}

}
