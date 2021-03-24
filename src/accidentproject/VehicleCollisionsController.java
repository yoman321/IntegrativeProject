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
    private static VehicleCrash car;
    private static VehicleCrash car2;
    private final String[] startLocations = {"up", "down", "left", "right"};
    ExecutorService executor = Executors.newFixedThreadPool(2);
    private ArrayList<VehicleCrash> vehicleArray = new ArrayList<>();
    Alert alert = new Alert(AlertType.WARNING);
    private TextField[] massArray;
    private TextField[] velocityArray;
    private TextField[] accelerationArray;
    private ComboBox[] directionArray;
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
    public void onclickStart(){
        int count = 1;
//        for (int i=1; i<vehicleValues.length-2; i++){
//            for (int j=1; j<vehicleValues[i].length; j++){
//                if (count % Integer.valueOf(nbreVehicle.getText()) == 0){
//                    out.println();
//                    count++;
//                }
//                else 
//                    count++;
//                    out.print((TextField)vehicleValues[i][j].getText()+" ");
//            }
//        }
//    public void onclickStart(){
//        if (cbox1.getValue().equals("up") || cbox2.getValue().equals("up")){
//            car = new VehicleCrash(0, 0, 0, 430, 5, "up", new Rectangle(430, 5, 20, 20));
//            pane.getChildren().add(car.getVehicle());
//            executor.execute(new vehicleMovementTask());
//            out.println("upCar");//test
////            executor.shutdown();
//            
//        }
//        if (cbox1.getValue().equals("down") || cbox2.getValue().equals("down")){
////            Rectangle car = new Rectangle(545, 975, 20, 20);
//            car2 = new VehicleCrash(0, 0, 0, 545, 975, "down", new Rectangle(545, 675, 20, 20));
//            pane.getChildren().add(car2.getVehicle());
//            executor.execute(new vehicleMovementTask());
//            out.println("downCar");//test
////            executor.shutdown();
//        }
//        if (cbox1.getValue().equals("right") || cbox2.getValue().equals("right")){
////            Rectangle car = new Rectangle(975, 435, 20, 20);
//            car = new VehicleCrash(0, 0, 0, 975, 435, "right", new Rectangle(975, 435, 20, 20));
//            pane.getChildren().add(car.getVehicle());
//            executor.execute(new vehicleMovementTask());
//            executor.shutdown();
//        }
//        if (cbox1.getValue().equals("left") || cbox2.getValue().equals("left")){
////            Rectangle car = new Rectangle(5, 545, 20, 20);
//            car = new VehicleCrash(0, 0, 0, 5, 545, "left", new Rectangle(5, 545, 20, 20));
//            pane.getChildren().add(car.getVehicle());
//            executor.execute(new vehicleMovementTask());
//            executor.shutdown();
//        }
//        
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


