/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import static java.lang.System.out;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javafx.scene.paint.Color;
import accidentproject.SpeedingVehicleController;
import accidentproject.Classes.SpeedingVehicle;
import javafx.scene.image.ImageView;
/**
 *
 * @author luoph
 */
public class Accident {

    public void accidentMovementAnimation(SpeedingVehicle vehicle, ImageView backgroundImage){
        
        //Set values for accident
        long time = 40;
        Rectangle accident = new Rectangle();
        accident.setHeight(70);
        accident.setWidth(50);
        accident.setY(-60);
        accident.setFill(Color.BLUE);
        
        //Create lane variable
        int lane = (int)(Math.random() * 3);
               
        //Randomize lane
        if (lane == 0){
            accident.setX(175);
        }
        else if (lane == 1){
            accident.setX(295);
        }
        else if (lane == 2){
            accident.setX(418);
        }
        
        //Add accident to pane        
        SpeedingVehicleController.speedingInstance.addAccident(accident);
        
        //Create accident movement
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (!accident.intersects(vehicle.getVehicle().getBoundsInLocal())){
                        if (SpeedingVehicleController.speedingInstance.accidentExecutorIsShutdown()){
                            break;
                        }
                        Platform.runLater(() -> accident.setY(accident.getY() + 8));
                        Thread.sleep(SpeedingVehicleController.speedingInstance.getTime());
                    }
                    SpeedingVehicleController.speedingInstance.accidentExecutorShutdown();
                    SpeedingVehicleController.speedingInstance.vehicleExecutorShutdown();
                    SpeedingVehicleController.speedingInstance.collisionText();
                    SpeedingVehicleController.speedingInstance.removeAccident(accident);
                    
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
        });
        thread.start();
    } 
//    //Create method for distance between accidents and vehicle
//    public boolean distance(double[] upLeftCorner, double[] upRightCorner, double[] bottomLeftCorner, double[] bottomRightCorner, double[] accidentCorners){
//        //Check distance between all corners
//        for (int i=0, j=i+1; i<accidentCorners.length; i+=2, j+=2){
//            if (Math.sqrt(Math.pow(upLeftCorner[0] - accidentCorners[i], 2) + Math.pow(upLeftCorner[1] - accidentCorners[j], 2)) < 10 || 
//                    Math.sqrt(Math.pow(upRightCorner[0] - accidentCorners[i], 2) + Math.pow(upRightCorner[1] - accidentCorners[j], 2)) < 10 ||
//                        Math.sqrt(Math.pow(bottomLeftCorner[0] - accidentCorners[i], 2) + Math.pow(bottomLeftCorner[1] - accidentCorners[j], 2)) < 10 ||
//                            Math.sqrt(Math.pow(bottomRightCorner[0] - accidentCorners[i], 2) + Math.pow(bottomRightCorner[1] - accidentCorners[j], 2)) < 10){
//                return true;
//            }
//        }
//        return false;
//    }
}
