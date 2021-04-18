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
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author luoph
 */
public class SpeedingVehicleController {
    
    public static SpeedingVehicleController speedingInstance;
    
    //Create FXML variables
    @FXML private Pane pane;
    @FXML private ImageView backgroundImage;
    @FXML private ImageView newImage;
    @FXML private Text collisionText;    
    
    //Create variables
    private long time = 40;
    
    //Create executors
    private ExecutorService vehicleExecutor = Executors.newFixedThreadPool(1);
    private ScheduledExecutorService accidentExecutor = Executors.newScheduledThreadPool(3);
    private ExecutorService imageExecutor = Executors.newFixedThreadPool(1);
    private ExecutorService timerExecutor = Executors.newFixedThreadPool(1);
    
    //Create objects
    private SpeedingVehicle vehicle = new SpeedingVehicle(new Rectangle(305, 500, 40, 60));
    private BackgroundImageClass bImage = new BackgroundImageClass();
    private TimerClass timer = new TimerClass();
    private Accident accident = new Accident();
    
    public void initialize(){
        
    }
    public SpeedingVehicleController(){
        speedingInstance = this;
    }
    public void onclickStart(){
        pane.getChildren().add(vehicle.getVehicle());
        vehicleExecutor.execute(new vehicleMovementTask());
        accidentExecutor.scheduleWithFixedDelay(new accidentMovementTask(), 0, getTime() * 20, TimeUnit.MILLISECONDS);
        imageExecutor.execute(new imageMovementTask());
        timerExecutor.execute(new timerTask());
        
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
                accident.accidentMovementAnimation(vehicle, backgroundImage);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private class imageMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                bImage.imageMovementAnimation(backgroundImage, newImage);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private class timerTask implements Runnable{
        @Override
        public void run(){
            try{
                timer.newTime();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public long getTime(){
        return time;
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
        
        FadeTransition ft2 = new FadeTransition(Duration.millis(3000), newImage);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.4);
        ft2.setAutoReverse(false);
        ft2.play();
    }
    //Create background image class
    private class BackgroundImageClass{
        
        public void imageMovementAnimation(ImageView backgroundImage, ImageView newImage){
            try{
                while (!SpeedingVehicleController.speedingInstance.vehicleExceutorIsShutdown()){
                    Platform.runLater(() -> backgroundImage.setY(backgroundImage.getY() + 8));
                    Platform.runLater(() -> newImage.setY(newImage.getY() + 8));
                    out.println(newImage.getY());
                    if (backgroundImage.getY() >= 1000){
                        backgroundImage.setY(-1000);
                    }
                    if (newImage.getY() >= 1000){
                        newImage.setY(-1000);
                    }
                    Thread.sleep(getTime());
                }
            }
            catch (Exception ex){
                ex.getStackTrace();
            }
        }
    }
    private class TimerClass{
        
        public void newTime(){
            try{
                while (time >= 20){
                    time -= 5;
                    Thread.sleep(15000);
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
