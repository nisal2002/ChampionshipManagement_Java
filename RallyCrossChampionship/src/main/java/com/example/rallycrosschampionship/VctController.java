package com.example.rallycrosschampionship;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class VctController {
    @FXML
    TableView vcttable;
    @FXML
    TableColumn colplace;
    @FXML
    TableColumn colfname;
    @FXML
    TableColumn collname;
    @FXML
    TableColumn colage;
    @FXML
    TableColumn colteam;
    @FXML
    TableColumn colcar;
    @FXML
    TableColumn colpoints;

    public void onViewVct(ActionEvent event) throws IOException {
        checkVct();
    }
    private void checkVct() throws IOException {

        //this is the code for get points in front of each record and append into sort.txt
        try {
            File file = new File("src/main/resources/com/example/rallycrosschampionship/details.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                Driver drv = new Driver();
                drv.setRecord(reader.nextLine());
                String[] recordArray = drv.getRecord().split(",");

                drv.setPoints(Integer.parseInt(recordArray[5]));
                drv.setFname(recordArray[0]);
                drv.setLname(recordArray[1]);
                drv.setAge(Integer.parseInt(recordArray[2]));
                drv.setTeam(recordArray[3]);
                drv.setCar(recordArray[4]);

                //saving data into sort file
                FileWriter writer = new FileWriter("src/main/resources/com/example/rallycrosschampionship/sort.txt", true);
                writer.write( drv.getPoints() + ","+drv.getFname() + "," + drv.getLname() + ","+ drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() +"\n");

                writer.close();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }



        //this is the code for get details for sort
        try{
            File file = new File("src/main/resources/com/example/rallycrosschampionship/sort.txt");
            Scanner reader = new Scanner(file);
            String[][] data = new String[300][6]; //maximum of 300 drivers with 6 attributes each

            int i=0;
            while (reader.hasNextLine() && i<300) {
                Driver drv= new Driver();
                String[] record=reader.nextLine().split(",");
                drv.setLine(record);
                drv.setPoints(Integer.parseInt(record[0]));
                drv.setFname(record[1]);
                drv.setLname(record[2]);
                drv.setAge(Integer.parseInt(record[3]));
                drv.setTeam(record[4]);
                drv.setCar(record[5]);

                //insert attributes into the data array
                data[i][0] = String.valueOf(drv.getPoints());
                data[i][1] = drv.getFname();
                data[i][2] = drv.getLname();
                data[i][3] = String.valueOf(drv.getAge());
                data[i][4] = drv.getTeam();
                data[i][5] = drv.getCar();

                i++;
            }
            reader.close();

            //sort data descending order (column 0)
            int n=i;
            for(i=0;i< n-1; i++){
                for(int j=i+1; j<n;j++){
                    if(Integer.parseInt(data[j][0])>Integer.parseInt(data[i][0])){
                        //swap rows i and j
                        String[] temp = data[i];
                        data[i] = data[j];
                        data[j] = temp;
                    }
                }
            }

            //this is the code for clear sort.txt
            FileWriter fw1= new FileWriter("src/main/resources/com/example/rallycrosschampionship/sort.txt");
            fw1.write("");
            fw1.close();

            //print sorted data into sort file
            for(i=0;i<n;i++){
                for (int j=0; j<data[i].length;j++){
                    FileWriter writer = new FileWriter("src/main/resources/com/example/rallycrosschampionship/sort.txt",true);
                    if(j==5){
                        writer.write(data[i][j]+"\n");
                        System.out.print(data[i][j]+",");
                        writer.close();
                    }else {
                        writer.write(data[i][j]+",");
                        System.out.print(data[i][j]+",");
                        writer.close();
                    }
                }
                System.out.println();
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        //this is code for insert data into table
        try {
            File file = new File("src/main/resources/com/example/rallycrosschampionship/sort.txt");
            Scanner reader = new Scanner(file);
            final ObservableList<Driver> data= FXCollections.observableArrayList();
            int i=1;
            while (reader.hasNextLine()) {
                Driver drv= new Driver();
                drv.setRecord(reader.nextLine());
                String[] recordArray=drv.getRecord().split(",");

                drv.setPosition(i);
                drv.setPoints(Integer.parseInt(recordArray[0]));
                drv.setFname(recordArray[1].toUpperCase());
                drv.setLname(recordArray[2].toUpperCase());
                drv.setAge(Integer.parseInt(recordArray[3]));
                drv.setTeam(recordArray[4].toUpperCase());
                drv.setCar(recordArray[5].toUpperCase());

                data.add(drv);

                //associate data with columns
                colplace.setCellValueFactory(new PropertyValueFactory<>("position"));
                colfname.setCellValueFactory(new PropertyValueFactory<>("fname"));
                collname.setCellValueFactory(new PropertyValueFactory<>("lname"));
                colage.setCellValueFactory(new PropertyValueFactory<>("age"));
                colteam.setCellValueFactory(new PropertyValueFactory<>("team"));
                colcar.setCellValueFactory(new PropertyValueFactory<>("car"));
                colpoints.setCellValueFactory(new PropertyValueFactory<>("points"));

                //add data into columns
                vcttable.setItems(data);

                i++;
            }

            //this is the code for clear sort.txt
            FileWriter fw1= new FileWriter("src/main/resources/com/example/rallycrosschampionship/sort.txt");
            fw1.write("");
            fw1.close();

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
