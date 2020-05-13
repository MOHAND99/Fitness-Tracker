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
public class Swimming extends Activities{
    private static int swimmingBurnt;
    private static float hRateInc;
    public static float gethRateInc() {return hRateInc;}
    public static int getSwimmingBurnt() {return swimmingBurnt;}
    public Swimming(int time) {
        super(time, 0.002f , 4);
        swimmingBurnt += getCaloriesPerMin()*time; 
        hRateInc += myHRate * time * 0.002f;
    }
}
