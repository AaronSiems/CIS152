package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classes.Coin;
import classes.Penny;
import helpers.CoinListHelper;
import helpers.XMLHelper;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MainForm {
	
	private static String fileLoc = "CoinList.xml";
	private static String backupLoc = "CoinListBackup.xml";
	public static Queue<Coin> coinQ = new LinkedList<>(); //Making public because I don't want to have to re-write all the methods when I call from 2nd form
	public static JList queueToAddList = new JList();
	public static JScrollPane queueToAddDisplay = new JScrollPane(queueToAddList);
	public static DefaultListModel qList = new DefaultListModel();

	private JFrame MainForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Create a backup file before doing anything.
		XMLHelper xmlh = new XMLHelper();
		xmlh.backup(fileLoc, backupLoc);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.MainForm.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		MainForm = new JFrame();
		MainForm.setTitle("Coin Collection App");
		MainForm.setBounds(100, 100, 850, 600);
		MainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainForm.getContentPane().setLayout(null);
		
		CoinListHelper CLH = new CoinListHelper(fileLoc);
		List<Coin> c = CLH.loadList();
		
		//JList queueToAddDisplay = new JList(); put at top because AddEditForm needs access
		queueToAddDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		queueToAddDisplay.setToolTipText("List of coins to add to the collection");
		queueToAddList.setModel(qList);
		queueToAddDisplay.setBounds(15, 73, 350, 300);
		MainForm.getContentPane().add(queueToAddDisplay);
		
		System.out.println("==========================");
		System.out.println("Coins found: ");
		DefaultListModel collectionModel = new DefaultListModel();
		c.forEach((coin) -> {
			String coinString = coin.toString();
			collectionModel.addElement(coinString);
			System.out.println(coin.toString());
		});
		System.out.println("==========================");
		
		
		JList collectionJList = new JList(collectionModel);
		JScrollPane collectionDisplay = new JScrollPane(collectionJList);
		collectionDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		collectionDisplay.setToolTipText("List of coins in the current collection");
		collectionDisplay.setBounds(463, 73, 350, 300);		
		MainForm.getContentPane().add(collectionDisplay);
		
		JLabel queueToAddLabel = new JLabel("Coins to add");
		queueToAddLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		queueToAddLabel.setHorizontalAlignment(SwingConstants.CENTER);
		queueToAddLabel.setBounds(15, 16, 350, 41);
		MainForm.getContentPane().add(queueToAddLabel);
		
		JLabel collectionLabel = new JLabel("Current Collection");
		collectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		collectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		collectionLabel.setBounds(463, 16, 350, 41);
		MainForm.getContentPane().add(collectionLabel);
		
		JButton commitQueueButton = new JButton("<html> <center> Save Queue to Collection </center></html>");
		commitQueueButton.setToolTipText("Save all coins in the queue to the collection");
		commitQueueButton.setHorizontalAlignment(SwingConstants.LEFT);
		commitQueueButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		commitQueueButton.setVerticalAlignment(SwingConstants.TOP);
		commitQueueButton.setBounds(367, 175, 94, 90);
		MainForm.getContentPane().add(commitQueueButton);
		
		JButton addToQueueButton = new JButton("Add Coin to Queue");
		addToQueueButton.setToolTipText("Opens dialogue to add a new coin");
		addToQueueButton.setBounds(15, 480, 169, 48);
		MainForm.getContentPane().add(addToQueueButton);
		
		JButton editCoinButton = new JButton("Edit Coin");
		editCoinButton.setEnabled(false);
		editCoinButton.setToolTipText("Opens dialogue to edit a coin");
		editCoinButton.setBounds(196, 480, 169, 48);
		MainForm.getContentPane().add(editCoinButton);
		
		JComboBox sortComboBox = new JComboBox();
		
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Sort By...", "Coin", "Year"}));
		sortComboBox.setBounds(463, 480, 169, 48);
		MainForm.getContentPane().add(sortComboBox);
		
		JButton exitButton = new JButton("Exit Program");
		exitButton.setToolTipText("Closes the program without saving the queue");
		exitButton.setBounds(644, 480, 169, 48);
		MainForm.getContentPane().add(exitButton);
		
		JButton btnRemoveCoin = new JButton("Remove Coin");
		
		btnRemoveCoin.setEnabled(false);
		btnRemoveCoin.setToolTipText("Removes a coin from the collection");
		btnRemoveCoin.setBounds(644, 389, 169, 48);
		MainForm.getContentPane().add(btnRemoveCoin);
		
		/*
		 * Event listeners below here
		 */
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainForm.dispose();
			}
		});
		
		
		addToQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEditForm aef = new AddEditForm();
				aef.NewAddEditForm();
				
			}
		});
		
		
		collectionJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!btnRemoveCoin.isEnabled()) { //if disabled, enable
					btnRemoveCoin.setEnabled(true);
				}
				if(!editCoinButton.isEnabled()) { //if disabled, enable
					editCoinButton.setEnabled(true);
				}
			}
		});
		
		
		btnRemoveCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int ind = collectionJList.getSelectedIndex();
				if(ind == -1) {
					return;
				}
				c.remove(ind);
				//Update collection sudo-method
				collectionModel.clear();
				c.forEach((coin) -> {
					String coinString = coin.toString();
					collectionModel.addElement(coinString);
				});
				collectionJList.setModel(collectionModel);
				CLH.saveList(c);
				collectionJList.setSelectedIndex(ind);
			}
		});
		
		commitQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!coinQ.isEmpty()) { //If not empty...
					int sizeTemp = coinQ.size();
					for(int i = 0; i < sizeTemp; i++) {
						Coin CTA = coinQ.poll();
						c.add(CTA);
					}
					//Update collection sudo-method
					collectionModel.clear();
					c.forEach((coin) -> {
						String coinString = coin.toString();
						collectionModel.addElement(coinString);
					});
					collectionJList.setModel(collectionModel);
					CLH.saveList(c);
					updateQueue();
				}//else nothing
			}
		});
		
		editCoinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(collectionJList.getSelectedIndex() == -1) {
					return;
				}
				Coin tempCoin = c.get(collectionJList.getSelectedIndex()); //Store the coin to load into edit
				c.remove(collectionJList.getSelectedIndex()); //Remove from list
				AddEditForm aef = new AddEditForm();//Create the edit form
				String type = tempCoin.getClass().getCanonicalName().replace("classes.", "");
				System.out.println("Called edit window for coin type " + type);
				if(type.contentEquals("Quarter")) {
					aef.NewAddEditForm(type, tempCoin.getYear(), tempCoin.getMint(), tempCoin.getDesign(), tempCoin.isSilver(), false, tempCoin.getError());
				} else if(type.contentEquals("Dime")) {
					aef.NewAddEditForm(type, tempCoin.getYear(), tempCoin.getMint(), tempCoin.getDesign(), tempCoin.isSilver(), false, tempCoin.getError());
				} else if(type.contentEquals("Nickel")) {
					aef.NewAddEditForm(type, tempCoin.getYear(), tempCoin.getMint(), tempCoin.getDesign(), tempCoin.isSilver(), false, tempCoin.getError());
				} else if(type.contentEquals("Penny")) {
					Penny tempPenny = (Penny) tempCoin;
					aef.NewAddEditForm(type, tempPenny.getYear(), tempPenny.getMint(), tempPenny.getDesign(), tempPenny.isSteel(), tempPenny.isCopper(), tempPenny.getError());
				}
				
				//Update collection sudo-method
				collectionModel.clear();
				c.forEach((coin) -> {
					String coinString = coin.toString();
					collectionModel.addElement(coinString);
				});
				collectionJList.setModel(collectionModel);
				//Do not save, commit queue will save it
			}
		});
		
		
		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sortType = sortComboBox.getSelectedItem().toString();
				List<Coin> tempList = CLH.sortList(c, sortType);
				c.clear();
				for(int i = 0; i < tempList.size(); i++) {
					c.add(tempList.get(i));
				}
				//Update collection sudo-method
				collectionModel.clear();
				c.forEach((coin) -> {
					String coinString = coin.toString();
					collectionModel.addElement(coinString);
				});
				collectionJList.setModel(collectionModel);
				CLH.saveList(c);
			}
		});
		
	}
	
	

	public static void updateQueue() {
		if(coinQ.isEmpty()) { //queue is empty, clear list
			qList.clear();
		} else {
			qList.clear();
			coinQ.forEach((coin) -> {
				String coinString = coin.toString();
				qList.addElement(coinString);
			});
		}
		queueToAddList.setModel(qList);
	}
}
