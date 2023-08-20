package com.example.rallycrosschampionship;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.net.URL;

public class SrrController implements Initializable {
    @FXML
    ComboBox<String> location;
    @FXML
    DatePicker date;
    @FXML
    Label srrmessage;
    @FXML
    TableView srrtable;
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

    final ObservableList<String> locations = FXCollections.observableArrayList("Nyirad", "Holjes", "Montalegre", "Barcelona", "Riga", "Norway");

    public void onGenerate(ActionEvent event) throws IOException {
        checkGenerate();
    }
    private void checkGenerate() throws IOException {

        if(date.getValue()==null || location.getValue()==null){
            srrmessage.setText("Please select Date & Location");

        }else{

            String dFile="src/main/resources/com/example/rallycrosschampionship/details.txt";
            String sFile="src/main/resources/com/example/rallycrosschampionship/sort.txt";
            String rFile="src/main/resources/com/example/rallycrosschampionship/race.txt";

            //user cann't input same date before entered
            Scanner sc=new Scanner(new File(rFile));
            while (sc.hasNextLine()){
                String dt= String.valueOf(date.getValue());
                String record= sc.nextLine();
                if (record.contains(dt)){
                    srrmessage.setText("Date already used.\nPick another one.");
                    sc.close();
                    return;
                }else{
                    srrmessage.setText("");
                }
            }

            //adding 10 points to first driver
            try {
                //write all records in detail.txt into arrayList
                File file= new File(dFile);
                ArrayList<String> arrayList = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line=br.readLine())!=null){
                    arrayList.add(line);
                }

                //randomly select line
                Random rand=new Random();
                int index = rand.nextInt(arrayList.size());
                String removeLine = arrayList.get(index);

                //split the randomly select line and adding +10 to points
                String[] fields = removeLine.split(",");
                Driver drv= new Driver();
                drv.setFname(fields[0]);
                drv.setLname(fields[1]);
                drv.setAge(Integer.parseInt(fields[2]));
                drv.setTeam(fields[3]);
                drv.setCar(fields[4]);
                drv.setPoints(Integer.parseInt(fields[5])+10);

                //remove selected line
                arrayList.remove(index);


                //rewrite all the lines into details.txt without randomly select line
                FileWriter writer = new FileWriter(file);
                for (String l: arrayList){
                    writer.write(l+"\n");
                }
                writer.close();

                //write updated line into sort.txt
                FileWriter newWriter = new FileWriter(sFile,true);
                newWriter.write(drv.getFname() + "," + drv.getLname() + ","+ drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() + "," + drv.getPoints() + "\n");
                newWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }


            //adding 7 points to second driver
            try {
                //write all records in detail.txt into arrayList
                File file= new File(dFile);
                ArrayList<String> arrayList = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line=br.readLine())!=null){
                    arrayList.add(line);
                }

                //randomly select line
                Random rand=new Random();
                int index = rand.nextInt(arrayList.size());
                String removeLine = arrayList.get(index);

                //split the randomly select line and adding +7 to points
                String[] fields = removeLine.split(",");
                Driver drv= new Driver();
                drv.setFname(fields[0]);
                drv.setLname(fields[1]);
                drv.setAge(Integer.parseInt(fields[2]));
                drv.setTeam(fields[3]);
                drv.setCar(fields[4]);
                drv.setPoints(Integer.parseInt(fields[5])+7);

                //remove selected line
                arrayList.remove(index);


                //rewrite all the lines into details.txt without randomly select line
                FileWriter writer = new FileWriter(file);
                for (String l: arrayList){
                    writer.write(l+"\n");
                }
                writer.close();

                //write updated line into sort.txt
                FileWriter newWriter = new FileWriter(sFile,true);
                newWriter.write(drv.getFname() + "," + drv.getLname() + ","+ drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() + "," + drv.getPoints() + "\n");
                newWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }


            //adding 5 points to third driver
            try {
                //write all records in detail.txt into arrayList
                File file= new File(dFile);
                ArrayList<String> arrayList = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line=br.readLine())!=null){
                    arrayList.add(line);
                }

                //randomly select line
                Random rand=new Random();
                int index = rand.nextInt(arrayList.size());
                String removeLine = arrayList.get(index);

                //split the randomly select line and adding +5 to points
                String[] fields = removeLine.split(",");
                Driver drv= new Driver();
                drv.setFname(fields[0]);
                drv.setLname(fields[1]);
                drv.setAge(Integer.parseInt(fields[2]));
                drv.setTeam(fields[3]);
                drv.setCar(fields[4]);
                drv.setPoints(Integer.parseInt(fields[5])+5);

                //remove selected line
                arrayList.remove(index);


                //rewrite all the lines into details.txt without randomly select line
                FileWriter writer = new FileWriter(file);
                for (String l: arrayList){
                    writer.write(l+"\n");
                }
                writer.close();

                //write updated line into sort.txt
                FileWriter newWriter = new FileWriter(sFile,true);
                newWriter.write(drv.getFname() + "," + drv.getLname() + ","+ drv.getAge() + "," + drv.getTeam() + "," + drv.getCar() + "," + drv.getPoints() + "\n");
                newWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }


            //adding other drivers
            try {
                //write all records in detail.txt into arrayList
                File file= new File(dFile);

                while (file.length()>0){
                    ArrayList<String> arrayList = new ArrayList<>();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line=br.readLine())!=null){
                        arrayList.add(line);
                    }

                    //randomly select line from arrayList
                    Random rand=new Random();
                    int index = rand.nextInt(arrayList.size());
                    String removeLine = arrayList.get(index);

                    //remove selected line
                    arrayList.remove(index);

                    //rewrite all the lines into details.txt without randomly select line
                    FileWriter writer = new FileWriter(file);
                    for (String l: arrayList){
                        writer.write(l+"\n");
                    }
                    writer.close();

                    //write updated line into sort.txt
                    FileWriter newWriter = new FileWriter(sFile,true);
                    newWriter.write(removeLine + "\n");
                    newWriter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }



            try{
                //write all details sort.txt -> details.txt
                BufferedReader br=new BufferedReader(new FileReader(sFile));
                FileWriter fw= new FileWriter(dFile);

                String record;
                while ((record=br.readLine())!=null){
                    fw.write(record+"\n");
                }

                br.close();
                fw.close();

                //this is the code for clear sort.txt
                FileWriter fw1= new FileWriter(sFile);
                fw1.write("");
                fw1.close();
            }catch (Exception e) {
                System.out.println(e);
            }


            //write records into table
            try{
                File file = new File(dFile);
                Scanner reader = new Scanner(file);
                final ObservableList<Driver> data= FXCollections.observableArrayList();
                int i=1;
                while (reader.hasNextLine()) {
                    Driver drv = new Driver();
                    drv.setRecord(reader.nextLine());
                    String[] recordArray = drv.getRecord().split(",");

                    drv.setPosition(i);
                    drv.setFname(recordArray[0].toUpperCase());
                    drv.setLname(recordArray[1].toUpperCase());
                    drv.setAge(Integer.parseInt(recordArray[2]));
                    drv.setTeam(recordArray[3].toUpperCase());
                    drv.setCar(recordArray[4].toUpperCase());
                    drv.setPoints(Integer.parseInt(recordArray[5]));

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
                    srrtable.setItems(data);

                    i++;
                }
                srrmessage.setText("Successfully generated");
            }catch (Exception e) {
                System.out.println(e);
            }


            //getting date and location
            try{
                Racer race =new Racer();
                race.setDate(date.getValue());
                race.setLocation(location.getValue());

                //write those into race.txt
                FileWriter fw2 = new FileWriter(rFile,true);
                fw2.write(race.getDate()+","+race.getLocation()+"\n");
                fw2.close();
            }catch (Exception e) {
                System.out.println(e);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        location.setItems(locations);
    }
}
