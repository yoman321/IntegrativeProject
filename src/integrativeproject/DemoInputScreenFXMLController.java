/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML private TextField angleTF;
    @FXML private TextField timeTF;
    @FXML private TextField iHeightTF;
    @FXML private AnchorPane demoPane;
    @FXML private Label rLabel;
    @FXML private Label accelerationLabel;
    @FXML private Label demoLabel;
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
    private double velXY, angle, time, height, distance;
    
    

    protected ProjectileMotion pm = new ProjectileMotion();
    

    public void handleNext(ActionEvent event) throws IOException{

        //Retrieving user input
        if(isDouble(iVelXYTF.getText())&& isDouble(angleTF.getText()) && isDouble(iHeightTF.getText())){
            velXY = Double.parseDouble(iVelXYTF.getText());
            angle = Double.parseDouble(angleTF.getText());
            height = Double.parseDouble(iHeightTF.getText());
        }
        else{
            demoLabel.setText("Not enough information");
            return;
        }
        //Adding input into the projectile motion class
        pm.setVelXY(velXY);
        pm.convertXY(angle);
        pm.setIHeight(height);
        pm.solveForTime(); //This method solves for the time and sets the value for the time variable in its method
        pm.solveForDistance();
        
        
        
        if(pm.solveForX() != -1 && pm.solveForY() != -1){
            System.out.println(pm.solveForX() + "\n" + pm.solveForY() );
            Parent demo = FXMLLoader.load(getClass().getResource("ProjectileMotionDemoScreen.fxml"));
            Scene scene = new Scene(demo);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
    }
    
    public boolean isDouble(String string){
        
        if(string.length() == 0){
            return false;
        }
        ArrayList<Character> charArray = new ArrayList<>();
        for(int i = 0; i < string.length(); i++){
            charArray.add(string.charAt(i));
        }
        for(int i = 0; i < charArray.size();i++){
            String test = "" + charArray.get(i);
            if((i == 0 && test.equals("-")) || (i == 0 && test.equals("+"))){
                continue;
            }
            if(test.equals("0") || test.equals("1") || test.equals("2") || test.equals("3") || test.equals("4") || test.equals("5") || test.equals("6") || test.equals("7") || test.equals("8") || test.equals("9")){
                continue;
            }
            else{
                return false;
            }
        }

        
        
        return true;
    }

}