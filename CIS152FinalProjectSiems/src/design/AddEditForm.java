package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import classes.Coin;
import helpers.NewCoinHelper;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextArea;

public class AddEditForm {

	private JFrame formAddEditACoin;
	private static JTextField txtYear;
	private static JTextField txtDesign;
	private static JTextField txtMint;
	private static JTextField txtError;
	private static JCheckBox chckbxS;
	private static JCheckBox chckbxC;
	private static JComboBox comboCoinType;
	private static boolean edit;

	/**
	 * Launch the application.
	 */
	public static void NewAddEditForm() {
		edit = false;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditForm window = new AddEditForm();
					window.formAddEditACoin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void NewAddEditForm(String type, int year, char mint, String design, boolean b1, boolean b2, String error) {
		edit = true;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditForm window = new AddEditForm();
					txtYear.setText(Integer.toString(year));
					txtDesign.setText(design);
					txtMint.setText(Character.toString(mint));
					txtError.setText(error);
					if(b1) {
						chckbxS.setSelected(true);
					}
					if(b2) {
						chckbxC.setSelected(true);
					}
					if(type.equals("Quarter")) {
						comboCoinType.setSelectedIndex(0);
					} else if(type.equals("Dime")) {
						comboCoinType.setSelectedIndex(1);
					} else if(type.equals("Nickel")) {
						comboCoinType.setSelectedIndex(2);
					} else if(type.equals("Penny")) {
						chckbxC.setVisible(true);
						chckbxS.setText("Steel");
						comboCoinType.setSelectedIndex(3);
					}
					
					window.formAddEditACoin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEditForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formAddEditACoin = new JFrame();
		formAddEditACoin.setTitle("Add/Edit a coin");
		formAddEditACoin.setAlwaysOnTop(true);
		formAddEditACoin.setBounds(100, 100, 350, 550);
		formAddEditACoin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formAddEditACoin.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(15, 449, 115, 29);
		formAddEditACoin.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formAddEditACoin.dispose();
			}
		});
		btnCancel.setBounds(198, 449, 115, 29);
		formAddEditACoin.getContentPane().add(btnCancel);
		
		comboCoinType = new JComboBox();
		comboCoinType.setModel(new DefaultComboBoxModel(new String[] {"Quarter", "Dime", "Nickel", "Penny"}));
		comboCoinType.setBounds(163, 48, 150, 26);
		formAddEditACoin.getContentPane().add(comboCoinType);
		
		txtYear = new JTextField();
		txtYear.setHorizontalAlignment(SwingConstants.CENTER);
		txtYear.setBounds(163, 108, 150, 26);
		formAddEditACoin.getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		txtDesign = new JTextField();
		txtDesign.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesign.setColumns(10);
		txtDesign.setBounds(163, 171, 150, 26);
		txtDesign.setText("Washington");
		formAddEditACoin.getContentPane().add(txtDesign);
		
		txtMint = new JTextField();
		txtMint.setHorizontalAlignment(SwingConstants.CENTER);
		txtMint.setColumns(1);
		txtMint.setBounds(163, 229, 50, 26);
		formAddEditACoin.getContentPane().add(txtMint);
		
		txtError = new JTextField();
		txtError.setHorizontalAlignment(SwingConstants.CENTER);
		txtError.setColumns(10);
		txtError.setBounds(163, 321, 150, 26);
		formAddEditACoin.getContentPane().add(txtError);
		
		chckbxS = new JCheckBox("Silver");
		chckbxS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxS.setBounds(38, 280, 103, 29);
		formAddEditACoin.getContentPane().add(chckbxS);
		
		chckbxC = new JCheckBox("Copper");
		chckbxC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxC.setVisible(false);
		chckbxC.setBounds(178, 280, 104, 29);
		formAddEditACoin.getContentPane().add(chckbxC);
		
		JLabel lblError = new JLabel("Error:");
		lblError.setHorizontalAlignment(SwingConstants.RIGHT);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblError.setLabelFor(txtError);
		lblError.setBounds(15, 324, 115, 20);
		formAddEditACoin.getContentPane().add(lblError);
		
		JLabel lblMintMark = new JLabel("Mint Mark:");
		lblMintMark.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMintMark.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMintMark.setBounds(15, 232, 115, 20);
		formAddEditACoin.getContentPane().add(lblMintMark);
		
		JLabel lblDesign = new JLabel("Design");
		lblDesign.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesign.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDesign.setBounds(15, 174, 115, 20);
		formAddEditACoin.getContentPane().add(lblDesign);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYear.setBounds(15, 111, 115, 20);
		formAddEditACoin.getContentPane().add(lblYear);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblType.setBounds(15, 51, 115, 20);
		formAddEditACoin.getContentPane().add(lblType);
		
		JTextArea txtCreation = new JTextArea();
		txtCreation.setEditable(false);
		txtCreation.setVisible(true);
		txtCreation.setBounds(15, 363, 298, 70);
		formAddEditACoin.getContentPane().add(txtCreation);
		
		if(edit) {
			btnCancel.setEnabled(false); //Disable the cancel button if editing a coin.
		}
		
		
		/*
		 * Methods
		 */
		
		
		comboCoinType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = comboCoinType.getSelectedItem().toString();
				if(type == "Penny") {
					chckbxC.setVisible(true);
					chckbxS.setText("Steel");
					txtDesign.setText("Union Shield");
				} else {
					chckbxC.setVisible(false);
					chckbxS.setText("Silver");
					if(type == "Quarter") {
						txtDesign.setText("Washington");
					} else if (type == "Dime") {
						txtDesign.setText("Roosevelt");
					} else if (type == "Nickel") {
						txtDesign.setText("Jefferson");
					}
				}
				
				
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				NewCoinHelper NCH = new NewCoinHelper();
				String sYear = txtYear.getText();				
				if(sYear.isEmpty() || txtDesign.getText().isEmpty() || txtMint.getText().isEmpty()) { //Check all values entered
					msg = "Please fill out all required fields.";
				} else if(txtMint.getText().length() != 1){ //Check mint is only one char
					msg = "Please enter 1 character for the mint field.";
				} else if(!isInteger(sYear) || (sYear.length() != 4)) {//Check year is an int and 4 digits (this program is for USA coins which are not from pre-1000 AD)
					msg = "Please enter a valid year.";
				} else { //Basic checks done, try to create coin.
					int year = Integer.parseInt(sYear);
					String design = txtDesign.getText();
					char mint = txtMint.getText().charAt(0);
					String error = txtError.getText();
					String type = comboCoinType.getSelectedItem().toString();
					Boolean b1 = chckbxS.isSelected();
					Boolean b2 = false;
					if(chckbxC.isEnabled()) {
						b2 = chckbxC.isSelected();
					}
					
					if(error.isEmpty()) { //Perform validation checks if there is no error
						if(type.equals("Quarter")) {
							msg = NCH.validateQuarter(year, mint, design, b1);
						} else if (type.equals("Dime")) {
							msg = NCH.validateDime(year, mint, design, b1);
						} else if (type.equals("Nickel")) {
							msg = NCH.validateNickel(year, mint, design, b1);
						} else if (type.equals("Penny")) {
							msg = NCH.validatePenny(year, mint, design, b1, b2);
						}
					}
					
					if(msg.isEmpty()) { //validation passed, create coin
						Coin c = NCH.newCoin(type, year, mint, design, b1, b2, error);
						MainForm.coinQ.add(c);
						MainForm.updateQueue();
						txtCreation.setText("Coin created succesfully \n" + c.toString());
						txtMint.setText("");
						txtError.setText("");
						txtYear.setText("");
						if(edit) {//Auto-close if window was called from an edit
							formAddEditACoin.dispose();
						}
					}
				}//End if-else
				
				if(!msg.isEmpty()) { //validation failed, output error msg
					txtCreation.setText("An error occured when trying to create your coin: \n" + msg);
				}
			}//End submit event
		});
	}
	
	
	//https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
}
