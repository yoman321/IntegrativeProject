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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    @FXML private StackPane demoPane;
    @FXML private ImageView targetObject;
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
    @FXML private ImageView demoLedge = new ImageView();
    @FXML private Slider heightSlider = new Slider();
    @FXML public AnchorPane anchorPane;
    @FXML public TabPane tabPane;
    
    private double velXY, angle, time, height, distance, targetObjectDistance;
    private double ledgeHeight, previewHeight, previewWidth;
    private boolean isInitialized = false;
    
    

    protected ProjectileMotion pm = new ProjectileMotion();
    

    public void handleNext(ActionEvent event) throws IOException{

        //Retrieving user input
        if(isInitialized == false){
            demoLabel.setText("Must Initialize to Proceed");
            return;
        }
        if(isDouble(iVelXYTF.getText())&& isDouble(angleTF.getText())){
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
        if(angle >= 90){
            demoLabel.setText("Angle must be < 90");
            return;
        }
        else if(angle <=0){
            demoLabel.setText("Angle must be > 0");
            return;
        }
        else{
        pm.convertXY(angle);
        }
        pm.setIHeight(height);
        pm.solveForTime(); //This method solves for the time and sets the value for the time variable in its method
        pm.solveForDistance();
        
        //If statement that has to be able to solve for a height and distance of a projectile motion
        if(pm.solveForHeight() != -1 && pm.solveForDistance() != -1){
            setLedgeHeight(ledgeHeight);
            setPreviewHeight(demoPane.getHeight());
            setPreviewWidth(demoPane.getWidth());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ProjectileMotionDemoScreen.fxml"));
            Parent demo = loader.load();
            Scene scene = new Scene(demo);
            
            DemoScreenFXMLController controller = loader.getController();
            controller.InitializeData(this);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public void handleBack(){
        
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        height = (int)heightSlider.getValue();;
        iHeightTF.setText(Double.toString(height));
        heightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                height = (int)heightSlider.getValue();;
                iHeightTF.setText(Double.toString(height));
            }
            
        });
        
        rSlider.valueProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                int value =(int)(rSlider.getValue());
                
            }
        });
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
    public void handleInitialize(ActionEvent event){
        previewHeight = demoPane.getHeight();
        ledgeHeight =(Double.parseDouble(iHeightTF.getText()));
        demoLedge.setFitHeight(ledgeHeight);
        targetObject.setTranslateX(objectPosSlider.getValue());
        isInitialized = true;
    }
    public void setLedgeHeight(double lh){
        ledgeHeight = lh;
}
    public void setPreviewHeight(double ph){
        previewHeight = ph;
    }
    public double getLedgeHeight(){
        return ledgeHeight;
    }
    public double getPreviewHeight(){
        return previewHeight;
    }
    public double getPreviewWidth(){
        return previewWidth;
    }
    public void setPreviewWidth(double width){
        previewWidth = width;
    }
    public double getTargetObjectDistance(){
        return targetObject.getTranslateX();
    }
    
    public void handleBack(ActionEvent event) throws IOException{
        System.out.println("Worked!");
        //Creates and loads the Previous FXML Screen
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MenuSceneFXML.fxml"));
        Parent previousScene = loader.load();
        
        
        
        //Displays the previous screen to the user
        Scene scene = new Scene(previousScene);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
            
}