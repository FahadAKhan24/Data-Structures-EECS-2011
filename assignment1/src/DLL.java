import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DLL {

	DLLNode header;
	DLLNode trailer;

	DLL() {
		header = new DLLNode(-1, null, null, null);
		trailer = new DLLNode(Integer.MAX_VALUE, null, header, null);
		header.setNextNode(trailer);
	}

	/*
	 * readAndAssemble performs the following operations: (1)Reads from file
	 * fileName (e.g. Mystery.txt) line by line (2)stores the content of each
	 * line in a DLLNode (3)places each DLLNode in DLL according to DLLNode’s
	 * packetID number
	 */
	void readAndAssemble(String fileName) throws IOException {
		DLLNode current = null;

		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\t");
				int order = Integer.parseInt(split[0]);
				String text = split[1];

				if (current == null) {
					current = add(order, text, header, trailer);
				} else {
					if (current.getPacketID() > order) {
						current = addLeft(current, order, text);
					} else {
						current = addRight(current, order, text);
					}
				}

				// System.out.println("Order=" + order + ", Text=" + text);
			}
		}
	}

	DLLNode addLeft(DLLNode node, int order, String text) {
		if (node.getPacketID() > order && node.getPrevNode().getPacketID() < order) {
			return add(order, text, node.getPrevNode(), node);
		}
		return addLeft(node.getPrevNode(), order, text);
	}

	DLLNode addRight(DLLNode node, int order, String text) {
		if (node.getPacketID() < order && node.getNextNode().getPacketID() > order) {
			return add(order, text, node, node.getNextNode());
		}
		return addRight(node.getNextNode(), order, text);
	}

	DLLNode add(int id, String text, DLLNode predecessor, DLLNode successor) {
		DLLNode newest = new DLLNode(id, text, predecessor, successor);
		predecessor.setNextNode(newest);
		successor.setPrevNode(newest);

		return newest;
	}

	/*
	 * printContent traverses DLL and prints out the content of its nodes so as
	 * to recreate the original message
	 */

	void printContent() {
		StringBuilder b = new StringBuilder();
		DLLNode next = header.getNextNode();
		while (next != null) {
			if (next.getPacketContent() != null) {
				b.append(next.getPacketContent() + " ");
			}
			next = next.getNextNode();
		}

		System.out.println(b.toString());
	}
}