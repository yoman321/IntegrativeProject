/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

/**
 *
 * @author Liam
 */
public class ProjectileMotion {
    private double iVelY, iVelX, time, velXY, iHeight, fDistance, fHeight = 0, iDistance = 0, fVelY, maxHeight, mass;
    private final double ACCELERATION = 9.8;
    private boolean hasIVelY = false, hasIVelX = false, hasTime = false, hasIHeight = false, hasFDistance = false, hasFHeight = false;
    
    
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
    public void setVelXY(double velXY,double angle){
        this.velXY = velXY;
    }
    public void setIHeight(double iHeight){
        hasIHeight = true;
        this.iHeight = iHeight;
    }
    public void setMaxHeight(double height){
        maxHeight = height;
    }
    public void setMass(double mass){
        this.mass = mass;
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
    public double getMass(){
        return mass;
    }
    public double getAcceleration(){
        return ACCELERATION;
    }
    
    //Solve for time has to be written before any solve for methods since they wont have access to the time
    
    //Solves motion for the distance at a specific point in time
    public double solveForDistance(){
        if(hasTime == true && hasIVelX == true){
            fDistance = iDistance + (iVelX * time);
            hasFDistance = true;
            return fDistance;
        }
        else{
            return -1;
        }
        
    }
    
    //Solves motion for the height at a specific point in time
    public double solveForHeight(){
         if(hasIHeight == true && hasIVelY == true && hasTime == true){
             fHeight = iHeight + (iVelY * time) - (0.5 * ACCELERATION * time * time);
             hasFHeight = true;
             return fHeight;
         }
         return -1;
    }

    //Solves for the time if it has initial height and initial velocity
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
    
    //Solves for the y velocity at a specific point int ime
    public double solveForFVelY(){
        fVelY = iVelY - (ACCELERATION * time);
        return fVelY;
    }
    
    //Converts a velocity with an x and y component into its respective velocity`s in each direction
    public void convertXY(double degree){
        double radians = (degree * Math.PI) / 180;
        
        double velY = this.velXY * Math.sin(radians);
        double velX = this.velXY * Math.cos(radians);
        
        setIVelY(velY);
        setIVelX(velX);
    }
    
    //Solves a quadratic 
    public double quadratic(double a, double b, double c){
        double resultOne = ((-b) + Math.sqrt((b*b) - (4*a*c)))/(2*a);
        double resultTwo = ((-b) - Math.sqrt((b*b) - (4*a*c)))/(2*a);
        
        if(resultOne > resultTwo)
            return resultOne;
        else{
            return resultTwo;
        }
    }
    
    
    public double getForce(){
        double distanceInObject = 0.1; //Since the box in our simulation is hard, we are only going to allow the falling object to travel 0.01 m into the target object. We are going to assume that after this threshold,
        double forceOnImpact = getMass() * ACCELERATION * maxHeight / distanceInObject;
        return forceOnImpact;
        
    }

}
