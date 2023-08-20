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

import java.io.*;
import java.util.Scanner;

public class UddController {
    @FXML
    private TextField updfname;
    @FXML
    private TextField updlname;
    @FXML
    private TextField updage;
    @FXML
    private TextField updteam;
    @FXML
    private TextField updcar;
    @FXML
    private TextField updpoints;
    @FXML
    private Label updmessage;
    @FXML
    private Label displayrecord;
    @FXML
    private TextField enterupdname;


    public void onUddLoad(ActionEvent event) throws IOException {
        checkLoad();
    }
    private void checkLoad() throws IOException {
        String filename = "src/main/resources/com/example/rallycrosschampionship/details.txt"; // name of the text file containing the records

        Driver drv=new Driver();
        drv.setFname(enterupdname.getText());

        File file = new File(filename); // create a file object
        Scanner scanner = new Scanner(file); // create a scanner object to read the file
        while (scanner.hasNextLine()) { // loop through each line of the file
            String record = scanner.nextLine();

            if (enterupdname.getText().toString().isEmpty()){
                updmessage.setText("Please enter name to update");
            }else if (record.contains(drv.getFname())) { // check if the line contains the search term
                String[] recordArray=record.split(","); //split the record from the ','
                displayrecord.setText(record);

                drv.setFname(recordArray[0]);
                drv.setLname(recordArray[1]);
                drv.setAge(Integer.parseInt(recordArray[2]));
                drv.setTeam(recordArray[3]);
                drv.setCar(recordArray[4]);
                drv.setPoints(Integer.parseInt(recordArray[5]));

                updfname.setText(drv.getFname());
                updlname.setText(drv.getLname());
                updage.setText(String.valueOf(drv.getAge()));
                updteam.setText(drv.getTeam());
                updcar.setText(drv.getCar());
                updpoints.setText(String.valueOf(drv.getPoints()));

                System.out.println("Details successfully split and entered into relevant text fields");
                break; // break used because if there are two persons with same name, problem will occer

            }
        }
        scanner.close(); // close the scanner
    }
    public void onSaveUdd(ActionEvent event) throws IOException {
        checkUdd();
    }
    private void checkUdd() throws IOException{
        if(updfname.getText().toString().isEmpty() || updlname.getText().toString().isEmpty() ||updage.getText().toString().isEmpty() || updteam.getText().toString().isEmpty() || updcar.getText().toString().isEmpty() || updpoints.getText().toString().isEmpty()){
            updmessage.setText("Fill the lines correctly.");

        }else {
            Driver drv=new Driver();
            /////////////////////////////////////////////remove details/////////////////////////////////////////////

            try{
                //age must be 21<=age<=45
                drv.setAge(Integer.parseInt(updage.getText()));
                if (drv.getAge()<21 || 45<drv.getAge()){
                    updmessage.setText("Driver age must be\nfrom 21 to 45.");
                    return;
                }

                //points must >=0
                drv.setPoints(Integer.parseInt(updpoints.getText()));
                if (drv.getPoints()<0){
                    updmessage.setText("Points must be\n greater than or equals\nto 0");
                    return;
                }

                drv.setRecord(displayrecord.getText());
                String dFile ="src/main/resources/com/example/rallycrosschampionship/details.txt";
                File myFile = new File(dFile);

                //check the given file is exist or not
                if (!myFile.isFile()) {
                    updmessage.setText("File does not exist.");
                    return;
                }

                //create a new file and will then be given the old name
                File tempFile = new File(myFile.getAbsolutePath() + ".tmp");
                BufferedReader br = new BufferedReader(new FileReader(dFile));
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                String line = null;

                //Read from the old file and write to the new one unless the content matches the data that has to be erased.
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals(drv.getRecord())) {
                        pw.println(line);
                        pw.flush();
                    }
                }
                pw.close();
                br.close();

                //Delete the original file
                if (!myFile.delete()) {
                    updmessage.setText("Could not delete file.");
                    return;
                }

                //Rename the new file to old file name
                if (!tempFile.renameTo(myFile)){
                    updmessage.setText("Could not delete file.");
                }

            } catch (NumberFormatException e) {
                updmessage.setText("Age & Current Points \nshould be Integer");
            }

            /////////////////////////////////////////////add details/////////////////////////////////////////////

            try {
                drv.setFname(updfname.getText());
                drv.setLname(updlname.getText());
                drv.setAge(Integer.parseInt(updage.getText()));
                drv.setTeam(updteam.getText());
                drv.setCar(updcar.getText());
                drv.setPoints(Integer.parseInt(updpoints.getText()));

                //Saving data into file
                FileWriter Writer = new FileWriter("src/main/resources/com/example/rallycrosschampionship/details.txt", true);
                Writer.write(drv.getFname() + "," + drv.getLname() + "," + drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() + "," + drv.getPoints() + "\n");
                Writer.close();
                updmessage.setText("Successfully updated.");
            } catch (NumberFormatException f) {
                updmessage.setText("Age & Current Points \nshould be Integer");
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