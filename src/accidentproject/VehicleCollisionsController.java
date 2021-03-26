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
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.text.Text;
/**
 *
 * @author luoph
 */
public class VehicleCollisionsController {
    
    //Create FFXML datafields
    @FXML private ComboBox cbox1;
    @FXML private ComboBox cbox2;
    @FXML private Pane pane;
    @FXML private TextField nbreVehicle;
    @FXML private GridPane variableGrid;
    @FXML private GridPane nbreVehicleGrid;
    @FXML private GridPane massGrid;
    @FXML private GridPane velocityGrid;
    @FXML private GridPane accelerationGrid;
    @FXML private GridPane directionGrid;
    @FXML private Button nbreVehicleBtn;
    @FXML private ImageView backgroundImage;
    @FXML private Text nbreVehicleText;
    
    
    //Create datafields
    private final String[] startLocations = {"up", "down", "left", "right"};
    ExecutorService executor = Executors.newFixedThreadPool(8);
    private ArrayList<VehicleCrash> vehicleArray = new ArrayList<>();
    Alert alert = new Alert(AlertType.WARNING);
    private TextField[] massArray;
    private TextField[] velocityArray;
    private TextField[] accelerationArray;
    private ComboBox[] directionArray;
//    private ArrayList<VehicleCrash> vehicles = new ArrayList<>();
    private VehicleCrash[] vehicles;
    private ObservableList<String> items = FXCollections.observableArrayList(startLocations);

    //Create initialize mesthod
    public void initialize(){
        variableGrid.setVisible(false);
        nbreVehicleGrid.setVisible(false);
        massGrid.setVisible(false);
        velocityGrid.setVisible(false);
        accelerationGrid.setVisible(false);
        directionGrid.setVisible(false);
        backgroundImage.setOpacity(0.4);
    }
    //Check for numbers of vehicle
    public void onclickNbreVehicles(){
        if (Integer.valueOf(nbreVehicle.getText()) > 8){
            alert.setContentText("There is more than 8 vehicles.");
            alert.show();
        }
        else if (Integer.valueOf(nbreVehicle.getText()) < 1){
            alert.setContentText("There is no vehicle in this simulation.");
            alert.show();
        }
        else {
            variableGrid.setVisible(true);
            nbreVehicleGrid.setVisible(true);
            massGrid.setVisible(true);
            velocityGrid.setVisible(true);
            accelerationGrid.setVisible(true);
            directionGrid.setVisible(true);
            nbreVehicleBtn.setVisible(false);
            nbreVehicle.setVisible(false);
            nbreVehicleText.setVisible(false);
            
            //Create grids and objects
            massArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            velocityArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            accelerationArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            directionArray = new ComboBox[Integer.valueOf(nbreVehicle.getText())];
            vehicles = new VehicleCrash[Integer.valueOf(nbreVehicle.getText())];   
            
            for (int i=0; i<Integer.valueOf(nbreVehicle.getText()); i++){
                massArray[i] = new TextField();
                velocityArray[i] = new TextField();
                accelerationArray[i] = new TextField();
                directionArray[i] = new ComboBox();
                directionArray[i].getItems().addAll(items);
                
                nbreVehicleGrid.add(new Text("Vehicle "+(i+1)), i, 0);
                massGrid.add(massArray[i], i, 0);
                velocityGrid.add(velocityArray[i], i, 0);
                accelerationGrid.add(accelerationArray[i], i, 0);
                directionGrid.add(directionArray[i], i, 0);
                
            }
        }
    }
    //Start simulation
    public void onclickStart(){
        for (int i=0; i<directionArray.length; i++){
            if (directionArray[i].getValue().equals("up")){
                // x - 430
//                vehicles.add(new VehicleCrash(0, 0, 0, 545, 5, "up", new Rectangle(545, 5, 20, 20)));
//                pane.getChildren().add(vehicles.get(i).getVehicle());
                vehicles[i] = new VehicleCrash(0, 0, 0, 545, 5, "up", new Rectangle(545, 5, 20, 20));
                pane.getChildren().add(vehicles[i].getVehicle());
            }
            if (directionArray[i].getValue().equals("down")){
//                vehicles.add(new VehicleCrash(0, 0, 0, 545, 975, "down", new Rectangle(545, 675, 20, 20)));
//                pane.getChildren().add(vehicles.get(i).getVehicle());
                vehicles[i] = new VehicleCrash(0, 0, 0, 545, 975, "down", new Rectangle(545, 675, 20, 20));
                pane.getChildren().add(vehicles[i].getVehicle());
       
            }
            if (directionArray[i].getValue().equals("right")){
//                vehicles.add(new VehicleCrash(0, 0, 0, 975, 435, "right", new Rectangle(975, 435, 20, 20)));
//                pane.getChildren().add(vehicles.get(i).getVehicle());
                vehicles[i] = new VehicleCrash(0, 0, 0, 975, 435, "right", new Rectangle(975, 435, 20, 20));
                pane.getChildren().add(vehicles[i].getVehicle());

            }
            if (directionArray[i].getValue().equals("left")){
//                vehicles.add(new VehicleCrash(0, 0, 0, 5, 545, "left", new Rectangle(5, 545, 20, 20)));
//                pane.getChildren().add(vehicles.get(i).getVehicle());
                vehicles[i] = new VehicleCrash(0, 0, 0, 5, 545, "left", new Rectangle(5, 545, 20, 20));
                pane.getChildren().add(vehicles[i].getVehicle());
            }
        }
        executor.execute(new vehicleMovementTask());
    }
    //Create tasks
    public class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
//                for (int i=0; i<vehicles.size(); i++){
//                    vehicles.get(i).vehicleAnimation(vehicles, vehicles.get(i));
//                }
                for (int i=0; i<vehicles.length; i++){
                    vehicles[i].vehicleAnimation(vehicles, vehicles[i]);
                }
                 
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}


