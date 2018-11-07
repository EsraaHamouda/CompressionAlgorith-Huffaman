import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Main {
	static Vector<Node> v = new Vector<Node>();// contains nodes of(char and the
												// code) as the table
	static String result = "";
	static Map<String, Character> decoding = new HashMap<String, Character>();

	public static void main(String[] args) {
		String data = getOrigonalTextFromFile("E:\\Faculty\\3rd Year\\1st Term\\Multimedia\\huffmanCode\\huff.txt");

		Vector<Integer> bytes = new Vector<Integer>();
		bytes = CompressionHuffman(data);
		System.out.println("CompressionHuffman = " + bytes);
		String streamResult = decompress(result, decoding);
		System.out.println("deCompressionHuffman  " + streamResult);
		System.out.println(streamResult.equals(data));

	}

	public static String DecompressionHuffman() {
		String streamResult = decompress(result, decoding);
		return streamResult;
	}

	public static Vector<Integer> CompressionHuffman(String data) {
		Vector<Character> letters = new Vector<Character>();
		Map<Character, Integer> charFrequancy = new HashMap<Character, Integer>();
		Map<Character, String> encoding = new HashMap<Character, String>();

		// //mapping frequencies to letters/////////
		for (int i = 0; i < data.length(); i++) {
			if (!charFrequancy.containsKey(data.charAt(i))) {
				letters.add(data.charAt(i));
				charFrequancy.put(data.charAt(i), 1);
			} else {
				int c = charFrequancy.get(data.charAt(i));
				c++;
				charFrequancy.put(data.charAt(i), c);
			}
		}
		// /////make stack of nodes using freq and letter//////
		Stack<Node> myStack = new Stack<Node>();
		for (int i = 0; i < letters.size(); i++) {
			Node x = new Node(0);
			x.ch = letters.get(i);
			int f = charFrequancy.get(x.ch);
			x.key = f;
			myStack.push(x);
		}
		myStack = sort(myStack);
		// /////construct the binary tree////////
		Node focusedNode = compress(myStack);
		// //////forming the codes///////
		getCode(focusedNode);
		// /////////fill maps///////////
		for (int i = 0; i < v.size(); i++) {
			encoding.put(v.get(i).ch, v.get(i).code);
			decoding.put(v.get(i).code, v.get(i).ch);
		}
		System.out.println("Codes  =  " + encoding.toString());
		// ///////encoding//////
		// encoding from letters to bits
		for (int i = 0; i < data.length(); i++) {
			String code = encoding.get(data.charAt(i));
			result += code;
		}
		Vector<Integer> bytes = new Vector<Integer>();
		bytes = binaryToDecimel(result);
		String bytesStr = bytes.toString();
		wirteOnFile(
				"E:\\Faculty\\3rd Year\\1st Term\\Multimedia\\huffmanCode\\huffResult.txt",
				bytesStr);
		return bytes;

	}

	// /////Sorting the stack/////
	public static Stack<Node> sort(Stack<Node> s) {
		if (s.isEmpty()) {
			return s;
		}
		Node pivot = s.pop();
		Stack<Node> left = new Stack<Node>();
		Stack<Node> right = new Stack<Node>();
		while (!s.isEmpty()) {
			Node y = s.pop();
			if (y.key > pivot.key) {
				left.push(y);
			} else {
				right.push(y);
			}
		}
		sort(left);
		sort(right);
		// merge
		Stack<Node> tmp = new Stack<Node>();
		while (!right.isEmpty()) {
			tmp.push(right.pop());
		}
		tmp.push(pivot);
		while (!left.isEmpty()) {
			tmp.push(left.pop());
		}
		while (!tmp.isEmpty()) {
			s.push(tmp.pop());
		}
		return s;
	}

	// ///////get the code for each letter(leaf)///////
	public static void getCode(Node node) {

		if (node.left != null) {
			node.left.code = node.code + node.left.code;
			getCode(node.left);
		}
		if (node.right != null) {
			node.right.code = node.code + node.right.code;
			getCode(node.right);
		}
		if (node.left == null && node.right == null) {
			node.code = node.code.substring(4);
			v.add(node);
		}
	}

	// //decompress data from bits to letters///////
	public static String decompress(String result,
			Map<String, Character> decoding) {
		String search = "";
		String streamResult = "";
		String x = "", m = "";
		char a = ' ';
		for (int i = 0; i < result.length(); i++) {

			x = String.valueOf(result.charAt(i));
			if (decoding.containsKey(x)) {
				a = decoding.get(x);
				m = String.valueOf(a);
				streamResult += m;
			} else {

				while (!decoding.containsKey(x)) {
					i++;
					if (i >= result.length())
						break;

					search = String.valueOf(result.charAt(i));
					x += search;

				}
				a = decoding.get(x);
				m = String.valueOf(a);
				streamResult += m;

			}

		}
		return streamResult;
	}

	// //////compression//////
	public static Node compress(Stack<Node> myStack) {
		while (myStack.size() != 1) {
			Node low = new Node();
			Node high = new Node();
			Node sum = new Node();
			low = myStack.peek();
			myStack.pop();
			high = myStack.peek();
			myStack.pop();
			sum.key = low.key + high.key;
			low.code = "0";
			high.code = "1";
			sum.left = low;
			sum.right = high;
			myStack.push(sum);
			myStack = sort(myStack);
		}
		Node focusedNode = myStack.peek();
		return focusedNode;
	}

	public static Vector<Integer> binaryToDecimel(String result) {
		Vector<Integer> bytes = new Vector<Integer>();
		for (int i = 0; i < result.length(); i += 31) {
			if (i + 31 >= result.length())
				break;
			String aa = result.substring(i, i + 31);
			int num = Integer.parseInt(aa, 2);
			bytes.add(num);
		}
		if (result.length() % 31 != 0) {
			String aa = result.substring(result.length()
					- (result.length() % 31));
			int num = Integer.parseInt(aa, 2);
			bytes.add(num);
		}
		return bytes;
	}

	public static String getOrigonalTextFromFile(String path) {
		String line = "";
		// String path =
		File file1 = new File(path);
		String g = "";
		if (!file1.exists()) {
			JOptionPane.showMessageDialog(null, "wrong file");
		}
		BufferedReader read;
		try {
			read = new BufferedReader(new FileReader(file1));
			while ((line = read.readLine()) != null) {
				g += line;
			}
			read.close();
		} catch (IOException e) {

		}
		return g;
	}

	public static void wirteOnFile(String path, String text) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(path, false)))) {
			out.print(text);

		} catch (IOException e) {

		}
	}

}