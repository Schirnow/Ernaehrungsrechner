// ToDo: Zeile 250, Berechnung fergig schreiben!
// Zeile 296: fillChoisedNutritions fertig schreiben!

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class CalculationApp extends JFrame /*implements ActionListener /*MouseListener*/{

	private ArrayList<Profile> profiles = new ArrayList<>();
	private ArrayList<Nutrition> nutritions = new ArrayList<>();
	private ArrayList<Nutrition> choisedNutritions = new ArrayList<>();
	private Profile activeProfile;

	public CalculationApp(int weight){
		//Menu
		var menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		var datei = new JMenu("Datei");
		menuBar.add(datei);
		var calcNew = new JMenuItem("Neue Berechnung...");
		datei.add(calcNew);
		
		var administration = new JMenu("Administration");
		menuBar.add(administration);
		var profileAdm = new JMenuItem("Profile verwalten");
		var nutritionAdm = new JMenuItem("Ernaehrung verwalten");
		administration.add(profileAdm);
		administration.add(nutritionAdm);
		
		// Window
		var panel = new JPanel(new GridBagLayout());
		var c = new GridBagConstraints();
		add(panel);
		
		var profileL = new JLabel("Ausgewählter Profil:");
		//c.ipady = 3;
		c.gridx = 0;
		c.gridy = 0;
		//c.ipady = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(profileL,c);
		
		// Zu ersetzen, wenn Profile Datei schreiben und lesen kann
		profiles.add(new Profile("V1", 24, 24,10,300,2000,4000));
		profiles.add(new Profile("Intensiv", 30, 20,10,300,2000,4000));
		System.out.println("Folgende Profile erstellt:\n" + profiles.get(0) + '\n' + profiles.get(1));
		nutritions.add(new Nutrition ("--", "--", 0, 0, ""));
		nutritions.add(new Nutrition ("Fresenius", "Fresubin Energy", 1.5, 500, "oral"));
		nutritions.add(new Nutrition ("Fresenius", "Fresubin Energy Fibre", 1.5, 500, "oral"));
		nutritions.add(new Nutrition ("Fresenius", "Fresubin Renal", 2.0, 500, "oral"));
		nutritions.add(new Nutrition ("Abbot", "Olimel", 1.08, 1500, "iv"));
		System.out.println("Folgende Ernaehreungen erstellt:\n" + nutritions);
		

		String[] profileChoise = new String[profiles.size()+1];
		profileChoise[0] = "--";
		for (Profile e: profiles) {profileChoise[profiles.indexOf(e)+1] = e.getName();}
		
		var profileCB = new JComboBox(profileChoise);
		c.gridx = 1;
		c.gridy = 0;
		//c.ipady = 3;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(profileCB, c);
		
		var sep = new JSeparator();
		//sep.setPreferredSize(new Dimension(1,3));
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		//c.ipady = 10;
		//c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;

		panel.add(new JSeparator(),c);
		
		// Auswahl was berechnet wird
		var calculationChoiseL = new JLabel("Berechne:");
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 0;
		panel.add(calculationChoiseL, c);
		
		String[] nutritionArt = {"--", "enterale Ernährung", "parenterale Ernährung", "zusätzliche parenterale Ernährung"};
		var calculationChoiseCB = new JComboBox(nutritionArt);
		calculationChoiseCB.setEnabled(false);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(calculationChoiseCB, c);
		
		// Auswahl womit berechnet wird (Gwicht oder Kalloriebedarf)
		var calculationWithL = new JLabel("Berechne mit:");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(calculationWithL, c);
		
		String[] kgKcall = {"--", "Körpergewicht", "Kalloriebedarf"};
		var calculationWithCB = new JComboBox(kgKcall);
		calculationWithCB.setEnabled(false);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(calculationWithCB, c);
		
		String kgKcallEntry = "Gewicht:";
		var kgKcallEntryL = new JLabel(kgKcallEntry);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(kgKcallEntryL, c);
		
		String bodyWeight = (weight == 0) ? "0" : "" + weight;
		var kgBodyWeightEntry = new JTextField(bodyWeight);
		kgBodyWeightEntry.setEnabled(false);
		kgBodyWeightEntry.addMouseListener(new MouseAdapter(){
			@Override public void mouseClicked(MouseEvent e){
				kgBodyWeightEntry.setText("");
			}
		});
		c.gridx = 1;
		c.gridy = 4;
		panel.add(kgBodyWeightEntry, c);
		
		// Auswahl der Ernaehrung, dessen Laufrate berechnet werden soll
		var nutritionChoiseL = new JLabel("Ernaehrung:");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(nutritionChoiseL, c);
		
		/*String[] nutritionChoisesOral = new String[nutritions.size() + 1];
		nutritionChoises[0] = "--";
		for (Nutrition e: nutritions){nutritionChoises[nutritions.indexOf(e) + 1] = e.getName() + " " + e.getKcallMl() + " kcall/ml";}*/
		
		ArrayList<String> nutritionChoice = new ArrayList<>();
		nutritionChoice.add("--");
		
		JComboBox nutritionChoiseCB = new JComboBox(nutritionChoice.toArray());
		nutritionChoiseCB.setEnabled(false);
		c.gridx = 1;
		c.gridy = 5;
		panel.add(nutritionChoiseCB, c);
		
		var calculate = new JButton("Berechne");
		c.gridx = 2;
		c.gridy = 10;
		panel.add(calculate, c);
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// Eventhandlers:
		// Handler fuer Profil
		var setProfile = new ActionListener(){
			@Override public void actionPerformed(ActionEvent event){
				var combobox = (JComboBox)event.getSource();
				if (combobox.getSelectedIndex() != 0){
					activeProfile = profiles.get(combobox.getSelectedIndex() - 1);
					System.out.println(activeProfile);
					calculationChoiseCB.setEnabled(true);
				} else {
					calculationChoiseCB.setSelectedIndex(0);
					calculationChoiseCB.setEnabled(false);
					calculationWithCB.setSelectedIndex(0);
					calculationWithCB.setEnabled(false);
					kgBodyWeightEntry.setText("0");
					kgBodyWeightEntry.setEnabled(false);
					nutritionChoiseCB.setSelectedIndex(0);
					nutritionChoiseCB.setEnabled(false);
				}
			}
		};
		// Handler fuer Art der Ernaehrung
		var calcChoise = new ActionListener(){
			@Override public void actionPerformed(ActionEvent event){
				var combobox = (JComboBox)event.getSource();
				if (combobox.getSelectedIndex() != 0) {
					calculationWithCB.setEnabled(true);
					String application = (combobox.getSelectedIndex() == 1) ? "oral" : "iv";
					if (nutritionChoice.size() > 1) {
						nutritionChoice.clear();
						nutritionChoice.add("--");
					}
					fillChoisedNutrition(application);
					for (Nutrition e: choisedNutritions) {
						nutritionChoice.add(e.getName() + " " + e.getKcallMl() + " kcall/ml");
					}
					nutritionChoiseCB.removeAllItems();
					for (String s: nutritionChoice) nutritionChoiseCB.addItem(s);
				} else {
					calculationWithCB.setSelectedIndex(0);
					calculationWithCB.setEnabled(false);
					kgBodyWeightEntry.setText("0");
					kgBodyWeightEntry.setEnabled(false);
					nutritionChoiseCB.setSelectedIndex(0);
					nutritionChoiseCB.setEnabled(false);
				}
			}
		};
		// Handler fuer Gewicht / Kaloriemetrie
		var calcWithChoise = new ActionListener(){
			@Override public void actionPerformed(ActionEvent event){
				var combobox = (JComboBox)event.getSource();
				if (combobox.getSelectedIndex() != 0) {
					kgKcallEntryL.setText(combobox.getSelectedItem().toString() + ":");
					kgBodyWeightEntry.setEnabled(true);
					nutritionChoiseCB.setEnabled(true);
				} else {
						kgBodyWeightEntry.setText("0");
						kgBodyWeightEntry.setEnabled(false);
						nutritionChoiseCB.setSelectedIndex(0);
						nutritionChoiseCB.setEnabled(false);
					}
			}
		};
		// Handler fuer Gewicht- / Kaloriemetrieeingabe
		var checkEntry = new ActionListener(){
			@Override public void actionPerformed(ActionEvent event){
				String userEntry = kgBodyWeightEntry.getText();
				if (calculationWithCB.getSelectedIndex() == 1){
					if (!validateText(userEntry,1)) kgBodyWeightEntry.setText("0");
				} else{
					if (!validateText(userEntry,2)) kgBodyWeightEntry.setText("0");
				}
			}
		};
		// Handler fuer Schaltflaeche "Berechnen"
		var calculateResult = new ActionListener(){
			@Override public void actionPerformed(ActionEvent event){
				boolean valideEntry = false;
				if (calculationWithCB.getSelectedIndex() == 1){
					valideEntry = validateText(kgBodyWeightEntry.getText(),1);
				} else{
					valideEntry = validateText(kgBodyWeightEntry.getText(),2);
				}
				if (!calculationChoiseCB.isEnabled() || !kgBodyWeightEntry.isEnabled() || !calculationWithCB.isEnabled() || !valideEntry || !nutritionChoiseCB.isEnabled()){
					showEntryError("Es muessen alle Auswahl- und Eingabefehlder augefuellt sein.", "Inkomplete Eingabe");
				} else {
					var selected = choisedNutritions.get(0);
					for (Nutrition e: choisedNutritions){
						String s = e.getName() + " " + e.getKcallMl() + " kcall/ml";
						if (e.toString() == nutritionChoiseCB.getSelectedItem()){
						selected = e;
						}
						System.out.println( e.toString() + '\t' + selected.toString());
					}
					System.out.println(selected);
					if (calculationWithCB.getSelectedIndex() == 1){
						System.out.println(Calculation.calculateEnteral(selected.getKcallMl(),
											stringToInt(kgBodyWeightEntry.getText()) * activeProfile.getKcalKgD(), activeProfile.getNutritionHours()));
					} else {
						System.out.println(Calculation.fluidCalc(selected.getKcallMl(), 
													stringToInt(kgBodyWeightEntry.getText()), 
													1, activeProfile.getNutritionHours()));
					}
				}
			}
		};
		
		profileCB.addActionListener(setProfile);
		calculationChoiseCB.addActionListener(calcChoise);
		kgBodyWeightEntry.addActionListener(checkEntry);
		calculationWithCB.addActionListener(calcWithChoise);
		calculate.addActionListener(calculateResult);
	}
	
	/*private boolean validateKcall(String value){
		boolean notValide = false;
		int kcallD = 0;
		try{
			kcallD = Integer.parseInt(value);
		} catch (NumberFormatException e){
			notValide = true;
		}
		if (kcallD < activeProfile.getMinKcallD() || kcallD > activeProfile.getMaxKcallD() || notValide){
			String message = "Kaloriebedarf muss eine ganze Zahl zwischen " + activeProfile.getMinKcallD() + " und " + activeProfile.getMaxKcallD() + " sein.";
			String name = "Falsche Kaloriebedarf-Eingabe";
			showEntryError(message, name);
			return false;
		}
		return true;
	}*/
	
	// Fuehle Array choised_nutritions
	private void fillChoisedNutrition(String choise){
		if (choisedNutritions != null) choisedNutritions.clear();
		if (choise == "oral"){
			for (Nutrition e: nutritions){
				if (e.getApplication() == "oral") choisedNutritions.add(e);
				System.out.println(choisedNutritions);
			}
		} else if (choise == "iv"){
			for (Nutrition e: nutritions){
				if (e.getApplication() == "iv") choisedNutritions.add(e);
			}
		}
	}
	
	private boolean validateText(String entry, int param){
		boolean notValide = false;
		int number = stringToInt(entry);
		if (param == 1){
			if (number < activeProfile.getMinWeight() || number > activeProfile.getMaxWeight()){
				String message = "Gewicht muss eine ganze Zahl zwischen " + activeProfile.getMinWeight() + " und " + activeProfile.getMaxWeight() + " sein.";
				String name = "Falsche Gewichteingabe";
				showEntryError(message, name);
			return false;
			}
		} else {
			if (number < activeProfile.getMinKcallD() || number > activeProfile.getMaxKcallD()){
				String message = "Kaloriebedarf muss eine ganze Zahl zwischen " + activeProfile.getMinKcallD() + " und " + activeProfile.getMaxKcallD() + " sein.";
				String name = "Falsche Kaloriebedarf-Eingabe";
				showEntryError(message, name);
				return false;
			}
		}
		return true;
	}
	
	private int stringToInt( String entry){
		int number = 0;
		try{
			number = Integer.parseInt(entry);
		} catch (NumberFormatException e){}
		return number;
	}
	
	private void showEntryError(String message, String name){
		JOptionPane.showMessageDialog(null, message, name, JOptionPane.WARNING_MESSAGE);
	}
	

	public static void main(String[] args){
		int weight = 0;
		if (args.length != 0){
			try {
				weight = Integer.parseInt(args[0]);
			} catch (NumberFormatException e){
				System.out.println("Ein ungueltiger Parameter wurde uebergeben");
			}
		}
		
		new CalculationApp(weight).setTitle("Ernaehrungsrechner");
	}
}
