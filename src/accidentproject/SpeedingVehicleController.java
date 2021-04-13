/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import java.util.concurrent.*;
import accidentproject.Classes.SpeedingVehicle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.out;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
/**
 *
 * @author luoph
 */
public class SpeedingVehicleController {
    
    //Create FXML variables
    @FXML private Pane pane;
    
    //Create variables
    ExecutorService executor = Executors.newFixedThreadPool(1);
    private SpeedingVehicle vehicle = new SpeedingVehicle(new Rectangle(305, 500, 40, 60));
   static String currentKey = "stop";
    
    public void initialize(){
        
    }
    public void onclickStart(){
        pane.getChildren().add(vehicle.getVehicle());
        executor.execute(new vehicleMovementTask());
    }
    public class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                vehicle.vehicleMovmentAnimation();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
