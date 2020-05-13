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
public class Running extends Activities{
    private static int runningBurnt;
    private static float hRateInc;
    public static float gethRateInc() {
        return hRateInc;
    }

    public static int getRunningBurnt() {
        return runningBurnt;
    }
    public Running(int time) {
        super(time, 0.003f , 5);
        runningBurnt+= getCaloriesPerMin()*time; 
        hRateInc += myHRate * time * 0.003f;
    }
}
