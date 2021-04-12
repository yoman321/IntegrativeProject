/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author liamd
 */
public class DemoScreenFXMLController implements Initializable{
    
    
    @FXML private ImageView background;
    @FXML private ImageView ledge;
    @FXML private ImageView fallingObject;
    @FXML private StackPane finalDemoPane;
    @FXML private ImageView targetObject;
    @FXML private TextField forceTF;
    @FXML private Button goButton;
    private double targetObjectLayoutX;
    private Image boxChipped = new Image("/resources/Box_Destroyed 2.png");
    DemoInputScreenFXMLController input = new DemoInputScreenFXMLController();
    ProjectileMotion motion = new ProjectileMotion();
    

    public void InitializeData(DemoInputScreenFXMLController inputController){
        input = inputController;
        motion = input.pm;
        ledge.setFitHeight(input.getLedgeHeight() * finalDemoPane.getPrefHeight() / input.getPreviewHeight());
        targetObject.setLayoutX(input.getTargetObjectDistance() * finalDemoPane.getPrefWidth() / input.getPreviewWidth());
        System.out.println(input.getTargetObjectDistance() +"\t\t\t" + finalDemoPane.getPrefWidth() + "\t\t\t" + input.getPreviewHeight());
        fallingObject.setY(finalDemoPane.getPrefHeight() - ledge.getFitHeight() -11);
        targetObjectLayoutX = targetObject.getX();
    }
    public void handleGo(){
        
                motion.setTime(0);
                final double  START_HEIGHT = motion.solveForHeight();
                final double START_Y = fallingObject.getY();
        
                
            Thread demoMotion = new Thread(() -> {
            
                double initialHeight = START_HEIGHT;
                double initialY = START_Y;
                double currentHeight = 0;
                double currentDistance = 0;
                double heightDifference = 0;
        
                //Motion works, but when the motion hits 0 its not on the floor. This could be a ratio thing, so maybe try finding the max height and ratio something. Could also be the difference between the cooridnate systems (like the fact that 0,100 in a regular system would be equivalent to 0,200 in javafx system if the total height is 300
                while(motion.solveForFVelY() > 0){
                
                    currentHeight = motion.solveForHeight();
                    currentDistance = motion.solveForDistance();
                    heightDifference = currentHeight - initialHeight;
                    System.out.println(currentHeight);
                    fallingObject.setX(currentDistance);
                    fallingObject.setY((initialY - heightDifference));
                    motion.setTime(motion.getTime() + 0.01);
                
                try {
                    Thread.sleep(5);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            
                initialHeight = currentHeight;
                motion.setMaxHeight(initialHeight);
                initialY = fallingObject.getY();
                motion.setTime(motion.getTime() + 0.01);
            
                try {
                    Thread.sleep(5);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                while(fallingObject.getY() < finalDemoPane.getPrefHeight() - 60){
                
                    //Need to add an if statement for if the falling object collides with the target object
                    currentHeight = motion.solveForHeight();
                    currentDistance = motion.solveForDistance();
                    heightDifference = initialHeight - currentHeight;
                    System.out.println(fallingObject.getY());
                    fallingObject.setX(currentDistance);
                    fallingObject.setY(initialY + heightDifference);
                
                    if((targetObjectLayoutX <= fallingObject.getX() && fallingObject.getX() < targetObjectLayoutX + targetObject.getFitWidth()) && fallingObject.getY() >= targetObject.getLayoutY() - 35){
                        break;
                    }
                
                    motion.setTime(motion.getTime() + 0.01);
                
                try {
                    Thread.sleep(3);
                }catch (InterruptedException ex) {
                    Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                //Added 0.49 so the integer gets rounded properly (ex: 1.60 + 0.49 = 2.09 so it gets rounded to 2. But, 1.50 + 0.49 = 1.99 which gets rounded to 1)
                forceTF.setText(Double.toString(motion.getForce()));
        });
        demoMotion.start();
        }
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}





/*final double DISTANCE = motion.solveForDistance();
        final double HEIGHT = motion.getIHeight();
        double maxHeight = 0;
        double maxHeightY = 0;
        
        boolean differenceSwitch = false;
        motion.setTime(0);    
        final double INITIAL_Y = fallingObject.getY();
        while(motion.solveForHeight() > 0){
            double currentDistance = motion.solveForDistance();
            double currentHeight = motion.solveForHeight();
            double heightDifference = Math.abs(currentHeight - HEIGHT);
            double maxHeightDifference = Math.abs(maxHeight - currentHeight);
            
            fallingObject.setX(currentDistance);
            if(motion.solveForFVelY() > 0){
                fallingObject.setY((INITIAL_Y - heightDifference));
            }
            else if(differenceSwitch == false){
                differenceSwitch = true;
                maxHeight = currentHeight;
                maxHeightY = fallingObject.getY();
            }
            else{
                fallingObject.setY((maxHeightY + maxHeightDifference));
            } //The project runs now but the only thing is that the path that it follows does not hit the bottom of the pane at the end of the run, so you might need to figure out how to have it do that by taking the final values and doing some ratio stuff to determine by what the object should fall by.
            motion.setTime(motion.getTime() + 0.01);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/