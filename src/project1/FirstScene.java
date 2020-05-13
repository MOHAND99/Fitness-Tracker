/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static project1.Strength_Training.getStrength_trainingBurnt;
import static project1.Kick_Boxing.getKick_boxingBurnt;
import static project1.Running.getRunningBurnt;
import static project1.Swimming.getSwimmingBurnt;

/**
 *
 * @author Mhnd Bshar
 */
public class GUI_Logic {
    Stage stage, resultStage, submitStage;
    Scene scene, scene2, scene3;
    String activity;
    int mins;

    
    Button submit;
    Label tCals;
    Label hRate;
    
    float swimContn;
    float runContn;
    float kick_boxContn;
    float strength_trainContn;
    
    
    public void preparescene() {
        
        Button newActivity = new Button("newActivity");
        Button results = new Button("Results");

        results.setMaxWidth(Double.MAX_VALUE);
        newActivity.setMaxWidth(Double.MAX_VALUE);

        GridPane grid = new GridPane();
        grid.add(newActivity, 0, 0);
        grid.setAlignment(Pos.CENTER);

        newActivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField type = new TextField();
                TextField time = new TextField();
                Label timeLabel = new Label("time :");
                Label TYPELabel = new Label("TYPE :");
                submit = new Button("Submit");
                grid.setVgap(25);
                grid.setHgap(10);
                grid.add(timeLabel, 1, 1);
                grid.add(TYPELabel, 1, 0);
                grid.add(time, 2, 1);
                grid.add(type, 2, 0);
                
                grid.add(submit, 2, 6);
                
                hRate = new Label();
                tCals = new Label();
                
                
                submit.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    activity = type.getText();
                    mins = Integer.parseInt(time.getText());
                    eventCheck();
                    
                    hRate.setText("Heart Rate : " + Project1.heartRate);
                    tCals.setText("Tot. Calories : "  + Project1.totCalories);
                    
                    GridPane subGrid = new GridPane();
                    scene3 = new Scene(subGrid, 200, 200);
                    subGrid.setAlignment(Pos.CENTER);
                    Label Done = new Label("Activity Recorded Successfully");
                    subGrid.addColumn(0, Done, tCals, hRate);
                    submitStage = new Stage();
                    setStage(submitStage);
                    submitStage.setScene(scene3);
                    submitStage.show();
                    
                   
                    grid.add(results, 0,8);
                    results.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            GridPane resultsGrid = new GridPane();
                            //GridPane grid2 = new GridPane();
                            
                            resultStage = new Stage();
                            setStage(resultStage);
                            
                            resultStage.setTitle("results");
                            int sortBurnt[] = {getKick_boxingBurnt(),getRunningBurnt(),getStrength_trainingBurnt(),getSwimmingBurnt()};
                            Arrays.sort(sortBurnt);
                            int i;
                            for ( i = 0; i < sortBurnt.length-1; i++) {
                                if (sortBurnt[i] == sortBurnt[i+1])
                                    if(checkElement(i, sortBurnt) > checkElement(i + 1, sortBurnt)){
                                        int temp = sortBurnt[i];
                                        sortBurnt[i] = sortBurnt[i+1];
                                        sortBurnt[i+1] = temp;
                                    }
                            }
                            resultsGrid.setAlignment(Pos.CENTER);
                            Label totCals = new Label("total calories Burnt = "+Project1.totCalories+" calories");
                            Label totHRate = new Label("total heart Rate: ~"+Project1.heartRate+" beat / minutes\n\n");
                            resultsGrid.addColumn(0, totCals, totHRate,new Label("Activities Rank :\n\n"));
                            short count = 0;
                            while(i!= -1){
                                if(sortBurnt[i] == 0.0f){i--;count++; continue; }
                                if(sortBurnt[i] == getKick_boxingBurnt()) {
                                   Label kickboxLabel = new Label((4-i) + ". KICK BOXING :\n");
                                   Label kickboxburntLabel = new Label("Calories Burnt : " + Kick_Boxing.getKick_boxingBurnt()+" calories\n"+"Heart Rate Inc : " + Kick_Boxing.gethRateInc()+ "  beat / minute\n\n");
                                   resultsGrid.addColumn(0, kickboxLabel, kickboxburntLabel);
                                }else if(sortBurnt[i] == getRunningBurnt()){
                                   Label runningLabel = new Label((4-i) + ". RUNNING :");
                                   Label runningburntLabel = new Label("Calories Burnt : "+Running.getRunningBurnt()+" calories\n"+"Heart Rate Inc : " + Running.gethRateInc()+ " beat / minute\n\n");
                                   resultsGrid.addColumn(0, runningLabel, runningburntLabel);
                                }else if(sortBurnt[i] == getStrength_trainingBurnt()) {
                                   Label strengthTrainingLabel = new Label((4-i) + ". STRENGTH TRAINING :");
                                   Label strengthTrainingburntLabel = new Label("Calories Burnt : " +Strength_Training.getStrength_trainingBurnt()+" calories\n"+"Heart Rate Inc : " + Strength_Training.gethRateInc()+ "  beat / minute\n\n");
                                   resultsGrid.addColumn(0, strengthTrainingLabel, strengthTrainingburntLabel);
                                }else if(sortBurnt[i] == getSwimmingBurnt()){
                                   Label swimmingLabel = new Label((4-i) + ". SWIMMING :");
                                   Label swimmingburntLabel = new Label("Calories Burnt : "+Swimming.getSwimmingBurnt()+" calories\n"+"Heart Rate Inc : " + Swimming.gethRateInc()+ "  beat / minute\n\n");
                                   resultsGrid.addColumn(0, swimmingLabel, swimmingburntLabel);
                                }i--;
                            }if(4 == count) resultsGrid.addColumn(0, new Label("No Activities"));
                            scene2 = new Scene(resultsGrid, 400, 600);
                            resultStage.setScene(scene2);
                            resultStage.show();
                    }
                    });
                }
            }));
        }
    });scene = new Scene(grid, 600, 400); 
    }
    public void eventCheck(){
        if(activity.equalsIgnoreCase("swimming")){
            Swimming obj;
            obj = new Swimming(mins);
        }else if (activity.equalsIgnoreCase("kick_boxing")){
            Kick_Boxing obj;
            obj = new Kick_Boxing(mins);
        }else if (activity.equalsIgnoreCase("strength_training")){
            Strength_Training obj;
            obj = new Strength_Training(mins);
        }else if (activity.equalsIgnoreCase("running")){
            Running obj;
            obj = new Running(mins);
        }
    }
    public Scene getScene() {
        return scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public GUI_Logic(Stage stage) {
        this.stage = stage;
    }
    public float checkElement(int i, int burnt[]){
            int f = burnt[i];
            if(f == getKick_boxingBurnt()) return Kick_Boxing.gethRateInc();
            else if(f == getRunningBurnt()) return Running.gethRateInc();
            else if(f == getStrength_trainingBurnt()) return Strength_Training.gethRateInc();
            else if(f == getSwimmingBurnt()) return Swimming.gethRateInc();
            return -1;
    }
}
