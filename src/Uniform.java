import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Uniform {

	private JFrame frame;
	private JTextField txtLevels;
	private JTextField txtPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uniform window = new Uniform();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Uniform() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterNumoflevel = new JLabel("Enter numOflevel :");
		lblEnterNumoflevel.setBounds(25, 61, 116, 14);
		frame.getContentPane().add(lblEnterNumoflevel);
		
		JLabel lblPicturePath = new JLabel("Picture Path: ");
		lblPicturePath.setBounds(25, 132, 87, 14);
		frame.getContentPane().add(lblPicturePath);
		
		txtLevels = new JTextField();
		txtLevels.setBounds(134, 58, 243, 20);
		frame.getContentPane().add(txtLevels);
		txtLevels.setColumns(10);
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		txtPath.setBounds(134, 129, 243, 20);
		frame.getContentPane().add(txtPath);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.setBounds(23, 191, 89, 23);
		frame.getContentPane().add(btnCompress);
	}
}
