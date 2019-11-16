package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame MainForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JList queueToAddDisplay = new JList();
		queueToAddDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		queueToAddDisplay.setToolTipText("List of coins to add to the collection");
		queueToAddDisplay.setModel(new AbstractListModel() {
			String[] values = new String[] {"Hello", "World"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		queueToAddDisplay.setBounds(15, 73, 350, 300);
		MainForm.getContentPane().add(queueToAddDisplay);
		
		JList collectionDisplay = new JList();
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
		commitQueueButton.setEnabled(false);
		commitQueueButton.setVerticalAlignment(SwingConstants.TOP);
		commitQueueButton.setBounds(367, 175, 94, 90);
		MainForm.getContentPane().add(commitQueueButton);
		
		JButton addToQueueButton = new JButton("Add Coin to Queue");
		addToQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				AddEditForm aef = new AddEditForm();
				aef.NewAddEditForm();
				
			}
		});
		addToQueueButton.setToolTipText("Opens dialogue to add a new coin");
		addToQueueButton.setBounds(15, 480, 169, 48);
		MainForm.getContentPane().add(addToQueueButton);
		
		JButton editCoinButton = new JButton("Edit Coin");
		editCoinButton.setEnabled(false);
		editCoinButton.setToolTipText("Opens dialogue to edit a coin");
		editCoinButton.setBounds(196, 480, 169, 48);
		MainForm.getContentPane().add(editCoinButton);
		
		JComboBox sortComboBox = new JComboBox();
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Sort By...", "Coin", "Year", "Mint", "Grade"}));
		sortComboBox.setBounds(463, 480, 169, 48);
		MainForm.getContentPane().add(sortComboBox);
		
		JButton exitButton = new JButton("Exit Program");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainForm.dispose();
			}
		});
		exitButton.setToolTipText("Closes the program without saving the queue");
		exitButton.setBounds(644, 480, 169, 48);
		MainForm.getContentPane().add(exitButton);
		
		JButton btnRemoveCoin = new JButton("Remove Coin");
		btnRemoveCoin.setEnabled(false);
		btnRemoveCoin.setToolTipText("Removes a coin from the collection");
		btnRemoveCoin.setBounds(644, 389, 169, 48);
		MainForm.getContentPane().add(btnRemoveCoin);
	}
}
