package com.example.rallycrosschampionship;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddController {
    @FXML
    private TextField addfname;
    @FXML
    private TextField addlname;
    @FXML
    private TextField addage;
    @FXML
    private TextField addteam;
    @FXML
    private TextField addcar;
    @FXML
    private TextField addpoints;
    @FXML
    private Label addmessage;
    public void onSaveAdd(ActionEvent event) throws IOException {
        checkAdd();
    }
    private void checkAdd() throws IOException{
        if(addfname.getText().toString().isEmpty() || addlname.getText().toString().isEmpty() ||  addage.getText().toString().isEmpty() || addteam.getText().toString().isEmpty() || addcar.getText().toString().isEmpty() || addpoints.getText().toString().isEmpty()){
            addmessage.setText("Fill the lines \ncorrectly.");

        } else { //Saving data into variables
            try{
                Driver drv = new Driver();
                drv.setFname(addfname.getText().toLowerCase());
                drv.setLname(addlname.getText().toLowerCase());

                //user cann't input same name before entered
                Scanner sc=new Scanner(new File("src/main/resources/com/example/rallycrosschampionship/details.txt"));
                while (sc.hasNextLine()){
                    String record= sc.nextLine();
                    String fullName =drv.getFname()+","+drv.getLname();
                    if (record.contains(fullName)){
                        addmessage.setText("Name already used.\nPick another one.");
                        sc.close();
                        return;
                    }
                }

                //age must be 21<=age<=45
                drv.setAge(Integer.parseInt(addage.getText()));
                if (drv.getAge()<21 || 45<drv.getAge()){
                    addmessage.setText("Driver age must be\nfrom 21 to 45.");
                    return;
                }
                drv.setTeam(addteam.getText().toLowerCase());
                drv.setCar(addcar.getText().toLowerCase());

                //points must >=0
                drv.setPoints(Integer.parseInt(addpoints.getText()));
                if (drv.getPoints()<0){
                    addmessage.setText("Points must be\n greater than or equals\nto 0");
                    return;
                }

                //Saving data into file
                FileWriter Writer = new FileWriter("src/main/resources/com/example/rallycrosschampionship/details.txt", true);
                Writer.write(drv.getFname() + "," + drv.getLname() + ","+ drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() + "," + drv.getPoints() + "\n");
                Writer.close();
                addmessage.setText("Successfully \nsaved.");

            } catch (NumberFormatException e){
                addmessage.setText("Age & Current Points \nshould be Integer");
            }
        }
    }
    public void backToHome(ActionEvent event) throws IOException {
        Parent homePage= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onExit(ActionEvent event) throws IOException {
        exit();
    }
    private void exit() throws IOException{
        System.exit(0);
    }
}
