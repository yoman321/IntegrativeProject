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
public class Physics {
    private double iVelX , iVelY, fVelY, time,velXY, iHeight, iDistance, fHeight, fDistance;
    private boolean  hasIVelX = false, hasIVelY = false, hasFVelY = false, hasTime = false, hasVelXY = false, hasIHeight = false, hasIDistance = false, hasFHeight = false, hasFDistance = false;
    public Physics(){
    }
    
    public void setIVelX(double iVelX){
        this.iVelX = iVelX;
        hasIVelX = true;
    }
    public void setIVelY(double iVelY){
        this.iVelY = iVelY;
        hasIVelY = true;
    }
    public void setFVelY(double fVelY){
        this.fVelY = fVelY;
        hasFVelY = true;
    }
    public void setTime(double time){
        this.time = time;
        hasTime = true;
    }
    public void setVelXY(double velXY){
        this.velXY = velXY;
        hasVelXY = true;
    }
    public void setIHeight(double iHeight){
        this.iHeight = iHeight;
        hasIHeight = true;
    }
    public void setIDistance(double iDistance){
        this.iDistance = iDistance;
        hasIDistance = true;
    }
    public void setFHeight(double fHeight){
        this.fHeight = fHeight;
        hasFHeight = true;
    }
    public void setFDistance(double fDistance){
        this.fDistance = fDistance;
        hasFDistance = true;
    }
    public double getIVelX(){
        return iVelX;
    }
    public double getIVelY(){
        return iVelY;
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
    
    //Remember that the boolean values are set to true and right now you do not turn them back false anywhere!!!!!!
    public double solveForTime(){
        if (hasIDistance == true && hasFDistance == true && hasIVelX == true){
            time = (fDistance - iDistance) / iVelX ;
            return time;
        }
        else if (hasIHeight == true && hasFHeight == true && hasIVelY == true && hasFVelY == true){
            time = (2 *(fHeight - iHeight))/(iVelY + fVelY);
            return time;
        }
        else if (hasIVelY == true && hasFVelY == true){
            time = (fVelY - iVelY) / -9.8;
            return time;
        }
        else if (hasFHeight == true && hasIHeight == true && hasIVelY == true){
            double a = -0.5 * 9.8;
            double b = iVelY;
            double c = iHeight - fHeight;
            time = quadratic(a, b, c);
            return time;
        }
        else{
            System.out.println("Not enough information available");
            return -1;
        }
    }
    
    public double solveForX(){
        if(hasTime == false && hasIHeight == true && hasFHeight == true && hasIVelY == true && hasFVelY == true){
             setTime((2 *(fHeight - iHeight))/(iVelY + fVelY));
        }
        if(hasIDistance == true && hasIVelX == true && hasTime == true){
            setFDistance((iDistance) + (iVelX * time));
            return fDistance;
            }
        else{
            return -1;
            }
            
    }
    
    //Converts the Initial velocity into its x velocity and its y velocity
    public void convertVelXY(double degree){
        degree = degree * (Math.PI / 180);
        double sinDegree = Math.sin(degree);
        double answerY = getVelXY() * sinDegree;
        setIVelY(answerY);
        
        double cosDegree = Math.cos(degree);
        double answerX = getVelXY() * cosDegree;
        setIVelX(answerX);
    }
    
    //Takes the information given by the user and determines the Y value. If there is not sufficient information the method returns -1.
    public double solveForY(){
        if(hasTime == false && hasIDistance == true && hasFDistance == true && hasIVelX == true){
            setTime((fDistance - iDistance) / iVelX );
        }
        else if(hasIHeight == true && hasIVelY == true && hasFVelY == true && hasTime == true){
            double partTwo = 0.5 * (iVelY + fVelY) * time;
            setFHeight(iHeight + partTwo);
            return fHeight;
        }
        else if(hasIHeight == true && hasIVelY == true && hasTime == true){
            double partTwo = iVelY * time;
            double timeSquared = Math.pow(time,2);
            double partThree = -0.5 * 9.8 * timeSquared;
            setFHeight(iHeight + partTwo + partThree);
            return fHeight;
        }
        else if(hasFVelY == true && hasIVelY == true && hasIHeight){
            double iVelYSquared = Math.pow(iVelY, 2);
            double fVelYSquared = Math.pow(fVelY, 2);
            double divisor = -2 * 9.8;
            double partOne = (fVelYSquared - iVelYSquared)/divisor;
            setFHeight(partOne + iHeight);
            return fHeight;
        }
            return -1;
    }
            
            
            
    //Solves a quadratic equation
    public double quadratic(double a, double b, double c){
        double toBeSquared = (Math.pow(b,2)) -(4 * a * c);
        double squared = Math.sqrt(toBeSquared);
        
        double answerOne = (-b + squared) / (2 * a);
        double answerTwo = (-b - squared) / (2 * a);
        
        if(answerOne >= 0){
            return answerOne;
        }
        else{
            return answerTwo;
        }
    }
}
