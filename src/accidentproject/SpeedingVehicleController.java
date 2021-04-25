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
import javafx.scene.control.Button;
import java.lang.Thread;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author luoph
 */
public class SpeedingVehicleController {
    
    //Create controller instance
    public static SpeedingVehicleController speedingInstance;
    
    //Create FXML variables
    @FXML private Pane pane;
    @FXML private ImageView backgroundImage;
    @FXML private ImageView newImage;
    @FXML private Text collisionText; 
    @FXML private Text offroadText;
    @FXML private Button resetBtn;
    @FXML private Button startBtn;
    @FXML private Button backBtn;
    
    
    //Create variables
    private long time = 40;
    
    //Create executors
    private ExecutorService vehicleExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ScheduledExecutorService accidentExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private ExecutorService imageExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ExecutorService timerExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //Create objects
    private SpeedingVehicle vehicle = new SpeedingVehicle(new ImageView("accidentproject/Ressources/YellowCarBase.png"));
    private BackgroundImageClass bImage = new BackgroundImageClass();
    private TimerClass timer = new TimerClass();
    private Accident accident = new Accident();
    
    public void initialize(){
        resetBtn.setVisible(false);
    }
    public SpeedingVehicleController(){
        speedingInstance = this;
    }
    public void onclickStart(){
        //Create executors
        accidentExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        vehicleExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        imageExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        timerExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        //Run executors
        vehicleExecutor.execute(new vehicleMovementTask());
        accidentExecutor.scheduleWithFixedDelay(new accidentMovementTask(), 0, getTime() * 20, TimeUnit.MILLISECONDS);
        imageExecutor.execute(new imageMovementTask());
        timerExecutor.execute(new timerTask());
        
        //Apply changes to pane
        pane.getChildren().add(vehicle.getVehicle());
        startBtn.setVisible(false);
        backBtn.setVisible(false);
        resetBtn.setVisible(true);
    }
    public void onclickReset(){
        //Reset executors
        accidentExecutor.shutdown();
        vehicleExecutor.shutdown();
        timerExecutor.shutdown();
        imageExecutor.shutdown();
        
        //Reset variables
        collisionText.setOpacity(0);
        offroadText.setOpacity(0);
        time = 40;
        startBtn.setVisible(true);
        backBtn.setVisible(true);
        resetBtn.setVisible(false);
    }
    //Return to menu button
    public void onclickBack(ActionEvent e) throws Exception{
        Parent backPane = FXMLLoader.load(getClass().getResource(("FXML/MenuSceneFXML.fxml")));
        Scene menuScene = new Scene(backPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(menuScene);
        stage.show();
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
    public synchronized void addAccident(ImageView accident){
        Platform.runLater(() -> pane.getChildren().add(accident));
    }
    public void removeAccident(ImageView accident, boolean endgame) throws Exception{
        if (endgame){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        while (accident.getOpacity() > 0){
                            out.println(accident.getOpacity());
                            accident.setOpacity(accident.getOpacity() - 0.055);
                            Thread.sleep(165);
                        }
                        Platform.runLater(() -> pane.getChildren().remove(accident));
                        Thread.currentThread().interrupt();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public void removeVehicle(ImageView vehicle) throws Exception{
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (vehicle.getOpacity() > 0){
                        vehicle.setOpacity(vehicle.getOpacity() - 0.055);
                        Thread.sleep(165);
                    }
                    Platform.runLater(() -> pane.getChildren().remove(vehicle));
                    Thread.currentThread().interrupt();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
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
    public void endgameText(String situation){
        FadeTransition ft = new FadeTransition();
        if (situation.equals("collision")){
            ft = new FadeTransition(Duration.millis(3000), collisionText);
        }
        else if (situation.equals("offroad")){
            ft = new FadeTransition(Duration.millis(3000), offroadText);
        }
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setAutoReverse(false);
        ft.play(); 
    }
    //Create background image class
    private class BackgroundImageClass{
        
        public void imageMovementAnimation(ImageView backgroundImage, ImageView newImage){
            try{
                while (!SpeedingVehicleController.speedingInstance.vehicleExceutorIsShutdown()){
                    Platform.runLater(() -> backgroundImage.setY(backgroundImage.getY() + 8));
                    Platform.runLater(() -> newImage.setY(newImage.getY() + 8));
                    if (backgroundImage.getY() >= 1000){
                        Platform.runLater(() -> backgroundImage.setY(-992));
                    }
                    else if (newImage.getY() >= 1000){
                        Platform.runLater(() -> newImage.setY(-992));
                    }
                    Thread.sleep(getTime());
                }
                if (imageExecutor.isShutdown()){
                    Thread.currentThread().interrupt();
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
                while (time >= 10){
                    time -= 8;
                    Thread.sleep(15000);
                }
                if (vehicleExecutor.isShutdown()){
                    Thread.currentThread().interrupt();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
