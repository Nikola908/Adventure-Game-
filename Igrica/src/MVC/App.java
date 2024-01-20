package MVC;



public class App {

	public static void main(String[] args) {

		
		Frame frame = new Frame();
		Model model = new Model();
		
		Controller controller = new Controller(frame, model);
		frame.setController(controller);
		
		frame.setLocationRelativeTo(null);

	}

}
