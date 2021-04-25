/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import accidentproject.Classes.Physics;

/**
 *
 * @author Liam
 */
public class FXMLController{
    
    @FXML private Circle circle;
    @FXML private TextField tfVel, tfHeight, tfDistance;
    @FXML private Pane pane;
    
    @FXML public void onClickGo(ActionEvent event){
        Physics test = new Physics();
        test.setIVelX(Double.parseDouble(tfVel.getText()));
        test.setIHeight(Double.parseDouble(tfHeight.getText()));
        test.setIDistance(0);
        test.setIVelY(0);
        test.setFDistance(Double.parseDouble(tfDistance.getText()));
        double totalDistance = test.getFDistance();
        double initialX = circle.getLayoutX();
        double initialY = circle.getLayoutY();
        test.setTime(0);
        
        Thread thread = new Thread(() ->{
        circle.setLayoutY((-test.solveForY() + test.getIHeight()) * (pane.getHeight() / test.getIHeight()));
        circle.setLayoutX((test.solveForX() - test.getIDistance()) * (pane.getWidth() / test.getFDistance()));
        
        test.setTime(test.getTime() + 0.01);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        while(test.getFHeight() >= 0){
            circle.setLayoutY((-test.solveForY() + test.getIHeight()) * (pane.getHeight() / test.getIHeight()));
            circle.setLayoutX(test.solveForX() * (pane.getWidth() / totalDistance));
            test.setTime(test.getTime() + 0.01);
              try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
    });
        thread.start();
        
}
}