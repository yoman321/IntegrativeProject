/*
 * To change this license header, choose License Headers in Project PrZoperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author luoph
 */
public class AccidentProject extends Application {
    
   public static void main(String[] args) {
       launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginSceneFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> System.exit(0));
    }
    
}
