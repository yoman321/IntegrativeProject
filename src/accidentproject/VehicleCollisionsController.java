/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import accidentproject.Classes.VehicleCrash;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static java.lang.System.out;
/**
 *
 * @author luoph
 */
public class VehicleCollisionsController {
    
    //Create FFXML datafields
    @FXML private TextField m1;
    @FXML private TextField m2;
    @FXML private TextField v1;
    @FXML private TextField v2;
    @FXML private TextField a1;
    @FXML private ImageView backgroundImage;
    @FXML private ComboBox cbox1;
    @FXML private Pane pane;
    
    //Create datafields
    private static VehicleCrash car;
    private final String[] startLocations = {"up", "down", "left", "right"};
    ExecutorService executor = Executors.newFixedThreadPool(4);
    
    //Create initialize mesthod
    public void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList(startLocations);
        cbox1.getItems().addAll(items);
    }
    //Create start button\
    public void onclickStart(){
        if (cbox1.getValue().equals("up")){
            car = new VehicleCrash(0, 0, 0, 430, 5, "up", new Rectangle(430, 5, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
            
        }
        if (cbox1.getValue().equals("down")){
//            Rectangle car = new Rectangle(545, 975, 20, 20);
            car = new VehicleCrash(0, 0, 0, 545, 975, "down", new Rectangle(545, 675, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
        }
        if (cbox1.getValue().equals("right")){
//            Rectangle car = new Rectangle(975, 435, 20, 20);
            car = new VehicleCrash(0, 0, 0, 975, 435, "right", new Rectangle(975, 435, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
        }
        if (cbox1.getValue().equals("left")){
//            Rectangle car = new Rectangle(5, 545, 20, 20);
            car = new VehicleCrash(0, 0, 0, 5, 545, "left", new Rectangle(5, 545, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
        }
        
    }
    //Create tasks
    public static class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                while(true){
                    if (car.getStartLocation().equals("up")){
                        out.println("something");//test
                        car.setY(car.getY() + 5);
                        car.getVehicle().setY(car.getY());
                        Thread.sleep(10);
                    }
                }
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}


