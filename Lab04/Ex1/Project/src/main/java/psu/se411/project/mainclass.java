package psu.se411.project;

import javafx.application.Application;
import javafx.stage.Stage;


	public class mainclass extends Application {

	    public static void main(String[] args) {
	        launch();
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        try {
	            primaryStage.setTitle("My Project");
	            primaryStage.show();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}