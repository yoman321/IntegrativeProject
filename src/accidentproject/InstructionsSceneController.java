/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author luoph
 */
public class InstructionsSceneController {
    
    public void onclickBack(ActionEvent e) throws Exception{
        Parent backPane = FXMLLoader.load(getClass().getResource(("FXML/VehicleCollisionsFXML.fxml")));
        Scene menuScene = new Scene(backPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(menuScene);
        stage.show();
    }
}
