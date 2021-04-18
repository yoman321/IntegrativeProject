/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import java.util.concurrent.*;
import accidentproject.Classes.SpeedingVehicle;
import accidentproject.Classes.Accident;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.out;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.image.ImageView;


/**
 *
 * @author luoph
 */
public class SpeedingVehicleController {
    
    public static SpeedingVehicleController speedingInstance;
    
    //Create FXML variables
    @FXML private Pane pane;
    @FXML private ImageView backgroundImage;
    @FXML private Text collisionText;
    
    //Create variables
    private ExecutorService vehicleExecutor = Executors.newFixedThreadPool(1);
    private ScheduledExecutorService accidentExecutor = Executors.newScheduledThreadPool(3);
    private SpeedingVehicle vehicle = new SpeedingVehicle(new Rectangle(305, 500, 40, 60));
    private Accident accident = new Accident();
    
    public void initialize(){
        
    }
    public SpeedingVehicleController(){
        speedingInstance = this;
    }
    public void onclickStart(){
        pane.getChildren().add(vehicle.getVehicle());
        vehicleExecutor.execute(new vehicleMovementTask());
        accidentExecutor.scheduleWithFixedDelay(new accidentMovementTask(), 0, 1, TimeUnit.SECONDS);
    }
    private class vehicleMovementTask implements Runnable{
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
    private class accidentMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                accident.accidentMovementAnimation(vehicle);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void addAccident(Rectangle accident){
        Platform.runLater(() -> pane.getChildren().add(accident));
    }
    public void removeAccident(Rectangle accident) throws Exception{
        FadeTransition ft = new FadeTransition(Duration.millis(3000), accident);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(false);
        ft.play();
        Thread.sleep(3000);
        Platform.runLater(() -> pane.getChildren().remove(accident));
    }
    public void removeVehicle(Rectangle vehicle) throws Exception{
        FadeTransition ft = new FadeTransition(Duration.millis(3000), vehicle);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(false);
        ft.play();
        Thread.sleep(3000);
        Platform.runLater(() -> pane.getChildren().remove(vehicle));
    }
    //Create thread shutdownsmethods
    public void accidentExecutorShutdown(){
        accidentExecutor.shutdown();
    }
    public void vehicleExecutorShutdown(){
        vehicleExecutor.shutdown();
    }
    public boolean vehicleExceutorIsShutdown(){
        return vehicleExecutor.isShutdown();
    }
    public boolean accidentExecutorIsShutdown(){
        return accidentExecutor.isShutdown();
    }
    //Create endgame text 
    public void collisionText(){
        FadeTransition ft = new FadeTransition(Duration.millis(3000), collisionText);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setAutoReverse(false);
        ft.play();
        
        FadeTransition ft1 = new FadeTransition(Duration.millis(3000), backgroundImage);
        ft1.setFromValue(1.0);
        ft1.setToValue(0.4);
        ft1.setAutoReverse(false);
        ft1.play();
    }
    
}
