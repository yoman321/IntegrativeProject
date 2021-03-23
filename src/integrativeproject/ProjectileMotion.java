/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

/**
 *
 * @author Liam
 */
public class ProjectileMotion {
    
<<<<<<< HEAD
    private double iVelY, iVelX, fVelY, time, velXY, iHeight, iDistance = 0, fHeight, fDistance;
    private final double ACCELERATION = 9.8;
    private boolean hasIVelY = false, hasIVelX = false, hasFVelY = false, hasTime = false, hasVelXY = false, hasIHeight = false, hasIDistance = false, hasFHeight = false, hasFDistance = false;
    FallingObject object = new FallingObject(); //Added a FallingObject variable
=======
    private double iVelY, iVelX, time, velXY, iHeight, fDistance, fHeight = 0, iDistance = 0;
    private final double ACCELERATION = 9.8;
    private boolean hasIVelY = false, hasIVelX = false, hasTime = false, hasIHeight = false, hasFDistance = false;
    FallingCircle circleObject;
    FallingRectangle rectangleObject;
    FallingSquare squareObject;
>>>>>>> 239c9311426ae4ca9d6f5b392420650077eb7ee8
    
    
    public ProjectileMotion(){
    }
    
    //Basic getter and setter methods
    public void setIVelY(double iVelY){
        hasIVelY = true;
        this.iVelY = iVelY;
    }
    public void setIVelX(double iVelX){
        hasIVelX = true;
        this.iVelX = iVelX;
    }
    public void setVelXY(double velXY){
        this.velXY = velXY;
    }
    public void setTime(double time){
        this.time = time;
    }
<<<<<<< HEAD
    public void setVelXY(double velXY,double angle){
        hasVelXY = true;
        this.velXY = velXY;
        convertXY(angle);
    }
=======
>>>>>>> 239c9311426ae4ca9d6f5b392420650077eb7ee8
    public void setIHeight(double iHeight){
        hasIHeight = true;
        this.iHeight = iHeight;
    }
    public void setObject(FallingCircle object){
        circleObject = object;
    }
    public void setObject(FallingSquare object){
        squareObject = object;
    }
    public void setObject(FallingRectangle object){
        rectangleObject = object;
    }
    public double getIVelY(){
        return iVelY;
    }
    public double getIVelX(){
        return iVelX;
    }
    public double getTime(){
        return time;
    }
    public double getVelXY(){
        return velXY;
    }
    public double getIHeight(){
        return iHeight;
    }
    public double getFDistance(){
        return fDistance;
    }
    public FallingCircle getCircleObject(){
        return circleObject;
    }
    public FallingRectangle getRectangleObject(){
        return rectangleObject;
    }
    public FallingSquare getSquareObject(){
        return squareObject;
    }
    
    //Solve for time has to be written before any solve for methods since they wont have access to the time
    public double solveForDistance(){
        if(hasTime == true && hasIVelX == true){
            fDistance = iDistance + (iVelX * time);
            hasFDistance = true;
            return fDistance;
        }
<<<<<<< HEAD
        else if(hasIVelX == true && hasTime == true){
            double position = iDistance + (iVelX * time);
            object.setX(position);
=======
        else{
            return -1;
        }
        
    }
    public double solveForX(){
        if(hasFDistance && hasIVelX){
            return fDistance - (iVelX * time);
>>>>>>> 239c9311426ae4ca9d6f5b392420650077eb7ee8
        }
            return -1;
    }
    public double solveForY(){
<<<<<<< HEAD
        if(hasIVelY == true && hasTime == true){
            double formulaTwo = iVelY - (9.8 * time);
            setFVelY(formulaTwo);
        }
        else if(hasIHeight == true && hasIVelY == true && hasFVelY == true && hasTime == true){
            double formulaOne = iHeight + (0.5 *(iVelY + fVelY) * time);
            object.setY(formulaOne);
        }
        else if(hasIHeight == true && hasIVelY == true && hasTime == true){
            double formulaThree = iHeight + (iVelY * time) - (0.5*9.8*(Math.pow(time, 2)));
            object.setY(formulaThree);
        }
        else if(hasIVelY == true && hasFVelY == true && hasIHeight == true){
            double formulaFour = (((iVelY*iVelY) - (fVelY*fVelY))/(2*9.8)) + iHeight;
            object.setY(formulaFour);
=======
        if(hasIVelY && hasIHeight){
            return (0.5 * ACCELERATION * time * time) - (iVelY * time) + fHeight;
>>>>>>> 239c9311426ae4ca9d6f5b392420650077eb7ee8
        }
        return -1;
    }
    public double solveForTime(){ 
        if (hasIHeight && hasIVelY){
            double a = -0.5*ACCELERATION;
            double b = iVelY;
            double c = (iHeight - fHeight);
            time = quadratic(a,b,c);
            hasTime = true;
            
            return time;
        }
        return -1;
    }
    
    
    public void convertXY(double degree){
        double radians = (degree * Math.PI) / 180;
        
        double velY = this.velXY * Math.sin(radians);
        double velX = this.velXY * Math.cos(radians);
        
        setIVelY(velY);
        setIVelX(velX);
    }
    public double quadratic(double a, double b, double c){
        double resultOne = ((-b) + Math.sqrt((b*b) - (4*a*c)))/(2*a);
        double resultTwo = ((-b) - Math.sqrt((b*b) - (4*a*c)))/(2*a);
        
        if(resultOne > resultTwo)
            return resultOne;
        else{
            return resultTwo;
        }
    }
    
    
    /*public double getForce(){
        
    }*/

}
