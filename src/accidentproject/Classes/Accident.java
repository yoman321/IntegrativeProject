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
import java.lang.Thread;
/**
 *
 * @author luoph
 */
public class Accident {

    public void accidentMovementAnimation(SpeedingVehicle vehicle, ImageView backgroundImage){
        
        //Create images for accidents
        ImageView[] accident = {new ImageView("accidentproject/Ressources/BlueCarBase.png"), 
            new ImageView("accidentproject/Ressources/PinkCarBase.png"), new ImageView("accidentproject/Ressources/RedCarBase.png")};
        
        //Create lane variable
        int lane = (int)(Math.random() * 3);
        int cars = (int)(Math.random() * 3);
              
        //Randomize lane
        if (lane == 0){
            accident[cars].setX(175);
        }
        else if (lane == 1){
            accident[cars].setX(295);
        }
        else if (lane == 2){
            accident[cars].setX(418);
        }
        //Set accident values
        accident[cars].setY(-60);
        accident[cars].setFitHeight(90);
        accident[cars].setFitWidth(50);
        
        //Add accident to pane        
        SpeedingVehicleController.speedingInstance.addAccident(accident[cars]);
        
        //Create accident movement
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (!accident[cars].intersects(vehicle.getVehicle().getBoundsInLocal()) || accident[cars].getY() > 1050){
                        if (SpeedingVehicleController.speedingInstance.accidentExecutorIsShutdown() || 
                                SpeedingVehicleController.speedingInstance.vehicleExceutorIsShutdown()){
                            break;
                        }
                        Platform.runLater(() -> accident[cars].setY(accident[cars].getY() + 8));
                        Thread.sleep(SpeedingVehicleController.speedingInstance.getTime());
                    }
                    if (accident[cars].getY() > 1050){
                        SpeedingVehicleController.speedingInstance.removeAccident(accident[cars], false);
                    }
                    if (accident[cars].intersects(vehicle.getVehicle().getBoundsInLocal()) || 
                            SpeedingVehicleController.speedingInstance.accidentExecutorIsShutdown() || 
                                SpeedingVehicleController.speedingInstance.vehicleExceutorIsShutdown()){
                        Platform.runLater(() -> SpeedingVehicleController.speedingInstance.accidentExecutorShutdown());
                        Platform.runLater(() -> SpeedingVehicleController.speedingInstance.vehicleExecutorShutdown());
                        SpeedingVehicleController.speedingInstance.removeAccident(accident[cars], true);
                        
                        if (accident[cars].intersects(vehicle.getVehicle().getBoundsInLocal())){
                            Platform.runLater(() -> SpeedingVehicleController.speedingInstance.endgameText("collision"));
                        }
                    }
                    Thread.currentThread().interrupt();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
        });
        thread.start();
    } 

}
