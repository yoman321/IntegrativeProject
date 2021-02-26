/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Liam
 */
public class DemoInputScreenFXMLController {
    @FXML private TextField iVelXYTF;
    @FXML private TextField fVelXYTF;
    @FXML private TextField accelTF;
    @FXML private TextField timeTF;
    @FXML private TextField iHeightTF;
    @FXML private TextField fHeightTF;
    @FXML private TextField iDistanceTF;
    @FXML private TextField fDistanceTF;
    @FXML private Pane demoPane;
    protected ProjectileMotion pm = new ProjectileMotion();
    

    public void handleNext(){
        
        
        
    }
    
            
}
