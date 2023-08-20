package com.example.rallycrosschampionship;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class VrlController {
    @FXML
    TableView vrltable;
    @FXML
    TableColumn coldate;
    @FXML
    TableColumn collocation;

    public void onSort(ActionEvent event) throws IOException {
        checkVrl();
    }
    private void checkVrl() throws IOException {

        String rFile = "src/main/resources/com/example/rallycrosschampionship/race.txt";
        String sFile = "src/main/resources/com/example/rallycrosschampionship/sort.txt";

        try{
            File file = new File(rFile);
            Scanner reader = new Scanner(file);
            String[][] data = new String[1000][2]; //max 1000 races with 2 attributes

            int i=0;
            while (reader.hasNextLine()&&i<=1000){
                Racer race= new Racer();
                String[] record=reader.nextLine().split(",");
                race.setLine(record);
                race.setDate(LocalDate.parse(record[0]));
                race.setLocation(record[1]);

                data[i][0]= String.valueOf(race.getDate()); //date
                data[i][1]=race.getLocation(); //location

                i++;
            }
            reader.close();

            //sort data - ascending order - column(0)
            int n=i;
            for (i=0;i<n-1;i++){
                int minIndex=i;
                for(int j=i+1; j<n;j++){
                    if(data[j][0].compareTo(data[minIndex][0])<0){
                        minIndex=j;
                    }
                }

                //swap rows i and j
                String[] temp =data[i];
                data[i] =data[minIndex];
                data[minIndex]=temp;
            }

            //insert sorted data into sort.txt
            for(i=0;i<n;i++){
                FileWriter fw1= new FileWriter(sFile,true);
                fw1.write(data[i][0]+","+data[i][1]+"\n");
                fw1.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }


        try{
            File file = new File(sFile);
            Scanner reader = new Scanner(file);
            final ObservableList<Driver> data= FXCollections.observableArrayList();

            while (reader.hasNextLine()) {
                Racer race= new Racer();
                race.setRecord(reader.nextLine());
                String[] recordArray=race.getRecord().split(",");

                race.setDate(LocalDate.parse(recordArray[0]));
                race.setLocation(recordArray[1].toUpperCase());

                data.add(race);

                //associate data with columns
                coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
                collocation.setCellValueFactory(new PropertyValueFactory<>("location"));

                //add data into columns
                vrltable.setItems(data);

            }

            //this is the code for clear sort.txt
            FileWriter fw1= new FileWriter(sFile);
            fw1.write("");
            fw1.close();

        }catch (Exception e){
            System.out.println(e);
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
