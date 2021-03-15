/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Liam
 */
public class DemoInputScreenFXMLController implements Initializable {
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
    @FXML private ImageView heightObject;
    @FXML private RadioButton rbBasketball;
    @FXML private RadioButton rbBoxSquare;
    @FXML private RadioButton rbBoxRectangle;
    @FXML private ImageView fallingObjectDisplay;
    @FXML private ImageView demoBackground;
    @FXML private ImageView demoLedge;
    
    
    

    protected ProjectileMotion pm = new ProjectileMotion();
    

    public void handleNext(ActionEvent event) throws IOException{
        
        Parent demo = FXMLLoader.load(getClass().getResource("ProjectileMotionDemoScreen.fxml"));
        Scene scene = new Scene(demo);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
    }
    
}