/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import static project1.Project1.heartRate;
import static project1.Project1.totCalories;

/**
 *
 * @author Mhnd Bshar
 */
public abstract class Activities {
    private float heartRateInc; 
    private int caloriesPerMin; 
    private final int time;
    public float myHRate;
    private float newHeartRate(float heartRate){
        myHRate = heartRate;
        float newHRate =  heartRate + heartRate*time*heartRateInc;
        return newHRate;
    }

    public Activities(int time, float heartRateInc, int caloriesPerMIn) {
        this.time = time;
        setCaloriesPerMin(caloriesPerMIn);
        setHeartRateInc(heartRateInc);
        heartRate = newHeartRate(heartRate);
        totCalories += (int) caloriesPerMin*time;
        //myHRate = Project1.heartRate / ( 1 + time * 0.002f);
    }

    public float getHeartRateInc() {
        return heartRateInc;
    }

    public void setHeartRateInc(float heartRateInc) {
        this.heartRateInc = heartRateInc;
    }

    public int getCaloriesPerMin() {
        return caloriesPerMin;
    }

    public void setCaloriesPerMin(int caloriesPerMin) {
        this.caloriesPerMin = caloriesPerMin;
    }

    
    
    
   
    
}
