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
import javafx.application.Platform;
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
    @FXML private ComboBox cbox2;
    @FXML private Pane pane;
    
    //Create datafields
    private static VehicleCrash car;
    private static VehicleCrash car2;
    private final String[] startLocations = {"up", "down", "left", "right"};
    ExecutorService executor = Executors.newFixedThreadPool(2);
    
    //Create initialize mesthod
    public void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList(startLocations);
        cbox1.getItems().addAll(items);
        cbox2.getItems().addAll(items);
    }
    //Create start button\
    public void onclickStart(){
        if (cbox1.getValue().equals("up") || cbox2.getValue().equals("up")){
            car = new VehicleCrash(0, 0, 0, 430, 5, "up", new Rectangle(430, 5, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            out.println("upCar");//test
//            executor.shutdown();
            
        }
        if (cbox1.getValue().equals("down") || cbox2.getValue().equals("down")){
//            Rectangle car = new Rectangle(545, 975, 20, 20);
            car2 = new VehicleCrash(0, 0, 0, 545, 975, "down", new Rectangle(545, 675, 20, 20));
            pane.getChildren().add(car2.getVehicle());
            executor.execute(new vehicleMovementTask());
            out.println("downCar");//test
//            executor.shutdown();
        }
        if (cbox1.getValue().equals("right") || cbox2.getValue().equals("right")){
//            Rectangle car = new Rectangle(975, 435, 20, 20);
            car = new VehicleCrash(0, 0, 0, 975, 435, "right", new Rectangle(975, 435, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
        }
        if (cbox1.getValue().equals("left") || cbox2.getValue().equals("left")){
//            Rectangle car = new Rectangle(5, 545, 20, 20);
            car = new VehicleCrash(0, 0, 0, 5, 545, "left", new Rectangle(5, 545, 20, 20));
            pane.getChildren().add(car.getVehicle());
            executor.execute(new vehicleMovementTask());
            executor.shutdown();
        }
        
    }
    //Create tasks
    public class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
//                while(true){
//                    if (car.getStartLocation().equals("up")){
//                        Platform.runLater(() -> car.setY(car.getY() + 5));
//                        Platform.runLater(() -> car.getVehicle().setY(car.getY()));
//                        Thread.sleep(10);
//                    }
//                    if (car.getStartLocation().equals("down")){
//                        Platform.runLater(() -> car.setY(car.getY() - 5));
//                        Platform.runLater(() -> car.getVehicle().setY(car.getY()));
//                        Thread.sleep(10);
//                    }
//                    if (car.getStartLocation().equals("left")){
//                        car.setX(car.getX() + 5);
//                        Platform.runLater(() -> car.getVehicle().setX(car.getX()));
//                        Thread.sleep(10);
//                    }
//                    if (car.getStartLocation().equals("right")){
//                        car.setX(car.getX() - 5);
//                        Platform.runLater(() -> car.getVehicle().setX(car.getX()));
//                        Thread.sleep(10);
//                    }
//                }
                  Platform.runLater(() -> car.vehicleAnimation());
                  Platform.runLater(() -> car2.vehicleAnimation());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}


