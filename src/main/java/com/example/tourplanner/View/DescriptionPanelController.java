package com.example.tourplanner.View;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class DescriptionPanelController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;
    @FXML
    private Label titleTour;
    @FXML
    private TextArea descriptionTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transAnimation(0.5,pane2,1200);
        transAnimation(0.5,pane3,1200);
    }

    public void transAnimation(double duration, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    int show = 0;
    @FXML
    public void onNextButtonClicked() throws Exception {
        if(show == 0){
            System.out.println("Before Click Show:"+show);
            transAnimation(0.5,pane2,-1200);
            show++;
            System.out.println("After Click Show:"+show);
        }else if(show == 1){
            System.out.println("Before Click Show:"+show);
            transAnimation(0.5,pane3,-1200);
            show++;
            System.out.println("After Click Show:"+show);
        }
    }

    @FXML
    public void onBackButtonClicked() {
        if(show == 1){
            System.out.println("Before Click Show:"+show);
            transAnimation(0.5,pane2,1200);
            show--;
            System.out.println("After Click Show:"+show);
        }else if(show == 2){
            System.out.println("Before Click Show:"+show);
            transAnimation(0.5,pane3,1200);
            show--;
            System.out.println("After Click Show:"+show);
        }
    }


    public void setTitle_Description(String title, String description){
        titleTour.setText(title);
        descriptionTextArea.setText(description);
    }



}


