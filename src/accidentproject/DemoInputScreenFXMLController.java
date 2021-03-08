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
    @FXML private AnchorPane demoPane;
    @FXML private Label rLabel;
    @FXML private Slider rSlider;
    @FXML private Label gLabel;
    @FXML private Slider gSlider;
    @FXML private Label bLabel;
    @FXML private Slider bSlider;
    @FXML private Slider objectPosSlider;

    protected ProjectileMotion pm = new ProjectileMotion();
    

    public void handleNext(){
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
            
}