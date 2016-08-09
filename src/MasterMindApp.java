import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MasterMindApp extends Application{

	private double width = 500;
	private double height = 500;

	MasterMindUI ui = null;

	@Override
	public void start(Stage primaryStage){
		ui = new MasterMindUI();

	    Scene scene = new Scene(ui, width, height);
	    primaryStage.setTitle("MasterMind App");
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show();

	} 
}