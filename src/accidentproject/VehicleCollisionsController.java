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
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;

/**
 *
 * @author luoph
 */
public class VehicleCollisionsController {
    
    //Create controller instance
    public static VehicleCollisionsController controllerInstance;
    
    //Create FFXML datafields
    @FXML private Pane pane;
    @FXML private TextField nbreVehicle;
    @FXML private GridPane variableGrid;
    @FXML private GridPane nbreVehicleGrid;
    @FXML private GridPane massGrid;
    @FXML private GridPane velocityGrid;
    @FXML private GridPane accelerationGrid;
    @FXML private GridPane sLocationGrid;
    @FXML private GridPane sPositionGrid;
    @FXML private GridPane trafficLightGrid;
    @FXML private Button nbreVehicleBtn;
    @FXML private ImageView backgroundImage;
    @FXML private Text nbreVehicleText;
    @FXML private CheckBox drunkChk;
    @FXML private Button startBtn;
    @FXML private Button resetBtn;
    @FXML private Button backNbreVehicleBtn;
    @FXML private Button backMenuBtn;
    @FXML private Button instructionsBtn;
    @FXML private Text collisionText;
    @FXML private Text drunkNoCollisionText;
    @FXML private Text noCollisionText;
    @FXML private Text crashValuesText;
    
    //right light (575,576)
    //left light (355, 274)
    //Create executor
    ExecutorService executor = Executors.newFixedThreadPool(1);
    
    //Create alert
    Alert alert = new Alert(AlertType.WARNING);
    
    //Create variable
    boolean clickReset = false;
    int runCount = 0;
    
    //Create combo box
    private final String[] startLocations = {"up", "down"};
    private final String[] startPositions = {"back", "center"};
    private final String[] trafficLighString = {"red", "green"};
    private ComboBox[] sLocationArray;
    private ComboBox[] sPositionArray;
    private ComboBox[] trafficLightArray;
    private ObservableList<String> sLocationItems = FXCollections.observableArrayList(startLocations);
    private ObservableList<String> sPositionItems = FXCollections.observableArrayList(startPositions);
    
    //Create imageviews
    private ObservableList<String> trafficLightItems = FXCollections.observableArrayList(trafficLighString);
    
    //Create arrays
//    private ArrayList<VehicleCrash> vehicleArray = new ArrayList<>();
    private TextField[] massArray;
    private TextField[] velocityArray;
    private TextField[] accelerationArray;
    private VehicleCrash[] vehicles;
    
    //Enable use of controller from classes
    public VehicleCollisionsController(){
        controllerInstance = this;
    }
    //Create initialize mesthod
    public void initialize(){
        //Grid visible
        variableGrid.setVisible(false);
        nbreVehicleGrid.setVisible(false);
        massGrid.setVisible(false);
        velocityGrid.setVisible(false);
        accelerationGrid.setVisible(false);
        sLocationGrid.setVisible(false);
        sPositionGrid.setVisible(false);
        trafficLightGrid.setVisible(false);
        crashValuesText.setVisible(false);
        
        //Button visible
        startBtn.setVisible(false);
        resetBtn.setVisible(false);
        backNbreVehicleBtn.setVisible(false);
        backMenuBtn.setVisible(false);
        instructionsBtn.setVisible(true);
        
        //Background opacity
        backgroundImage.setOpacity(0.4);
        
    }
    //Check for numbers of vehicle
    public void onclickNbreVehicles(){
        if (nbreVehicle.getText().isEmpty()){
            alert.setContentText("No value has been inputed.");
            alert.show();
        }
        else if (Integer.valueOf(nbreVehicle.getText()) > 2){
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
            sLocationGrid.setVisible(true);
            sPositionGrid.setVisible(true);
            trafficLightGrid.setVisible(true);
            startBtn.setVisible(true);
            backNbreVehicleBtn.setVisible(true);
            backMenuBtn.setVisible(true);
            instructionsBtn.setVisible(false);
            if (Integer.valueOf(nbreVehicle.getText()) < 2){
                drunkChk.setVisible(false);
            }
            else 
                drunkChk.setVisible(true);
            //Clear unuse nodes in grids
            nbreVehicleGrid.getChildren().clear();
            massGrid.getChildren().clear();
            velocityGrid.getChildren().clear();
            accelerationGrid.getChildren().clear();
            sLocationGrid.getChildren().clear();
            sPositionGrid.getChildren().clear();
            trafficLightGrid.getChildren().clear();
            
            
            //Set button visibility
            nbreVehicleBtn.setVisible(false);
            nbreVehicle.setVisible(false);
            nbreVehicleText.setVisible(false);
            
            //Create grids and objects
            massArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            velocityArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            accelerationArray = new TextField[Integer.valueOf(nbreVehicle.getText())];
            sLocationArray = new ComboBox[Integer.valueOf(nbreVehicle.getText())];
            sPositionArray = new ComboBox[Integer.valueOf(nbreVehicle.getText())];
            trafficLightArray = new ComboBox[Integer.valueOf(nbreVehicle.getText())];
            vehicles = new VehicleCrash[Integer.valueOf(nbreVehicle.getText())];   
            
            for (int i=0; i<Integer.valueOf(nbreVehicle.getText()); i++){
                massArray[i] = new TextField();
                velocityArray[i] = new TextField();
                accelerationArray[i] = new TextField();
                sLocationArray[i] = new ComboBox();
                sLocationArray[i].getItems().addAll(sLocationItems);
                sPositionArray[i] = new ComboBox();
                sPositionArray[i].getItems().addAll(sPositionItems);
                trafficLightArray[i] = new ComboBox();
                trafficLightArray[i].getItems().addAll(trafficLightItems);
                
                nbreVehicleGrid.add(new Text("Vehicle "+(i+1)), i, 0);
                massGrid.add(massArray[i], i, 0);
                velocityGrid.add(velocityArray[i], i, 0);
                accelerationGrid.add(accelerationArray[i], i, 0);
                sLocationGrid.add(sLocationArray[i], i, 0);
                sPositionGrid.add(sPositionArray[i], i, 0);
                trafficLightGrid.add(trafficLightArray[i], i, 0);
                
                //Add placeholders
                massArray[i].setPromptText("kg");
                velocityArray[i].setPromptText("km/h");
                accelerationArray[i].setPromptText("m/s^2");
            }
        }
    }
    //Start simulation
    public void onclickStart(){
        //check reset set to false
        clickReset = false;
        
        //Check if values are correct
        int startUp = 0;
        int startDown = 0;
        boolean correctValues = true;
        for (int i=0; i<vehicles.length; i++){
            if (massArray[i].getText().isEmpty() || velocityArray[i].getText().isEmpty() || accelerationArray[i].getText().isEmpty()){
                alert.setContentText("Error: Vehicle Mass");
                alert.show();
                correctValues = false;
            } 
            else if (Double.valueOf(massArray[i].getText()) < 700 || Double.valueOf(massArray[i].getText()) > 2000){
                alert.setContentText("Error: Vehicle Mass");
                alert.show();
                correctValues = false;
            }
            else if (Double.valueOf(velocityArray[i].getText()) <= 0 ||Double.valueOf(velocityArray[i].getText()) > 250){
                alert.setContentText("Error: Vehicle velocity");
                alert.show();
                 correctValues = false;
            }
            else if (Double.valueOf(accelerationArray[i].getText()) < 0 || Double.valueOf(accelerationArray[i].getText()) > 6.5){
                alert.setContentText("Error: Vehicle Acceleration");
                alert.show();
                correctValues = false;
            }
            else if (sLocationArray[i].getSelectionModel().isEmpty()){
                alert.setContentText("Error: Vehicle Start Location");
                alert.show();
                correctValues = false;            
            }
            else if (sPositionArray[i].getSelectionModel().isEmpty()){
                alert.setContentText("Error: Vehicle Position");
                alert.show();
                correctValues = false;
            }
            else if (trafficLightArray[i].getSelectionModel().isEmpty()){
                alert.setContentText("Error: Traffic Lights");
                alert.show();
                correctValues = false;
            }
            else if (sLocationArray[i].getValue().equals("up") || sLocationArray[i].getValue().equals("down")){
                out.println("hello");
                if (sLocationArray[i].getValue().equals("up")){
                    startUp++;
                }
                if (sLocationArray[i].getValue().equals("down")){
                    startDown++;
                }
                if (startUp >= 2 || startDown >= 2){
                    alert.setContentText("Error: Same Start Location");
                    alert.show();
                    correctValues = false;
                }
            }
        }
        //Add values from textfield to arrays
        ImageView trafficLight = new ImageView();
        if (correctValues){
            runCount++;
            for (int i=0; i<vehicles.length; i++){
                if (sLocationArray[i].getValue().equals("up") && sPositionArray[i].getValue().equals("back")){
                // x - 430
                    if (trafficLightArray[i].getValue().equals("red")){
                        trafficLight = new ImageView("accidentProject/Ressources/RedLight.png");
                        vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                            430, 5, i,false, "up", "back", "red", new ImageView("accidentproject/Ressources/BlueCarReverse.png"));
                    } 
                    else if (trafficLightArray[i].getValue().equals("green")){
                        trafficLight = new ImageView("accidentProject/Ressources/GreenLight.png");
                        vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                             430, 5, i, false, "up", "back", "green", new ImageView("accidentproject/Ressources/BlueCarReverse.png"));
                    }
                    //Set traffic light values
                    trafficLight.setX(372);
                    trafficLight.setY(331);
                    trafficLight.setFitWidth(53);
                    trafficLight.setFitHeight(95);
                    pane.getChildren().add(trafficLight);
                
                    //Set imageView values
                    vehicles[i].getVehicle().setX(vehicles[i].getX());
                    vehicles[i].getVehicle().setY(vehicles[i].getY());
                    vehicles[i].getVehicle().setFitHeight(40);
                    vehicles[i].getVehicle().setFitWidth(20);
                    pane.getChildren().add(vehicles[i].getVehicle());
                }
                else if (sLocationArray[i].getValue().equals("up") && sPositionArray[i].getValue().equals("center")){
                    if (trafficLightArray[i].getValue().equals("red")){
                        trafficLight = new ImageView("accidentProject/Ressources/RedLight.png");
                        vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                            430, 385, i, false, "up", "center", "red", new ImageView("accidentproject/Ressources/BlueCarReverse.png"));
                    
                    }
                    else if (trafficLightArray[i].getValue().equals("green")){
                        trafficLight = new ImageView("accidentProject/Ressources/GreenLight.png");
                        vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                            430, 385, i, false, "up", "center", "green", new ImageView("accidentproject/Ressources/BlueCarReverse.png"));
                    }
                    trafficLight.setX(372);
                    trafficLight.setY(331);
                    trafficLight.setFitWidth(53);
                    trafficLight.setFitHeight(95);
                    pane.getChildren().add(trafficLight);
                
                    vehicles[i].getVehicle().setX(vehicles[i].getX());
                    vehicles[i].getVehicle().setY(vehicles[i].getY());
                    vehicles[i].getVehicle().setFitHeight(40);
                    vehicles[i].getVehicle().setFitWidth(20);
                    pane.getChildren().add(vehicles[i].getVehicle());
                }
                else if (sLocationArray[i].getValue().equals("down") && sPositionArray[i].getValue().equals("back")){
                    if (trafficLightArray[i].getValue().equals("red")){
                        trafficLight = new ImageView("accidentProject/Ressources/RedLight.png");
                        if (drunkChk.isSelected()){
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                                545, 975, i, true, "down", "back", "red", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                        else {
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                                545, 975, i, false, "down", "back", "red", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                    }   
                    else if (trafficLightArray[i].getValue().equals("green")){
                        trafficLight = new ImageView("accidentProject/Ressources/GreenLight.png");
                        if (drunkChk.isSelected()){
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                                 545, 975, i, true, "down", "back", "green", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                        else {
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                                 545, 975, i, false, "down", "back", "green", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }

                    }
                    trafficLight.setX(574);
                    trafficLight.setY(574);
                    trafficLight.setFitWidth(53);
                    trafficLight.setFitHeight(95);
                    pane.getChildren().add(trafficLight);
                
                    vehicles[i].getVehicle().setX(vehicles[i].getX());
                    vehicles[i].getVehicle().setY(vehicles[i].getY());
                    vehicles[i].getVehicle().setFitHeight(40);
                    vehicles[i].getVehicle().setFitWidth(20);
                    pane.getChildren().add(vehicles[i].getVehicle());
       
                }
                else if (sLocationArray[i].getValue().equals("down") && sPositionArray[i].getValue().equals("center")){
                    if (trafficLightArray[i].getValue().equals("red")){
                        trafficLight = new ImageView("accidentProject/Ressources/RedLight.png");
                        if (drunkChk.isSelected()){
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                             545, 585, i, true, "down", "center", "red", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                        else{
                            trafficLight = new ImageView("accidentProject/Ressources/RedLight.png");
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                             545, 585, i, false, "down", "center", "red", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                    }
                    else if (trafficLightArray[i].getValue().equals("green")){
                        trafficLight = new ImageView("accidentProject/Ressources/GreenLight.png");
                        if (drunkChk.isSelected()){
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                             545, 585, i, true, "down", "center", "green", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                        else {
                            vehicles[i] = new VehicleCrash(Double.valueOf(massArray[i].getText()), Double.valueOf(velocityArray[i].getText()), Double.valueOf(accelerationArray[i].getText()), 
                             545, 585, i, false, "down", "center", "green", new ImageView("accidentproject/Ressources/CollisionYellowCarBase.png"));
                        }
                
                    }
                    trafficLight.setX(574);
                    trafficLight.setY(574);
                    trafficLight.setFitWidth(53);
                    trafficLight.setFitHeight(95);
                    pane.getChildren().add(trafficLight);
                
                    vehicles[i].getVehicle().setX(vehicles[i].getX());
                    vehicles[i].getVehicle().setY(vehicles[i].getY());
                    vehicles[i].getVehicle().setFitHeight(40);
                    vehicles[i].getVehicle().setFitWidth(20);
                    pane.getChildren().add(vehicles[i].getVehicle());
                }
            }
        }
        if (correctValues){
            executor.execute(new vehicleMovementTask());
            
            //initialize properties on click
            backgroundImage.setOpacity(1);
            startBtn.setVisible(false);
            resetBtn.setVisible(true);
            backNbreVehicleBtn.setVisible(false);
            backMenuBtn.setVisible(false);
        }
    }
    //Reset the simulation
    public void onclickReset(){
        
        //Reset values in textfields
        for (int i=0; i<Integer.valueOf(nbreVehicle.getText()); i++){
            massArray[i].setText("");
            velocityArray[i].setText("");
            accelerationArray[i].setText("");
            
        }
        //Make click reset true
        clickReset = true;
        
        //Reset properties
        startBtn.setVisible(true);
        resetBtn.setVisible(false);
        backNbreVehicleBtn.setVisible(true);
        backMenuBtn.setVisible(true);
        
//        //Reset vehicles and text
//        for (int i=0; i<vehicles.length; i++){
//            vehicles[i].getVehicle().setX(2000);
//            vehicles[i].getVehicle().setY(2000);
//        }
        collisionText.setOpacity(0.0);
        drunkNoCollisionText.setOpacity(0.0);
        noCollisionText.setOpacity(0.0);
        crashValuesText.setVisible(false);
        vehicles = new VehicleCrash[Integer.valueOf(nbreVehicle.getText())];   
        executor = Executors.newFixedThreadPool(2);
        out.println("reset");
    }
    //Back to nbre of vehicle screen
    public void backNbreVehicle(){
        nbreVehicleBtn.setVisible(true);
        nbreVehicle.setVisible(true);
        nbreVehicleText.setVisible(true);
        
        variableGrid.setVisible(false);
        nbreVehicleGrid.setVisible(false);
        massGrid.setVisible(false);
        velocityGrid.setVisible(false);
        accelerationGrid.setVisible(false);
        sLocationGrid.setVisible(false);
        sPositionGrid.setVisible(false);
        trafficLightGrid.setVisible(false);
        startBtn.setVisible(false);
        backNbreVehicleBtn.setVisible(false);
        backMenuBtn.setVisible(false);
        instructionsBtn.setVisible(true);
        drunkChk.setVisible(false);
        
        backgroundImage.setOpacity(0.4);
    }
    //Back to main menu
    public void backMenu(ActionEvent e) throws Exception{
        nbreVehicleBtn.setVisible(true);
        nbreVehicle.setVisible(true);
        nbreVehicleText.setVisible(true);
        
        variableGrid.setVisible(false);
        nbreVehicleGrid.setVisible(false);
        massGrid.setVisible(false);
        velocityGrid.setVisible(false);
        accelerationGrid.setVisible(false);
        sLocationGrid.setVisible(false);
        sPositionGrid.setVisible(false);
        trafficLightGrid.setVisible(false);
        startBtn.setVisible(false);
        backNbreVehicleBtn.setVisible(false);
        backMenuBtn.setVisible(false);
        instructionsBtn.setVisible(false);
        drunkChk.setVisible(false);
        
        Parent backPane = FXMLLoader.load(getClass().getResource(("FXML/MenuSceneFXML.fxml")));
        Scene menuScene = new Scene(backPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(menuScene);
        stage.show();
    }
    //Button to instructions
    public void onclickInstructions(ActionEvent e) throws Exception{
        Parent backPane = FXMLLoader.load(getClass().getResource(("FXML/InstructionsSceneFXML.fxml")));
        Scene menuScene = new Scene(backPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(menuScene);
        stage.show();
    }
    //Remove vehicle method
    public void removeVehicle(ImageView vehicle){
        pane.getChildren().remove(vehicle);
    }
    public void collisionText(String situation){
        FadeTransition ft = new FadeTransition();
        if (collisionText.getOpacity() == 0 && drunkNoCollisionText.getOpacity() == 0 && noCollisionText.getOpacity() == 0){
            if (situation.equals("collision")){
                ft = new FadeTransition(Duration.millis(3000), collisionText);
            }
            else if (situation.equals("drunkNoCollision")){
                ft = new FadeTransition(Duration.millis(3000), drunkNoCollisionText);  
            }
            else
                ft = new FadeTransition(Duration.millis(3000), noCollisionText);
        
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.setAutoReverse(false);
            ft.play();
        }
    }
    //Show crash values
    public void showCrashValues(double velocity, double acceleration, double distance){
        crashValuesText.setText("Crash Velocity: "+velocity+" \nCrash Acceleration: "+acceleration+" \nCrash Distance: "+distance);
        crashValuesText.setVisible(true);
        
    }
    //Change values in textfield 
    public synchronized void setPhysicsValues(double velocity, double acceleration, int vehicleNbre){
        velocityArray[vehicleNbre].setText(String.valueOf(velocity));
        accelerationArray[vehicleNbre].setText(String.valueOf(acceleration));
    }
    //Check if reset button has been clicked
    public boolean isClickReset(){
        return clickReset;
    }
    //Check runCount
    public int getRunCount(){
        return runCount;
    }
    //Create tasks
    public class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                for (int i=0; i<vehicles.length; i++){
                    vehicles[i].vehicleAnimation(vehicles, vehicles[i]);
                }
                out.println("nothing");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}


