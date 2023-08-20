package com.example.rallycrosschampionship;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;


public class HelloController {
    public void onAdd(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("add-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onDdd(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("ddd-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onUdd(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("udd-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onVCT(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("vct-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onSRR(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("srr-view.fxml"));
        Scene homepageScene = new Scene(homePage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();

        addStage.setScene(homepageScene);
        addStage.show();
    }
    public void onVRL(ActionEvent event) throws IOException {
        Parent homePage=FXMLLoader.load(getClass().getResource("vrl-view.fxml"));
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