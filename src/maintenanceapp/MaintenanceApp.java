package maintenanceapp;

import maintenanceapp.controller.FXMLDocumentController;
import maintenanceapp.model.Datasource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.util.Callback;


public class MaintenanceApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        final FXMLDocumentController controller = new FXMLDocumentController();
        FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> param) {
				return controller;
			}
		});
        Parent root = loader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.setTitle("Maintenance Logger");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Couldn't open datasource");
            return;
        }
        
        datasource.close();
        launch(args);
    }

}
