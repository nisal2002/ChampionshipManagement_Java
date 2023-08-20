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

public class DddController {
    @FXML
    private TextField delname;
    @FXML
    private Label delmessage;
    @FXML
    private Label delrecord;

    public void onDddLoad(ActionEvent event) throws IOException {
        checkLoad();
    }
    private void checkLoad() throws IOException {
        String filename = "src/main/resources/com/example/rallycrosschampionship/details.txt"; // name of the text file containing the records

        Driver drv=new Driver();
        drv.setFname(delname.getText());

        File file = new File(filename); // create a file object
        Scanner scanner = new Scanner(file); // create a scanner object to read the file
        while (scanner.hasNextLine()) { // loop through each line of the file
            String record = scanner.nextLine();

            if (delname.getText().toString().isEmpty()){
                delrecord.setText("Please enter name given field");
            }else if (record.contains(drv.getFname())) { // check if the line contains the search term
                delrecord.setText(record);
            }
        }
        scanner.close(); // close the scanner
    }
    public void onDelete(ActionEvent event) throws IOException {
        checkDelete();
    }
    private void checkDelete() throws IOException{
        if (delname.getText().toString().isEmpty()){
            delmessage.setText("Enter driver's name \nto delete.");
        }
        else{
            try {
                Driver drv=new Driver();
                drv.setRecord(delrecord.getText());

                String dFile = "src/main/resources/com/example/rallycrosschampionship/details.txt";
                File myFile = new File(dFile);

                //check the given file is exist or not
                if (!myFile.isFile()) {
                    delmessage.setText("File does not exist.");
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
                delrecord.setText("Driver deleted successfully.");

                //Delete the original file
                if (!myFile.delete()) {
                    delmessage.setText("Could not delete file.");
                    return;
                }

                //Rename the new file to old file name
                if (!tempFile.renameTo(myFile)){
                    delmessage.setText("Could not delete file.");
                }

            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
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