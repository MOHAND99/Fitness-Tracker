/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static project1.Project1.totCalories;
import static project1.Running.gethRateInc;
import static project1.Strength_Training.strength_trainingBurnt;
import static project1.Swimming.getSwimmingBurnt;

/**
 *
 * @author Mhnd Bshar
 */
public class SecondScene {
    Scene scene;
    Stage stage;
    
    GridPane grid = new GridPane();
    public void preparescene(){
        exit();
        
        scene = new Scene(grid, 400, 600);
    }
    
    public SecondScene(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

   
    
    
}
