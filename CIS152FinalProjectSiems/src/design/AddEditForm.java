package design;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class AddEditForm {

	private JFrame frmAddeditACoin;

	/**
	 * Launch the application.
	 */
	public static void NewAddEditForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditForm window = new AddEditForm();
					window.frmAddeditACoin.setVisible(true);
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
		frmAddeditACoin = new JFrame();
		frmAddeditACoin.setTitle("Add/Edit a coin");
		frmAddeditACoin.setAlwaysOnTop(true);
		frmAddeditACoin.setBounds(100, 100, 350, 550);
		frmAddeditACoin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddeditACoin.getContentPane().setLayout(null);
	}

}
