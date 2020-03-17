public class MessageAssembler {
	public static void main(String[] args) throws Exception {
		DLL myDLL = new DLL();
		myDLL.readAndAssemble("/Users/ovaisa/Documents/workspace/DLL/src/Mystery.txt");
		myDLL.printContent();
	}
}
