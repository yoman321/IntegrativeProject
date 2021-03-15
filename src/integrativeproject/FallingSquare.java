/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author liamd
 */
public class FallingSquare extends FallingObject{
    
    private Rectangle square;
    private double side;
    
    public FallingSquare(double side){
        this.side = side;
        square.setWidth(side);
        square.setHeight(side);
    }
    
    public void setSide(double side){
        this.side = side;
    }
    public double getSide(){
        return side;
    }
    
}
