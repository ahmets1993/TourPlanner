package com.example.tourplanner.View;

import com.example.tourplanner.App;
import com.example.tourplanner.ViewModel.DeletePanelViewModel;
import com.example.tourplanner.ViewModel.TourDTO;
import com.example.tourplanner.ViewModel.UpdatePanelViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeletePanelController implements Initializable {


    @FXML
    private ChoiceBox<String> choiseBox;
    @FXML
    private TableView<TourDTO> tableView;
    @FXML
    private TableColumn<TourDTO, Integer> tour_id;
    @FXML
    private TableColumn<TourDTO, String> country;
    @FXML
    private TableColumn<TourDTO, String> start_point;
    @FXML
    private TableColumn<TourDTO, String> end_point;
    @FXML
    private TableColumn<TourDTO, String> duration;
    @FXML
    private TableColumn<TourDTO, String> distance;
    @FXML
    private TableColumn<TourDTO, String> price;
    @FXML
    private TableColumn<TourDTO, String> date;
    @FXML
    private TableColumn<TourDTO, String> time;
    @FXML
    private TextField textFieldTourID;
    @FXML
    private Button deleteButton;
    @FXML
    void tableView(ActionEvent event) {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdatePanelViewModel updatePanelViewModel = new UpdatePanelViewModel();
        ObservableList<String> list = null;
        try {
            list = FXCollections.observableArrayList(updatePanelViewModel.getTourNames());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        choiseBox.setItems(list);
        ChoiseBoxWorker();
    }

    @FXML
    void onDeleteButtonClicked(ActionEvent event) throws IOException {
        final String[] value = {null};
        String tourID = textFieldTourID.getText();
        int ID=Integer.parseInt(tourID);
        choiseBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                value[0] = newValue;
            }
        });
        System.out.println("Tour Name: "+ value[0]);
        System.out.println(tourID);
        DeletePanelViewModel deletePanelViewModel = new DeletePanelViewModel();
        deletePanelViewModel.deleteTourByID(ID);

        //This part should change
        Stage stage = (Stage) deleteButton.getScene().getWindow();
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

    public void ChoiseBoxWorker() {
        choiseBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                updateTableView(newValue);
            }
        });
    }
    public void updateTableView(String selectedTour){
        tour_id.setCellValueFactory(new PropertyValueFactory<>("tour_id"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        start_point.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        end_point.setCellValueFactory(new PropertyValueFactory<>("endPoint"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        UpdatePanelViewModel updatePanelViewModel = new UpdatePanelViewModel();
        ObservableList<TourDTO> tourList = FXCollections.observableArrayList();
        try {
            tourList = updatePanelViewModel.getToursByName(selectedTour);
            System.out.println("\nTurlar geliyor");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableView.setItems(tourList);
    }
}
