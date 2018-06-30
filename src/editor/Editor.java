package editor;

import javax.swing.JFrame;

import editor.controller.EditorController;
import editor.model.EditorModel;
import editor.view.EditorView;

public class Editor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Editor() {
		EditorView  view  = new EditorView();
		EditorModel model = new EditorModel();
		EditorController controller = new EditorController(model, view);
	}
	
	public static void main(String[] args) {
		EditorView  view  = new EditorView();
		EditorModel model = new EditorModel();
		EditorController controller = new EditorController(model, view);
	}
}
