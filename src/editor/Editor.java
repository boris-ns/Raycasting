package editor;

import javax.swing.JFrame;

public class Editor extends JFrame {

	private static final long serialVersionUID    = 1L;
	
	public static void main(String[] args) {
		EditorView  view  = new EditorView();
		EditorModel model = new EditorModel();
		EditorController controller = new EditorController(model, view);
	}
}
