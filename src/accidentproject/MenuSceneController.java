/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 *
 * @author luoph
 */
public class MenuSceneController {
    
    public void onclickProjectileObject(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource(("FXML/DemoInputScreen.fxml")));
        Scene collisionScene = new Scene (nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(collisionScene);
        stage.show();
    }
    public void onclickVehicleCollisions(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource(("FXML/VehicleCollisionsFXML.fxml")));
        Scene collisionScene = new Scene (nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(collisionScene);
        stage.show();
    }
    public void onclickSpeedingVehicle(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource(("FXML/SpeedingVehicleFXML.fxml")));
        Scene speedingScene = new Scene (nextPane);
      
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(speedingScene);
        stage.show();
    }
}
