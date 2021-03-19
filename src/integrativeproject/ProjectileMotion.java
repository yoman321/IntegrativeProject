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
    
    private double iVelY, iVelX, fVelY, time, velXY, iHeight, iDistance = 0, fHeight, fDistance;
    private final double ACCELERATION = 9.8;
    private boolean hasIVelY = false, hasIVelX = false, hasFVelY = false, hasTime = false, hasVelXY = false, hasIHeight = false, hasIDistance = false, hasFHeight = false, hasFDistance = false;
    FallingObject object = new FallingObject(); //Added a FallingObject variable
    
    
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
    public void setFVelY(double fVelY){
        hasFVelY = true;
        this.fVelY = fVelY;
    }
    public void setTime(double time){
        hasTime = true;
        this.time = time;
    }
    public void setVelXY(double velXY,double angle){
        hasVelXY = true;
        this.velXY = velXY;
        convertXY(angle);
    }
    public void setIHeight(double iHeight){
        hasIHeight = true;
        this.iHeight = iHeight;
    }
    public void setIDistance(double iDistance){
        hasIDistance = true;
        this.iDistance = iDistance;
    }
    public void setFHeight(double fHeight){
        hasFHeight = true;
        this.fHeight = fHeight;
    }
    public void setFDistance(double fDistance){
        hasFDistance = true;
        this.fDistance = fDistance;
    }
    public void setObject(FallingCircle object){
        this.object = object;
    }
    public void setObject(FallingSquare object){
        this.object = object;
    }
    public void setObject(FallingRectangle object){
        this.object = object;
    }
    public double getIVelY(){
        return iVelY;
    }
    public double getIVelX(){
        return iVelX;
    }
    public double getFVelY(){
        return fVelY;
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
    public double getIDistance(){
        return iDistance;
    }
    public double getFHeight(){
        return fHeight;
    }
    public double getFDistance(){
        return fDistance;
    }
    
    
    
    public double solveForX(){
        if (hasIVelX == false & hasIDistance == true && hasFDistance == true && hasTime){
            setIVelX((fDistance - iDistance)/time);
        }
        else if(hasIVelX == true && hasTime == true){
            double position = iDistance + (iVelX * time);
            object.setX(position);
        }
            return -1;
    }
    public double solveForY(){
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
        }
        return -1;
        
    }
    public double solveForTime(){
        
        if (hasIHeight == true && hasFHeight == true && hasIVelY == true && hasFVelY == true){
            double numerator = 2 * (fHeight - iHeight);
            double denominator = iVelY + fVelY;
            double time = numerator / denominator;
            
            return time;
            
        }
        else if (hasFVelY == true && hasIVelY == true){
            
            double numerator = -1 * (fVelY - iVelY);
            double denominator = ACCELERATION;
            double time = numerator / denominator;
            
            return time;
        }
        else if (hasFHeight == true && hasIHeight == true && hasIVelY == true){
            double a = -0.5*ACCELERATION;
            double b = iVelY;
            double c = (iHeight + fHeight);
            double time = quadratic(a,b,c);
            
            return time;
        }
        else if (hasFDistance == true && hasFDistance == true && hasIVelX){
            double numerator = (fDistance - iDistance);
            double denominator = iVelX;
            double time = numerator / denominator;
            
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
    
    public void main(String[] args){
        
    }
}
