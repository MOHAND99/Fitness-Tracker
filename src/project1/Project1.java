/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Mhnd Bshar
 */
public class Project1 extends Application{
    
    public static float heartRate = 80;
    public static int totCalories;
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void  start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Fitness Tracker");
        GUI_Logic firstScene = new GUI_Logic(primaryStage);
        firstScene.preparescene();
        primaryStage.setScene(firstScene.getScene());
        primaryStage.show();
        
    }
}
