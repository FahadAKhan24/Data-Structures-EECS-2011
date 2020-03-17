
public class DLLNode {

	int ID;
	String content;
	DLLNode PREV;
	DLLNode NEXT;

	DLLNode(int packetID, String packetContent, DLLNode prev, DLLNode next) {
		ID = packetID;
		content = packetContent;
		PREV = prev;
		NEXT = next;
	}

	int getPacketID() {
		return ID;
	}

	String getPacketContent() {
		return content;
	}

	DLLNode getNextNode() {
		return NEXT;
	}

	DLLNode getPrevNode() {
		return PREV;
	}

	void setNextNode(DLLNode n) {
		NEXT = n;
	}

	void setPrevNode(DLLNode p) {
		PREV = p;
	}
}