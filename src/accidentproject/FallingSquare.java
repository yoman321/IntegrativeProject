/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Liam
 */
public class FallingSquare extends FallingObject{
    private double side;
    private Rectangle square;
    
    //Constructors
    public FallingSquare(Rectangle square, double side, double x, double y){
        this.side = side;
        square = new Rectangle(side, side);
        setX(x);
        setY(y);
    }
    public FallingSquare(Rectangle square, double side){
        this.side = side;
        square = new Rectangle(side, side);
    }
    public FallingSquare(){
    }
    
    //Setters and getters
    public void setSide(double side){
        this.side = side;
    }
    public double getSide(){
        return side;
    }
}
