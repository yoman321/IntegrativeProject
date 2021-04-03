/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author liamd
 */
public class DemoScreenFXMLController{
    
    
    @FXML private ImageView background;
    @FXML private ImageView ledge;
    @FXML private ImageView fallingObject;
    @FXML private StackPane finalDemoPane;
    private final double FINAL_DEMO_PANE_HEIGHT = 337;
    DemoInputScreenFXMLController input = new DemoInputScreenFXMLController();
    ProjectileMotion motion = new ProjectileMotion();

    public void InitializeData(DemoInputScreenFXMLController inputController){
        input = inputController;
        motion = input.pm;
        ledge.setFitHeight(input.getLedgeHeight() * FINAL_DEMO_PANE_HEIGHT / input.getPreviewHeight());
        fallingObject.setY(FINAL_DEMO_PANE_HEIGHT - ledge.getFitHeight() -11);
    }
    public void handleGo(){
        
        

        Thread demoMotion = new Thread(() -> {
            
            motion.setTime(0);
            double initialHeight = motion.solveForHeight();
            double initialY = fallingObject.getY();
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
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            
            initialHeight = currentHeight;
            initialY = fallingObject.getY();
            motion.setTime(motion.getTime() + 0.01);
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while(fallingObject.getY() < FINAL_DEMO_PANE_HEIGHT - 60){
                
                currentHeight = motion.solveForHeight();
                currentDistance = motion.solveForDistance();
                heightDifference = initialHeight - currentHeight;
                System.out.println(currentHeight);
                fallingObject.setX(currentDistance);
                fallingObject.setY(initialY + heightDifference);
                motion.setTime(motion.getTime() + 0.01);
                
              try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemoScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        demoMotion.start();
                
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