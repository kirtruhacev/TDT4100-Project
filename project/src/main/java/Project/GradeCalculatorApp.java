package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradeCalculatorApp extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Grade Calculator");
        primaryStage.setScene(new Scene(FXMLLoader.load(GradeCalculatorApp.class.getResource("gradeCalculator.fxml"))));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		GradeCalculatorApp.launch(args);
	}
}