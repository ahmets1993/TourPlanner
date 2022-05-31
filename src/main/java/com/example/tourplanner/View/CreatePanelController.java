package com.example.tourplanner.View;

import com.example.tourplanner.App;
import com.example.tourplanner.ViewModel.CreatePanelViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;


public class CreatePanelController implements Initializable {

    @FXML
    private TextField textFieldTourName;
    @FXML
    private TextField textFieldCountry;
    @FXML
    private TextField textFieldStartPoint;
    @FXML
    private TextField textFieldEndPoint;
    @FXML
    private TextField textFieldDate;
    @FXML
    private TextField textFieldTime;
    @FXML
    private Label duration_;
    @FXML
    private Label price_;
    @FXML
    private Label distance_;
    @FXML
    private TextField textFieldTourDescription;
    @FXML
    private Button createButton;
    @FXML
    private Button checkButton;
    @FXML
    private ImageView imageView1;
    @FXML
    public ImageView imageView2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImage("Template");
    }


    @FXML
    protected void onCreateButtonClick() throws Exception {

        String tourName = textFieldTourName.getText();
        String Country = textFieldCountry.getText();
        String StartPoint = textFieldStartPoint.getText();
        String EndPoint = textFieldEndPoint.getText();
        String Date = textFieldDate.getText();
        String Time = textFieldTime.getText();
        String Duration = duration_.getText();
        String Price = price_.getText();
        String distance = distance_.getText();
        String description = textFieldTourDescription.getText();
        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        boolean opt = createPanelViewModel.insertTour(Country, StartPoint, EndPoint, Duration, Price, Date, Time,tourName,distance,description);

        //This part should change
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
            }
        });
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("tourPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 870);
        stage = new Stage();
        stage.setTitle("TOUR PLANNER");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        }

    @FXML
    public void onCheckButtonClick() throws Exception {
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String tourName = textFieldTourName.getText();
                String StartPoint = textFieldStartPoint.getText();
                String EndPoint = textFieldEndPoint.getText();
                try {
                    sendRequestAsync(StartPoint,EndPoint,tourName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
                try {
                    Float distance = createPanelViewModel.getDistance(StartPoint,EndPoint);
                    int distanceInt =  Math.round(distance); // 3
                    setLabels(distanceInt);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }



    public void sendRequestAsync(String StartPoint,String EndPoint, String imageName) throws IOException, InterruptedException {
        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        URI resourceUrl = URI.create("http://www.mapquestapi.com/staticmap/v5/map?start="+StartPoint+"&end="+EndPoint+"&size=@2x&key=1VZGQLYYGNeRDrGnYXRQTTNPKU8lG1TX");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl).GET().build();
        CompletableFuture<HttpResponse<byte[]>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
        System.out.println(future);
        future.thenApply(HttpResponse::body).thenAccept(res ->
        {
            try {
                Files.write(Paths.get("src/main/resources/Images/Maps/"+imageName+".jpg"), res);
                System.out.println("Gegebene imagename = "+imageName);
                Platform.setImplicitExit(false);
                setImage(imageName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        future.join();
    }


    public void setImage(String imageName){
        System.out.println(imageName);
        File file = new File("C:/Users/ahmet/IdeaProjects/TourPlanner/src/main/resources/Images/Maps/"+imageName+".jpg");
        Image image = new Image(file.toURI().toString());
        System.out.println(image.getUrl());
        imageView2.setImage(image);
    }

    private void setLabels(Integer distance) {
        distance_.setText(String.valueOf(distance)+" km");
        Integer estimatedTime = distance/100;
        if(estimatedTime < 1){estimatedTime = 1;}
        duration_.setText(String.valueOf(estimatedTime)+" day");
        Integer price = estimatedTime*630;
        price_.setText(String.valueOf(price)+ " $");
    }
}
