/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 *
 * @author Mhnd Bshar
 */
public class Strength_Training extends Activities{
    private static int strength_trainingBurnt;

    public static int getStrength_trainingBurnt() {
        return strength_trainingBurnt;
    }
    private static float hRateInc;
    public static float gethRateInc() {
        return hRateInc;
    }
    public Strength_Training(int time) {
        super(time, 0.006f , 5);
        strength_trainingBurnt += getCaloriesPerMin()*time; 
        hRateInc += myHRate * time * 0.006f;
    }
}
