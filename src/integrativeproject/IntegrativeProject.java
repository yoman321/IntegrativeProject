/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author liamd
 */
public class IntegrativeProject extends Application {
    
   public static void main(String[] args) {
        //Application.launch(args);
        ProjectileMotion pm = new ProjectileMotion();
        pm.solveForY();
       
       
       //TEST FOR PUSH
        
        /*test.solveForY();
        System.out.println(test.getFHeight());
        test.setTime(test.getTime() + 0.01);
        while(test.getFHeight() >= 0){
            test.solveForY();
            System.out.println(test.getFHeight());
            test.setTime(test.getTime() + 0.01); */
        
    }
        
        

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DemoInputScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}