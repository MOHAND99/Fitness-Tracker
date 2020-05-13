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
public class Kick_Boxing extends Activities{
    private static int kick_boxingBurnt;
    private static float hRateInc;
    public static float gethRateInc() {
        return hRateInc;
    }

    public static int getKick_boxingBurnt() {
        return kick_boxingBurnt;
    }
    public Kick_Boxing(int time) {
        super(time, 0.005f , 3);
        kick_boxingBurnt += getCaloriesPerMin()*time;
        hRateInc += myHRate * time * 0.005f;
    }
}
