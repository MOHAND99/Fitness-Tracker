/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    Label tCals, hRate;
    boolean sClick = true;
    
    public void preparescene() {
        // Buttons
        Button submit = new Button("Submit");
        Button results = new Button("Results");
        // Entered Data
        TextField time = new TextField();
        ComboBox typeBox = new ComboBox();
        // Width setting
        results.setMaxWidth(Double.MAX_VALUE);
        typeBox.setMaxWidth(Double.MAX_VALUE);
        submit.setMaxWidth(Double.MAX_VALUE);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        typeBox.getItems().addAll("swimming", "kick boxing", "strength training", "running");
        typeBox.setPromptText("TYPE");
        time.setPromptText("TIME (minutes)");
        grid.setVgap(25);
        grid.setHgap(10);
        grid.add(time, 0, 1);
        grid.add(typeBox, 0, 0);
        grid.add(submit, 0, 3);
        hRate = new Label();
        tCals = new Label();
        submit.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            if(time.getText().equals("") || typeBox.getValue().toString().equals("")) return;
            activity = typeBox.getValue().toString();
            mins = Integer.parseInt(time.getText());
            eventCheck();
            DecimalFormat dff = new DecimalFormat("##.###");
            dff.setRoundingMode(RoundingMode.DOWN);
            hRate.setText("Heart Rate : " + dff.format(Project1.heartRate)+" beat / minutes");
            tCals.setText("total calories burnt : "  + Project1.totCalories+" calories");
            GridPane subGrid = new GridPane();
            scene3 = new Scene(subGrid, 300, 150);
            subGrid.setAlignment(Pos.CENTER);
            Label Done = new Label("Activity Recorded Successfully");
            subGrid.addColumn(0, Done, tCals, hRate);
            submitStage = new Stage();
            setStage(submitStage);
            submitStage.setScene(scene3);
            submitStage.show();
           if(sClick) {grid.add(results, 0,4); sClick = false;}
            results.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DecimalFormat df = new DecimalFormat("#.###");
                    df.setRoundingMode(RoundingMode.DOWN);
                    GridPane resultsGrid = new GridPane();
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
                    Label totHRate = new Label("total heart Rate: ~"+dff.format(Project1.heartRate)+" beat / minute\n\n");
                    Label activityRAnk = new Label("Activities Rank :\n\n"); activityRAnk.setStyle("-fx-font: 24 arial;");
                    resultsGrid.addColumn(0, tCals, totHRate, activityRAnk);
                    while(i!= -1){
                        if(sortBurnt[i] == 0.0f){i--; continue;}
                        if(sortBurnt[i] == getKick_boxingBurnt()) {
                           Label kickboxLabel = new Label((4-i) + ". KICK BOXING :\n");
                           Label kickboxburntLabel = new Label("calories Burnt : " + Kick_Boxing.getKick_boxingBurnt()+" calories\n"+"heart rate inc : " + df.format(Kick_Boxing.gethRateInc())+ "  beat / minute\n\n");
                           resultsGrid.addColumn(0, kickboxLabel, kickboxburntLabel);
                        }else if(sortBurnt[i] == getRunningBurnt()){
                           Label runningLabel = new Label((4-i) + ". RUNNING :");
                           Label runningburntLabel = new Label("calories Burnt : "+Running.getRunningBurnt()+" calories\n"+"heart rate inc : " + df.format(Running.gethRateInc())+ " beat / minute\n\n");
                           resultsGrid.addColumn(0, runningLabel, runningburntLabel);
                        }else if(sortBurnt[i] == getStrength_trainingBurnt()) {
                           Label strengthTrainingLabel = new Label((4-i) + ". STRENGTH TRAINING :");
                           Label strengthTrainingburntLabel = new Label("calories Burnt : " +Strength_Training.getStrength_trainingBurnt()+" calories\n"+"heart rate inc : " + df.format(Strength_Training.gethRateInc())+ "  beat / minute\n\n");
                           resultsGrid.addColumn(0, strengthTrainingLabel, strengthTrainingburntLabel);
                        }else if(sortBurnt[i] == getSwimmingBurnt()){
                           Label swimmingLabel = new Label((4-i) + ". SWIMMING :");
                           Label swimmingburntLabel = new Label("calories Burnt : "+Swimming.getSwimmingBurnt()+" calories\n"+"heart rate inc : " + df.format(Swimming.gethRateInc())+ "  beat / minute\n\n");
                           resultsGrid.addColumn(0, swimmingLabel, swimmingburntLabel);
                        }i--;
                    }
                    scene2 = new Scene(resultsGrid, 300, 475);
                    resultStage.setScene(scene2);
                    resultStage.show();
            }
            });
        }
    }));
        scene = new Scene(grid, 300, 300); 
        typeBox.requestFocus();
    };
    public void eventCheck(){
        switch (activity) {
            case "swimming":
                {
                    Swimming obj;
                    obj = new Swimming(mins);
                    break;
                }
            case "kick boxing":
                {
                    Kick_Boxing obj;
                    obj = new Kick_Boxing(mins);
                    break;
                }
            case "strength training":
                {
                    Strength_Training obj;
                    obj = new Strength_Training(mins);
                    break;
                }
            case "running":
                {
                    Running obj;
                    obj = new Running(mins);
                    break;
                }
            default:
                break;
        }
    }
    public Scene getScene() {return scene;}
    public void setStage(Stage stage) {this.stage = stage;}
    public void setScene(Scene scene) {this.scene = scene;}
    public GUI_Logic(Stage stage) {this.stage = stage;}
    public float checkElement(int i, int burnt[]){
            int f = burnt[i];
            if(f == getKick_boxingBurnt()) return Kick_Boxing.gethRateInc();
            else if(f == getRunningBurnt()) return Running.gethRateInc();
            else if(f == getStrength_trainingBurnt()) return Strength_Training.gethRateInc();
            else if(f == getSwimmingBurnt()) return Swimming.gethRateInc();
            return -1;
    }
}
