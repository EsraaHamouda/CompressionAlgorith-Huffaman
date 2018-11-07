class Node {

	public int key;
	public Node left;
	public Node right;
	public String code;
	public char ch;

	public Node() {
		super();
	}

	Node(int key) {
		this.key = key;
		right = null;
		left = null;
		code = "";
	}

	public String toString() {
		return "[key=" + key + ", code=" + code + ", ch=" + ch + "]";
	}

}
