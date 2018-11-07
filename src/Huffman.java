import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Huffman {

	private JFrame frmHuffmanCompression;
	static Vector<Node> v = new Vector<Node>();
	Main m = new Main();
	private JTextField txtPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Huffman window = new Huffman();
					window.frmHuffmanCompression.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Huffman() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHuffmanCompression = new JFrame();
		frmHuffmanCompression.setTitle("Huffman Compression");
		frmHuffmanCompression.setBounds(100, 100, 542, 439);
		frmHuffmanCompression.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHuffmanCompression.getContentPane().setLayout(null);

		final JTextPane txtCompressionRes = new JTextPane();
		txtCompressionRes.setBounds(23, 86, 486, 120);
		frmHuffmanCompression.getContentPane().add(txtCompressionRes);

		final JTextPane txtDecompressionRes = new JTextPane();
		txtDecompressionRes.setBounds(23, 242, 486, 120);
		frmHuffmanCompression.getContentPane().add(txtDecompressionRes);

		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String data = m.getOrigonalTextFromFile(txtPath.getText());
				Vector<Integer> bytes = new Vector<Integer>();
				bytes = m.CompressionHuffman(data);
				String strBytes = bytes.toString();
				txtCompressionRes.setText(strBytes);
				String streamResult = m.DecompressionHuffman();
				txtDecompressionRes.setText(streamResult);
			}
		});

		btnCompress.setBounds(405, 32, 104, 23);
		frmHuffmanCompression.getContentPane().add(btnCompress);

		txtPath = new JTextField();
		txtPath.setBounds(100, 33, 295, 20);
		frmHuffmanCompression.getContentPane().add(txtPath);
		txtPath.setColumns(10);

		JLabel lblFilePath = new JLabel("File Path:");
		lblFilePath.setBounds(23, 36, 67, 14);
		frmHuffmanCompression.getContentPane().add(lblFilePath);

		JLabel lblUsingFiles = new JLabel("Using Files");
		lblUsingFiles.setBounds(23, 11, 118, 14);
		frmHuffmanCompression.getContentPane().add(lblUsingFiles);

		JLabel lblComperssionResult = new JLabel("Comperssion Result : ");
		lblComperssionResult.setBounds(23, 64, 178, 14);
		frmHuffmanCompression.getContentPane().add(lblComperssionResult);

		JLabel lblDecomperssionResult = new JLabel("Decomperssion Result : ");
		lblDecomperssionResult.setBounds(23, 217, 178, 14);
		frmHuffmanCompression.getContentPane().add(lblDecomperssionResult);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHuffmanCompression.dispose();
			}
		});
		btnExit.setBounds(211, 373, 89, 23);
		frmHuffmanCompression.getContentPane().add(btnExit);

	}
}