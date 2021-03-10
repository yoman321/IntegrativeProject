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
    
    //Create datafields
    private VehicleCrash vehicle = new VehicleCrash();
    private String[] directions = {"up", "down", "left", "right"};
    
    //Create initialize mesthod
    public void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList(directions);
        cbox1.getItems().addAll(items);
    }
    //Create start button\
    public void onclickStart(){
        
    }
}
