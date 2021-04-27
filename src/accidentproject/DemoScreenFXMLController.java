/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import accidentproject.DemoInputScreenFXMLController;
import accidentproject.Classes.ProjectileMotion;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author liamd
 */
public class DemoScreenFXMLController{
    
    
    @FXML private ImageView ledge;
    @FXML private ImageView fallingObject;
    @FXML private StackPane finalDemoPane;
    @FXML private ImageView targetObject;
    @FXML private TextArea impactForceTA;
    @FXML private TextArea distanceTA;
    @FXML private Button goButton;
    private double targetObjectLayoutX;
    private boolean collision = false;
    private final Image boxChipped = new Image("accidentproject/Ressources/Box_Destroyed 1.png");
    private final Image boxDestroyed = new Image("accidentproject/Ressources/Box_Destroyed 2.png");
    private DemoInputScreenFXMLController input = new DemoInputScreenFXMLController();
    private ProjectileMotion motion = new ProjectileMotion();
    

    public void InitializeData(DemoInputScreenFXMLController inputController){
        
        //Retrieving data from the demo input screen
        input = inputController;
        motion = input.pm;
        
        //Sets the scene from the demo input screen with proper ratio
        ledge.setFitHeight(input.getLedgeHeight() * finalDemoPane.getPrefHeight() / input.getPreviewHeight());
        targetObject.setLayoutX(input.getTargetObjectDistance() * finalDemoPane.getPrefWidth() / input.getPreviewWidth());
        fallingObject.setY(finalDemoPane.getPrefHeight() - ledge.getFitHeight() -11);
        
        //Creating a variable that is equal to the targetObjects's x position to avoid a bug
        targetObjectLayoutX = targetObject.getLayoutX();
        
        //Disables the go button after the motion has started
        goButton.setDisable(false);
        motion.setMass(0.59);
    }
    
    //Handles the motion of the falling object once the GO button is clicked
    @FXML
    public void handleGo(){
        
            //Disables the go button
            goButton.setDisable(true);
        
            //Setting the motion at (0,0)
            motion.setTime(0);
            final double  START_HEIGHT = motion.solveForHeight();
            final double START_Y = fallingObject.getY();
            
            
            
            //Thread that controls the motion of the falling object
            Thread demoMotion = new Thread(() -> {
                
                //Creating the format for the displayed values
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
            
                //Setting the initial values of the variables
                double initialHeight = START_HEIGHT;
                double initialY = START_Y;
                double currentHeight = 0;
                double currentDistance = 0;
                double heightDifference = 0;
                
                
                
                //Loop for the motion up until the object reaches it's highest height
                while(motion.solveForFVelY() > 0){
                
                    //Retrieves the position of the object at a specific point in time
                    currentHeight = motion.solveForHeight();
                    currentDistance = motion.solveForDistance();
                    
                    //Setting the falling object to it's position at that specific point in time
                    heightDifference = currentHeight - initialHeight;
                    fallingObject.setX(currentDistance);
                    fallingObject.setY((initialY - heightDifference));
                    
                    //Increasing the time by 0.01 seconds for the next iteration
                    motion.setTime(motion.getTime() + 0.01);
                
                try {
                    Thread.sleep(4);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            
                //Code that deals with the object at its max height
                
                //Setting the initial height to the max height for the calculation of the height difference
                initialHeight = currentHeight;
                
                //Setting the maxHeight for the instance of ProjectileMotion so as to be able to calculate the force on impact
                motion.setMaxHeight(initialHeight);
                
                //Setting the initial Y to be able to calculate the current Y position of the falling object
                initialY = fallingObject.getY();
                
                //Increasing the time by 0.01 seconds
                motion.setTime(motion.getTime() + 0.01);
            
                try {
                    Thread.sleep(4);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                
                //Loop that deals with the motion after the falling object reaches its max height
                while(fallingObject.getY() < finalDemoPane.getPrefHeight() - 60){
                    
                    //Retrieves the position of the object at a specific point in time
                    currentHeight = motion.solveForHeight();
                    currentDistance = motion.solveForDistance();
                    
                    //Setting the falling object to it's position at that specific point in time
                    heightDifference = initialHeight - currentHeight;
                    fallingObject.setX(currentDistance);
                    fallingObject.setY(initialY + heightDifference);
                
                    //If statement that controls the collision with the falling object and the target object
                    if((targetObjectLayoutX <= fallingObject.getX() && fallingObject.getX() < targetObjectLayoutX + targetObject.getFitWidth()) && fallingObject.getY() >= targetObject.getLayoutY() - 35){
                        collision = true;
                        break;
                    }
                
                    //Increasing the time by 0.01 seconds for the next iteration
                    motion.setTime(motion.getTime() + 0.01);
                
                try {
                    Thread.sleep(4);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
                
                
                //If the objects collide, this statement is true
                if(collision == true){
                    
                    //If and else if statement to set the damage done to the target object
                    if(START_HEIGHT >= 85 && START_HEIGHT < 180){
                        targetObject.setImage(boxChipped);
                    }
                    else if(START_HEIGHT >= 180){
                        targetObject.setImage(boxDestroyed);
                    }
                
                }

                //Sets the impact force exerted on the target object or floor from the falling object into the text area
                impactForceTA.setText(decimalFormat.format(motion.getForce()) + "N");
                
                //Sets the distance of the falling object into the text area
                distanceTA.setText(decimalFormat.format(motion.solveForDistance()) + "m");
        });
        demoMotion.start();
        
        

        }

    //Handles the code for the "Back" button
    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        
        //Creates and loads the Previous FXML Screen
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/DemoInputScreen.fxml"));
        Parent previousScene = loader.load();
        
        //Displays the previous screen to the user
        Scene scene = new Scene(previousScene);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

